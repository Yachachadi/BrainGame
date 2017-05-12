package com.example.root.braingame;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.root.braingame.MainActivity.BRAIN_GAME_INFO;
import static com.example.root.braingame.MainActivity.level_int;

/**
 * Created by root on 5/5/17.
 */

public class TasksFragment extends Fragment implements View.OnClickListener {
    TableLayout tl;
    TextView task_text;
    TextView goodjob,scoreText;
    ArrayList<String> tasks;
    ArrayList<Answer> answers;
    Button btnA,btnB,btnC;
    Button[] btns = new Button[3];
    int score=0;
    int counter;
    int current_task;
    int location=0;
    View dialogView;
    ProgressBar scoreBar;
    AlertDialog.Builder builder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_fragment,null);

        dialogView = View.inflate(getActivity(), R.layout.score_dialog, null);
        scoreBar = (ProgressBar)dialogView.findViewById(R.id.barScore);
        goodjob = (TextView)dialogView.findViewById(R.id.goodjob);
        scoreText = (TextView)dialogView.findViewById(R.id.textScore);
        builder= new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        DialogInterface.OnClickListener onClickListener =  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Log",i+"");
                if(i==-1){
                    ((MainActivity)getActivity()).switchFragments(3);
                }
                else if(i==-2 && level_int!=3){
                    level_int++;
                    ((MainActivity)getActivity()).switchFragments(3);
                }
                else{
                    ((MainActivity)getActivity()).switchFragments(1);
                }
            }
        };
        if(level_int!=3){
            builder.setNegativeButton("next level",onClickListener);
            builder.setNeutralButton("Main page",onClickListener);
            builder.setPositiveButton("Try again",onClickListener);}
        else{
            builder.setNeutralButton("Main page",onClickListener);
            builder.setPositiveButton("Try again",onClickListener);
        }
        builder.create();




        btnA = (Button)view.findViewById(R.id.btnA);
        btnB = (Button)view.findViewById(R.id.btnB);
        btnC = (Button)view.findViewById(R.id.btnC);
        btns[0] = btnA;
        btns[1] = btnB;
        btns[2] = btnC;
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        task_text = (TextView)view.findViewById(R.id.task_text);
        tasks = new ArrayList<>();
        counter=1;
        current_task=0;
        Log.d("Log","OnCreateFragment");
        tl = (TableLayout)view.findViewById(R.id.table);
        for(int i = 0 ; i<4; i++){
            for(int j = 0 ; j<3; j++){
                TableRow tr = (TableRow)tl.getChildAt(i);
                TextView btn = (TextView)tr.getChildAt(j);
                btn.setText(counter+"");
                counter++;
            }
        }
        tasks=((MainActivity)getActivity()).getTasks();
        answers= ((MainActivity)getActivity()).getAnswers();
        btnA.setText(answers.get(0).text);
        btnB.setText(answers.get(1).text);
        btnC.setText(answers.get(2).text);
        task_text.setText(tasks.get(0));
        task_text.setMovementMethod(new ScrollingMovementMethod());
        return  view;
    }

    @Override
    public void onClick(View view) {
        int i=(int)(current_task)/3;
        int j=(int)(current_task)%3;
        TableRow tr = (TableRow)tl.getChildAt(i);
        TextView btn = (TextView)tr.getChildAt(j);








        switch (view.getId()){
            case R.id.btnA:
                if(answers.get(current_task*3-(current_task)%3).check){
                    btn.setBackgroundColor(Color.GREEN);
                    score++;
                }
                else btn.setBackgroundColor(Color.RED);
                break;
            case R.id.btnB:
                if(answers.get(current_task*3+2-(current_task+1)%3).check){
                    btn.setBackgroundColor(Color.GREEN);
                    score++;
                }
                else btn.setBackgroundColor(Color.RED);
                break;
            case R.id.btnC:
                if(answers.get(current_task*3+1-(current_task+2)%3).check){
                    btn.setBackgroundColor(Color.GREEN);
                    score++;
                }
                else btn.setBackgroundColor(Color.RED);
                break;
        }



            current_task++;
        if(current_task>=12){
            goodjob.setText("Good Job "+ BRAIN_GAME_INFO.getString("login","")+" !");
            scoreText.setText(score+"/12");
            scoreBar.setProgress((int)(score*100/12));
            builder.show();}else {
            task_text.setText(tasks.get(current_task));
            btns[current_task%3].setText(answers.get(current_task*3).text);
            btns[(current_task+1)%3].setText(answers.get(current_task*3+1).text);
            btns[(current_task+2)%3].setText(answers.get(current_task*3+2).text);}





    }
}
