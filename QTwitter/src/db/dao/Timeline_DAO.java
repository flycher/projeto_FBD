package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Conexao;

public class Timeline_DAO {

    private Connection con;

    public ResultSet timeline(int userID) {

        String sql = "SELECT username, postID, message, horario, count(*) over (partition by 1) total_rows FROM " +
            "(" +
            "SELECT username, postID, message, horario from post p, usuario u WHERE p.userID = ? AND p.userID = u.userID" +
            " UNION " +
            "SELECT username, postID, message, horario from post p, usuario u WHERE p.userID = u.userID AND p.userID in " +
            "(SELECT following_UID FROM following WHERE follower_UID = ?)" +
            ") " +
            "as timeline " +
            "ORDER BY horario DESC";

        try {

            this.con = Conexao.getConection();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

            ps.setInt(1, userID);
            ps.setInt(2, userID);

            ResultSet rs = ps.executeQuery();

            this.con.close();

            return rs;

        } catch (SQLException e) {

            //System.out.println("Get Post Error -> " + e.getMessage());

        }

        return null;

    }

}