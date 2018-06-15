package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Conexao;

public class Likes_DAO {

    private Connection con;

    public void addLike(int userID, int postID) {

        try {

            this.con = Conexao.getConection();
            String sql = "INSERT INTO likes (userID, postID) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setInt(2, postID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Add Like Error -> " + e.getMessage());

        }

    }

    public void removeLike(int userID, int postID) {

        try {

            this.con = Conexao.getConection();
            String sql = "DELETE FROM likes WHERE postID = ? and userID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postID);
            ps.setInt(2, userID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Remove Like Error -> " + e.getMessage());

        }
    }

    public int getLikes(int postID) {

        int likes = 0;

        try {

            this.con = Conexao.getConection();
            String sql = "SELECT count(*) FROM likes WHERE postID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postID);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                likes = rs.getInt("count");

            rs.close();
            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Get Like Error -> " + e.getMessage());

        }

        return likes;
    }

}