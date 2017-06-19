package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Notification;
import com.models.UserModel;

@Path("/")
public class NotificationService {
	@POST
	@Path("/getNotifications")
	@Produces(MediaType.TEXT_PLAIN)
	public String  getNotifications(@FormParam("email") String email){
		Notification n=new Notification(new UserModel(email));
		ArrayList<Notification> res = n.getNotifications();
		JSONArray json = new JSONArray();
		JSONObject obj;
		for (int i = 0; i < res.size(); i++) {
			obj=new JSONObject();
			obj.put("to", res.get(i).getTo().getEmail());
			obj.put("from", res.get(i).getFrom().getEmail());
			obj.put("notificationCheckin", res.get(i).getCheckin());
			obj.put("notificationDescription", res.get(i).getDescription());
			obj.put("type", res.get(i).getType());
			json.add(obj.toJSONString());
		}
		return json.toJSONString();
	}
}
