package com.example.assignment_10;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    String Databasename = "Dukankholo.db";
    public Dbhelper(@Nullable Context context) {
        super(context, "Dukankholo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table Userdetail(Id integer primary key autoincrement , Email text , Password text)");
        sqLiteDatabase.execSQL("Create Table Startupdetail(Id integer primary key autoincrement , Startupname text , Amount Integer , Person Integer , Idea text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("Drop Table If Exists Userdetail");
    sqLiteDatabase.execSQL("Drop Table If Exists Startupdetail");
    }
    public boolean userdetailinsert(String Email , String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userinsert = new ContentValues();
        userinsert.put("Email" , Email);
        userinsert.put("Password" , Password);
        long userresult = db.insert("Userdetail" , null , userinsert);
        if (userresult > 0 ){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean startupdetailinsert(String Startupname , Integer Amount , Integer Person, String Idea){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues startupinsert = new ContentValues();
        startupinsert.put("Startupname" , Startupname);
        startupinsert.put("Amount" , Amount);
        startupinsert.put("Person" , Person);
        startupinsert.put("Idea" , Idea);
        long Startupresult = db.insert("Startupdetail" , null , startupinsert);
        if (Startupresult > 0 ){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean emailcheck(String Emailcheck){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor emailcheck = db.rawQuery("Select * From Userdetail Where Email= ?" , new String[]{Emailcheck});
        if (emailcheck.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean Startupname(String startupname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor startupcheck = db.rawQuery("Select * From Startupdetail Where Startupname= ?" , new String[]{startupname});
        if (startupcheck.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean logincheck(String loginemail , String loginpassword){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor logincheck = db.rawQuery("Select * From Userdetail Where Email= ? And Password = ?" , new String[]{loginemail , loginpassword});
        if (logincheck.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
