package com.codewithshristy.collegegram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class adminRegistration extends AppCompatActivity {

    Spinner depart_spinner;
    TextView login;
    String emailPattern="[a-zA-Z0-9._`]+@[a-z]+\\.+[a-z]+";

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);



        final EditText FullName = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);
        final EditText Password = findViewById(R.id.password);
        final EditText ConfirmPass = findViewById(R.id.confirmpass);
        final Button RegisterBtn = findViewById(R.id.signupbtn);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String FullNameTxt = FullName.getText().toString();
                final String DeptTxt=depart_spinner.getSelectedItem().toString();
                final String emailTxt = email.getText().toString();
                final String PasswordTxt = Password.getText().toString();
                final String ConfirmPassTxt = ConfirmPass.getText().toString();
                final int userType = 1;

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                if(FullNameTxt.isEmpty() || emailTxt.isEmpty()){
                    Toast.makeText(adminRegistration.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!emailTxt.matches(emailPattern)){
                    email.setError("Enter Correct Email");
                }
                else if(!PasswordTxt.equals(ConfirmPassTxt)){
                    Toast.makeText(adminRegistration.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.createUserWithEmailAndPassword(emailTxt,PasswordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                String uid = task.getResult().getUser().getUid();

                                Admins admin = new Admins(uid,FullNameTxt,DeptTxt,emailTxt,PasswordTxt,1);
                                firebaseDatabase.getReference().child("user").child(uid).setValue(admin);


                                Intent in = new Intent(adminRegistration.this, adminLogin.class);
                                startActivity(in);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminRegistration.this, adminLogin.class);
                startActivity(intent);
            }
        });
        depart_spinner=findViewById(R.id.depart_spinner);
        ArrayList<String> branch=new ArrayList<>();
        branch.add(0,"Department");
        branch.add("Computer");
        branch.add("EnTC");
        branch.add("Civil");
        branch.add("Mechanical");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(adminRegistration.this,
                R.layout.branchspinner,branch);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        depart_spinner.setAdapter(adapter);
        depart_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Department")){

                }else {
                    String value = parent.getItemAtPosition(position).toString();
                    Toast.makeText(adminRegistration.this, value, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}