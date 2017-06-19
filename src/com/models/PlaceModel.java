package com.models;

import java.sql.Connection;
/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceModel {
	Integer id;
	String name, description;
	Double lat, lon;
	
	public PlaceModel(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public static boolean addNewPlace(String name , double lat  , double lon , String desc ,String email){
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into places (name,lat,places.long,description) VALUES  (?,?,?,?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			if(lat==0 && lon==0){
				UserModel user=UserModel.getLastLocation(email);
				stmt.setDouble(2,user.getLat());
				stmt.setDouble(3,user.getLon());
			}
			else{
			stmt.setDouble(2, lat);
			stmt.setDouble(3, lon);
			}
			stmt.setString(4, desc);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
		
	}
	public int getNumberOfCheckins()
	{
		int count=0;
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT COUNT(*) FROM  checkin WHERE placeName= ? ";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,this.getName());
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
	/*public static PlaceModel getPlace(double lat,double lon) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from places where 'lat'= ? and 'long'= ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1,lat);
			stmt.setDouble(2,lon);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				PlaceModel place=new PlaceModel();
				place.setName();
				return place;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return null;
	}*/
}
