package com.labs.backend.utils;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request,
                       ContainerResponseContext response) throws IOException {

        response.getHeaders().remove("Access-Control-Allow-Origin");
        response.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE");
        response.getHeaders().add("Access-Control-Allow-Headers", "Authorization");
    }
}