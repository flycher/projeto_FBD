package app;

import db.dao.Likes_DAO;

public class Likes {

    static Likes_DAO likes = new Likes_DAO();

    public static void addLike(int userID, int postID) {

        likes.addLike(userID, postID);

    }

    public static void removeLike(int userID, int postID) {

        likes.removeLike(userID, postID);

    }

    public static int getLikes(int postID) {

        return likes.getLikes(postID);

    }

}