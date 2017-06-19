package com.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class LikeCheckin implements Like{
	UserModel user,to;
	int checkin ;
	Date date;
	
 public LikeCheckin(int ID) {
		super();
		checkin=ID;
	}	
	
 public LikeCheckin(UserModel user, int checkin) {
		super();
		this.user = user;
		this.checkin = checkin;
	}

 public LikeCheckin(UserModel user,UserModel to, int checkin) {
		super();
		this.user = user;
		this.to=to;
		this.checkin = checkin;
	}


public LikeCheckin(UserModel userModel, int int1, Date date2) {
	// TODO Auto-generated constructor stub
	super();
	this.user = userModel;
	this.checkin = int1;
	this.date=date2;
}

public LikeCheckin(UserModel userModel) {
	// TODO Auto-generated constructor stub
	this.user=userModel;
}

public boolean add_Like()
 {
	 String email = user.getEmail();
	 try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into likecheckin (`userEmail`,`checkinID`) VALUES  (?,?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,email);
            stmt.setInt(2,checkin);
			stmt.executeUpdate();
			
			Notification notification = new Notification(to, user, checkin, "Like", "liked your post");
			return notification.addNotification();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	  return false;
 }

public ArrayList<String> whoLike()
{
	ArrayList <String> usersEmails = new ArrayList<String>();
	try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Select * from likecheckin where `checkinID` = ?";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1,this.checkin);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			usersEmails.add( rs.getString("userEmail"));
		}
		return usersEmails;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

public int getNumberOfLikes()
{
	int count=0;
	try{
		Connection conn = DBConnection.getActiveConnection();
		String sql = "SELECT COUNT(*) FROM  likecheckin WHERE checkinID= ? ";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1,this.checkin);
		stmt.executeQuery();
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
	    count = rs.getInt(1);
		return count;
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
  return count;
}
public ArrayList<Like> getLikes(){
	 try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from likecheckin where userEmail= ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Like> list = new ArrayList<Like>();
			while(rs.next()){
				Like like = new LikeCheckin(new UserModel(rs.getString("userEmail")),rs.getInt("checkinID"),rs.getDate("date"));
				Checkin c=new SearchPlaceForCheckin();
				like.setTo(c.getCheckin(like.getCheckin()).getUser());
				list.add(like);
			}
			return list;
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	return null; 
}
public boolean undoLike(){
	 try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = " delete from likecheckin where userEmail  = ? and checkinID= ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getEmail());
			stmt.setInt(2,checkin);
			stmt.executeUpdate();
			 return true ;	
		}catch(SQLException e){
			e.printStackTrace();
		}
	return false;
}

public boolean removeCheckinLikes() {
	try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "delete from likecheckin where checkinID=?";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, checkin);
		stmt.executeUpdate();
		return true ;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false ;
}
public UserModel getUser() {
	return user;
}

public void setUser(UserModel user) {
	this.user = user;
}

public UserModel getTo() {
	return to;
}

public void setTo(UserModel to) {
	this.to = to;
}

public int getCheckin() {
	return checkin;
}

public void setCheckin(int checkin) {
	this.checkin = checkin;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

}
