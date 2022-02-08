package com.labs.backend.restResources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.labs.backend.beans.UsersData;
import com.sun.jersey.multipart.FormDataParam;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/register")
public class RegisterResource {
    @EJB
    private UsersData usersData;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(@FormDataParam("regData") String regData) {

        JsonObject jsonRegData = new Gson().fromJson(regData, JsonObject.class);
        String login = jsonRegData.get("login").getAsString();
        String password = jsonRegData.get("password").getAsString();

        return usersData.addUser(login, password);

    }
}
