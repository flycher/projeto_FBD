package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/Qtwitter";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "winston1984";

    public static Connection getConection() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //System.out.println("Connection Succesfull!");

        } catch (SQLException e) {

            //System.out.println("Connection Error -> " + e.getMessage());

        }

        return conn;
    }

}