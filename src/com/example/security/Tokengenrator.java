package com.example.security;

import java.util.Base64;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.example.util.Util;

@Path("/auth")
public class Tokengenrator {
	final static Logger log = Logger.getLogger(Tokengenrator.class.getName());
	Util util;

	@GET
	@Path("/gettoken")
	public Response gettoken(@HeaderParam("authorization") String authString) {
		util = new Util();
		JSONObject json = new JSONObject();
		json.put("accessToken", util.getToken());
		json.put("refreshToken", util.getToken());
		if (isAuthenticated(authString)) {
			return Response.status(200).entity(json.toString()).build();
		} else {
			return Response.status(401).build();
		}
	}

	private boolean isAuthenticated(String authString) {
		String decodedAuth = "";
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		// Decode the data back to original string
		byte[] bytes = null;
		bytes = Base64.getDecoder().decode(authInfo);
		decodedAuth = new String(bytes);
		System.out.println(decodedAuth);
		String[] credentials = decodedAuth.split(":");
		if (credentials[0].equals("admin") && credentials[1].equals("admin")) {
			return true;
		} else {
			return false;
		}
	}
}
