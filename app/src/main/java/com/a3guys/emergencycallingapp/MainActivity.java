package com.a3guys.emergencycallingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mValueField;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValueField = (EditText) findViewById(R.id.valueField);
        mAddButton = (Button) findViewById(R.id.addButton);




    }
}
