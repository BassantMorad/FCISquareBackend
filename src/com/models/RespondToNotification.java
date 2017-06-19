package com.models;

public class RespondToNotification {
	
	public static Checkin RespondCheckinNotification(int checkinID){
		Checkin c=new SearchPlaceForCheckin();
		c=c.getCheckin(checkinID);
		return c;
	}

}
