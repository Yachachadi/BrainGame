package com.example.root.braingame;

/**
 * Created by root on 5/8/17.
 */

public class Answer {

    public int task_id;
    public String text;
    public boolean check;

    public Answer(int task_id,String text , boolean check){
        this.task_id=task_id;
        this.text=text;
        this.check=check;
    }

}
