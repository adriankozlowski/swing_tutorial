/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sda.standalone.swingapp;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import pl.sda.standalone.jdbc.JdbcTutorial;

/**
 *
 * @author RENT
 */
public class User implements Serializable{
    private String name;
    private String surname;

    public User(@NotNull String newName, @NotNull String newSurname){
        this.name = newName;
        this.surname = newSurname;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }
    
    public void saveToDB(JdbcTutorial jdbc){
        jdbc.makeQuery(
                jdbc.connect(), 
                "insert into users (first_name, last_name) values ("+this.name+", "+this.surname+")", 
                new String[]{"first_name", "last_name"}
        );
    }
}
