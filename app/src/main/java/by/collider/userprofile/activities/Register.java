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

public class Register extends Activity {

    private EditText email;
    private EditText password;
    private EditText confirm;
    private AlertDialog incorrectData;
    private AlertDialog userExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!");
        builder.setMessage("Some data incorrect.");
        incorrectData = builder.create();
        builder.setTitle("Error!");
        builder.setMessage("This email algready registered.");
        userExists = builder.create();
    }

    public void onRegisterClick(View view) {
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String confirmString = confirm.getText().toString();
        if (!TextUtils.isEmpty(emailString) && !TextUtils.isEmpty(passwordString) && !TextUtils.isEmpty(confirmString) && passwordString.equals(confirmString)) {
            if (Users.getInstance(this).createUser(emailString, passwordString)) {
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);
            } else {
                userExists.show();
            }
        } else {
            incorrectData.show();
        }
    }

}
