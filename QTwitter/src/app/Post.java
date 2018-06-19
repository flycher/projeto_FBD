package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.Date;

import db.dao.Post_DAO;

public class Post {

    static Post_DAO post = new Post_DAO();

    public static void addPost(String message, User usuario) {

        post.addPost(message, usuario.getUserId());

    }

    public static void removePost(int postID) {

        post.removePost(postID);

    }

    public static void alterPost(int postID, String message) {

        post.alterPost(postID, message);

    }

    public static void getPosts(Scanner scan, User usuario) {

        ResultSet rs = post.getPosts(usuario.getUserId());

        try {
            if (rs.next()) {

                int option, row = 0, total_rows = -1;

                boolean running = true;

                Date date = null;

                Timestamp timestamp = null;

                String message = null;

                String op = null;

                int postID = -1;

                while (running) {

                    try {

                        if (rs.next()) {

                            try {

                                message = rs.getString("message");
                                op = rs.getString("username");
                                postID = rs.getInt("postID");
                                timestamp = rs.getTimestamp("horario");
                                total_rows = rs.getInt("total_rows");

                            } catch (SQLException e) {

                                //System.out.println("Error getting data from message on post -> " + e.getMessage());

                            }

                            date = new java.util.Date(timestamp.getTime());

                        }

                    } catch (SQLException e) {

                        //System.out.println("Error getting post -> " + e.getMessage());

                    }

                    System.out.println(message);
                    System.out.println("Post by: " + op + " on " + date);
                    System.out.println("Shares: " + Shares.getShares(postID));
                    System.out.println("Likes: " + Likes.getLikes(postID));
                    System.out.println("[1] - Go top. | [2] - Go bottom. | [3] - Previows Post | [4] - Next Post | " +
                        "[5] - Update Post | [6] - Delete Post | [7] - Back");
                    System.out.print("> ");

                    try {
                        option = scan.nextInt();
                    } catch (Exception e) {
                        scan.nextLine();
                        continue;
                    }

                    switch (option) {

                        case 1:
                            try {

                            	rs.absolute(0);
			                    row = 0;

                            } catch (SQLException e) {

                                //System.out.println("Error getting first row on post -> " + e.getMessage());

                            }
                            break;

                        case 2:
                            try {

                            	rs.absolute(total_rows - 1);
			                    row = total_rows - 1;

                            } catch (SQLException e) {

                                //System.out.println("Error getting last row on post -> " + e.getMessage());

                            }
                            break;

                        case 3:
                            try {

                                if (rs.previous())
                                    if (row > 0)
                                        rs.absolute(--row);

                            } catch (SQLException e) {

                                //System.out.println("Error getting previous row on post -> " + e.getMessage());

                            }
                            break;

                        case 4:
                            try {

                                if (rs.next()) {
                                    if (row < total_rows)
                                        rs.absolute(++row);

                                }
                            } catch (SQLException e) {

                                //System.out.println("Error getting next row on post -> " + e.getMessage());

                            }
                            break;

                        case 5:
                            System.out.println("Limit your message to 280 characters.");
                            System.out.print("> ");
                            scan.nextLine();
                            message = scan.nextLine();
                            post.alterPost(postID, message);
                            try {

                                rs.absolute(row);

                            } catch (SQLException e) {

                                //System.out.println("Error staying in row on post -> " + e.getMessage());

                            }
                            break;

                        case 6:
                            System.out.println("Are you sure you want to remove this post?");
                            System.out.println("[1]Yes [2]No\nThis cant be undone!");
                            int del = scan.nextInt();
                            if (del == 1) {
                                Post.removePost(postID);
                                System.out.println("Post " + postID + " deleted.");
                            }
                            break;

                        case 7:
                            running = false;
                            break;

                        default:
                            break;

                    }
                }

            } else
                System.out.println("You dont have any posts yet.");

        } catch (SQLException e) {

            //System.out.println("Error getting posts -> " + e.getMessage());
            
        }
    }

}