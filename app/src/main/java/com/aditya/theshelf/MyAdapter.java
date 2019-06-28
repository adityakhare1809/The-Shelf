package com.aditya.theshelf;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    Context context;
    ArrayList<bookdata> profiles;
    String bb=null;
    String dd=null;

    public MyAdapter(Context c , ArrayList<bookdata> p)
    {
        context=c;
        profiles =p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

        holder.name.setText(profiles.get(position).getUser());
        holder.email.setText(profiles.get(position).getEmailaddress());
        Picasso.get().load(profiles.get(position).getUrl()).into(holder.pic);
        //bb=;
        //dd=profiles.get(position).getEmailaddress();
        holder.Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent acti=new Intent(context,Details.class);
                        acti.putExtra("BOOK",profiles.get(position).getUser());
                        acti.putExtra("Email",profiles.get(position).getEmailaddress());
                        acti.putExtra("Image",profiles.get(position).getUrl());
                        acti.putExtra("Condition",profiles.get(position).getCondition());
                        acti.putExtra("Subject",profiles.get(position).getSubject());
                        acti.putExtra("Original_Price",profiles.get(position).getOriginalprice());
                        acti.putExtra("SellingPrice",profiles.get(position).getSellingprice());
                        acti.putExtra("Description",profiles.get(position).getDescription());
                        acti.putExtra("Fullname",profiles.get(position).getFullname());

                        context.startActivity(acti);

                    }

        });


    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,email;
        ImageView pic;
        Button Details;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.book);
            email=(TextView) itemView.findViewById(R.id.email);
            pic=(ImageView)itemView.findViewById(R.id.profilePic);
            Details=(Button)itemView.findViewById(R.id.checkdetails);

        }
    }


}
