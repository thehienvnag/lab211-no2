/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author THE HIEN
 */
public class DBConnection {

    public static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String URL = "jdbc:sqlserver://localhost:1433; databaseName=BookStoreDB";
    

    public static Connection openConnection() throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, "sa", "12345678");
    }

}
