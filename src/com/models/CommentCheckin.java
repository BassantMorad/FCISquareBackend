package com.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class CommentCheckin implements Comment {
	UserModel user,to;
	int checkin;
	String Comment;
	Date date;

	public CommentCheckin(int checkin) {
		super();
		this.checkin = checkin;
	}

	public CommentCheckin(UserModel user,UserModel to, int checkin, String comment) {
		super();
		this.user = user;
		this.to=to;
		this.checkin = checkin;
		Comment = comment;
	}

	public CommentCheckin(Date date , String comment,UserModel user,int id) {
		// TODO Auto-generated constructor stub
		this.date=date;
		this.Comment=comment;
		this.user=user;
		this.checkin=id;
	}
	public CommentCheckin(UserModel userModel) {
		// TODO Auto-generated constructor stub
		this.user=userModel;
	}
	public CommentCheckin(UserModel userModel,int id) {
		// TODO Auto-generated constructor stub
		this.user=userModel;
		this.checkin=id;
	}

	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into commentcheckin (`userEmail`,`checkinID`,`comment`) VALUES  (?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getEmail());
			stmt.setInt(2, checkin);
			stmt.setString(3, Comment);
			stmt.executeUpdate();
			
			Notification notification = new Notification(to, user, checkin,"Comment","commented on your post");
			return notification.addNotification();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Comment> getComments() {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from commentcheckin where userEmail=? ";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Comment> list = new ArrayList<Comment>();
			while (rs.next()) {
				Comment comment = new CommentCheckin(rs.getDate("date"),
						rs.getString("comment"), new UserModel(rs.getString("userEmail")),
						rs.getInt("checkinID"));
				Checkin c=new SearchPlaceForCheckin();
				comment.setTo(c.getCheckin(comment.getCheckin()).getUser());
				list.add(comment);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean undoComment() {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = " delete from commentcheckin where userEmail= ? and checkinID=?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setInt(2,checkin);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeCheckinComments() {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = " delete from commentcheckin where checkinID=?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,checkin);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
