package com.example.hopon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class submit extends AppCompatActivity {
ImageButton sendbtn,backbtn;
EditText fromp,top,date,time,desc;
DatabaseReference databaseReference;
int i=0;
boolean check=false;
public void openhome(){
  Intent intent1= new Intent(this,feedact.class);
    startActivity(intent1);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        fromp=findViewById(R.id.from);
        top=findViewById(R.id.to);
        backbtn=findViewById(R.id.back);
        sendbtn=findViewById(R.id.send);
        date=findViewById(R.id.dat);
        time=findViewById(R.id.time);
        desc=findViewById(R.id.description);
        databaseReference= FirebaseDatabase.getInstance().getReference("Post");
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddata();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhome();
            }
        });

    }
    public void senddata(){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(submit.this);
        String fro=fromp.getText().toString();
        String topp=top.getText().toString();
        String dat=date.getText().toString();
        String tim=time.getText().toString();
        String des=desc.getText().toString();
        String pfpf=String.valueOf(acct.getPhotoUrl());
        String nam=acct.getDisplayName();
        String mail=acct.getEmail();
        //databaseReference= FirebaseDatabase.getInstance().getReference(nam);
        if(!TextUtils.isEmpty(fro)&&!TextUtils.isEmpty(topp)&&!TextUtils.isEmpty(dat)&&!TextUtils.isEmpty(tim)&&!TextUtils.isEmpty(des))
        {
                //String id= databaseReference.push().getKey();
                String id = acct.getId();
                String na=acct.getDisplayName();
                sending send= new sending(fro,dat,tim,des,topp,pfpf,nam,"0",mail);
                databaseReference.child(na).setValue(send);
                fromp.setText("");
                top.setText("");
                date.setText("");
                time.setText("");
                desc.setText("");
                openhome();

            /*databaseReference=FirebaseDatabase.getInstance().getReference("Names");
            naming namee=new naming(na);
            databaseReference.setValue(namee);*/

        }
        else {
            Toast.makeText(submit.this,"Please fill all details",Toast.LENGTH_SHORT).show();
        }
    }
}
