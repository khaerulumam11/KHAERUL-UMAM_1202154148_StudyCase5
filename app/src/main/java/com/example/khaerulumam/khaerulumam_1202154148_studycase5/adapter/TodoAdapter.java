package com.example.khaerulumam.khaerulumam_1202154148_studycase5.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khaerulumam.khaerulumam_1202154148_studycase5.R;
import com.example.khaerulumam.khaerulumam_1202154148_studycase5.model.Data;

import java.util.List;

/**
 * Created by Umam on 3/22/2018.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private List<Data> userList;
    private Context con;
    int id;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNama, tvDes,tvJumlah;
        public CardView a;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNama  = itemView.findViewById(R.id.nama);
            tvDes   = itemView.findViewById(R.id.deskripsi);
            tvJumlah   = itemView.findViewById(R.id.jumlah);
            a = itemView.findViewById(R.id.card);
        }
    }
    public TodoAdapter (Context con, List<Data> listUser,int id){
        this.userList = listUser;
        this.con = con;
        this.id = id;
    }

    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }

    public void add(Data user){
        userList.add(user);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(TodoAdapter.ViewHolder holder, int position) {
        Data user = userList.get(position);
        holder.tvNama.setText(user.getName());
        holder.tvDes.setText(user.getDeskripsi());
        holder.tvJumlah.setText(user.getJumlah());
        holder.a.setCardBackgroundColor(con.getResources().getColor(this.id));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
