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

import com.models.DBConnection;
import com.models.Like;
import com.models.LikeCheckin;
import com.models.PlaceModel;
import com.models.UserModel;

@Path("/")
public class Services {

	/*
	 * @GET
	 * 
	 * @Path("/signup")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	 * Response.ok(new Viewable("/Signup.jsp")).build(); }
	 */

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id),
				Double.parseDouble(lat), Double.parseDouble(lon));
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}

	@POST
	@Path("/getlastlocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String getlastlocation(@FormParam("email") String email) {
		UserModel user = UserModel.getLastLocation(email);
		JSONObject json = new JSONObject();
		// json.put("id", user.getId());
		// json.put("name", user.getName());
		// json.put("email", user.getEmail());
		// json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	@POST
	@Path("/follow")
	@Produces(MediaType.TEXT_PLAIN)
	public String follow(@FormParam("email1") String emailFrom,
			@FormParam("email2") String emailTo) {
		boolean status = UserModel.addNewfollow(emailFrom, emailTo);
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}

	@POST
	@Path("/unfollow")
	@Produces(MediaType.TEXT_PLAIN)
	public String unfollow(@FormParam("email1") String emailfrom,
			@FormParam("email2") String emailto) {
		boolean status = UserModel.unfollow(emailfrom, emailto);
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}

	@POST
	@Path("/getfollowlist")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFollowers(@FormParam("email") String email) {
		ArrayList<UserModel> res = UserModel.getFollowers(email);
	    JSONArray json = new JSONArray();
		JSONObject obj;
		for (int i = 0; i <res.size(); i++) {
			obj=new JSONObject();
			String tmp = "user";
			tmp += Integer.toString(i+1);
			obj.put(tmp, res.get(i).getEmail());
			json.add(obj.toJSONString());
		}
		return json.toJSONString();
	}

	@POST
	@Path("/addNewPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewPlace(@FormParam("name") String name,
			@FormParam("latitude") String latitude ,@FormParam("longitude") String longitude,@FormParam("description") String desc,@FormParam("email") String email) {
		boolean status= PlaceModel.addNewPlace(name, Double.parseDouble(latitude) , Double.parseDouble(longitude),desc,email);
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
	
	@POST
	@Path("/numberOfCheckins")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNumLikes(@FormParam("placeName")String place) {
		PlaceModel p=new PlaceModel(place);
		int count = p.getNumberOfCheckins();
		JSONObject json = new JSONObject();
		json.put("number of checkins",count);
		return json.toJSONString();
	}
}
