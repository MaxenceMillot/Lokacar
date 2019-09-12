package com.eni.lokacar.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eni.lokacar.R;
import com.eni.lokacar.data.model.Vehicule;

import java.util.ArrayList;

public class RecyclerViewVehiculeAdapter extends RecyclerView.Adapter<RecyclerViewVehiculeAdapter.VehiculeViewHolder> {
    ArrayList<Vehicule> listVehicule;
    OnItemClickListener listener;
    Context context;

    public class VehiculeViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMarque;
        TextView textViewModele;
        TextView textViewPlaque;
        TextView textViewPrixJour;
        View viewColorDispo;

        public VehiculeViewHolder(@NonNull View itemView){
            super(itemView);
            textViewMarque = itemView.findViewById(R.id.textViewMarque);
            textViewModele = itemView.findViewById(R.id.textViewModele);
            textViewPlaque = itemView.findViewById(R.id.textViewPlaque);
            textViewPrixJour = itemView.findViewById(R.id.textViewPrixJour);
            viewColorDispo = itemView.findViewById(R.id.viewColorDispo);
        }
    }

    public RecyclerViewVehiculeAdapter(OnItemClickListener listener){
        this.listener = listener;
    }

    public RecyclerViewVehiculeAdapter(ArrayList<Vehicule> listVehicule, OnItemClickListener listener,Context context){
        this.listVehicule = listVehicule;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public VehiculeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new VehiculeViewHolder(li.inflate(R.layout.element_list_vehicule,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculeViewHolder holder, int position) {
        final Vehicule vehiculeAAfficher = listVehicule.get(position);

        holder.textViewMarque.setText(listVehicule.get(position).getMarque());
        holder.textViewModele.setText(listVehicule.get(position).getModele());
        holder.textViewPlaque.setText(listVehicule.get(position).getPlaque());
        holder.textViewPrixJour.setText(Float.toString(listVehicule.get(position).getPrixJour())+"€/Jour");
        holder.viewColorDispo.setBackgroundColor(listVehicule.get(position).isDispo()?context.getResources().getColor(R.color.colorGreen):context.getResources().getColor(R.color.colorRed));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(vehiculeAAfficher);
            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick(Vehicule vehicule);
    }

    @Override
    public int getItemCount() {
        return listVehicule.size();
    }

    public void setVehiculeArrayList(ArrayList<Vehicule> listVehicule, Activity activity){
        this.listVehicule =  listVehicule;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
}
