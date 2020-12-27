package com.example.parkingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParkingPlacesAdapter extends RecyclerView.Adapter<ParkingPlacesAdapter.ViewHolder> {

    private List<String> myList;
    private int parkingLayout;
    private Context myContext;
    private String grad;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextView free;
        public TextView taken;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.parkingcityname);
            button = (Button) itemView.findViewById(R.id.parkingcitybutton);
            free = (TextView) itemView.findViewById(R.id.slobodni);
            taken = (TextView) itemView.findViewById(R.id.zafateni);
        }
    }

    public ParkingPlacesAdapter(List<String> myList, int parkingLayout, Context myContext, String grad) {
        this.myList = myList;
        this.parkingLayout = parkingLayout;
        this.myContext = myContext;
        this.grad = grad;
    }

    @Override
    public ParkingPlacesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(parkingLayout, viewGroup, false);
        return new ParkingPlacesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ParkingPlacesAdapter.ViewHolder viewHolder, int i) {
        String entry = myList.get(i);
        viewHolder.text.setText(entry);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReservationConfirmation.class);
                intent.putExtra("grad", grad);
                intent.putExtra("parking", viewHolder.text.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }


}
