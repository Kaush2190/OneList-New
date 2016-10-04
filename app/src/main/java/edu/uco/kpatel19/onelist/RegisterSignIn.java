package edu.uco.kpatel19.onelist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.buddy.sdk.Buddy;
import com.buddy.sdk.BuddyCallback;
import com.buddy.sdk.BuddyResult;
import com.buddy.sdk.models.User;

import java.util.Date;

public class RegisterSignIn extends Activity {

    private EditText username1;
    private EditText password1;
    private Button Signin;
    private Button Register;
    private String username;
    private String password;
    private final int RETURN = 1;
    private String APP_LOG = "This:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sign_in);

        username1 = (EditText) findViewById(R.id.nameEditText);
        password1 = (EditText) findViewById(R.id.passwordEditText);
        Signin = (Button) findViewById(R.id.signInButton);
        Register = (Button) findViewById(R.id.registerButton);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username1.getText().toString();
                password = password1.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("OneListData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", username1.getText().toString());
                editor.commit();

                Buddy.loginUser(username, password, new BuddyCallback<User>(User.class) {
                    @Override
                    public void completed(BuddyResult<User> result) {
                        Intent main = new Intent(RegisterSignIn.this, MainActivity.class);
                        main.putExtra("username", username);
                        startActivityForResult(main, RETURN);
                    }
                });
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username1.getText().toString();
                password = password1.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("OneListData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", username1.getText().toString());
                editor.commit();

                Buddy.createUser(username, password, null, null, null, null, null, "tag", new BuddyCallback<User>(User.class) {
                    @Override
                    public void completed(BuddyResult<User> result) {
                        if (result.getIsSuccess()) {
                            Log.w(APP_LOG, "User created");
                            Intent main = new Intent(RegisterSignIn.this, MainActivity.class);
                            startActivityForResult(main, RETURN);
                        } else {
                            Log.w(APP_LOG, "Failed");
                            Log.w(APP_LOG, username);
                            Log.w(APP_LOG, password);
                            Toast.makeText(RegisterSignIn.this, "User Already Exists. Please select a different Username.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                }
        });


    }

}
