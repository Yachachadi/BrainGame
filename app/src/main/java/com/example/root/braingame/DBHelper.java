package com.example.root.braingame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 5/3/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context ctx){
        super(ctx, "braingame", null, 1);
    }



    ArrayList<HashMap<String,String>> answersList;
    static String TABLE_TASKS = "tasks";
    static String KEY_ID = "_id";
    static String TASK_TEXT = "task_text";

    static String TABLE_ANSWERS = "answers";
    static String ANSWER = "answer";
    static String TASK_ID = "task_id";
    static String CHECK = "right";
    static String TASK_LEVEL = "level";
    private static final String CREATE_TABLE_TASKS = "CREATE TABLE "
            + TABLE_TASKS + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement," +TASK_TEXT+ " text ,"+TASK_LEVEL
            +" integer" + ")";
    private static final String CREATE_TABLE_ANSWERS = "CREATE TABLE "
            + TABLE_ANSWERS + " ( "
            + KEY_ID
            + " INTEGER PRIMARY KEY autoincrement,"
            +ANSWER
            + " text,"
            + CHECK + " INTEGER,"
            +TASK_ID+ " INTEGER,"
            +TASK_LEVEL+" integer"
            +")";
    @Override
    public void onCreate(SQLiteDatabase db) {
        answersList = new ArrayList<>();
        db.execSQL("create table users(" +
                "_id integer primary key autoincrement," +
                "login text unique," +
                "password text)"
        );
        db.execSQL(CREATE_TABLE_TASKS);
        db.execSQL(CREATE_TABLE_ANSWERS);

        String[] tasks_texts1 = {
                "what is 5 +5 ?"
                , "what is 93 +10 ?"
                , "what is 523 +45 ?"
                , "what is 52 +45 ?"
                , "what is 42 +15 ?"
                , "what is 42 +54 ?"
                , "what is 14 +45 ?"
                , "what is 12 +145 ?"
                , "what is 51 +45 ?"
                , "what is 52 +42 ?"
                , "what is 53 +45 ?"
                , "what is 25 +23 ?"
                ,"Самая маленькая птица в мире – колибри, делает 80 взмахов крыльев в секунду. Сколько взмахов за час сделает эта птичка?"
                ,"400 лет требуется для образования 1 см плодородного слоя почвы. Сколько лет нужно чтобы этот слой достиг 20-ти сантиметровой толщины?"
                ,"2 тонны нефти могут загрязнить 24 кв. км поверхности океана. За последний год в океан попало около 8 миллионов тон нефти. Сколько квадратных километров морской поверхности будет загрязнено за 3 таких года?"
                ,"Сколько зерна съедят за зиму 30 хомяков, если каждый из них съедает за зиму 800 грамм?"
                ,"Длина Земной окружности составляет 40 000 км. Какое количество суток понадобилось бы пешеходу, задумавшему совершить кругосветное путешествие, если каждые сути он бы преодолевал 25 км?"
                ,"В лесу было разорено 3 муравейника, муравьи каждого из которых за сутки могут поймать 15 000 насекомых. Вычисли количество насекомых, которые не были пойманы муравьями за 1 сутки."
                ,"Такое растение, как лишайник, вырастает в год, в среднем, на 8 мм. Прожить некоторые из них могут до 80 лет. На сколько сантиметров вырастет восьмидесятилетний лишайник?"
                ,"Студенты собирали свеклу 3 дня и собрали 3888 кг. В первый день третью часть, во второй на 672 кг больше, чем в первый. Вычисли количество моркови, собранное в третий день."
                ,"Спортсменов, прибывших на соревнования, построили в колонну, в которой в каждом ряду 15 спортсменов. Сколько рядов в колонне, если прибыло 2 группы по 67 спортсменов, 4 по 40 спортсменов и 3 по 62 спортсмена?"
                ,"В каждом ряду актового зала школы по 25 стульев. Сколько рядов нужно, что бы разместить: 4 класса по 26 учащихся, 3 класса по 27 учащихся, 5 классов по 28 учащихся?"
                ,"В строке 8 слов, на странице 33 таких строки, в рассказе 25 страниц. Сколько в рассказе слов?"
                ,"Для производства 50 изделий определенного образца предприятие должно затратить 9 00 000 рублей. После доработки технологии стоимость изготовления одного изделия уменьшилась на 3000 руб. Сколько изделий на ту же сумму сможет выпустить предприятие после доработки технологии?"
                ,"Сколько трехзначных чисел можно составить из 4 цифр: 1, 2, 3, 4?"
                ,"Сколько четырехзначных чисел можно составить из 4 цифр: 1, 2, 3, 4?"
                ,"Сколько семизначных чисел можно составить из 7 цифр: 1; 1; 2; 2; 2; 3; 4?"
                ,"Сколько трехзначных чисел, в которых цифры не повторяются, можно составить из 4 цифр: 1, 2, 3, 4?"
                ,"Сколько четырехзначных чисел, в которых цифры не повторяются, можно составить из 4 цифр: 1, 2, 3, 4?"
                ,"Сколько трехэлементных подмножеств, различающихся хотя бы одним элементом друг от друга и без учета порядка в подмножестве, можно составить из 4 цифр: 1, 2, 3, 4?"
                ,"Сколькими способами можно выбрать 6 предметов из 49?"
                ,"Сколько будет костей в игре домино, если использовать, только четыре цифры 1, 2, 3, 4?"
                ,"Сколько четырехзначных чисел можно составить из 9 цифр: 1, 2, 3, 4, 5, 6, 7, 8, 9?"
                ,"В чемпионате участвует 12 команд. Сколькими различными способами могут быть распределены три различные медали?"
                ,"В семье 6 человек. За столом 6 стульев. В семье решили каждый вечер рассаживаться на эти 6 стульев по-новому. Сколько дней члены семьи смогут делать это без повторений?\n"
                ,"Сколькими способами из класса, в котором учатся 30 школьников, можно выбрать капитана команды для математических соревнований и его заместителя?"
        };
        for(int j = 0;j<tasks_texts1.length;j++){
            String task = tasks_texts1[j];
            ContentValues cv = new ContentValues();
            Log.d("Log","insert");
            cv.put(TASK_TEXT,task);
            if(j<12)cv.put(TASK_LEVEL,1);
            else if(j<24)cv.put(TASK_LEVEL,2);
            else if(j<36)cv.put(TASK_LEVEL,3);
            db.insert(TABLE_TASKS,null,cv);
        }
        String[] answers = {
                "10", "11", "9",
                "103", "104", "102",
                "578", "579", "577",
                "97", "98", "96",
                "57", "58", "56",
                "96", "97", "95",
                "59", "60", "58",
                "157", "158", "156",
                "96", "97", "95",
                "94", "95", "93",
                "98", "99", "97",
                "48", "49", "47",
                "288000","278000","268000",
                "8000","6000","4000",
                "288000000","278000000","268000000",
                "24000","16000","18000",
                "1600","2000","1800",
                "45000","55000","35000",
                "64","56","60",
                "1920","1968","1296",
                "32","64","70",
                "13","16","18",
                "6398","6388","6378",
                "60","50","30",
                "64","56","72",
                "256","240","264",
                "1260","1200","1300",
                "24","30","45",
                "24","45","20",
                "4","12","36",
                "13980000","23980000","33980000",
                "10","45","60",
                "6561","10000","6000",
                "1320","1256","9000",
                "720","640","800",
                "870","900","840",
        };
        for(int i = 0 ;i< answers.length;i++){
            HashMap<String,String> map = new HashMap<>();
            map.put("answer",answers[i]);
            map.put("task_id",i/3+"");
            if(i<36) map.put(TASK_LEVEL,1+"");
            else if(i<72) map.put(TASK_LEVEL,2+"");
            else if(i<108) map.put(TASK_LEVEL,3+"");
            if(i%3==0) map.put("right",1+"");
            else map.put("right",0+"");

            answersList.add(map);

        }
        for(HashMap m: answersList){
            ContentValues cv = new ContentValues();
            cv.put("answer",(String)m.get("answer"));
            cv.put("task_id",(String)m.get("task_id"));
            cv.put("right",(String)m.get("right"));
            cv.put(TASK_LEVEL,(String)m.get(TASK_LEVEL));
            db.insert(TABLE_ANSWERS,null,cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}