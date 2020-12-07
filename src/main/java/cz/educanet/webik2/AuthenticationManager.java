package cz.educanet.webik2;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class AuthenticationManager {
    private final ArrayList<User> loggedUsers = new ArrayList<User>();
    private final UserManager userManager = new UserManager();
    private final Gson gson = new Gson();

    public Response.ResponseBuilder getLoggedUsers() {
        return Response.ok(gson.toJson(loggedUsers));
    }

    public Response.ResponseBuilder loginUser(String username, String password) {
        ArrayList<User> users = userManager.getUsers();
        for (User u : users) {
            if (u.getUserName().equals(username)){
                if (!u.getPassword().equals(password))
                    return Response.status(400, "Invalid Username or password");
                else {
                    loggedUsers.add(u);
                    return Response.ok();
                }
            }
        }
        return Response.status(404, "user not found");
    }

    public Response.ResponseBuilder registerUser(String name, String userName, String email, String password) {
        if ((name == null) || (userName == null) || (email == null) || (password == null))
            return Response.status(400, "None can be empty");
        else {

            return userManager.createUser(name, userName, email, password);
        }
    }

    public Response.ResponseBuilder logoutUser(String username) {
        if (loggedUsers.stream().anyMatch(u -> u.getUserName().equals(username))) {
            loggedUsers.remove(loggedUsers.stream().filter(u -> u.getUserName().equals(username)).findFirst().get());
            return Response.ok();
        } else return Response.status(404, "user not found or not logged in");

    }

}
