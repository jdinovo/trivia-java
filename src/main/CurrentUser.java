package main;

import javabean.User;

public class CurrentUser {

    private static User currUser;

    public static User getUser() {
        return currUser;
    }

    public static void setUser(User user) {
        currUser = user;
    }
}
