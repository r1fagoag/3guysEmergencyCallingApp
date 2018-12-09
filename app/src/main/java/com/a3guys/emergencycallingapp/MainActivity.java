package com.a3guys.emergencycallingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String PREFERENCE_ID = "UserData";
    public static final String Name = "Name";
    public static final String Email = "Email";
    public static final String SignedIn = "SignedIn";
    public static final String phoneNumber = "Number";
    public static final String Addressname = "Address";
    public static final String LocSet = "LocationSet";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    EditText editTextName;
    EditText editTextEmail;
    EditText editPhoneNumber;
    EditText editAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFERENCE_ID, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editTextName = (EditText) findViewById(R.id.login_name);
        editTextEmail = (EditText) findViewById(R.id.login_email);
        editPhoneNumber =  (EditText) findViewById(R.id.login_number);
        editAddress = (EditText) findViewById(R.id.login_address);


        editTextName.setText(sharedPreferences.getString(Name, "Your Name"));
        editTextEmail.setText(sharedPreferences.getString(Email, "Your Email"));
        editPhoneNumber.setText(sharedPreferences.getString(phoneNumber, "Your Number"));
        editAddress.setText(sharedPreferences.getString(Addressname, "Your Address"));




        if(sharedPreferences.getBoolean(SignedIn, false))
        {
            editor.putBoolean(LocSet, false);

            editor.commit();
            Intent intent = new Intent(this, MainPageActivity.class);
            startActivity(intent);
        }

    }

    public void signIn(View view) {

        String uName = editTextName.getText().toString();
        String uEmail = editTextEmail.getText().toString();
        String uNumber = editPhoneNumber.getText().toString();
        String uAddress = editAddress.getText().toString();


        editor.putString(Name, uName);
        editor.putString(Email, uEmail);
        editor.putString(phoneNumber, uNumber);
        editor.putString(Addressname, uAddress);
        editor.putBoolean(SignedIn, true);
        editor.putBoolean(LocSet, false);

        editor.commit();

        if(sharedPreferences.getBoolean(SignedIn, false))
        {
            Intent intent = new Intent(this, MainPageActivity.class);
            startActivity(intent);
        }

    }
}
