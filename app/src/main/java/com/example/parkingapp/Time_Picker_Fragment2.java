package com.example.parkingapp;

import android.content.Intent;
import android.drm.DrmManagerClient;
import android.media.MediaDrm;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Time_Picker_Fragment2 extends Fragment {

    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1235;

    TextView date;
    String time;
    Button next;
    String grad;

    public Time_Picker_Fragment2() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.time_picker_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Spinner spin = (Spinner) getActivity().findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Одберете време", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        next = (Button) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getIntent();
                grad = intent.getStringExtra("grad");
                intent = new Intent(v.getContext(), ParkingPlaces.class);
                intent.putExtra("grad", grad);
                v.getContext().startActivity(intent);
            }
        });
    }
}

