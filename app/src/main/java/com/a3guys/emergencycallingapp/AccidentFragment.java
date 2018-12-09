package com.a3guys.emergencycallingapp;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccidentFragment extends Fragment {
    public static final String locurl = "http://maps.google.com/?q=";

    public static final String PREFERENCE_ID = "UserData";
    public static final String Name = "Name";
    public static final String phoneNumber = "Number";

    SharedPreferences sharedPreferences;

    String mNumber;
    String mName;

    double lat;
    double lng;

    public AccidentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accident, container, false);

        sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_ID, Context.MODE_PRIVATE);
        mName = sharedPreferences.getString(Name, "");
        mNumber = sharedPreferences.getString(phoneNumber, "");


        if (!mNumber.isEmpty()) {

            Button callcontact = view.findViewById(R.id.call_contact);
            callcontact.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + mNumber));

                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED)
                        return;

                    startActivity(callIntent);

                }
            });

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                Location location = locationManager.getLastKnownLocation(
                        locationManager.getBestProvider(criteria, false)
                );

                lat = location.getLatitude();
                lng = location.getLongitude();

                final String latlngstring = "" + lat + "," + lng;

                Button textcontact = view.findViewById(R.id.text_contact);
                textcontact.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + mNumber));
                        smsIntent.putExtra("sms_body", "It's " + mName
                                + ". I am in an accident!\n"
                                + locurl + latlngstring);

                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS)
                                != PackageManager.PERMISSION_GRANTED)
                            return;

                        startActivity(smsIntent);
                    }
                });

            }
        }

        return view;
    }

}
