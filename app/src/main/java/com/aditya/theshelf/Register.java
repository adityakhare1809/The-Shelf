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

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText username1;
    EditText pass1;
    EditText pass2;
    Button signup;
    TextView signin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();
        username1=(EditText)(findViewById(R.id.userName1));
        pass1=(EditText)(findViewById(R.id.Password1));
        pass2=(EditText)(findViewById(R.id.Password2));
        signup=(Button)(findViewById(R.id.signup));
        signin=(TextView)(findViewById(R.id.Register1));

        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

            private void RegisterUser()
            {
                String user=username1.getText().toString().trim();
                String pass_1=pass1.getText().toString().trim();
                String pass_2=pass2.getText().toString().trim();
                if(pass_1.equals(pass_2))
                {
                    firebaseAuth.createUserWithEmailAndPassword(user,pass_1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "SignUp Done", Toast.LENGTH_SHORT).show();
                                Intent acti = new Intent(Register.this, MainActivity.class);
                                startActivity(acti);

                            }
                            else {
                                Toast.makeText(Register.this,"Sign Up Error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Register.this, "Enter same Password", Toast.LENGTH_SHORT).show();
                }

            }

    @Override
    public void onClick(View view) {
        if(view==signin)
        {
            Intent active=new Intent(Register.this,MainActivity.class);
            startActivity(active);

        }
        if(view==signup)
        {
            RegisterUser();
        }

    }
}
