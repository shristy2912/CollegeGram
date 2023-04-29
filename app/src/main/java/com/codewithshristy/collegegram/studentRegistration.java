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

public class studentRegistration extends AppCompatActivity {

    Spinner branch_spinner;
    Spinner year_spinner;
    TextView login;
    String emailPattern="[a-zA-Z0-9._`]+@[a-z]+\\.+[a-z]+";

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

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
                final int userType = 0;

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                if(FullNameTxt.isEmpty() || emailTxt.isEmpty()){
                    Toast.makeText(studentRegistration.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!regNoTxt.matches("^[0-9]{6}$")){
                    if(regNoTxt.length()>6){
                        RegNo.setError("Enter six digits only");
                    }else{
                        RegNo.setError("Enter only digits");
                    }
                }else if(!emailTxt.matches(emailPattern)){
                    email.setError("Enter Correct Email");
                }
                else if(!PasswordTxt.equals(ConfirmPassTxt)){
                    Toast.makeText(studentRegistration.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{

                    firebaseAuth.createUserWithEmailAndPassword(emailTxt,PasswordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                String uid = task.getResult().getUser().getUid();

                                Students student = new Students(uid,FullNameTxt,branchTxt,yearTxt,regNoTxt,emailTxt,PasswordTxt,0);
                                firebaseDatabase.getReference().child("user").child(uid).setValue(student);

                                Intent in = new Intent(studentRegistration.this, studentLogin.class);
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