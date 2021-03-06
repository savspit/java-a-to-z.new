package shestakov.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.jsoup.JobOffersParsing;

import java.sql.*;

/**
 * The type Job offers storage.
 */
public class JobOffersStorage {
    private static final Logger Log = LoggerFactory.getLogger(JobOffersStorage.class);
    private Connection conn;
    private boolean firstRun;
    private long lastRunTime;
    private String url;
    private String username;
    private String password;
    private String offersUrl;

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets offers url.
     *
     * @param offersUrl the offers url
     */
    public void setOffersUrl(String offersUrl) {
        this.offersUrl = offersUrl;
    }

    /**
     * Sets connection.
     *
     * @param conn the conn
     */
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gets offers url.
     *
     * @return the offers url
     */
    public String getOffersUrl() {
        return offersUrl;
    }

    /**
     * Gets job offers.
     */
    public void getJobOffers() {
        openConnection();
        setLastRunTime();
        setCurrentRunTime();
        getJobOffersFromOffersUrl();
        printNewJobOffers();
        closeConnection();
    }

    /**
     * Open connection.
     */
    public void openConnection() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Is first run boolean.
     *
     * @return the boolean
     */
    public boolean isFirstRun() {
        return this.firstRun;
    }

    /**
     * Close connection.
     */
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Sets current run time.
     */
    public void setCurrentRunTime() {
        if (firstRun()) {
            insertCurrentRunTime();
        } else {
            updateCurrentRunTime();
        }
    }

    /**
     * Gets job offers from offers url.
     */
    public void getJobOffersFromOffersUrl() {
        JobOffersParsing jobOffersParsing = new JobOffersParsing(this);
        jobOffersParsing.getJobOffers();
    }

    /**
     * First run boolean.
     *
     * @return the boolean
     */
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

    /**
     * Sets last run time.
     */
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

    /**
     * Gets last run time.
     *
     * @return the last run time
     */
    public long getLastRunTime() {
        return this.lastRunTime;
    }

    /**
     * Insert current run time.
     */
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

    /**
     * Update current run time.
     */
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

    /**
     * Add data in db.
     *
     * @param offerLink  the offer link
     * @param offerText  the offer text
     * @param author     the author
     * @param authorLink the author link
     * @param offerDate  the offer date
     */
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

    /**
     * Print new job offers.
     */
    public void printNewJobOffers() {
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT o.text offerText, o.link offerLink, o.createDate offerDate, a.name authorName, a.link authorLink FROM offers AS o JOIN authors AS a ON o.authorId = a.id AND o.createDate > ? ORDER BY o.createDate DESC");
        ) {
            st.setTimestamp(1, new Timestamp(this.lastRunTime));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Log.info(String.format("date: %s, offer: %s(%s), author: %s(%s)", rs.getTimestamp("offerDate"), rs.getString("offerText"), rs.getString("offerLink"), rs.getString("authorName"), rs.getString("authorLink")));
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Print all job offers.
     */
    public void printAllJobOffers() {
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT o.text offerText, o.link offerLink, o.createDate offerDate, a.name authorName, a.link authorLink FROM offers AS o LEFT JOIN authors AS a ON o.authorId = a.id ORDER BY o.createDate DESC");
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Log.info(String.format("date: %s, offer: %s(%s), author: %s(%s)", rs.getTimestamp("offerDate"), rs.getString("offerText"), rs.getString("offerLink"), rs.getString("authorName"), rs.getString("authorLink")));
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Clear tables.
     */
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

    /**
     * Gets count of filter by offer text from db.
     *
     * @param offerText the offer text
     * @return the count of filter by offer text from db
     */
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

    /**
     * Gets delta of max and min dates in month.
     *
     * @return the delta of max and min dates in month
     */
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

    /**
     * Gets max create date.
     *
     * @return the max create date
     */
    public Timestamp getMaxCreateDate() {
        Timestamp result = null;
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT MAX(o.createDate) maxCreateDate FROM offers AS o");
        ) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getTimestamp("maxCreateDate");
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Gets min create date.
     *
     * @return the min create date
     */
    public Timestamp getMinCreateDate() {
        Timestamp result = null;
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT MIN(o.createDate) minCreateDate FROM offers AS o");
        ) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getTimestamp("minCreateDate");
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return result;
    }
}
