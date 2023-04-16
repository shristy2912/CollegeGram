package com.codewithshristy.collegegram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adminRegistration extends AppCompatActivity {

    Spinner depart_spinner;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);
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