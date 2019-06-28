package com.aditya.theshelf;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText User;
    FirebaseAuth firebaseAuth;
    EditText password;
    Button signinn;
    TextView signupp;
    //FirebaseFirestore mFirestore;
    DatabaseReference myRef;
    bookdata st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mFirestore=FirebaseFirestore.getInstance();
        Toast.makeText(MainActivity.this,"Firebase connection success",Toast.LENGTH_LONG).show();

        firebaseAuth = FirebaseAuth.getInstance();
        /*if(firebaseAuth.getCurrentUser()!=null)
        {

        }*/
        User=(EditText)findViewById(R.id.userName);
        password=(EditText)findViewById(R.id.password);
        signinn=(Button)findViewById(R.id.button1);
        signupp=(TextView)findViewById(R.id.Signup);
        //myRef= FirebaseDatabase.getInstance().getReference("user");
        signupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity=new Intent(MainActivity.this,Register.class);
                startActivity(activity);
            }
        });

        signinn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=User.getText().toString().trim();
                String pass=password.getText().toString().trim();
               // tr.setUser(user);
                //myRef.push().setValue(tr);

                //Map<String,String> userMap=new HashMap<>();
                //userMap.put("name",user);
                //userMap.put("image","image_link");
               // mFirestore.collection("users").add(userMap);
                sign(user,pass);

            }
        });

    }

    public void sign(String user,String pass)
    {
        firebaseAuth.signInWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent activity= new Intent(MainActivity.this,After_Login.class);
                            startActivity(activity);
                            //Toast.makeText(MainActivity.this,"Signed In",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Sign In Error",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
