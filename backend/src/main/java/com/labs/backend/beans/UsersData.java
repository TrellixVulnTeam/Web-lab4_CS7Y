package com.labs.backend.beans;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.labs.backend.entities.User;
import com.labs.backend.pojo.ResponseData;
import com.labs.backend.utils.SecurePassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.servlet.http.HttpSessionListener;
import javax.ws.rs.core.Response;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@Stateless
public class UsersData {

    @EJB
    private DatabaseManager dbManager;
    @EJB
    private JwtManager jwtManager;
    private User user;

    private byte[] getRandomSalt() {
        byte[] salt = new byte[32];
        new Random().nextBytes(salt);
        return salt;
    }

    public Response addUser(String login, String password) {
        ResponseData responseData = new ResponseData();

        if (login.equals("") || password.equals("")){
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .build();
        }

        if (dbManager.getUser(login) != null) {
            responseData.setSuccess(false);
            responseData.setInfo("User already exists");
            return Response
                    .status(Response.Status.OK)
                    .entity(responseData)
                    .build();
        }
        byte[] salt = getRandomSalt();
        String securePassword = SecurePassword.getSHA512(password, salt);
        user = new User(login, securePassword, salt);

        if (dbManager.addUser(user)) {
            responseData.setSuccess(true);
            return Response
                    .status(Response.Status.OK)
                    .entity(responseData)
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }


    public Response loginUser(String login, String password) {
        ResponseData responseData = new ResponseData();

        User user = dbManager.getUser(login);
        if (user == null) {
            responseData.setSuccess(false);
            responseData.setInfo("User doesn't exist");
            return Response
                    .status(Response.Status.OK)
                    .entity(responseData)
                    .build();
        }
        String securePassword = SecurePassword.getSHA512(password, user.getSalt());
        if (user.getPassword().equals(securePassword)) {
            responseData.setToken(jwtManager.createTokenByLogin(login));
            responseData.setSuccess(true);
            return Response
                    .status(Response.Status.OK)
                    .entity(responseData)
                    .build();
        }
        responseData.setSuccess(false);
        responseData.setInfo("Wrong password");
        return Response
                .status(Response.Status.OK)
                .entity(responseData)
                .build();
    }

    public boolean isRegisteredByToken(String token) {
        DecodedJWT decodedJWT = jwtManager.decodeToken(token);
        if (decodedJWT == null)
            return false;
        return dbManager.getUser(decodedJWT.getClaim("login").asString()) != null;
    }

    public boolean clear() {
        return dbManager.clearUserList();
    }
}
