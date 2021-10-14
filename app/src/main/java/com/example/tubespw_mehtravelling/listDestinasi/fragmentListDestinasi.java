package com.example.tubespw_mehtravelling.listDestinasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubespw_mehtravelling.MainActivity;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.ui.auth.LoginActivity;
import com.example.tubespw_mehtravelling.ui.auth.RegisterActivity;


public class fragmentListDestinasi extends Fragment {

    private Button btnback;

        public fragmentListDestinasi () {
            // Required empty public constructor
        }


//        protected void onCreate(Bundle savedInstanceState, View view) {
//            super.onCreate(savedInstanceState);
//            btnback = view.findViewById(R.id.btnback);
//
//            btnback.setOnClickListener(new View.OnClickListener() {
//            @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(fragmentListDestinasi.this,MainActivity.class));
//    //                finish();
//                }
//            });
//        }

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

