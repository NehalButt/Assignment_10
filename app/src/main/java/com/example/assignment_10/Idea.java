package com.example.assignment_10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Idea extends AppCompatActivity {
    EditText Startupname,Amount,Person,Idea;
    Button Start;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea);

        Startupname = (EditText) findViewById(R.id.Startupname);
        Amount = (EditText) findViewById(R.id.Amount);
        Person = (EditText) findViewById(R.id.person);
        Idea = (EditText) findViewById(R.id.Idea);
        Start = (Button) findViewById(R.id.Start);
        dbhelper = new Dbhelper(Idea.this);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(Idea.this);
//                alert.setTitle("Confirmation");
//                alert.setMessage("Are You Sure");
//                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
                        String startupname = Startupname.getText().toString();
                        int amount = Integer.parseInt(Amount.getText().toString());
                        int person = Integer.parseInt(Person.getText().toString());
                        String idea = Idea.getText().toString();
                        if (startupname.isEmpty() == true){
                            Startupname.requestFocus();
                            Startupname.setError("Required");
                        }
                        else  if (!startupname.matches("[a-zA-Z]+")){
                            Startupname.requestFocus();
                            Startupname.setError("Only Alphabets Allow");
                        }
                        else  if (amount == 0){
                            Amount.requestFocus();
                            Amount.setError("Amount must be 25000 to 100000");
                        }
                        else if(amount <= 25000){
                    Amount.requestFocus();
                    Amount.setError("Amount must be 25000");
                        }
                        else  if (person == 0){
                            Person.requestFocus();
                            Person.setError("Required");
                        }
                        else  if (person <= 10){
                            Person.requestFocus();
                            Person.setError("Only 10 person allow");
                        }
                        else  if (idea.isEmpty() == true){
                            Idea.requestFocus();
                            Idea.setError("Required");
                        }
                        else {
                            boolean startupcheck = dbhelper.Startupname(startupname);
                            if (startupcheck == true) {
                                Startupname.requestFocus();
                                Startupname.setError("Starup name Already Exists");
                            } else {
                                boolean successfullyideainserted = dbhelper.startupdetailinsert(startupname, amount, person, idea);
                                if (successfullyideainserted == true) {
                                    Toast.makeText(Idea.this, "Start your Business", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Idea.this, Welcome.class);
                                    intent.putExtra("Startupname", startupname);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
//                });
//                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//                alert.show();
//                }
        });
    }
}