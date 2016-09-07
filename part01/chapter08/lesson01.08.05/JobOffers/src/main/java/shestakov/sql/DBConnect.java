package shestakov.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * The type Db connect.
 */
public class DBConnect {
    private static final Logger Log = LoggerFactory.getLogger(DBConnect.class);
    private Connection conn;
    private String offersUrl;
    private boolean firstRun;

    /**
     * Is first run boolean.
     *
     * @return the boolean
     */
    public boolean isFirstRun() {
        return firstRun;
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
     * Open connection.
     */
    public void openConnection() {
        Properties prop = new Properties();
        try (
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/part01/chapter08/lesson01.08.05/JobOffers/jobOffers.cfg");
        ) {
            prop.load(fis);
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
        String url = prop.getProperty("url").toString();
        String username = prop.getProperty("username").toString();
        String password = prop.getProperty("password").toString();
        this.offersUrl = prop.getProperty("offersUrl").toString();
        try {
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
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
     * Sets start time.
     */
    public void setStartTime() {
        if (firstRun()) {
            setRunTime();
        } else {
            updateRunTime();
        }
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
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return this.firstRun;
    }

    /**
     * Gets run time.
     *
     * @return the run time
     */
    public long getRunTime() {
        long result = 0L;
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT p.runTime FROM properties AS p ORDER BY p.id LIMIT 1");
        ) {
            ResultSet rs = st.executeQuery();
            rs.next();
            result = rs.getTimestamp("runTime").getTime();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Sets run time.
     */
    public void setRunTime() {
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
     * Update run time.
     */
    public void updateRunTime() {
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
     * Print all job offers.
     */
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

}
