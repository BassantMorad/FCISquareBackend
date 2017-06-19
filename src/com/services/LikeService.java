package com.services;

import java.util.ArrayList;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Checkin;
import com.models.Like;
import com.models.LikeCheckin;
import com.models.UserModel;

@Path("/")
public class LikeService {
	@POST
	@Path("/likeCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String like(@FormParam("email") String userEmail,@FormParam("toEmail") String to,
			@FormParam("Checkin") String checkin) {
		UserModel user = new UserModel(userEmail);
		Like like = new LikeCheckin(user,new UserModel(to),Integer.parseInt(checkin));
		boolean status = like.add_Like();
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}

	@POST
	@Path("/numberOfLikes")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNumLikes(@FormParam("CheckinID") int checkinID) {
		Like like = new LikeCheckin(checkinID);
		int count = like.getNumberOfLikes();
		JSONObject json = new JSONObject();
		json.put("number of likes",count);
		return json.toJSONString();
	}
	@POST
	@Path("/whoLikes")
	@Produces(MediaType.TEXT_PLAIN)
	public String whoLikes(@FormParam("CheckinID") int checkinID) {
		Like like = new LikeCheckin(checkinID);
		ArrayList<String>userEmails = new ArrayList<String>();
	    userEmails=like.whoLike();
	    JSONArray json = new JSONArray();
		JSONObject obj;
		for (int i = 0; i <userEmails.size(); i++) {
			obj=new JSONObject();
			String tmp = "user";
			tmp += Integer.toString(i+1);
			obj.put(tmp, userEmails.get(i));
			json.add(obj.toJSONString());
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/getLikes")
	@Produces(MediaType.TEXT_PLAIN)
	public String  getLikes(@FormParam("email") String email){
		Like l=new LikeCheckin(new UserModel(email));
		ArrayList<Like> res = l.getLikes();
		JSONArray json = new JSONArray();
		JSONObject obj;
		for (int i = 0; i < res.size(); i++) {
			obj=new JSONObject();
			obj.put("aDate", res.get(i).getDate().toString());
			obj.put("ToUser", res.get(i).getTo().getEmail());
			obj.put("aCheckin", res.get(i).getCheckin());
			json.add(obj.toJSONString());
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/undoLike")
	@Produces(MediaType.TEXT_PLAIN)
	public String undoLike(@FormParam("email") String email,@FormParam("checkinID") String checkinID) {
		Like l=new LikeCheckin(new UserModel(email),Integer.parseInt(checkinID));
		boolean status= l.undoLike();
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
}
