package com.example.b3tempomagaud;

import static com.example.b3tempomagaud.MainActivity.edfApi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.b3tempomagaud.databinding.ActivityHistoryV2Binding;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivityV2 extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivityV2.class.getSimpleName();

    ActivityHistoryV2Binding binding;

    // Data model
    List<TempoDate> tempoDates = new ArrayList<>();

    TempoAdapterV2 tempoAdapterV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryV2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Init recycler view
        binding.tempoHistoryV2.setHasFixedSize(true);
        binding.tempoHistoryV2.setLayoutManager(new LinearLayoutManager(this));
        tempoAdapterV2 = new TempoAdapterV2(tempoDates, this);
        binding.tempoHistoryV2.setAdapter(tempoAdapterV2);
    }

    public void loadData(){
        String YearNow = Tools.getNowDate("yyyy");
        String YearBefore = "";
        try{
            YearBefore = String.valueOf(Integer.parseInt(YearNow) - 1);
        }catch(NumberFormatException e){
            Log.e(LOG_TAG, e.getMessage());
        }

        if (edfApi != null) {

            // Create call to getTempoDaysLeft
            Call<TempoHistory> call = edfApi.getTempoHistory(YearBefore, YearNow);
            call.enqueue(new Callback<TempoHistory>() {
                @Override
                public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                    tempoDates.clear();
                    if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                        tempoDates.addAll(response.body().getTempoDates());
                        Log.d(LOG_TAG,"nb elements = " + tempoDates.size());
                    }
                    tempoAdapterV2.notifyDataSetChanged();
                }

                @Override
                public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {
                    Log.e(LOG_TAG, "call to getTempoHistory () failed ");
                }
            });

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
