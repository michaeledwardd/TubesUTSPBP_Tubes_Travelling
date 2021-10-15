package com.example.tubespw_mehtravelling.listDestinasi;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tubespw_mehtravelling.R;

import com.example.tubespw_mehtravelling.databinding.ActivityRecyclerViewAdapterBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    private List<DataDestinasi> listdestinasi;

    public RecyclerViewAdapter(List<DataDestinasi> listdestinasi) {
        this.listdestinasi = listdestinasi;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ActivityRecyclerViewAdapterBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_recycler_view_adapter, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Glide.with(holder.binding.getRoot())
                .load(listdestinasi.get(position).getImgurl())

                .into(holder.binding.ProfilePicture);

        holder.binding.tvNamadestinasi.setText(listdestinasi.get(position).getNamaDestinasi());
        holder.binding.tvAlamatdestinasi.setText(listdestinasi.get(position).getAlamatDestinasi());
        holder.binding.tvDeskripsidestinasi.setText(listdestinasi.get(position).getAlamatDestinasi());
    }

    @Override
    public int getItemCount() {
        return listdestinasi.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ActivityRecyclerViewAdapterBinding binding;

        public viewHolder(@NonNull ActivityRecyclerViewAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}