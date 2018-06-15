package app;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.dao.User_DAO;

public class User {

    static User_DAO userDAO = new User_DAO();

    private int userID;
    private String username;
    private String email;
    private String passwrd;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public void setUserId(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPasswrd() {
        return this.passwrd;
    }

    public int getUserId() {
        return this.userID;
    }

    public void getUser(String username, String passwrd) {

        try {

            ResultSet rs = userDAO.getUser(username, passwrd);

            if (rs.next()) {

                setUserId(rs.getInt("userID"));
                setUsername(rs.getString("username"));
                setEmail(rs.getString("email"));
                setPasswrd(rs.getString("passwrd"));
                rs.close();

            } else {

                System.out.println("Usuário nao esta da Base de Dados!");

            }

        } catch (SQLException e) {

            //System.out.println("Get User Error on User Class -> " + e.getMessage());

        }
    }

    public void addUser(String username, String email, String passwrd) {
    	
        userDAO.addUser(username, email, passwrd);
        
    }

    public void removeUser() {
    	
        userDAO.removeUser(this.userID);
        
    }

    public void alterUser(String email, String passwrd) {
    	
        userDAO.alterUser(this.userID, email, passwrd);
        
    }

}