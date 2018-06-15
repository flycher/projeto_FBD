package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Conexao;

public class Shares_DAO {

    private Connection con;

    public void addShare(int userID, int postID) {

        try {

            this.con = Conexao.getConection();
            String sql = "INSERT INTO sharing (userID, postID) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setInt(2, postID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Add Share Error -> " + e.getMessage());

        }

    }

    public void removeShare(int userID, int postID) {

        try {

            this.con = Conexao.getConection();
            String sql = "DELETE FROM sharing WHERE postID = ? and userID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postID);
            ps.setInt(2, userID);

            ps.executeUpdate();

            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Remove Share Error -> " + e.getMessage());

        }
    }

    public int getShares(int postID) {

        int shares = 0;

        try {

            this.con = Conexao.getConection();
            String sql = "SELECT count(*) FROM sharing WHERE postID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postID);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                shares = rs.getInt("count");

            rs.close();
            ps.close();

            this.con.close();

        } catch (SQLException e) {

            //System.out.println("Add Share Error -> " + e.getMessage());

        }

        return shares;
    }

}