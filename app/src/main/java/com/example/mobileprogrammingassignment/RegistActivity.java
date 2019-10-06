package com.example.mobileprogrammingassignment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.regex.Pattern;

public class RegistActivity extends AppCompatActivity {

    EditText id_field,pw_field,name_field,phone_field,address_field;
    TextView tv_pw_check;
    Button btn_id_check, btn_register;
    RadioGroup rdogrp;
    RadioButton rdobtn_yes, rdobtn_no;

    DbOpenHelper mDbOpenHelper;

    Context context = this;

    boolean id_ok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbOpenHelper = new DbOpenHelper(this);

        setContentView(R.layout.activity_regist);
        id_field = findViewById(R.id.id_field);
        pw_field = findViewById(R.id.pw_field);
        name_field = findViewById(R.id.name_field);
        phone_field = findViewById(R.id.phone_field);
        address_field = findViewById(R.id.address_field);
        tv_pw_check = findViewById(R.id.tv_pw_check);
        btn_id_check = findViewById(R.id.btn_id_check);
        btn_register = findViewById(R.id.btn_register);
        rdogrp = findViewById(R.id.rdogrp);
        rdobtn_no = findViewById(R.id.rdobtn_no);
        rdobtn_yes = findViewById(R.id.rdobtn_yes);
        btn_id_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbOpenHelper.open();
                mDbOpenHelper.create();
                String new_id = id_field.getText().toString();
                new_id = new_id.trim();
                if(new_id.length() == 0) {
                    id_ok = false;
                    mDbOpenHelper.close();
                    return;
                }
                Cursor cursor = mDbOpenHelper.searchID(new_id);
                cursor.moveToFirst();
                id_ok = true;
                int cursorPos = 0;
//                while(!cursor.moveToPosition(cursorPos+1) && (cursor != null) && (cursor.getCount() != 0)){
//                    cursor.moveToPosition(cursorPos);
//                    String dbUserID = cursor.getString(cursor.getColumnIndex(Database.DB.USERID));  // 다른방법 있나?
//                    if(dbUserID.trim().equals(new_id)){
//                        id_ok = false;
//                        break;
//                    }
//                }
                if(!cursor.moveToPosition(cursorPos+1) && (cursor != null) && (cursor.getCount() != 0)){
                    id_ok = false;
                }
                if(id_ok){
                    makeAlert(context,"사용가능한 아이디입니다.");
                }
                else{
                    makeAlert(context,"이미 사용중인 아이디입니다.");
                }
                cursor.close();
                mDbOpenHelper.close();
            }
        });
        pw_field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String pw = pw_field.getText().toString();
                    if(Pattern.matches("^(?=.*[!@#$%\\^&*])(?=\\S+$).{6,15}$", pw)){
                        tv_pw_check.setTextColor(Color.BLUE);
                        tv_pw_check.setText("비밀번호를 사용할 수 있습니다.");
                    }
                    else{
                        tv_pw_check.setTextColor(Color.BLACK);
                        tv_pw_check.setText(R.string.regist_pw_check);
                    }
                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!id_ok){
                    makeAlert(context,"아이디 중복확인을 완료해주세요.");
                }
                else if(rdogrp.getCheckedRadioButtonId() == rdobtn_no.getId()){
                    makeAlert(context,"약관에 동의해 주세요.");
                }
                else{
                    mDbOpenHelper.open();
                    mDbOpenHelper.create();
                    mDbOpenHelper.insertColumn(id_field.getText().toString(),pw_field.getText().toString(),
                            name_field.getText().toString(),phone_field.getText().toString(),address_field.getText().toString());
                    mDbOpenHelper.close();
                    finish();
                }
            }
        });
    }
    public void makeAlert(Context context, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage(message);
        alert.setPositiveButton("확인",null);
        alert.show();
    }
}
