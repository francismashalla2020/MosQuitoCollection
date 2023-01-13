package com.thinkbold.mosquitocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class AllEntrySubmit extends AppCompatActivity {
    String submitmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_entry_submit);

        ImageView imageBack=findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitmessage = getResources().getString(R.string.smessage);
    }



    public void buttonClicked(View view) {
        switch (view.getId()){
            case R.id.buttonSave:
                Toast.makeText(this, submitmessage, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonAnother:
                startActivity(new Intent(getApplicationContext(), PreliminaryData.class));
                break;
            default:
        }
    }
}