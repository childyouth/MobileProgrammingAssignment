package com.example.mobileprogrammingassignment;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class GameActivity extends AppCompatActivity {

    Button btn_reverse;

    Switch[] switchs = new Switch[12];
    int[] switch_ids = {R.id.switch1,R.id.switch2,R.id.switch3,
            R.id.switch4,R.id.switch5,R.id.switch6,
            R.id.switch7,R.id.switch8,R.id.switch9,
            R.id.switch10,R.id.switch11,R.id.switch12};
    int[] switch_trigger = {1,2,4,7,11,8,9,5,10,6,3,0};//0~11

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btn_reverse = findViewById(R.id.btn_reverse);
        for(int i = 0;i<12;i++){
            switchs[i] = findViewById(switch_ids[i]);
            switchs[i].setOnClickListener(onClickListener);
        }
        btn_reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i<12;i++){
                    switchs[i].setChecked(!switchs[i].isChecked());
                }
                isGameEnd();
            }
        });
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int viewID = v.getId();
            for(int i = 0;i<12;i++){
                if(switch_ids[i] == viewID){
                    switchs[switch_trigger[i]].setChecked(!switchs[switch_trigger[i]].isChecked());
                    break;
                }
            }
            isGameEnd();
        }
    };
    public void isGameEnd(){
        for(int i = 0;i<12;i++){
            if(!switchs[i].isChecked()){
                return;
            }
        }
        makeAlert("모든 스위치를 켰습니다. 축하합니다.");
    }

    public void makeAlert(String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(message);
        alert.setPositiveButton("확인",null);
        alert.show();
    }
}
