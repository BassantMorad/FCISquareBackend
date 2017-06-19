package com.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class SearchPlaceForCheckin implements Checkin {
	private Date date;
	private Time time;
	private UserModel user;
	private PlaceModel place;
	private String checkinPost;
	int ID;

	public SearchPlaceForCheckin() {
		super();
	}

	public SearchPlaceForCheckin(UserModel user) {
		super();
		this.user= user;
	}
	
	public SearchPlaceForCheckin(int iD) {
		super();
		this.ID = iD;
	}

	public SearchPlaceForCheckin(UserModel user, PlaceModel place, int iD) {
		super();
		this.user = user;
		this.place = place;
		ID = iD;
	}

	public SearchPlaceForCheckin(UserModel user, PlaceModel place,
			String checkinPost) {
		super();
		this.user = user;
		this.place = place;
		this.checkinPost = checkinPost;
	}

	public SearchPlaceForCheckin(Date date, Time time, String checkinPost,
			UserModel user, PlaceModel place, int iD) {
		super();
		this.date = date;
		this.time = time;
		this.user = user;
		this.place = place;
		this.checkinPost = checkinPost;
		this.ID = iD;
	}

	@Override
	public Checkin add() {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into checkin (`placeName`,`userEmail`,`post`) VALUES  (?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, place.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, checkinPost);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			sql = "Select * from checkin where ID= ? ";
			PreparedStatement stmt2 = conn.prepareStatement(sql);
			if (rs.next()) {
				stmt2.setInt(1, rs.getInt(1));
				ResultSet rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					Checkin c1 = new SearchPlaceForCheckin(rs2.getDate("date"),
							rs2.getTime("date"), rs2.getString("post"),
							new UserModel(rs2.getString("userEmail")),
							new PlaceModel(rs2.getString("placeName")),
							rs2.getInt("ID"));
					return c1;
				}
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Checkin getCheckin(int checkinID){
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from checkin where ID=?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, checkinID);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				Checkin checkin = new SearchPlaceForCheckin(rs.getDate("date"),
						rs.getTime("date"), rs.getString("post"),
						new UserModel(rs.getString("userEmail")),
						new PlaceModel(rs.getString("placeName")),
						rs.getInt("ID"));
				return checkin ;
			}		
			return null;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public  ArrayList<Checkin> getCheckins(){
		 try{
				Connection conn = DBConnection.getActiveConnection();
				String sql = "select * from checkin where userEmail  = ?";
				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,user.getEmail());
				ResultSet rs = stmt.executeQuery();
				ArrayList<Checkin> list = new ArrayList<Checkin>();
				while(rs.next()){
					Checkin checkin = new SearchPlaceForCheckin();
					checkin.setDate(rs.getDate("date"));
					checkin.setPost(rs.getString("post"));
					checkin.setUser(new UserModel(rs.getString("userEmail")));
					checkin.setPlace(new PlaceModel(rs.getString("placeName")));
					checkin.setID(rs.getInt("ID"));
					list.add(checkin);
				}
				return list;	
			}catch(SQLException e){
				e.printStackTrace();
			}
		return null;
	}

	public boolean undoCheckin(){
		 try{
				Connection conn = DBConnection.getActiveConnection();
				String sql = " delete from checkin where userEmail= ? and ID= ? and placeName= ?";
				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getEmail());
				stmt.setInt(2, ID);
				stmt.setString(3,place.getName());
				stmt.executeUpdate();
				
				boolean done=true;
	
				Like like=new LikeCheckin(ID);
				done&=like.removeCheckinLikes();
				
				Comment comment=new CommentCheckin(ID);
				done&=comment.removeCheckinComments();
				
				Notification notification = new Notification(ID);
				done&=notification.removeNotification();
				
				return done;
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		return false;
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public String getPost() {
		// TODO Auto-generated method stub
		return checkinPost;
	}

	@Override
	public UserModel getUser() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public PlaceModel getPlace() {
		// TODO Auto-generated method stub
		return place;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public Time getTime() {
		// TODO Auto-generated method stub
		return time;
	}

	@Override
	public void setID(int id) {
		// TODO Auto-generated method stub
		this.ID=id;
	}

	@Override
	public void setPost(String post) {
		// TODO Auto-generated method stub
		this.checkinPost=post;
	}

	@Override
	public void setUser(UserModel user) {
		// TODO Auto-generated method stub
		this.user=user;
	}

	@Override
	public void setPlace(PlaceModel place) {
		// TODO Auto-generated method stub
		this.place=place;
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		this.date=date;
	}

	@Override
	public void setTime(Time time) {
		// TODO Auto-generated method stub
		this.time=time;
	}

}
