package com.example.ksvcem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends FirebaseRecyclerAdapter<modal_class, myAdapter.myviewHolder> {

    public myAdapter(@NonNull FirebaseRecyclerOptions<modal_class> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull modal_class model) {
        holder.fac_name.setText(model.getFac_name());
        holder.fac_dept.setText(model.getFac_dept());
        holder.yoe.setText(model.getYear_of_exp());
        holder.email.setText(model.getEmail());
        Glide.with(holder.img.getContext())
                .load(model.getPurl())
                .error(R.drawable.no_image)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView fac_name, fac_dept, yoe, email;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            img =(CircleImageView)itemView.findViewById(R.id.img1);
            fac_name =(TextView)itemView.findViewById(R.id.fac_name);
            fac_dept =(TextView)itemView.findViewById(R.id.fac_dept);
            yoe =(TextView)itemView.findViewById(R.id.yoe);
            email =(TextView)itemView.findViewById(R.id.email);
        }
    }
}
