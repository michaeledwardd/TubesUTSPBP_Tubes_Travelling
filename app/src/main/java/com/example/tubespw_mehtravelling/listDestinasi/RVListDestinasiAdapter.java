package com.example.tubespw_mehtravelling.listDestinasi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubespw_mehtravelling.R;

public class RVListDestinasiAdapter extends RecyclerView.Adapter<RVListDestinasiAdapter.viewHolder>{
    private final Destinasi[] listDestinasi;
    private ImageView img_icon;

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tv_namadestinasi;
        TextView tv_alamatdestinasi;
        TextView tv_deskripsidestinasi;
        LinearLayout tampildatadestinasi;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namadestinasi= itemView.findViewById(R.id.tv_namadestinasi);
            tv_alamatdestinasi = itemView.findViewById(R.id.tv_alamatdestinasi);
            tv_deskripsidestinasi = itemView.findViewById(R.id.tv_deskripsidestinasi);
            tampildatadestinasi = itemView.findViewById(R.id.datadestinasi);
        }
    }
    RVListDestinasiAdapter(Destinasi[] Destinasi) {
        listDestinasi=Destinasi;
    }
    @NonNull
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVListDestinasiAdapter.viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_destinasi,
                parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.tv_namadestinasi.setText(listDestinasi[position].getNamadestinasi());
        holder.tv_alamatdestinasi.setText(listDestinasi[position].getAlamat());
        holder.tv_deskripsidestinasi.setText(listDestinasi[position].getDeskripsi());
        holder.tampildatadestinasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Data Destinasi");
                builder.setMessage("Destinasi yang dipilih adalah "+ holder.tv_namadestinasi.getText().toString());
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }) .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDestinasi.length;
    }
}
