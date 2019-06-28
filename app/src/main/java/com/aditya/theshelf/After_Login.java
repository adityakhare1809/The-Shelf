package com.aditya.theshelf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class After_Login extends AppCompatActivity {


    TextView add;
    TextView search;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after__login);
        add = (TextView) findViewById(R.id.add);
        search=(TextView) findViewById(R.id.search);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity =new Intent(After_Login.this,up.class);
                startActivity(activity);
                //Intent ac=new Intent(After_Login.this,ff.class);
                //startActivity(ac);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acti=new Intent(After_Login.this,Display1.class);
                startActivity(acti);
            }
        });
    }
}
