package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Conexao;

public class User_DAO {

    private Connection con;

    public int getUserId(String username) {

        String sql = "SELECT userID from usuario WHERE username = ?";
        int userId = -1;

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            this.con.close();


            if (rs.next())
                userId = rs.getInt("userID");

            rs.close();

            return userId;

        } catch (SQLException e) {

            //System.out.println("Get User Error -> " + e.getMessage());

        }
        return userId;

    }


    public ResultSet getUser(String username, String passwrd) {

        String sql = "SELECT userID, username, email, passwrd from usuario WHERE username = ? AND passwrd = ?";

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, passwrd);

            ResultSet rs = ps.executeQuery();

            this.con.close();

            return rs;

        } catch (SQLException e) {

            //System.out.println("Get User Error -> " + e.getMessage());

        }
        return null;
    }

    public void addUser(String username, String email, String passwrd) {

        try {

            this.con = Conexao.getConection();
            String sql = "INSERT INTO usuario (username, email, passwrd) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, passwrd);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Add User Error -> " + e.getMessage());

        }

    }

    public void removeUser(int userID) {

        try {

            this.con = Conexao.getConection();
            String sql = "DELETE FROM following WHERE follower_UID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userID);

            ps.executeUpdate();

            sql = "DELETE FROM following WHERE following_UID = ?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, userID);

            ps.executeUpdate();

            sql = "DELETE FROM sharing WHERE userID = ?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, userID);

            ps.executeUpdate();

            sql = "DELETE FROM likes WHERE userID = ?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, userID);

            ps.executeUpdate();

            sql = "DELETE FROM post WHERE userID = ?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, userID);

            ps.executeUpdate();

            sql = "DELETE FROM usuario WHERE userID = ?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, userID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Remove User Error -> " + e.getMessage());

        }
    }

    public void alterUser(int userID, String email, String passwrd) {

        try {

            this.con = Conexao.getConection();
            String sql = "UPDATE usuario SET email = ?, passwrd = ? WHERE userID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ps.setString(2, passwrd);

            ps.setInt(3, userID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Alter User Error -> " + e.getMessage());

        }

    }

}