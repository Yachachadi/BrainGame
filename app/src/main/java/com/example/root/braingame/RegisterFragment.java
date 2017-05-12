package com.example.root.braingame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.root.braingame.MainActivity.BRAIN_GAME_INFO;

/**
 * Created by root on 5/3/17.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener{


    Button register;
    EditText login,password,password2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment,null);
        register = (Button)view.findViewById(R.id.register);
        login = (EditText)view.findViewById(R.id.login_edit_text);
        password = (EditText)view.findViewById(R.id.password_edit_text);
        password2 = (EditText)view.findViewById(R.id.password_edit_text2);
        register.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                Log.d("Log","Click Register");
                if(login.getText().toString().equals(""))Toast.makeText(getActivity(),"Login is empty",Toast.LENGTH_SHORT).show();
                else if(password.getText().toString().equals(""))Toast.makeText(getActivity(),"Password is empty",Toast.LENGTH_SHORT).show();
                else if(password.getText().toString().equals(password2.getText().toString())){
                    BRAIN_GAME_INFO.edit().putString("login",login.getText().toString()).commit();
                    BRAIN_GAME_INFO.edit().putString("password",password.getText().toString()).commit();
                ((MainActivity)getActivity()).register(login.getText().toString(),password.getText().toString());}
                else Toast.makeText(getActivity(), "Passwords aren't same", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
