package by.collider.userprofile.model;

import com.google.gson.Gson;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    public String email;
    public String hash;

    public User(String emai, String password) {
        this.email = emai;
        hash = md5(password);
    }

    public static User fromJson(String json) {
        return new Gson().fromJson(json, User.class);
    }

    public String md5(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(input.getBytes(), 0, input.length());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return input;
        }
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

}
