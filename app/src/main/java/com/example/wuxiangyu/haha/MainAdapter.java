package com.example.wuxiangyu.haha;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    List<String> temp;

    public void setTemp(List<String> list) {
        this.temp = list;
        notifyDataSetChanged();
    }
    public String getIem(int position) {
        return temp == null ? "" : temp.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("xxxx", "onCreateViewHolder: ");
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String temp = getIem(position);
        Log.e("xxxx", "onBindViewHolder: " + position);
        holder.tvCongent.setText(temp);
        if (position % 2 == 0) {
            holder.tvCongent.setBackgroundColor(Color.WHITE);
        } else {
            holder.tvCongent.setBackgroundColor(Color.BLACK);
        }
        holder.btnAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickAfter(position);
                }
            }
        });
        holder.btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickBefore(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
//        Log.e("xxxx", "getItemCount: " + (temp == null ? 0 : temp.size()));
        return temp == null ? 0 : temp.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCongent;
        Button btnBefore;
        Button btnAfter;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCongent = itemView.findViewById(R.id.tvCongent);
            btnBefore = itemView.findViewById(R.id.btnBefore);
            btnAfter = itemView.findViewById(R.id.btnNext);
        }
    }

    public interface MyClick {
        void clickBefore(int currentPosition);
        void clickAfter(int currentPosition);

    }
    private MyClick listener;

    public void setListener(MyClick listener) {
        this.listener = listener;
    }
}
