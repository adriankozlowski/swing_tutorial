/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sda.standalone.swingapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pl.sda.standalone.jdbc.DBConnectionManager;

/**
 * ak.quider@gmail.com adrian.kozlowski@outlook.com
 *
 * @author Adrian
 */
public class JdbcTutorial {

    private static String url = "jdbc:mysql://192.168.99.100:3306/books_db";
    private static String userName = "root";
    private static String password = "password";

    public static void main(String... args) throws ClassNotFoundException, SQLException {
        DBConnectionManager dbConnectionManager = new DBConnectionManager(url, userName, password);
        Connection connection = dbConnectionManager.getConnection();
        java.sql.PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select id, first_name, last_name from users");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                System.out.println(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
