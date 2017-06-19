package com.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

public interface Like {
	public abstract boolean add_Like();

	public abstract int getNumberOfLikes();

	public abstract ArrayList<String> whoLike();

	public abstract ArrayList<Like> getLikes();

	public abstract boolean undoLike();
	
	public abstract boolean removeCheckinLikes();

	public abstract UserModel getUser();

	public abstract void setUser(UserModel user);

	public abstract UserModel getTo();

	public abstract void setTo(UserModel to);

	public abstract int getCheckin();

	public abstract void setCheckin(int checkin);

	public abstract Date getDate();

	public abstract void setDate(Date date);
}
