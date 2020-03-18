package com.test.pokedex.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.test.pokedex.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button loginBtn;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        manageIntent();

        initializeComponents();
        initializeListeners();
        initializeData();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public void manageIntent(){
        if(getIntent() != null){
            username = getIntent().getStringExtra("USERNAME");
            password = getIntent().getStringExtra("PASSWORD");
        }
    }

    public void initializeComponents(){
        usernameText = findViewById(R.id.username_login);
        passwordText = findViewById(R.id.password_login);
        loginBtn     = findViewById(R.id.button_login);

        usernameText.setText(username);
        passwordText.setText(password);
    }

    public void initializeListeners(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(usernameText.getText().toString(),passwordText.getText().toString());
            }
        });
    }

    public void initializeData(){

    }

    public void login(String username,String password){
        if(username.equals("")){
            Toast.makeText(this,"Username cannot be empty",Toast.LENGTH_LONG).show();
            usernameText.setFocusable(true);
        }else if(password.equals("")){
            Toast.makeText(this,"Password cannot be empty",Toast.LENGTH_LONG).show();
            passwordText.setFocusable(true);
        }else{
            if(username.equals("pokedex") && password.equals("pokedex")){
                Intent intent = new Intent(ActivityLogin.this, ActivityList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Username or Password is not correct",Toast.LENGTH_LONG).show();
            }
        }

    }

}
