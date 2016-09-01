package shestakov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;

public class SQLStorage {
    private static final Logger Log = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
        String username = "postgres";
        String password = "1";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);

            /*Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");*/

            /*PreparedStatement st = conn.prepareStatement("SELECT * FROM users as u WHERE u.id IN (?, ?, ?)");
            st.setInt(1, 3);
            st.setInt(2, 7);
            st.setInt(3, 8);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                System.out.println(String.format("%s %s", rs.getString("login"), rs.getTimestamp("create_date")));
            }
            rs.close();
            st.close();*/

            /*PreparedStatement st = conn.prepareStatement("INSERT INTO users(login, password, create_date) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "new java user test 2");
            st.setString(2, "password");
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
            }*/

            /*PreparedStatement st = conn.prepareStatement("UPDATE users SET login=? WHERE id=?");
            st.setString(1, "new java user test 3");
            st.setInt(2, 9);
            st.executeUpdate();*/

            /*PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE id=?");
            st.setInt(1, 9);
            st.executeUpdate();*/


        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
    }
}
