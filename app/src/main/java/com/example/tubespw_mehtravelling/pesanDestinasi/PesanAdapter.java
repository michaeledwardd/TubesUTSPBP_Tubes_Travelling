package com.example.tubespw_mehtravelling.pesanDestinasi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.pesanDestinasi.UpdateFragment;
import com.example.tubespw_mehtravelling.pesanDestinasi.Pesan;

import java.util.ArrayList;
import java.util.List;

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.PesanViewHolder> {
    private Context context;
    private List<Pesan> pesanList,pesanListCopy;


    public PesanAdapter(Context context, List<Pesan> pesanList) {
        this.context = context;
        this.pesanList = pesanList;
        pesanListCopy = new ArrayList<>(pesanList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PesanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pesan, parent, false);
        return new PesanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesanViewHolder holder, int position) {
        Pesan pesan = pesanList.get(position);
        holder.textViewNamaDestinasi.setText(pesan.getNama());
        holder.textViewTanggalPemesan.setText(pesan.getTanggal());
        holder.textViewLamaPemesan.setText(String.valueOf(pesan.getLama()));
        holder.textViewTipePesanan.setText(String.valueOf(pesan.getTipe()));
    }

    @Override
    public int getItemCount() {
        return pesanList.size();
    }

    public class PesanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        TextView textViewNamaDestinasi;
        TextView textViewTanggalPemesan;
        TextView textViewLamaPemesan;
        TextView textViewTipePesanan;

        public PesanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaDestinasi = itemView.findViewById(R.id.twnamaDestinasi);
            textViewTanggalPemesan = itemView.findViewById(R.id.twtanggalPemesanan);
            textViewLamaPemesan = itemView.findViewById(R.id.twlamaPesanan);
            textViewTipePesanan = itemView.findViewById(R.id.twtipePesanan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Pesan pesan = pesanList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("pesan", pesan);
            UpdateFragment updateFragment = new UpdateFragment();
            updateFragment.setArguments(data);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, updateFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void filter(String text) {
        pesanList.clear();
        if(text.equals("")){
            pesanList.addAll(pesanListCopy);
        } else{
            text = text.toLowerCase();
            for(Pesan item: pesanListCopy){
                if(item.getNama().toLowerCase().contains(text)){
                    pesanList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
