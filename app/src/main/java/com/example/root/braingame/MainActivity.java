package com.example.root.braingame;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.root.braingame.DBHelper.ANSWER;
import static com.example.root.braingame.DBHelper.CHECK;
import static com.example.root.braingame.DBHelper.TABLE_ANSWERS;
import static com.example.root.braingame.DBHelper.TABLE_TASKS;
import static com.example.root.braingame.DBHelper.TASK_ID;
import static com.example.root.braingame.DBHelper.TASK_TEXT;

public class MainActivity extends AppCompatActivity {

    public  static int level_int;
    SignFragment signFragment;
    LevelFragment levelFragment;
    RegisterFragment registerFragment;
    FragmentTransaction fragmentTransaction;
    DBHelper dbh;
    SQLiteDatabase db;
    TasksFragment tasksFragment;
    public static int location=0;
    public static SharedPreferences BRAIN_GAME_INFO;
    public static String SHARED_NAME = "BRAIN_GAME_INFO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(BRAIN_GAME_INFO == null)BRAIN_GAME_INFO = getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        signFragment = new SignFragment();
        levelFragment = new LevelFragment();
        registerFragment = new RegisterFragment();
        tasksFragment = new TasksFragment();
        if(BRAIN_GAME_INFO.getString("login","").equals("")){
            switchFragments(0);
        }else switchFragments(1);
        dbh = new DBHelper(this);
        db = dbh.getWritableDatabase();

    }
    public  void switchFragments(int i){
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        switch(i){
            case 0:
                fragmentTransaction.replace(R.id.fragment_container,signFragment);
                break;
            case 1:
                fragmentTransaction.replace(R.id.fragment_container,levelFragment);
                break;
            case 2:
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment_container,registerFragment);
                break;
            case 3:
                fragmentTransaction.replace(R.id.fragment_container,new TasksFragment());
                if(location==0)fragmentTransaction.addToBackStack(null);
                location=1;
                break;
        }
        fragmentTransaction.commit();
    }
    public String getPassword(String name){

        String table = "users";
        String[] columns = {"login", "password"};
        String selection = "login =?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query("users",null,"login =?",selectionArgs,null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount()==0)return null;
        return cursor.getString(cursor.getColumnIndex("password"));
    }
    public boolean register(String login , String password){
        String table = "users";
        String[] columns = {"login", "password"};
        String selection = "login =?";
        String[] selectionArgs = {login};
        Cursor cursor = db.query("users",null,"login =?",selectionArgs,null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0) Toast.makeText(this,"Such user exist",Toast.LENGTH_SHORT).show();
        else{
        ContentValues cv = new ContentValues();
        cv.put("login", login);
        cv.put("password", password);
        db.insert("users", null, cv);
        switchFragments(1);}
        return true;
    }
    public ArrayList<String> getTasks(){
        ArrayList<String> data = new ArrayList<>();
        String table = "users";
        String[] columns = {"login", "password"};
        String selection = "login =?";
        String[] selectionArgs = {level_int+""};
        Cursor c = db.query(TABLE_TASKS,null,"level =?",selectionArgs,null,null,"_id");
        if(c.moveToFirst()){
            do{
                String task_text = c.getString(c.getColumnIndex(TASK_TEXT));
                data.add(task_text);
            }while(c.moveToNext());
        }
        c.close();
        Log.d("Log",data.size()+"");
        return data;
    }
    public ArrayList<Answer> getAnswers(){
        ArrayList<Answer> data = new ArrayList<>();
        String[] selectionArgs = {level_int+""};
        Cursor c = db.query(TABLE_ANSWERS,null,"level =?",selectionArgs,null,null,"task_id");
        if(c.moveToFirst()){
            do{
                int task_id =  c.getInt(c.getColumnIndex(TASK_ID));
                String answer_text = c.getString(c.getColumnIndex(ANSWER));
                boolean check=false;
                if(c.getInt(c.getColumnIndex(CHECK))==1)check=true;
                data.add(new Answer(task_id,answer_text,check));
            }while(c.moveToNext());
        }
        c.close();
        Log.d("Log",data.size()+"");
        return  data;
    }


}
