package cz.educanet.webik2;

import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@ApplicationScoped
public class AuthenticationManager {
    private final ArrayList<User> loggedUsers = new ArrayList<User>();
    @Inject
    private UserManager userManager;

    private final Gson gson = new Gson();

    public Response.ResponseBuilder getLoggedUsers(Token token) {
        if (validate(token))
            return Response.ok(gson.toJson(loggedUsers));
        else
            return Response.status(403, "Invalid token");
    }

    public boolean validate(Token token){
        for (User loggedUser : loggedUsers) {
            if (loggedUser.getToken() == token)
                return true;
        }
        return false;
    }

    public Response.ResponseBuilder loginUser(String username, String password) {
        ArrayList<User> users = userManager.getUsers();
        for (User u : users) {
            if (u.getUserName().equals(username)){
                if (!u.getPassword().equals(password))
                    return Response.status(400, "Invalid Username or password");
                else {
                    u.setToken(new Token());
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

    public Response.ResponseBuilder logoutUser(String username, Token token) {
        if (loggedUsers.stream().anyMatch(u -> u.getUserName().equals(username) && (u.getToken() == token))) {
            loggedUsers.remove(loggedUsers.stream().filter(u -> u.getUserName().equals(username)).findFirst().get());
            return Response.ok();
        } else return Response.status(404, "user not found or not logged in");

    }

}
