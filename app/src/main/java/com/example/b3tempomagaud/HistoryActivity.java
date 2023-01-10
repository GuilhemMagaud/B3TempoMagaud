package com.example.b3tempomagaud;

import static com.example.b3tempomagaud.MainActivity.edfApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.b3tempomagaud.databinding.ActivityHistoryBinding;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();

    // Init views
    ActivityHistoryBinding binding;

    // Data model
    List<TempoDate> tempoDates = new ArrayList<>();

    // RV adapter
    TempoDateAdapter tempoDateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_history);
        setContentView(binding.getRoot());

        // Init recycler view
        binding.tempoHistoryRv.setHasFixedSize(true);
        binding.tempoHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        tempoDateAdapter = new TempoDateAdapter(tempoDates, this);
        binding.tempoHistoryRv.setAdapter(tempoDateAdapter);





    }
    public void reloaddate(){
        String YearNow = Tools.getNowDate("yyyy");
        String YearBefore = "";
        try{
            YearBefore = String.valueOf(Integer.parseInt(YearNow) - 1);
        }catch(NumberFormatException e){

        }

        if (edfApi != null) {

            // Create call to getTempoDaysLeft
            Call<TempoHistory> call = edfApi.getTempoHistory(YearBefore, YearNow);

            binding.progressBar.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<TempoHistory>() {
                @Override
                public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                    tempoDates.clear();
                    if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                        tempoDates.addAll(response.body().getTempoDates());
                        Log.d(LOG_TAG,"nb elements = " + tempoDates.size());
                    }
                    tempoDateAdapter.notifyDataSetChanged();
                    binding.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {
                    binding.progressBar.setVisibility(View.GONE);
                }
            });

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        reloaddate();
    }
}