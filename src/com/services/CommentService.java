package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Checkin;
import com.models.Comment;
import com.models.CommentCheckin;
import com.models.SuggestedCheckin;
import com.models.UserModel;
@Path("/")
public class CommentService {
	
	@POST
	@Path("/addcommentcheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCommentCheckin(@FormParam("email") String email,@FormParam("toEmail") String to,
			@FormParam("checkin") String checkin , @FormParam("comment") String comment) {
		UserModel user = new UserModel(email);
		Comment com = new CommentCheckin(user,new UserModel(to) , Integer.parseInt(checkin) , comment);
		boolean status = com.add();
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
	
	@POST
	@Path("/getComments")
	@Produces(MediaType.TEXT_PLAIN)
	public String  getComments(@FormParam("email") String email){
		Comment com = new CommentCheckin(new UserModel(email));
		ArrayList<Comment> res = com.getComments();
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
	@Path("/undoComment")
	@Produces(MediaType.TEXT_PLAIN)
	public String undoComment(@FormParam("email") String email,@FormParam("checkin") String checkinID) {
		Comment com = new CommentCheckin(new UserModel(email),Integer.parseInt(checkinID));
		boolean status= com.undoComment();
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
}
