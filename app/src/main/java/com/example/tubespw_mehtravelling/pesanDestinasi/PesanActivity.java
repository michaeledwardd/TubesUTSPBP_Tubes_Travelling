package com.example.tubespw_mehtravelling.pesanDestinasi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.tubespw_mehtravelling.Database.DatabasePesanan;
import com.example.tubespw_mehtravelling.R;
import java.util.List;

public class PesanActivity extends AppCompatActivity{
    //serupa dengan riwayat activity//

    private SearchView searchView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private List<Pesan> pesanList;
    private PesanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_pemesanan);


        refreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                AddFragment mAddFragment = new AddFragment();
//                activity.getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layout, mAddFragment)
//                        .addToBackStack(null)
//                        .commit();
//                fab.hide();
//
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

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                adapter.filter(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                adapter.filter(s);
//                return false;
//            }
//        });
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
