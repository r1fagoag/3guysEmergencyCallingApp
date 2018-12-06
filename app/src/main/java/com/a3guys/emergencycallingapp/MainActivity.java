package com.a3guys.emergencycallingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import com.firebase.client.Firebase;

import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private EditText mValueFieldFirstName;
    private EditText mValueFieldLastName;
    private EditText mValueFieldEmail;
    private EditText mValueFieldEmergencyContact;
    private EditText mValueFieldMedicalInformation;
    private Button mAddButton;
    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        URL url = NetworkUtils.buildUrl("AIzaSyDvtauAMCrK0yGbJwyBrll25KtZAOiAQhQ\n");

        //url taken from tutorial video. URL address is preferred, /Users
        mRootRef = new Firebase("https://emergency-calling-app.firebaseio.com/Users");

        mValueFieldFirstName = (EditText) findViewById(R.id.firstName);
        mValueFieldLastName = (EditText) findViewById(R.id.lastName);
        mValueFieldEmail = (EditText) findViewById(R.id.email);
        mValueFieldEmergencyContact = (EditText) findViewById(R.id.emergencyContact);
        mValueFieldEmail = (EditText) findViewById(R.id.medicalInformation);

        mAddButton = (Button) findViewById(R.id.addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = mValueFieldFirstName.getText().toString();
                String lastName = mValueFieldLastName.getText().toString();
                String _email = mValueFieldEmail.getText().toString();
                String emergencyContact = mValueFieldEmergencyContact.getText().toString();
                String medicalInformation = mValueFieldMedicalInformation.getText().toString();

                //emergency situation when users information including location is sent to
                //social media platforms and or to the emergency contact

                //the following will be registration information
                //Creating firebase variables. Example: on firebase it will show "First Name"
                Firebase first_name = mRootRef.child("First Name");
                Firebase last_name = mRootRef.child("Last Name");
                Firebase email = mRootRef.child("Email");
                Firebase emergency_contact = mRootRef.child("Emergency Contact");
                Firebase medical_information = mRootRef.child("Medical Information");

                //set value firstName to the firebase variable first_name
                first_name.setValue(firstName);
                last_name.setValue(lastName);
                email.setValue(_email);
                emergency_contact.setValue(emergencyContact);
                medical_information.setValue(medicalInformation);
            }
        });




    }
}
