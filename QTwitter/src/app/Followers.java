package app;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.dao.Followers_DAO;
import db.dao.User_DAO;

public class Followers {

    static Followers_DAO followers = new Followers_DAO();

    static User_DAO user = new User_DAO();

    public static void startFollowing(int userID, String username) {

        int following = user.getUserId(username);

        if (following != -1)
            followers.startFollowing(userID, following);
        else
            System.out.println(username + " não existe");

    }

    public static void stopFollowing(int userID, String username) {

        int following = user.getUserId(username);

        if (following != -1)
            followers.stopFollowing(userID, following);
        else
            System.out.println(username + " não existe");

    }

    public static void getFollowers(int userID) {

        try {

            ResultSet rs = followers.getFollowers(userID);

            if (!rs.next())
                System.out.println("You dont have any followers yet.");
            else
                System.out.println(rs.getString("username"));

            while (rs.next()) {

                System.out.println(rs.getString("username"));

            }

            rs.close();

        } catch (SQLException e) {

            //System.out.println("Get Followers Error on Followers Class -> " + e.getMessage());

        }
    }

    public static void getFollowing(int userID) {

        try {

            ResultSet rs = followers.getFollowing(userID);

            if (!rs.next())
                System.out.println("You dont follow anyone yet.");
            else
                System.out.println(rs.getString("username"));

            while (rs.next()) {

                System.out.println(rs.getString("username"));

            }

            rs.close();

        } catch (SQLException e) {

            //System.out.println("Get Following Error on Foloowers Class -> " + e.getMessage());

        }
    }

}