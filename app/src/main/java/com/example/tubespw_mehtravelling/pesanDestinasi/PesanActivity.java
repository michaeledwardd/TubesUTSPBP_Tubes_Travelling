package com.example.tubespw_mehtravelling.pesanDestinasi;

import static java.nio.file.Files.delete;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.tubespw_mehtravelling.Database.DatabasePesanan;
import com.example.tubespw_mehtravelling.MainActivity;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.UpdateFragment;
import com.example.tubespw_mehtravelling.listDestinasi.TampilDataDestinasi;

import java.util.List;

public class PesanActivity extends AppCompatActivity{
    //serupa dengan riwayat activity//

    private SearchView searchView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private List<Pesan> pesanList;
    private PesanAdapter adapter;
//    private ImageButton btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_pemesanan);

        refreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        btnEdit=findViewById(R.id.btnEdit);

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                UpdateFragment mAddFragment = new UpdateFragment();
//                activity.getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.ParentAdapter, mAddFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPesans();
                refreshLayout.setRefreshing(false);
            }
        });

        getPesans();


    }

    private void getPesans(){
        class GetPesans extends AsyncTask<Void, Void, List<Pesan>> {

            @Override
            protected List<Pesan> doInBackground(Void... voids) {
                pesanList = DatabasePesanan
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .pesanDao()
                        .getAll();
                return pesanList;
            }

            @Override
            protected void onPostExecute(List<Pesan> pesans) {
                super.onPostExecute(pesans);
                adapter = new PesanAdapter(PesanActivity.this, pesans);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (pesans.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetPesans get = new GetPesans();
        get.execute();
    }
}
