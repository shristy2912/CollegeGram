package com.codewithshristy.collegegram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class adminLogin extends AppCompatActivity {

    TextView signup;
    TextView fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        signup=(TextView)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminLogin.this, adminRegistration.class);
                startActivity(intent);
            }
        });
        fp=(TextView)findViewById(R.id.forgotpass);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminLogin.this, studentForgotPass.class);
                startActivity(intent);
            }
        });

    }
}