package com.example.root.braingame;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class SignFragment  extends Fragment implements View.OnClickListener {

    Button login,register;
    EditText loginEditText,passwordEditText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_fragment,null);
        login = (Button)view.findViewById(R.id.login);
        register = (Button)view.findViewById(R.id.register);
        loginEditText = (EditText)view.findViewById(R.id.login_edit_text);
        passwordEditText = (EditText)view.findViewById(R.id.password_edit_text);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                String password =((MainActivity)getActivity()).getPassword(loginEditText.getText().toString());
                if(password==null) Toast.makeText(getActivity(),"Such user doesn't exist",Toast.LENGTH_SHORT).show();
                else if(password.equals(passwordEditText.getText().toString())){
                    BRAIN_GAME_INFO.edit().putString("login",loginEditText.getText().toString()).commit();
                    BRAIN_GAME_INFO.edit().putString("password",passwordEditText.getText().toString()).commit();
                    ((MainActivity)getActivity()).switchFragments(1);
                }else Toast.makeText(getActivity(),"Password wrong",Toast.LENGTH_SHORT).show();
                break;
            case R.id.register:
                ((MainActivity)getActivity()).switchFragments(2);
                break;
        }
    }
}
