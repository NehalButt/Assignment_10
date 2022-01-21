package com.example.assignment_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText Email,Password;
    TextView Gotologin;
    Button Register;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        Gotologin = (TextView) findViewById(R.id.Gotologin);
        Register = (Button) findViewById(R.id.Register);
        dbhelper = new Dbhelper(Registration.this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                if (email.length() == 0 && password.length() == 0){
                    Email.requestFocus();
                    Password.requestFocus();
                    Email.setError("Enter Email address");
                    Password.setError(" Password must be 4 Character Long");

                }
                else if (password.isEmpty()){
                    Password.requestFocus();
                    Password.setError(" Password must be 4 Character Long");
                }
                else if(email.matches("[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+")){
                    boolean checkedemail = dbhelper.emailcheck(email);
                    if (checkedemail == true){
                        Toast.makeText(Registration.this, "Email Already Exists", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        boolean successfullyinserted = dbhelper.userdetailinsert(email, password);
                        if (successfullyinserted == true) {
                            Intent intent = new Intent(Registration.this, Login.class);
                            Toast.makeText(Registration.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    else {
                            Toast.makeText(Registration.this, "Invalid data", Toast.LENGTH_SHORT).show();
                        }
                }
                }

                else {
                    Toast.makeText(Registration.this, "Invalid Email address", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this , Login.class);
                startActivity(intent);
            }
        });
    }
}