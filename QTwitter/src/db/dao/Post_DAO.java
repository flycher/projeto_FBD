package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Conexao;

public class Post_DAO {

    private Connection con;

    public void addPost(String message, int userID) {

        try {

            this.con = Conexao.getConection();
            String sql = "INSERT INTO post (userID, message, horario) VALUES (?, ?, NOW())";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setString(2, message);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Add Post Error -> " + e.getMessage());

        }
    }

    public void removePost(int postID) {

        try {

            this.con = Conexao.getConection();
            String sql = "DELETE FROM post WHERE postID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Remove Post Error -> " + e.getMessage());

        }
    }

    public void alterPost(int postID, String message) {

        try {

            this.con = Conexao.getConection();
            String sql = "UPDATE post SET message = ? WHERE postID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, message);

            ps.setInt(2, postID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Alter Post Error -> " + e.getMessage());

        }

    }

    public ResultSet getPosts(int userID) {

        String sql = "SELECT u.username, postID, message, horario, count(*) over (partition by 1) total_rows" +
            " from post p, usuario u WHERE p.userID = ? AND p.userID = u.userID ORDER BY horario DESC";

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();

            this.con.close();

            return rs;

        } catch (SQLException e) {

            //System.out.println("Get Post Error -> " + e.getMessage());

        }

        return null;

    }

}