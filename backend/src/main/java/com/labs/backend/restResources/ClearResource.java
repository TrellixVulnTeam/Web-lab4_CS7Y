package com.labs.backend.restResources;

import com.labs.backend.beans.DotsData;
import com.labs.backend.beans.JwtManager;
import com.labs.backend.beans.UsersData;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("/clear")
public class ClearResource {

    @EJB
    private DotsData dotsData;
    @EJB
    private JwtManager jwtManager;

    @DELETE
    public Response removeDots(@HeaderParam("Authorization") String token) {
        String login = jwtManager.decodeToken(token).getClaim("login").asString();
        return dotsData.clear(login);
    }
}
