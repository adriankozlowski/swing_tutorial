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
import java.util.ArrayList;
import java.util.List;
import pl.sda.standalone.swingapp.User;

/**
 *
 * @author RENT
 */
public class JdbcTutorial {
    
    private static String url = "jdbc:mysql://localhost/books_db";
    private static String userName = "root";
    private static String password = "";
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        
        
    }
    
    public List<User> makeQuery(Connection c, String q, String[] params){
        PreparedStatement ps = null;
        List<User> users = new ArrayList<User>();
        try{
            ps = c.prepareStatement(q);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                String firstName = resultSet.getString(params[0]);
                System.out.println(firstName);
                String lastName = resultSet.getString(params[1]);
                System.out.println(lastName);
                users.add(new User(firstName, lastName));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return users;
    }
    
    public Connection connect(){
        DBConnectionManager dbConnectionManager = null;
        try{
            dbConnectionManager =
                new DBConnectionManager(url, userName, password);
        }catch(ClassNotFoundException cnfe){
            
        }catch(SQLException sqle){
            
        }
        
        return dbConnectionManager.getConnection();
    }
}
