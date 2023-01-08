package com.example.b3tempomagaud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b3tempomagaud.databinding.TempoDateItemBinding;

import java.util.List;

public class TempoDateAdapter extends RecyclerView.Adapter<TempoDateAdapter.TempoDateViewHolder> {

    private List<TempoDate> localDateSet;
    private Context context;

    public TempoDateAdapter(List<TempoDate> dateSet, Context context) {
        localDateSet = dateSet;
        this.context = context;
    }



    @NonNull
    @Override
    public TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tempo_date_item, parent, false);
        TempoDateItemBinding binding = TempoDateItemBinding.bind(v);

        return new TempoDateViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateViewHolder holder, int position) {
        holder.binding.dateTv.setText(localDateSet.get(position).getDate());
        holder.binding.colorFl.setBackgroundColor(ContextCompat.getColor(context, localDateSet.get(position).getCouleur().getResId()));
    }




    @Override
    public int getItemCount() {
        return localDateSet.size();
    }

    public class TempoDateViewHolder extends RecyclerView.ViewHolder {

        TempoDateItemBinding binding;

        public TempoDateViewHolder(@NonNull TempoDateItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            /*
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            colorFl = (FrameLayout) itemView.findViewById(R.id.color_fl);
            */
        }
    }
}
