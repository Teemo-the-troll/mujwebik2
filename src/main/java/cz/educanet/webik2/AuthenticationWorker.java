package cz.educanet.webik2;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
@Produces (MediaType.APPLICATION_JSON)
public class AuthenticationWorker {

    @Inject
    private AuthenticationManager manager;

    @Path("/login")
    @POST
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password){
       return manager.loginUser(username, password).build();
    }

    @Path("/register")
    @POST
    public Response register(@QueryParam("name") String name, @QueryParam("username") String userName,@QueryParam("email") String email,@QueryParam("password") String password){
        if (!email.contains("@") || !email.contains("."))
            return Response.status(400,"invalid email").build();
        else
            return manager.registerUser(name,userName,email,password).build();
    }




}
