package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SavePlace {
	UserModel user;
	PlaceModel place;
	public SavePlace(UserModel user, PlaceModel place) {
		super();
		this.user = user;
		this.place = place;
	}
	public SavePlace(UserModel user) {
		super();
		this.user = user;
	}
	
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public PlaceModel getPlace() {
		return place;
	}
	public void setPlace(PlaceModel place) {
		this.place = place;
	}
	public boolean add() {
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into savedplace (`userEmail`,`placeName`) VALUES  (?,?)";
			PreparedStatement stmt;
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, place.getName());

			stmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<SavePlace> getSaves(){
		 try{
				Connection conn = DBConnection.getActiveConnection();
				String sql = "select * from savedplace where userEmail= ?";
				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getEmail());
				ResultSet rs = stmt.executeQuery();
				ArrayList<SavePlace> list = new ArrayList<SavePlace>();
				while(rs.next()){
					SavePlace save = new SavePlace(new UserModel(rs.getString("userEmail")),new PlaceModel(rs.getString("placeName")));
					list.add(save);
				}
				return list;
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		return null; 
	}

}
