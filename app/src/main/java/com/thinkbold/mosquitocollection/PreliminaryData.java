package com.thinkbold.mosquitocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class PreliminaryData extends AppCompatActivity {

    Spinner districtNo, wardno, hamletNo, traptype;
    EditText houseno, nameofficer;
    Button buttonDate, proceed;
    String district_number, ward_no, hamlet_no, trap_type, o_name, h_number, d_date, d_dist, d_ward, d_hamlet, d_trap, serialno;
    DatePickerDialog datePickerDialog;
    Calendar cal;
    TextView selectedDate;
    DataSQLite controller = new DataSQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preliminary_data);

        districtNo = findViewById(R.id.districtNo);
        wardno = findViewById(R.id.wardno);
        hamletNo = findViewById(R.id.hamletNo);
        traptype = findViewById(R.id.traptype);
        houseno = findViewById(R.id.houseno);
        nameofficer = findViewById(R.id.nameofficer);
        buttonDate = findViewById(R.id.buttonDate);
        proceed = findViewById(R.id.proceed);
        selectedDate = findViewById(R.id.selectedDate);

        //DistrictNo spinner implementation
        final String[] district = getResources().getStringArray(R.array.dist_no);;
        ArrayAdapter<String> distadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, district);
        distadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //assigning adapter to spinner
        districtNo.setAdapter(distadapter);
        //add listener
        districtNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district_number = district[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //WardNo spinner implementation
        final String[] ward = getResources().getStringArray(R.array.ward_no);;
        ArrayAdapter<String> wardadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ward);
        wardadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //assigning adapter to spinner
        wardno.setAdapter(wardadapter);
        //add listener
        wardno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ward_no = ward[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //HamletNo spinner implementation
        final String[] hamlet = getResources().getStringArray(R.array.hamlet_no);;
        ArrayAdapter<String> hamadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hamlet);
        hamadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //assigning adapter to spinner
        hamletNo.setAdapter(hamadapter);
        //add listener
        hamletNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hamlet_no = hamlet[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Trap Type spinner implementation
        final String[] trap = getResources().getStringArray(R.array.trap_id);;
        ArrayAdapter<String> trapadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trap);
        trapadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //assigning adapter to spinner
        traptype.setAdapter(trapadapter);
        //add listener
        traptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                trap_type = trap[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //DatePicker Dialog
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                int mYear = cal.get(Calendar.YEAR);
                int mMonth = cal.get(Calendar.MONTH);
                int mDay = cal.get(Calendar.DAY_OF_MONTH);

                //datePickerDialog
                datePickerDialog = new DatePickerDialog(PreliminaryData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        ImageView imageBack=findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void clickedButton(View view) {
        switch (view.getId()){
            case R.id.proceed:
                final Dialog dialog = new Dialog(PreliminaryData.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.customdialog);
                Button buttonNo = dialog.findViewById(R.id.buttonNo);
                Button buttonYes = dialog.findViewById(R.id.buttonYes);
                buttonYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        o_name = nameofficer.getText().toString();
                        h_number = houseno.getText().toString();
                        d_date = selectedDate.getText().toString();
                        d_dist = district_number;
                        d_ward = ward_no;
                        d_hamlet = hamlet_no;
                        d_trap = trap_type;
                        Random rand = new Random();
                        int x = rand.nextInt(10000);
                        serialno = "SERIAL" + x;
                        HashMap<String, String> querryvalues = new HashMap<String, String>();
                        querryvalues.put("serialno", serialno);
                        querryvalues.put("o_name", o_name);
                        querryvalues.put("h_number", h_number);
                        querryvalues.put("d_date", d_date);
                        querryvalues.put("d_dist", d_dist);
                        querryvalues.put("d_ward", d_ward);
                        querryvalues.put("d_hamlet", d_hamlet);
                        querryvalues.put("d_trap", d_trap);
                        controller.insertDataInPreps(querryvalues);
                        Intent intent = new Intent(getApplicationContext(), PartOne.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("serialno", serialno);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
                dialog.show();

                buttonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }
}