package by.collider.userprofile.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import by.collider.userprofile.R;
import by.collider.userprofile.controller.Users;

public class Profile extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        ((TextView) findViewById(R.id.email)).setText(Users.getInstance(this).getCurrent().email);
    }
}
