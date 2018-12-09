package com.a3guys.emergencycallingapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrunkFragment extends Fragment {

    public static final String PREFERENCE_ID = "UserData";
    public static final String Addressname = "Address";

    SharedPreferences sharedPreferences;


    public DrunkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drunk, container, false);

        sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_ID, Context.MODE_PRIVATE);
        return view;
    }

}
