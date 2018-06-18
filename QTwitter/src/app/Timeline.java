package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import db.dao.Timeline_DAO;

public class Timeline {

    static Timeline_DAO timeline = new Timeline_DAO();

    public static void timeline(Scanner scan, User usuario) {

        int uid = usuario.getUserId();

        ResultSet rs = timeline.timeline(uid);
        
        try {
			if(rs.next()) {

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

			                    System.out.println("Error getting data from message on timeline -> " + e.getMessage());

			                }

			                date = new java.util.Date(timestamp.getTime());

			            }

			        } catch (SQLException e) {

			            System.out.println("Error getting post -> " + e.getMessage());

			        }

			        System.out.println(message);
			        System.out.println("Post by: " + op + " on " + date);
			        System.out.println("Shares: " + Shares.getShares(postID));
			        System.out.println("Likes: " + Likes.getLikes(postID));
			        System.out.println("[1] - Go top. | [2] - Go bottom. | [3] - Previous Post | [4] - Next Post | " +
			            "[5] - Share | [6] - Stop Sharing | [7] - Like | [8] - Remove like | [9] - Back");

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

			                    //System.out.println("Error getting first row on timeline -> " + e.getMessage());

			                }
			                break;

			            case 2:
			                try {

			                    rs.absolute(total_rows - 1);
			                    row = total_rows - 1;

			                } catch (SQLException e) {

			                    //System.out.println("Error getting last row on timeline -> " + e.getMessage());

			                }
			                break;

			            case 3:
			                try {

			                    if (rs.previous())
			                        if (row > 0)
			                            rs.absolute(--row);

			                } catch (SQLException e) {

			                    //System.out.println("Error getting previous row on timeline -> " + e.getMessage());

			                }
			                break;

			            case 4:
			                try {

			                    if (rs.next()) {
			                        if (row < total_rows)
			                            rs.absolute(++row);

			                    }
			                } catch (SQLException e) {

			                    //System.out.println("Error getting next row on timeline -> " + e.getMessage());

			                }
			                break;

			            case 5:
			                Shares.addShare(uid, postID);
			                try {

			                    rs.absolute(row);

			                } catch (SQLException e) {

			                    //System.out.println("Error staying in row on timeline -> " + e.getMessage());

			                }
			                break;

			            case 6:
			                Shares.removeShare(uid, postID);
			                try {

			                    rs.absolute(row);

			                } catch (SQLException e) {

			                    //System.out.println("Error staying in row on timeline -> " + e.getMessage());


			                }
			                break;

			            case 7:
			                Likes.addLike(uid, postID);
			                try {

			                    rs.absolute(row);

			                } catch (SQLException e) {

			                    //System.out.println("Error staying in row on timeline -> " + e.getMessage());


			                }
			                break;

			            case 8:
			                Likes.removeLike(uid, postID);
			                try {

			                    rs.absolute(row);

			                } catch (SQLException e) {

			                    //System.out.println("Error staying in row on timeline -> " + e.getMessage());


			                }
			                break;

			            case 9:
			                running = false;
			                break;

			            default:
			                break;

			        }
			    }

			}
			else 
				System.out.println("You dont have any activity yet.");
			
		} catch (SQLException e) {
			
			//System.out.println("Error getting timeline -> " + e.getMessage());
		}
    } 

}