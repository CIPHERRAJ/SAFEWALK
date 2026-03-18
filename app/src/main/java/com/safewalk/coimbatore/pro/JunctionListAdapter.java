package com.safewalk.coimbatore.pro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class JunctionListAdapter extends ListAdapter<Junction, JunctionListAdapter.JunctionViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Junction junction);
    }

    private final OnItemClickListener listener;

    protected JunctionListAdapter(@NonNull DiffUtil.ItemCallback<Junction> diffCallback, OnItemClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @NonNull
    @Override
    public JunctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return JunctionViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull JunctionViewHolder holder, int position) {
        Junction current = getItem(position);
        holder.bind(current.getName(), current.getSafetyScore());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(current));
        holder.buttonViewDetails.setOnClickListener(v -> listener.onItemClick(current));
    }

    static class JunctionViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameItemView;
        private final TextView riskItemView;
        private final View buttonViewDetails;

        private JunctionViewHolder(View itemView) {
            super(itemView);
            nameItemView = itemView.findViewById(R.id.textViewName);
            riskItemView = itemView.findViewById(R.id.textViewRisk);
            buttonViewDetails = itemView.findViewById(R.id.buttonViewDetails);
        }

        public void bind(String name, String risk) {
            nameItemView.setText(name);
            riskItemView.setText(risk);
        }

        static JunctionViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item, parent, false);
            return new JunctionViewHolder(view);
        }
    }

    static class JunctionDiff extends DiffUtil.ItemCallback<Junction> {
        @Override
        public boolean areItemsTheSame(@NonNull Junction oldItem, @NonNull Junction newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Junction oldItem, @NonNull Junction newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
