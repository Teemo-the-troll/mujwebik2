package cz.educanet.webik2;


import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserWorker {
    Gson gson = new Gson();
    @Inject
    private AuthenticationManager manager;
    @Inject
    private UserManager userManager;



    @GET
    public Response loggedinUsers(Token token){
        return manager.getLoggedUsers(token).build();
    }

    /*
    @GET
    public Response getusers() {
        return Response.ok(gson.toJson(userManager.getUsers())).build();
    }*/

    @Path("/authentication/logout")
    @DELETE
    public Response logoutUser(@QueryParam("username") String username, Token token) {
        return manager.logoutUser(username, token).build();
    }


}
