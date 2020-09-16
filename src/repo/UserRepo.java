package repo;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserRepo {
    HashMap<String, User> users = new HashMap<String, User>();
}
