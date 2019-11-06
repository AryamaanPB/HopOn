package com.example.hopon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class notification extends AppCompatActivity {
DatabaseReference databaseReference;
TextView req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        req=findViewById(R.id.req);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(notification.this);
        final String nm=account.getEmail();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("requests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("namereq")) {
                    String mailauth=dataSnapshot.child("emailauth").getValue().toString();
                     if (nm.equals(mailauth))
                    {
                    String n = dataSnapshot.child("namereq").getValue().toString();
                    String m = dataSnapshot.child("mailreq").getValue().toString();
                    req.setText(n + " would like to join you on your trip \n For more info contact on their mail id " + m);
                    }
                }
                else
                {
                    req.setText("No Notification");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
