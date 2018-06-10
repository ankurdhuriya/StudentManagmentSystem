package com.epizy.ankurdhuriya.studentmanagmentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class Student {
    String studentName, address, collegeName;
    long number;

    Student(String studentName, long number, String address, String collegeName) {
        this.studentName = studentName;
        this.number = number;
        this.address = address;
        this.collegeName = collegeName;
    }
}

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtStudentName, txtAddress, txtNumber;
    TextView txtView;
    ArrayList<Student> std = new ArrayList<Student>();
    Button add, display;
    String collegeName = "";

    Spinner spin;

    String[] collegeNames={"Select college name","DIT","GEra","UPES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStudentName = (EditText)findViewById(R.id.edit_txt1);
        txtNumber = (EditText)findViewById(R.id.edit_txt2);
        txtAddress = (EditText)findViewById(R.id.edit_txt3);
        //txtCollege = (EditText)findViewById(R.id.edit_txt4);
        txtView = (TextView) findViewById(R.id.txt_view2);

        spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, collegeNames);

        spin.setAdapter(arrayAdapter);

        spin.setPrompt(collegeNames[0]);

        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = String.valueOf(txtStudentName.getText());
                String address = String.valueOf(txtAddress.getText());

                try {
                    long number = Integer.parseInt(txtNumber.getText().toString());
                    std.add(new Student(studentName, number, address, collegeName));
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Exception occured.", Toast.LENGTH_LONG).show();
                }
            }
        });

        display = (Button)findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( !(std.isEmpty()) ) {
                    String str = "";
                    for (int i=0; i<std.size(); i++ )
                    {
                        str = str + ("Name : " + std.get(i).studentName + "\n" + "Number : " + std.get(i).number + "\n" + "Address : "
                                + std.get(i).address + "\n" + "College : " + std.get(i).collegeName + "\n");
                        str = str + ("#######################################\n");
                    }
                    txtView.setText(str);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No details found.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        collegeName = collegeNames[position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    */
}
