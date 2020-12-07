package cz.educanet.webik2;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class UserManager {
    private final ArrayList<User> users = new ArrayList<>();
    Gson gson = new Gson();

    public ArrayList<User> getUsers() {
        return users;
    }

    public Response.ResponseBuilder createUser(String name, String userName, String email, String password) {
        for (User u : users)
            if (u.getUserName().equals(userName))
                return Response.status(400, "User already exists");
        User temp = new User(name, userName, email, password);
        users.add(temp);
        return Response.ok(gson.toJson(temp));
    }

}
