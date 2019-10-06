package com.example.mobileprogrammingassignment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView id_field,pw_field;
    Button btn_login,btn_go_register;

    DbOpenHelper mDbOpenHelper;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_field = findViewById(R.id.editText);
        pw_field = findViewById(R.id.editText2);
        btn_login = findViewById(R.id.btn_login);
        btn_go_register = findViewById(R.id.btn_go_register);

        mDbOpenHelper = new DbOpenHelper(context);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbOpenHelper.open();
                mDbOpenHelper.create();
                boolean login_ok = false;
                String id = id_field.getText().toString();
                String pw = pw_field.getText().toString();
                Cursor cursor = mDbOpenHelper.searchID(id);
                cursor.moveToFirst();
                if((cursor != null) && (cursor.getCount() != 0)){
                    String db_id = cursor.getString(cursor.getColumnIndex(Database.DB.USERID));
                    String db_pw = cursor.getString(cursor.getColumnIndex(Database.DB.USERPW));
                    if(db_id.equals(id) && db_pw.equals(pw)) { // pw 암호화해야함
                        login_ok = true;
                    }
                }
                cursor.close();
                mDbOpenHelper.close();
                if(login_ok){
                    Intent intent = new Intent(getApplicationContext(),GameActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    makeAlert(context,"ID 혹은 PW가 없거나 틀립니다.");
                }
            }
        });
        btn_go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegistActivity.class);
                startActivity(intent);
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