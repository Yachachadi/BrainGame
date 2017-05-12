package com.example.root.braingame;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.root.braingame.MainActivity.BRAIN_GAME_INFO;
import static com.example.root.braingame.MainActivity.level_int;
import static com.example.root.braingame.MainActivity.location;

/**
 * Created by root on 5/3/17.
 */

public class LevelFragment extends Fragment implements View.OnClickListener{
    Button easy,medium,hard,logout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.level_fragment,null);
        location = 0;
        easy = (Button)view.findViewById(R.id.easy);
        medium = (Button)view.findViewById(R.id.medium);
        hard = (Button)view.findViewById(R.id.hard);
        logout = (Button)view.findViewById(R.id.logout);
        easy.setOnClickListener(this);
        medium.setOnClickListener(this);
        hard.setOnClickListener(this);
        logout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.easy:
                level_int=1;
                ((MainActivity)getActivity()).switchFragments(3);
                break;
            case R.id.medium:
                level_int=2;
                ((MainActivity)getActivity()).switchFragments(3);
                break;
            case R.id.hard:
                level_int=3;
                ((MainActivity)getActivity()).switchFragments(3);
                break;
            case  R.id.logout:
                View dialogView = View.inflate(getActivity(), R.layout.logout_dialog, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(dialogView);
//                Button cancel = (Button)dialogView.findViewById(R.id.cancel);
                DialogInterface.OnClickListener onClickListener =  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==-1){
                            BRAIN_GAME_INFO.edit().clear().commit();
                            ((MainActivity)getActivity()).switchFragments(0);
                        }
                    }
                };
                builder.setNegativeButton("Cancel",onClickListener);
                builder.setPositiveButton("Ok",onClickListener);
                builder.create();
                builder.show();
                break;
        }
    }
}
