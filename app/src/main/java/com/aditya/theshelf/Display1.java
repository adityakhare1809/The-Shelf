package com.aditya.theshelf;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Display1 extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    //ArrayList<Profile>list;

    ArrayList<bookdata>list;

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display1);

        recyclerView=(RecyclerView)findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list =new ArrayList<bookdata>();



        reference= FirebaseDatabase.getInstance().getReference().child("BOOK");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    //Profile p=dataSnapshot1.getValue(Profile.class);
                    bookdata p=dataSnapshot1.getValue(bookdata.class);

                    list.add(p);
                }
                adapter=new MyAdapter(Display1.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Display1.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });
    }
}
