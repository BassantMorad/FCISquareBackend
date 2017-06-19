package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.Checkin;
import com.models.RespondToNotification;
import com.models.UserModel;
@Path("/")
public class RespondNotificationService {
	@POST
	@Path("/respondNotification")
	@Produces(MediaType.TEXT_PLAIN)
	public String respond(@FormParam("checkinID") String CheckinID) {
		Checkin checkin = RespondToNotification.RespondCheckinNotification(Integer.parseInt(CheckinID));
		JSONObject json = new JSONObject();
		json.put("id", checkin.getID());
		json.put("userEmail", checkin.getUser().getEmail());
		json.put("placeName", checkin.getPlace().getName());
		json.put("post", checkin.getPost());
		json.put("date", checkin.getDate().toString());
		json.put("time", checkin.getTime().toString());
		return json.toJSONString();
	}
	
}
