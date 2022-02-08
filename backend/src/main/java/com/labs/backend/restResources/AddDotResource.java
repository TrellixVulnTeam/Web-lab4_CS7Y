package com.labs.backend.restResources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.labs.backend.beans.DotsData;
import com.labs.backend.beans.JwtManager;
import com.labs.backend.beans.UsersData;
import com.sun.jersey.multipart.FormDataParam;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/add")
public class AddDotResource {

    @EJB
    private DotsData dotsData;
    @EJB
    private UsersData usersData;
    @EJB
    private JwtManager jwtManager;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDot(@FormDataParam("dotData") String dotData, @HeaderParam("Authorization") String token) {

        String login = jwtManager.decodeToken(token).getClaim("login").asString();
        String xStr;
        String yStr;
        String rStr;
        try {
            JsonObject jsonDotData = new Gson().fromJson(dotData, JsonObject.class);
            xStr = jsonDotData.get("x").getAsString();
            yStr = jsonDotData.get("y").getAsString();
            rStr = jsonDotData.get("r").getAsString();
        } catch (JsonSyntaxException e) {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage())
                    .build();
        }

        try {
            double x = Double.parseDouble(xStr);
            double y = Double.parseDouble(yStr);
            double r = Double.parseDouble(rStr);

            if (y <= -3 || y >= 3)
                throw new IllegalArgumentException("y not in (-3, 3)");

            return dotsData.addDot(login, x, y, r);

        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
