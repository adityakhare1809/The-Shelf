package com.aditya.theshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Details extends AppCompatActivity {
    TextView book,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getIncoming();
    }

    private void getIncoming()
    {
        String book=getIntent().getStringExtra("BOOK");
        String email=getIntent().getStringExtra("Email");
        String condition=getIntent().getStringExtra("Condition");
        String sp=getIntent().getStringExtra("SellingPrice");
        String cp=getIntent().getStringExtra("Original_Price");
        String desc=getIntent().getStringExtra("Description");
        String subb=getIntent().getStringExtra("Subject");
        String user_name=getIntent().getStringExtra("Fullname");
        String image=getIntent().getStringExtra("Image");
        setInfo(book,email,image,condition,sp,cp,desc,subb,user_name);
    }

    private void setInfo(String book,String email,String Image,String Cond,String Sp,String Cp,String disc,String sub,String full)
    {
        TextView boook=(TextView)findViewById(R.id.bk);
        TextView eemail=(TextView)findViewById(R.id.em);
        ImageView immage=(ImageView)findViewById(R.id.ima);
        TextView cond=(TextView)findViewById(R.id.cond);
        TextView sp=(TextView)findViewById(R.id.Sp);
        TextView cp=(TextView)findViewById(R.id.Op);
        TextView Full=(TextView)findViewById(R.id.Full);
        TextView Desc=(TextView)findViewById(R.id.Disc);
        TextView subb=(TextView)findViewById(R.id.Sub);
        boook.setText(book);
        eemail.setText(email);
        cond.setText(Cond);
         sp.setText(Sp);
         cp.setText(Cp);
        Full.setText(full);
         Desc.setText(disc);
         subb.setText(sub);
        Picasso.get().load(Image).into(immage);

    }
}
