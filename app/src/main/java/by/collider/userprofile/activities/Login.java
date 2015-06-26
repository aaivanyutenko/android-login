package by.collider.userprofile.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import by.collider.userprofile.R;
import by.collider.userprofile.controller.Users;
import by.collider.userprofile.model.User;

public class Login extends Activity {

    private EditText email;
    private EditText password;
    private AlertDialog incorrectData;
    private AlertDialog userDoesntExist;
    private AlertDialog wrongPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!");
        builder.setMessage("Some data incorrect.");
        incorrectData = builder.create();
        builder.setTitle("Error!");
        builder.setMessage("This email not registered.");
        userDoesntExist = builder.create();
        builder.setTitle("Error!");
        builder.setMessage("Wrong password.");
        wrongPassword = builder.create();
    }

    public void onLoginClick(View view) {
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        if (!TextUtils.isEmpty(emailString) && !TextUtils.isEmpty(passwordString)) {
            User user = Users.getInstance(this).getUser(emailString);
            if (user != null) {
                if (user.md5(passwordString).equals(passwordString)) {
                    Intent intent = new Intent(this, Profile.class);
                    startActivity(intent);
                } else {
                    wrongPassword.show();
                }
            } else {
                userDoesntExist.show();
            }
        } else {
            incorrectData.show();
        }
    }

}
