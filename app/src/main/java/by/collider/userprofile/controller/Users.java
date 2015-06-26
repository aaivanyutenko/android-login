package by.collider.userprofile.controller;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import by.collider.userprofile.model.User;

public class Users {
    private static Users instance;

    private SharedPreferences preferences;
    private User current;

    public static Users getInstance(Context context) {
        if (instance == null) {
            instance = new Users(context);
        }
        return instance;
    }

    private Users(Context context) {
        preferences = context.getSharedPreferences("by.collider.userprofile.SHARED_PREFERENCES", Context.MODE_PRIVATE);
    }

    public boolean createUser(String email, String password) {
        String userString = preferences.getString(email, null);
        if (userString == null) {
            User user = new User(email, password);
            current = user;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(email, user.toJson());
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    public User getUser(String email) {
        String userString = preferences.getString(email, null);
        if (userString == null) {
            return null;
        } else {
            return User.fromJson(userString);
        }
    }

    public User getCurrent() {
        return current;
    }
}
