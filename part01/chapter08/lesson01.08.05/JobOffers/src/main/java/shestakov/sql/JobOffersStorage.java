package shestakov.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.jsoup.JobOffersParsing;

import java.sql.*;

public class JobOffersStorage {
    private static final Logger Log = LoggerFactory.getLogger(JobOffersStorage.class);
    private Connection conn;
    private boolean firstRun;
    private long lastRunTime;
    private String url;
    private String username;
    private String password;
    private String offersUrl;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOffersUrl(String offersUrl) {
        this.offersUrl = offersUrl;
    }

    public String getOffersUrl() {
        return offersUrl;
    }

    public void getJobOffers() {
        openConnection();
        setLastRunTime();
        setCurrentRunTime();
        getJobOffersFromOffersUrl();
        printNewJobOffers();
        closeConnection();
    }

    public void openConnection() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public boolean isFirstRun() {
        return this.firstRun;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void setCurrentRunTime() {
        if (firstRun()) {
            insertCurrentRunTime();
        } else {
            updateCurrentRunTime();
        }
    }

    public void getJobOffersFromOffersUrl() {
        JobOffersParsing jobOffersParsing = new JobOffersParsing(this);
        jobOffersParsing.getJobOffers();
    }

    public boolean firstRun() {
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT p.runTime FROM properties AS p ORDER BY p.id LIMIT 1");
        ) {
            ResultSet rs = st.executeQuery();
            this.firstRun = !rs.next();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return this.firstRun;
    }

    public void setLastRunTime() {
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT p.runTime FROM properties AS p ORDER BY p.id LIMIT 1");
        ) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                this.lastRunTime = rs.getTimestamp("runTime").getTime();
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public long getLastRunTime() {
        return this.lastRunTime;
    }

    public void insertCurrentRunTime() {
        try (
                PreparedStatement st = this.conn.prepareStatement("INSERT INTO properties(runTime) VALUES (?)");
        ) {
            st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void updateCurrentRunTime() {
        try (
                PreparedStatement st = this.conn.prepareStatement("UPDATE properties SET runTime=? WHERE id IN(SELECT p.id FROM properties AS p ORDER BY p.id LIMIT 1)");
        ) {
            st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void addDataInDB(String offerLink, String offerText, String author, String authorLink, long offerDate) {
        try (
                PreparedStatement st1 = this.conn.prepareStatement("INSERT INTO authors(name, link) VALUES(?, ?)");
                PreparedStatement st2 = this.conn.prepareStatement("INSERT INTO offers(text, link, createDate, authorId) VALUES (?, ?, ?, (SELECT a.id FROM authors AS a WHERE a.name = ? AND a.link = ?))");
        ) {
            st1.setString(1, author);
            st1.setString(2, authorLink);
            st1.executeUpdate();
            st1.close();
            st2.setString(1, offerText);
            st2.setString(2, offerLink);
            st2.setTimestamp(3, new Timestamp(offerDate));
            st2.setString(4, author);
            st2.setString(5, authorLink);
            st2.executeUpdate();
            st2.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void printNewJobOffers() {
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT o.text offerText, o.link offerLink, o.createDate offerDate, a.name authorName, a.link authorLink FROM offers AS o JOIN authors AS a ON o.authorId = a.id AND o.createDate > ? ORDER BY o.createDate DESC");
        ) {
            st.setTimestamp(1, new Timestamp(this.lastRunTime));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String offerText = rs.getString("offerText");
                String offerLink = rs.getString("offerLink");
                Timestamp offerDate = rs.getTimestamp("offerDate");
                String authorName = rs.getString("authorName");
                String authorLink = rs.getString("authorLink");
                Log.info(String.format("date: %s, offer: %s(%s), author: %s(%s)", offerDate,offerText,offerLink,authorName,authorLink));
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void printAllJobOffers() {
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT o.text offerText, o.link offerLink, o.createDate offerDate, a.name authorName, a.link authorLink FROM offers AS o LEFT JOIN authors AS a ON o.authorId = a.id ORDER BY o.createDate DESC");
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String offerText = rs.getString("offerText");
                String offerLink = rs.getString("offerLink");
                Timestamp offerDate = rs.getTimestamp("offerDate");
                String authorName = rs.getString("authorName");
                String authorLink = rs.getString("authorLink");
                Log.info(String.format("date: %s, offer: %s(%s), author: %s(%s)", offerDate,offerText,offerLink,authorName,authorLink));
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void clearTables() {
        try (
                PreparedStatement st1 = this.conn.prepareStatement("DELETE FROM properties");
                PreparedStatement st2 = this.conn.prepareStatement("DELETE FROM offers");
                PreparedStatement st3 = this.conn.prepareStatement("DELETE FROM authors");
        ) {
            st1.execute();
            st2.execute();
            st3.execute();
            st1.close();
            st2.close();
            st3.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public int getCountOfFilterByOfferTextFromDB(String offerText) {
        int result = 0;
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT COUNT(o.id) cnt FROM offers AS o WHERE o.text = ?");
        ) {
            st.setString(1, offerText);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = Integer.parseInt(rs.getString("cnt"));
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return result;
    }

    public int getDeltaOfMaxAndMinDatesInMonth() {
        int result = 0;
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT (date_part('month', MAX(o.createDate)) - date_part('month', MIN(o.createDate)) + (date_part('year', MAX(o.createDate)) - date_part('year', MIN(o.createDate))) * 12) delta FROM offers AS o");
        ) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = Integer.parseInt(rs.getString("delta"));
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return result;
    }

}
