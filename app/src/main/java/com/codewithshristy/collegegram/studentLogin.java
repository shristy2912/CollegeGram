package com.codewithshristy.collegegram;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class studentLogin extends AppCompatActivity {

    TextView signup;
    TextView fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        final EditText username=findViewById(R.id.username);
        final EditText password=findViewById(R.id.password);
        final Button loginbtn=findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UsernameTxt=username.getText().toString();
                final String PasswordTxt=password.getText().toString();

                if(UsernameTxt.isEmpty() || PasswordTxt.isEmpty()){
                    Toast.makeText(studentLogin.this,"Please Enter your username and Password",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });

        signup=(TextView)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentLogin.this, studentRegistration.class);
                startActivity(intent);
            }
        });
        fp=(TextView)findViewById(R.id.forgotpass);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentLogin.this, studentForgotPass.class);
                startActivity(intent);
            }
        });

    }
}