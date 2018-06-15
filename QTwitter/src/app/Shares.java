package app;

import db.dao.Shares_DAO;

public class Shares {
	
	static Shares_DAO shares = new Shares_DAO();

	public static void addShare(int userID, int postID) {
		
		shares.addShare(userID, postID);
		
	}
	
	public static void removeShare(int userID, int postID) {
		
		shares.removeShare(userID, postID);
		
	}
	
	public static int getShares(int postID) {
		
		return shares.getShares(postID);
		
	}
	
}
