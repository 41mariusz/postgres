package generic;

import java.sql.*;

public class PostgresTool {

    private static PostgresTool instance;
    private static PreparedStatement pstmt;
    private Connection connection;
    private String url = "jdbc:postgresql://manny.db.xx.com:5432/sxxssjdayn";
    private String username = "xx";
    private String password = "xxx";

    private PostgresTool() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static PostgresTool getInstance() throws SQLException {
        if (instance == null) {
            instance = new PostgresTool();
        } else if (instance.getConnection().isClosed()) {
            instance = new PostgresTool();
        }
        return instance;
    }

    public static String getValue(String table, String columnWhere, Object valueWhere, String columnWithResult) {
        String SQL_SELECT = "SELECT * FROM " + table + " WHERE " + columnWhere + " = ?";
        ResultSet rs;
        try {
            pstmt = getInstance().getConnection().prepareStatement(SQL_SELECT);

            if (valueWhere instanceof java.lang.Long) pstmt.setLong(1, (Long) valueWhere);
            else if (valueWhere instanceof java.lang.Integer) pstmt.setInt(1, (Integer) valueWhere);
            else pstmt.setString(1, (String) valueWhere);

            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getString(columnWithResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void executeQuery(String query) {
        try {
            pstmt = getInstance().getConnection().prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}