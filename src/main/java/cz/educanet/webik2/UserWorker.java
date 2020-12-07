package cz.educanet.webik2;


import com.google.gson.Gson;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserWorker {
    private final AuthenticationManager manager = new AuthenticationManager();
    private final UserManager userManager = new UserManager();
    Gson gson = new Gson();
    /*
    @GET
    public Response loggedinUsers(){
        return manager.getLoggedUsers().build();
    }
    */


    @GET
    public Response getusers(){
        return Response.ok(gson.toJson(userManager.getUsers())).build();
    }

    @Path("/authentication/logout")
    @DELETE
    public Response logoutUser(@QueryParam("username") String username){
        return manager.logoutUser(username).build();
    }





}
