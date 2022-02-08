package com.labs.backend.restResources;

import com.labs.backend.beans.DotsData;
import com.labs.backend.beans.JwtManager;
import com.labs.backend.beans.UsersData;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Stateless
@Path("/data")
public class DataResource {
    @EJB
    DotsData dotsData;
    @EJB
    JwtManager jwtManager;

    @GET
    public Response getData() {
        return dotsData.getDots();
    }
}
