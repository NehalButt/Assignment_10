package com.example.assignment_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText Loginemail,Loginpassword;
    TextView Gotosignup;
    Button Login;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Loginemail = (EditText) findViewById(R.id.Loginemail);
        Loginpassword = (EditText) findViewById(R.id.Loginpassword);
        Gotosignup = (TextView) findViewById(R.id.gotosignup);
        dbhelper = new Dbhelper(Login.this);
        Login = (Button) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginemail = Loginemail.getText().toString();
                String loginpassword = Loginpassword.getText().toString();

                if (loginemail.length() == 0 && loginpassword.length() == 0){
                    Loginemail.requestFocus();
                    Loginpassword.requestFocus();
                    Loginemail.setError("Enter Email address");
                    Loginpassword.setError(" Password must be 8 Character Long");
                }
                        boolean logincheck = dbhelper.logincheck(loginemail, loginpassword);
                        if (logincheck == true) {
                            Intent i = new Intent(Login.this, Idea.class);
                            Toast.makeText(Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                            i.putExtra("myemail" , loginemail);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
        });
        Gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this , Registration.class);
                startActivity(intent);
            }
        });
    }
}