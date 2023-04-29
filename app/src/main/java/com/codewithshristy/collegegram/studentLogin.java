package com.codewithshristy.collegegram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentLogin extends AppCompatActivity {

    TextView signup;
    TextView fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        //firebaseDatabase.getReference().child("Node").setValue("NodeValue");

        final EditText username=findViewById(R.id.username);
        final EditText password=findViewById(R.id.password);
        final Button loginbtn=findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UsernameTxt=username.getText().toString();
                final String PasswordTxt=password.getText().toString();

                FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

                if(UsernameTxt.isEmpty() || PasswordTxt.isEmpty()){
                    Toast.makeText(studentLogin.this,"Please Enter your username and Password",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.signInWithEmailAndPassword(UsernameTxt,PasswordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                String uid = task.getResult().getUser().getUid();
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("user").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int usertype = snapshot.getValue(Integer.class);
                                        if(usertype == 0){
                                            Intent intent=new Intent(studentLogin.this,mainPage.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            
                        }
                    });
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