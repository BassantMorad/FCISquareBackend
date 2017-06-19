package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Like;
import com.models.LikeCheckin;
import com.models.PlaceModel;
import com.models.SavePlace;
import com.models.UserModel;

@Path("/")
public class SavePlaceService {
	
	@POST
	@Path("/savePlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String savePlace(@FormParam("userEmail") String userEmail , @FormParam("placeName") String placeName )
	{
		UserModel userM =new UserModel(userEmail);
		PlaceModel placeM =new PlaceModel(placeName);
		SavePlace save=new SavePlace(userM ,placeM);
		boolean status = save.add();
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0 );
		
		return json.toJSONString();
	}

	@POST
	@Path("/getSaves")
	@Produces(MediaType.TEXT_PLAIN)
	public String  getSaves(@FormParam("email") String email){
		SavePlace s=new SavePlace(new UserModel(email));
		ArrayList<SavePlace> res = s.getSaves();
		JSONArray json = new JSONArray();
		JSONObject obj;
		for (int i = 0; i < res.size(); i++) {
			obj=new JSONObject();
			obj.put("placeName", res.get(i).getPlace().getName());
			obj.put("userEmail", res.get(i).getUser().getEmail());
			json.add(obj.toJSONString());
		}
		return json.toJSONString();
	}
}
