package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Notification {
	UserModel to, from;
	String type, description;
	int checkin;
	
	public Notification(int checkin) {
		super();
		this.checkin = checkin;
	}

	public Notification(UserModel to, UserModel from, int checkin, String type,
			String description) {
		super();
		this.from = from;
		this.to = to;
		this.type = type;
		this.description = description;
		this.checkin = checkin;
	}

	public Notification(UserModel touserModel) {
		// TODO Auto-generated constructor stub
		this.to=touserModel;
	}

	public ArrayList<Notification> getNotifications() {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from notification where toEmail = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, to.getEmail());

			ResultSet rs = stmt.executeQuery();
			ArrayList<Notification> list = new ArrayList<Notification>();
			while (rs.next()) {
				Notification tmp = new Notification(new UserModel(rs.getString("toEmail")),
						new UserModel(rs.getString("fromEmail")), rs.getInt("checkinID"),
						rs.getString("notificationType"),
						rs.getString("description"));
				list.add(tmp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	boolean addNotification() {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into notification (`toEmail`,`fromEmail`,`notificationType` ,`checkinID`, `description`) VALUES  (?,?,?,?,?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, to.getEmail());
			stmt.setString(2, from.getEmail());
			stmt.setString(3, type);
			stmt.setInt(4, checkin);
			stmt.setString(5, description);
			stmt.executeUpdate();
			return true ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false ;
	}

	public boolean removeNotification() {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "delete from notification where  checkinID=?";
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

	
	public UserModel getTo() {
		return to;
	}

	public void setTo(UserModel to) {
		this.to = to;
	}

	public UserModel getFrom() {
		return from;
	}

	public void setFrom(UserModel from) {
		this.from = from;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCheckin() {
		return checkin;
	}

	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}
	
}
