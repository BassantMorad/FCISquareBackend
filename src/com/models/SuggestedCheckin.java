package com.models;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class SuggestedCheckin implements Checkin {
	Date date;
	Time time;
	private UserModel user;
	private PlaceModel place;
	private String checkinPost;
	int ID;
	
	public SuggestedCheckin(int iD) {
		super();
		ID = iD;
	}
	
	public SuggestedCheckin(UserModel user, PlaceModel place,
			String checkinPost) {
		super();
		this.user = user;
		this.place = place;
		this.checkinPost = checkinPost;
	}
	
	@Override
	public Checkin add() {
		return null;
		// TODO Auto-generated method stub
		
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
	public ArrayList<Checkin> getCheckins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean undoCheckin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setID(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPost(String post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUser(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlace(PlaceModel place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTime(Time time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Checkin getCheckin(int checkinID) {
		// TODO Auto-generated method stub
		return null;
	}

}
