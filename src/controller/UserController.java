package controller;

import model.User;
import repo.UserRepo;

import java.util.List;

public class UserController {
    UserRepo service;
    public void getUsers() {
        for(User user : service.users) {
            System.out.println(user);
        }
    }
    public void addUser(User newUser) {

    }
}
