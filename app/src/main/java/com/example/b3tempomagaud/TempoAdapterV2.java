package com.example.b3tempomagaud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b3tempomagaud.databinding.TempoDateItemV2Binding;

import java.util.List;

public class TempoAdapterV2 extends RecyclerView.Adapter<TempoAdapterV2.TempoDateViewHolder>{

    private List<TempoDate> localDateSet;
    private Context context;

    public TempoAdapterV2(List<TempoDate> dateSet, Context context) {
        localDateSet = dateSet;
        this.context = context;
    }

    @NonNull
    @Override
    public TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tempo_date_item_v2, parent, false);
        TempoDateItemV2Binding binding = TempoDateItemV2Binding.bind(v);

        return new TempoDateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateViewHolder holder, int position) {
        holder.binding.textView5.setText(Tools.formatTempoHistoryDate(localDateSet.get(position).getDate()));
        holder.binding.colorFl2.setBackgroundColor(ContextCompat.getColor(context, localDateSet.get(position).getCouleur().getColorResId()));
        holder.binding.textView6.setText(localDateSet.get(position).getCouleur().getStringResId());
    }

    @Override
    public int getItemCount() {
        return localDateSet.size();
    }

    public class TempoDateViewHolder extends RecyclerView.ViewHolder {

        TempoDateItemV2Binding binding;

        public TempoDateViewHolder(@NonNull TempoDateItemV2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
            /*
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            colorFl = (FrameLayout) itemView.findViewById(R.id.color_fl);
            */
        }
    }
}
