package com.raven.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class XJdbc {

    private static Connection connection;

    
    public static Connection openConnection() {
        var driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        var dburl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QLHOCVIEN1;encrypt=true;trustServerCertificate=true;";
        var username = "sa";
        var password = "1234";

        try {
            if (!XJdbc.isReady()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(dburl, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (XJdbc.isReady()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  
    public static boolean isReady() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdate(String sql, Object... values) {
        try {
            var stmt = XJdbc.getStmt(sql, values);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public static ResultSet executeQuery(String sql, Object... values) {
        try {
            var stmt = XJdbc.getStmt(sql, values);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

 
    public static <T> T getValue(String sql, Object... values) {
        try {
            var resultSet = XJdbc.executeQuery(sql, values);
            if (resultSet.next()) {
                return (T) resultSet.getObject(1);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

  
    private static PreparedStatement getStmt(String sql, Object... values) throws SQLException {
        var conn = XJdbc.openConnection();
        var stmt = sql.trim().startsWith("{") ? conn.prepareCall(sql) : conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

    private static void demo1() {
        String sql = "SELECT * FROM Drinks WHERE UnitPrice BETWEEN ? AND ?";
        var rs = XJdbc.executeQuery(sql, 1.5, 5.0);
    }

    private static void demo2() {
        String sql = "SELECT max(UnitPrice) FROM Drinks WHERE UnitPrice > ?";
        var maxPrice = XJdbc.getValue(sql, 1.5);
    }

    private static void demo3() {
        String sql = "DELETE FROM Drinks WHERE UnitPrice < ?";
        var count = XJdbc.executeUpdate(sql, 0.0);
    }
}
