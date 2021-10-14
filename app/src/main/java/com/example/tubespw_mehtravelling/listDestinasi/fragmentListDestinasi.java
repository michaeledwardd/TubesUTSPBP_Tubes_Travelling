package com.example.tubespw_mehtravelling.listDestinasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubespw_mehtravelling.R;


public class fragmentListDestinasi extends Fragment {

        public fragmentListDestinasi () {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_list_destinasi, container, false);
        }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            RecyclerView rv_list_destinasi = view.findViewById(R.id.rv_destinasi);

            //  Set Layout Manager dari recycler view
            rv_list_destinasi.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));

            rv_list_destinasi.setAdapter(new RVListDestinasiAdapter(Destinasi.listOfDestinasi));
        }
    }

