package com.example.sunrin.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    AlertDialog.Builder builder;
    Button button1, button2, button3, button4, button5, button6;
    AlertDialog alertDialog;
    String[] item;
    int index;
    boolean choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        builder = new AlertDialog.Builder(this);
//        builder.setMessage("끌래요?");
//        builder.setTitle("On/off");
//        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        builder.setNegativeButton("아니오",null);
//        Button btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.create().show();
//            }
//        });
        button1 = findViewById(R.id.btn);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button1 || v == button2 || v == button3 || v == button4) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (v == button1) {
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("ON/OFF");
                builder.setMessage("끌래요?");
                builder.setNegativeButton("아니오", null);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("OK clicked");
                    }
                });
                builder.setCancelable(false);

            } else if (v == button2) {
                builder.setTitle("여러개~");
                item = new String[]{"어른", "청소년", "어린이", "유아"};
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(item[which] + "(이)가 선택되었습니다.");
                    }
                });
                builder.setNegativeButton("취소", null);

            } else if (v == button3) {
                builder.setTitle("알람 벨소리");
                builder.setSingleChoiceItems(R.array.dialog_array, index, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        index = which;
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] datas = getResources().getStringArray(R.array.dialog_array);
                        String s = datas[index];
                        showToast(s + "(이)가 선택되었습니다.");

                    }
                });
            } else if (v == button4) {
                choice = false;

                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.custom_dialog, null);

                builder.setView(view);
                CheckBox cb = view.findViewById(R.id.check);
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        choice = isChecked;
                        //Log.e("msg",isChecked+"");
                    }
                });
                builder.setTitle("알람 체크");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choice) {
                            showToast("체크됨!!");
                        } else if (!choice) {
                            showToast("체크안됨...");
                        }
                    }
                });
                builder.setNegativeButton("아니오", null);
            }
            alertDialog = builder.create();
            alertDialog.show();
        }else{
            if(v ==button5){
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        showToast(year+":"+(month+1)+":"+dayOfMonth);
                    }
                },year,month,day);
                dateDialog.show();
            }
            else if(v==button6){
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        showToast(hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        }
    }


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
