package com.csiindonesia.id.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.csiindonesia.id.R;
import com.csiindonesia.id.model.modelAbsen;
import com.csiindonesia.id.server.InitRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;


public class adapterAbsen extends RecyclerView.Adapter<adapterAbsen.MyViewHolder> {
    Context context;
    List<modelAbsen> menu;
    public adapterAbsen(Context context, List<modelAbsen> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_absen, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // Set widget
        holder.nama.setText(menu.get(position).getNama());
        holder.date.setText(menu.get(position).getTanggal()+"-"+menu.get(position).getJam());
        holder.deskripsi.setText(menu.get(position).getDeskripsi());
        final String urlGambar = InitRetrofit.BASE_URL+ menu.get(position).getGambar();
        Picasso.with(context).load(urlGambar).into(holder.gambar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent varIntent = new Intent(context, Detail_Menu.class);
//                varIntent.putExtra("ID", menu.get(position).getId());
//                varIntent.putExtra("NAMA", menu.get(position).getNama());
//                varIntent.putExtra("HARGA", menu.get(position).getHarga());
//                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//                varIntent.putExtra("GAMBAR_MENU", urlGambar);
//                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//                context.startActivity(varIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView nama,date,deskripsi;
        public MyViewHolder(View itemView) {
            super(itemView);
            gambar = (ImageView) itemView.findViewById(R.id.Gambar);
            nama = (TextView) itemView.findViewById(R.id.Nama);
            date = (TextView) itemView.findViewById(R.id.Date);
            deskripsi = (TextView) itemView.findViewById(R.id.Deskripsi);

        }
    }


}
