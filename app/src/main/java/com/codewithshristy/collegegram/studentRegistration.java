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


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class studentRegistration extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://collegegram-cd0e4-default-rtdb.firebaseio.com/");

    Spinner branch_spinner;
    Spinner year_spinner;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        final EditText FullName = findViewById(R.id.name);
        final EditText RegNo = findViewById(R.id.regno);
        final EditText email = findViewById(R.id.email);
        final EditText Password = findViewById(R.id.password);
        final EditText ConfirmPass = findViewById(R.id.confirmpass);
        final Button RegisterBtn = findViewById(R.id.signupbtn);

       RegisterBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String FullNameTxt = FullName.getText().toString();
               final String branchTxt=branch_spinner.getSelectedItem().toString();
               final String yearTxt=year_spinner.getSelectedItem().toString();
               final String regNoTxt = RegNo.getText().toString();
               final String emailTxt = email.getText().toString();
               final String PasswordTxt = Password.getText().toString();
               final String ConfirmPassTxt = ConfirmPass.getText().toString();

               if(FullNameTxt.isEmpty() || emailTxt.isEmpty()){
                   Toast.makeText(studentRegistration.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
               }
               else if(!PasswordTxt.equals(ConfirmPassTxt)){
                   Toast.makeText(studentRegistration.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
               }
               else{
                   databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.hasChild(regNoTxt)){
                               Toast.makeText(studentRegistration.this, "Already Registered", Toast.LENGTH_SHORT).show();
                           }
                           else{
                               databaseReference.child("users").child(regNoTxt).child("fullname").setValue(FullNameTxt);
                               databaseReference.child("users").child(regNoTxt).child("branch_spinner").setValue(branchTxt);
                               databaseReference.child("users").child(regNoTxt).child("year_spinner").setValue(yearTxt);
                               databaseReference.child("users").child(regNoTxt).child("email").setValue(emailTxt);
                               databaseReference.child("users").child(regNoTxt).child("password").setValue(PasswordTxt);

                               Toast.makeText(studentRegistration.this, "User Registered Successfully.", Toast.LENGTH_SHORT).show();
                               finish();
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });


               }
           }
       });


        branch_spinner = findViewById(R.id.branch_spinner);
        ArrayList<String> branch=new ArrayList<>();
        branch.add(0,"Branch");
        branch.add("Computer");
        branch.add("EnTC");
        branch.add("Civil");
        branch.add("Mechanical");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(studentRegistration.this,
               R.layout.branchspinner,branch);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch_spinner.setAdapter(adapter);
        branch_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Branch")){

                }else {
                    String value = parent.getItemAtPosition(position).toString();
                    Toast.makeText(studentRegistration.this, value, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        year_spinner=findViewById(R.id.year_spinner);
        ArrayList<String> years=new ArrayList<>();
        years.add(0,"Year");
        years.add("FE");
        years.add("SE");
        years.add("TE");
        years.add("BE");
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(studentRegistration.this,
                R.layout.yearspinner,years);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(adapter1);
        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Year")){

                }else {
                    String value = parent.getItemAtPosition(position).toString();
                    Toast.makeText(studentRegistration.this, value, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentRegistration.this, studentLogin.class);
                startActivity(intent);
            }
        });

    }
}