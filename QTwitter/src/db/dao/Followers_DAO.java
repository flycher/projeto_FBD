package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Conexao;

public class Followers_DAO {

    private Connection con;

    public ResultSet getFollowing(int follower_uid) {

        String sql = "SELECT username from usuario u, following f WHERE f.follower_uid = ? AND u.userID = following_UID ";

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, follower_uid);

            ResultSet rs = ps.executeQuery();

            this.con.close();


            return rs;

        } catch (SQLException e) {

            //System.out.println("Get Following Error -> " + e.getMessage());

        }

        return null;

    }

    public ResultSet getFollowers(int following_uid) {

        String sql = "SELECT username from usuario, following WHERE following_uid = ? AND userID = follower_uid";

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, following_uid);


            ResultSet rs = ps.executeQuery();

            this.con.close();

            return rs;

        } catch (SQLException e) {

            //System.out.println("Get Followers Error -> " + e.getMessage());

        }

        return null;

    }

    public void startFollowing(int userID, int following_uid) {

        String sql = "INSERT INTO following (follower_uid, following_uid) VALUES (?, ?)";

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setInt(2, following_uid);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Start Following Error -> " + e.getMessage());
        	System.out.println("You already follow this user!");

        }

    }

    public void stopFollowing(int userID, int following_uid) {

        String sql = "DELETE FROM following WHERE follower_uid = ? AND following_uid = ?";

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setInt(2, following_uid);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Stop Following Error -> " + e.getMessage());
        	System.out.println("You don't follow this user!");

        }

    }


}