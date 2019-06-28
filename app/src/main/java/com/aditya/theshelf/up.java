package com.aditya.theshelf;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class up extends AppCompatActivity {


    EditText book_name, subject, Condition, OP, SP, description;
    EditText seller_name, mob, email;
    Button submit,choose,upload;
    ImageView img;
    DatabaseReference mRef;
    StorageReference streff;
    bookdata Boook;
    long maxid=0;
    Uri imguri;
    StorageTask uploadTask;
    String imageid=null;
    String URLl=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);
        book_name = (EditText) findViewById(R.id.book_name);
        subject = (EditText) findViewById(R.id.subject);
        Condition = (EditText) findViewById(R.id.condition);
        OP = (EditText) findViewById(R.id.original_price);
        SP = (EditText) findViewById(R.id.selling_price);
        description = (EditText) findViewById(R.id.description);
        seller_name = (EditText) findViewById(R.id.full_name);
        mob = (EditText) findViewById(R.id.Cell);
        email = (EditText) findViewById(R.id.Email_id);
        submit = (Button) findViewById(R.id.submit);
        choose=(Button)findViewById(R.id.choose_image);
        img=(ImageView)findViewById(R.id.Pic);
        upload=(Button)findViewById(R.id.upload);

       // myRef = FirebaseDatabase.getInstance().getReference("BOOK");*/
        Boook=new bookdata();

        mRef = FirebaseDatabase.getInstance().getReference("BOOK");
        streff= FirebaseStorage.getInstance().getReference("Images");

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(uploadTask!=null&& uploadTask.isInProgress())
                {
                    Toast.makeText(up.this,"Uploading in progress",Toast.LENGTH_LONG).show();
                }
                else
                {
                    imageid="https://firebasestorage.googleapis.com/v0/b/the-shelf-aad92.appspot.com/o/Images%2F"+FileUploader()+"?alt=media";
                }

            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    maxid=dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book = book_name.getText().toString().trim();
               String sub = subject.getText().toString().trim();
                String Con = Condition.getText().toString().trim();
               String orig = OP.getText().toString().trim();
                String Sell = SP.getText().toString().trim();
                String Desc = description.getText().toString().trim();
                String name = seller_name.getText().toString().trim();
                String num = mob.getText().toString().trim();
                String mail = email.getText().toString().trim();
               //FirebaseUser user=mAuth.getCurrentUser();
                String id=mRef.getKey();
                if (!TextUtils.isEmpty(book)&& !TextUtils.isEmpty(sub) && !TextUtils.isEmpty(Con) && !TextUtils.isEmpty(orig) && !TextUtils.isEmpty(Sell) && !TextUtils.isEmpty(Desc) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(num) && !TextUtils.isEmpty(mail)) {
                    Boook.setUser(book);
                    Boook.setCondition(Con);
                    Boook.setDescription(Desc);
                    Boook.setEmailaddress(mail);
                    Boook.setNumber(num);
                    Boook.setFullname(name);
                     Boook.setOriginalprice(orig);
                     Boook.setSellingprice(Sell);
                    Boook.setSubject(sub);
                    Boook.setImageid(imageid);
                    Boook.setUrl(imageid);
                    mRef.child(String.valueOf(maxid+1)).setValue(Boook);
                    Toast.makeText(up.this, "Book Ad Posted", Toast.LENGTH_LONG).show();
                    //Intent activity=new Intent(up.this,After_Login.class);
                    //startActivity(activity);
               } else {
                    Toast.makeText(up.this, "Fill all Details", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private String getExtention(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        //URLl=uri.toString();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

   private String FileUploader()
   {
       final String imageid=System.currentTimeMillis()+"."+getExtention(imguri);

       //final String UR;
       StorageReference Ref=streff.child(imageid);
       //URLl=String.valueOf(Ref.getDownloadUrl());
       URLl=imguri.toString();
       uploadTask=Ref.putFile(imguri)
               .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       // Get a URL to the uploaded content
                       //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                      // URL1=taskSnapshot.getDownloadUrl().toString();
                      // URLl=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                       Toast.makeText(up.this,"Image uploaded Successfully",Toast.LENGTH_LONG).show();
                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception exception) {
                       // Handle unsuccessful uploads
                       // ...
                   }
               });
       return imageid;
   }

    private void FileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&& resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            imguri=data.getData();
            img.setImageURI(imguri);

        }
    }
}
