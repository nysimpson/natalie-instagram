package com.example.natalie_instagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // check if there is a user logged
        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            goMainActivity();
        } else {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "mgs: onClick login button");
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    loginUser(username, password);
                }
            });
        }
    }

    private void loginUser(String username, String password){
        Log.i(TAG,"Attempting to login user" + username);
        //TODO: navigate to the main activity if the user has signed in properly
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    // There is an error
                    Log.e(TAG, "Issue with login");
                    e.printStackTrace();
                    return;
                }
                goMainActivity();
            }
        });

        Toast.makeText(LoginActivity.this, "USERNAME " + username, Toast.LENGTH_SHORT).show();
    }

    private void goMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
