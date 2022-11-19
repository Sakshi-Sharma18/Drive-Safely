package com.sakshi.myemergencyapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameR, passwordR, identity;
    SharedPreferences sharedPreferences;
    Button registerButton;

    public void buttonAction(View view)
    {
        /*if(registerButton.getText().toString()=="REGISTER")
        {
            register();
        }*/
        //else
        //{
        goToLogin();
        Toast.makeText(getApplicationContext(), "New user created!", Toast.LENGTH_SHORT).show();
        //}
    }

    /*public void register()
    {
        sharedPreferences.edit().putString("username", usernameR.getText().toString()).apply();
        sharedPreferences.edit().putString("password", passwordR.getText().toString()).apply();
        sharedPreferences.edit().putString("identity", identity.getText().toString()).apply();

        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT);

        registerButton.setText("Proceed to Login");
    }*/

    public void goToLogin()
    {
        Intent intent= new Intent(getApplicationContext(), loginActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameR= (EditText) findViewById(R.id.usernameR);
        passwordR= (EditText) findViewById(R.id.passwordR);
        identity= (EditText) findViewById(R.id.identity);
        //registerButton= (Button) findViewById(R.id.registerButton);

        sharedPreferences= this.getSharedPreferences("com.sakshi.myemergencyapplication", Context.MODE_PRIVATE);



    }
}