package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Checkin;
import com.models.DBConnection;
import com.models.PlaceModel;
import com.models.SearchPlaceForCheckin;
import com.models.SuggestedCheckin;
import com.models.UserModel;

@Path("/")
public class CheckinService {
	@POST
	@Path("/checkin")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkin(@FormParam("email") String email,
			@FormParam("placeName") String name, @FormParam("post") String post) {
		UserModel user = new UserModel(email);
		Checkin checkin;
		if (name.compareTo("") == 0) {
			PlaceModel place = new PlaceModel(name);
			checkin=new SuggestedCheckin(user, place, post);
			
		} else {
			PlaceModel place = new PlaceModel(name);
			checkin = new SearchPlaceForCheckin(user, place, post);
		}
		Checkin c=checkin.add();
		JSONObject json = new JSONObject();
		json.put("id", c.getID());
		json.put("date", c.getDate().toString());
		json.put("time", c.getTime().toString());
		json.put("post", c.getPost());
		json.put("user", c.getUser().getEmail());
		json.put("place",c.getPlace().getName());
		return json.toJSONString();
	}
	
	@POST
	@Path("/getCheckins")
	@Produces(MediaType.TEXT_PLAIN)
	public String  getCheckins(@FormParam("email") String email){
		Checkin c=new SearchPlaceForCheckin(new UserModel(email));
		ArrayList<Checkin> res = c.getCheckins();
		JSONArray json = new JSONArray();
		JSONObject obj;
		for (int i = 0; i < res.size(); i++) {
			obj=new JSONObject();
			obj.put("aDate", res.get(i).getDate().toString());
			obj.put("ToUser", res.get(i).getUser().getEmail());
			obj.put("aCheckin", res.get(i).getID());
			obj.put("place", res.get(i).getPlace().getName());
			json.add(obj.toJSONString());
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/undoCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String undoCheckin(@FormParam("email") String email,@FormParam("name") String name,@FormParam("id") String id) {
		Checkin c=new SearchPlaceForCheckin(new UserModel(email),new PlaceModel(name),Integer.parseInt(id));
		boolean status= c.undoCheckin();
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
}
