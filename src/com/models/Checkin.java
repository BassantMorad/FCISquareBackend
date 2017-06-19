package com.models;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public interface Checkin {
	public abstract Checkin add();

	public abstract ArrayList<Checkin> getCheckins();
	
	public boolean undoCheckin();
	
	public abstract int getID();

	public abstract String getPost();

	public abstract UserModel getUser();

	public abstract PlaceModel getPlace();

	public abstract Date getDate();

	public abstract Time getTime();

	public abstract void setID(int id);

	public abstract void setPost(String post);

	public abstract void setUser(UserModel user);

	public abstract void setPlace(PlaceModel place);

	public abstract void setDate(Date date);

	public abstract void setTime(Time time);
	
	public abstract Checkin getCheckin(int checkinID);
}
