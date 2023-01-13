package com.thinkbold.mosquitocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PartOne extends AppCompatActivity {

    RadioGroup aedes1, aedes2;
    DataSQLite controller = new DataSQLite(this);
    String serialno, aedes_1, aedes_2, error1, error2;
    int checkGroup1, checkGroup2;
    Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one);

        Bundle bundle = getIntent().getExtras();
        serialno = bundle.getString("serialno");

        aedes1 = findViewById(R.id.aedes1);
        aedes2 = findViewById(R.id.aedes2);
        proceed = findViewById(R.id.proceed_1);

        error1 = getResources().getString(R.string.error_1);
        error2 = getResources().getString(R.string.error_2);

        ImageView imageBack=findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startActivity(new Intent(getApplicationContext(), PartTwo.class));

                }
            });
        }

//    public void captureData(){
//        if ((aedes1.getCheckedRadioButtonId() + "ID").equalsIgnoreCase("-1ID")){
//            aedes_1 = "not specified";
//        }else {
//            aedes_1 = (String) ((RadioButton) findViewById(aedes1.getCheckedRadioButtonId())).getText();
//        }
//
//        if ((aedes2.getCheckedRadioButtonId() + "ID").equalsIgnoreCase("-1ID")){
//            aedes_2 = "not specified";
//        }else {
//            aedes_2 = (String) ((RadioButton) findViewById(aedes2.getCheckedRadioButtonId())).getText();
//        }
//    }
//
//    public void clickedButton(View view) {
//
//    }
}