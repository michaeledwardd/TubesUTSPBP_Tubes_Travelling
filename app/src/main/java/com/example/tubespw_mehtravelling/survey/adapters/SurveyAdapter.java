package com.example.tubespw_mehtravelling.survey.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.survey.SurveyActivity;
import com.example.tubespw_mehtravelling.survey.models.Survey;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> implements Filterable {
    private List<Survey> surveyList, filteredSurveyList;
    private Context context;

    public SurveyAdapter(List<Survey> surveyList, Context context)
    {
        this.surveyList = surveyList;
        filteredSurveyList = new ArrayList<>(surveyList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_survey, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Survey survey = filteredSurveyList.get(position);
        holder.tvNama.setText(survey.getAlasan());
        holder.tvNpm.setText(survey.getNamaDestinasi());
        holder.tvInfo.setText(survey.getNamaPengguna() + " - " + survey.getPenilaian());
        holder.btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data mahasiswa ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Hapus", new
                                DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        if (context instanceof SurveyActivity)
                                            ((SurveyActivity)
                                                    context).deleteSurvey(survey.getId());
                                    }
                                })
                        .show();
            }
        });

        holder.cvMahasiswa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddEditActivity.class);
                i.putExtra("id", survey.getId());
                if (context instanceof SurveyActivity)
                    ((SurveyActivity) context).startActivityForResult(i,
                            SurveyActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return filteredSurveyList.size();
    }

    public void setMahasiswaList(List<Survey> mahasiswaList)
    {
        this.surveyList = mahasiswaList;
        filteredSurveyList = new ArrayList<>(mahasiswaList);
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charSequenceString = charSequence.toString();
                List<Survey> filtered = new ArrayList<>();
                if (charSequenceString.isEmpty())
                {
                    filtered.addAll(surveyList);
                }
                else
                {
                    for (Survey survey : surveyList)
                    {
                        if (survey.getAlasan().toLowerCase().contains(charSequenceString.toLowerCase()))
                            filtered.add(survey);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredSurveyList.clear();
                filteredSurveyList.addAll((List<Survey>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNama, tvNpm, tvInfo;
        ImageButton btnDelete;
        CardView cvMahasiswa;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvNpm = itemView.findViewById(R.id.tv_npm);
            tvNama = itemView.findViewById(R.id.tv_title);
            tvInfo = itemView.findViewById(R.id.tv_info);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            cvMahasiswa = itemView.findViewById(R.id.cv_mahasiswa);
        }
    }
}
