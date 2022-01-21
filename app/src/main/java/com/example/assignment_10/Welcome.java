package com.example.assignment_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Welcome extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle hamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationview);

        hamburger = new ActionBarDrawerToggle(Welcome.this , drawer ,toolbar ,R.string.open , R.string.close);
        drawer.addDrawerListener(hamburger);
        hamburger.syncState();
        Intent intent = getIntent();
        String toolbartitle =intent.getStringExtra("Startupname");
        toolbar.setTitle(toolbartitle);
//        String useremail =intent.getStringExtra("myuseremail");
//        loginuseremail.setText();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Logout:
                        AlertDialog.Builder Alert = new AlertDialog.Builder(Welcome.this);
                        Alert.setTitle("Exit");
                        Alert.setMessage("Are You Sure ? ");
                        Alert.setIcon(R.drawable.ic_logout_line);
                        Alert.setNegativeButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Welcome.this , Login.class);
                                startActivity(intent);
                            }
                        });
                        Alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        Alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        Alert.show();
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}