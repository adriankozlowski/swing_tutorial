/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sda.standalone.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RENT
 */
public class JdbcTutorial {
    
    private static String url = "jdbc:mysql://localhost/book_db";
    private static String userName = "root";
    private static String password = "";
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        DBConnectionManager dbConnectionManager =
                new DBConnectionManager(url, userName, password);
        Connection connection = dbConnectionManager.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("select id, first_name, last_name from users");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                System.out.println(firstName);
            }
        }catch(SQLException sqle){
            
        }
    }
}
