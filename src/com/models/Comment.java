package com.models;

import java.sql.Date;
import java.util.ArrayList;

public interface Comment {
	public abstract boolean add();

	public abstract ArrayList<Comment> getComments();

	public abstract boolean undoComment();
	
	public abstract boolean removeCheckinComments() ;

	public abstract UserModel getUser();

	public abstract void setUser(UserModel user);

	public abstract UserModel getTo();

	public abstract void setTo(UserModel to);

	public abstract int getCheckin();

	public abstract void setCheckin(int checkin);

	public abstract Date getDate();

	public abstract void setDate(Date date);
}
