package com.labs.backend.utils;

import com.labs.backend.beans.UsersData;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class VerifyTokenFilter implements ContainerRequestFilter {

    private final static String ApiUriBase = "http://localhost:8080/backend-1.0/api/";

    @EJB
    private UsersData usersData;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        if (request.getMethod().equals("OPTIONS")) {
            return;
        }
        String requestUri = request.getUriInfo().getRequestUri().toString();
        if (requestUri.equals(ApiUriBase + "add") ||
                requestUri.equals(ApiUriBase + "clear") ||
                requestUri.equals(ApiUriBase + "data")) {

            String token = request.getHeaderString("Authorization");
            if (token == null || !usersData.isRegisteredByToken(token)) {
                request.abortWith(Response
                        .status(Response.Status.FORBIDDEN)
                        .build());
            }
        }
    }
}
