package com.example.hopon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feedact extends AppCompatActivity {
ImageButton pluss,message,profle;
TextView frompst,topst,datepst,desc,frompst2,frompst3,frompst4,topst2,topst3,topst4,datepst2,datepst3,datepst4,desc2,desc3,desc4,nameV,nameV2,nameV3,nameV4,vote,vote2,vote3,vote4;
DatabaseReference databaseReference,databaseReference1,databaseReference2,databaseReference3,databaserefreq;
ImageView post,post2,post3,post4,profp,profp2,profp3,profp4,upvote,share,share2,share3,share4,upvote2,upvote3,upvote4;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        message=findViewById(R.id.message);
        pluss=findViewById(R.id.plus);
        pluss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfeed();
            }
        });
        frompst=findViewById(R.id.frompost);
        topst=findViewById(R.id.topost);
        post=findViewById(R.id.post);
        datepst=findViewById(R.id.date);
        desc=findViewById(R.id.description);
        frompst2=findViewById(R.id.frompost2);
        frompst3=findViewById(R.id.frompost3);
        frompst4=findViewById(R.id.frompost4);
        topst2=findViewById(R.id.topost2);
        post2=findViewById(R.id.post2);
        post3=findViewById(R.id.post3);
        post4=findViewById(R.id.post4);
        datepst2=findViewById(R.id.date2);
        datepst3=findViewById(R.id.date3);
        datepst4=findViewById(R.id.date4);
        desc2=findViewById(R.id.description2);
        desc3=findViewById(R.id.description3);
        desc4=findViewById(R.id.description4);
        profp=findViewById(R.id.profpic);
        post.setVisibility(View.INVISIBLE);
        profp.setVisibility(View.INVISIBLE);
        post2.setVisibility(View.INVISIBLE);
        post3.setVisibility(View.INVISIBLE);
        post4.setVisibility(View.INVISIBLE);
        nameV=findViewById(R.id.nameView);
        nameV2=findViewById(R.id.nameView2);
        nameV3=findViewById(R.id.nameView3);
        nameV4=findViewById(R.id.nameView4);
        upvote=findViewById(R.id.upvote);
        vote=findViewById(R.id.vote);
        upvote2=findViewById(R.id.upvote2);
        upvote3=findViewById(R.id.upvote3);
        upvote4=findViewById(R.id.upvote4);
        vote2=findViewById(R.id.vote2);
        vote3=findViewById(R.id.vote3);
        vote4=findViewById(R.id.vote4);
        share=findViewById(R.id.share);
        share2=findViewById(R.id.share2);
        share3=findViewById(R.id.share3);
        share4=findViewById(R.id.share4);
        share.setVisibility(View.INVISIBLE);
        share2.setVisibility(View.INVISIBLE);
        share3.setVisibility(View.INVISIBLE);
        share4.setVisibility(View.INVISIBLE);
        //final boolean[] check = {false};
        upvote.setVisibility(View.INVISIBLE);
        upvote2.setVisibility(View.INVISIBLE);
        upvote3.setVisibility(View.INVISIBLE);
        upvote4.setVisibility(View.INVISIBLE);
        profle=findViewById(R.id.profle);
        profp2=findViewById(R.id.profpic2);
        profp3=findViewById(R.id.profpic3);
        profp4=findViewById(R.id.profpic4);
        databaserefreq=FirebaseDatabase.getInstance().getReference("requests");

     message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(feedact.this,notification.class));
            }
        });
         final GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(feedact.this);
         String nam=acct.getDisplayName();
             databaseReference=FirebaseDatabase.getInstance().getReference().child("Post").child("Vibhu Upmanyu");
                final boolean[] check = {false};
                 databaseReference.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                         if (dataSnapshot.hasChild("date")) {
                             share.setVisibility(View.VISIBLE);
                             final int[] voter = new int[1];
                             upvote.setVisibility(View.VISIBLE);
                             voter[0] = Integer.parseInt(dataSnapshot.child("upvot").getValue().toString());
                             vote.setText(Integer.toString(voter[0]));
                             upvote.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {


                                     if (!(vote.getText().toString()).equals("")) {
                                         if (check[0] == false) {
                                             voter[0] += 1;
                                             vote.setText(Integer.toString(voter[0]));
                                             check[0] = true;
                                             databaseReference.child("upvot").setValue(Integer.toString(voter[0]));
                                             String mai=dataSnapshot.child("mail").getValue().toString();
                                             if(!mai.equals(acct.getEmail()))
                                             {
                                                 naming name1=new naming(mai,acct.getDisplayName(),acct.getEmail());
                                                 databaserefreq.setValue(name1);
                                             }
                                         } else if (check[0] == true) {
                                             voter[0] -= 1;
                                             vote.setText(Integer.toString(voter[0]));
                                             check[0] = false;
                                             databaseReference.child("upvot").setValue(Integer.toString(voter[0]));
                                             databaserefreq.removeValue();
                                         }


                                     }


                                 }
                             });

                             post.setVisibility(View.VISIBLE);
                             final String f = dataSnapshot.child("fromp").getValue().toString();
                             frompst.setText(f);
                             final String t = dataSnapshot.child("top").getValue().toString();
                             topst.setText(t);
                             final String d = dataSnapshot.child("date").getValue().toString();
                             final String tim = dataSnapshot.child("time").getValue().toString();
                             datepst.setText(d + ", " + tim);
                             String ds = dataSnapshot.child("desc").getValue().toString();
                             desc.setText(ds);
                             String p = dataSnapshot.child("pfp").getValue().toString();
                             profp.setVisibility(View.VISIBLE);
                             Glide.with(feedact.this).load(p).into(profp);
                             final String n = dataSnapshot.child("nam").getValue().toString();
                             nameV.setText(n);
                             share.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent intentt = new Intent(Intent.ACTION_SEND);
                                     intentt.setType("text/plain");
                                     String shareBody = "Check out this trip on HopOn by " + n + " going from " + f + " to " + t + " on " + d + " at " + tim;
                                     //String shareSub="A trip from x to z by n";
                                     //intentt.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                                     intentt.putExtra(Intent.EXTRA_TEXT, shareBody);
                                     startActivity(Intent.createChooser(intentt, "Share using"));
                                 }
                             });

                         }

                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });
     databaseReference1=FirebaseDatabase.getInstance().getReference().child("Post").child("YOSHI BANSAL");
     final boolean[] check2 = {false};
     databaseReference1.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull final DataSnapshot dataSnapshot1) {
             if (dataSnapshot1.hasChild("date")) {
                 final String n = dataSnapshot1.child("nam").getValue().toString();
                 share2.setVisibility(View.VISIBLE);
                 final int[] voter2 = new int[1];
                 upvote2.setVisibility(View.VISIBLE);
                 voter2[0] = Integer.parseInt(dataSnapshot1.child("upvot").getValue().toString());
                 vote2.setText(Integer.toString(voter2[0]));
                 upvote2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {


                         if (!(vote2.getText().toString()).equals("")) {
                             if (check2[0] == false) {
                                 voter2[0] += 1;
                                 vote2.setText(Integer.toString(voter2[0]));
                                 check2[0] = true;
                                 databaseReference1.child("upvot").setValue(Integer.toString(voter2[0]));
                                 String mai=dataSnapshot1.child("mail").getValue().toString();
                                 if(!mai.equals(acct.getEmail()))
                                 {
                                     naming name1=new naming(mai,acct.getDisplayName(),acct.getEmail());
                                     databaserefreq.child("2").setValue(name1);
                                 }

                             } else if (check2[0] == true) {
                                 voter2[0] -= 1;
                                 vote2.setText(Integer.toString(voter2[0]));
                                 check2[0] = false;
                                 databaseReference1.child("upvot").setValue(Integer.toString(voter2[0]));
                                 databaserefreq.removeValue();
                             }


                         }


                     }
                 });

                 post2.setVisibility(View.VISIBLE);
                 final String f = dataSnapshot1.child("fromp").getValue().toString();
                 frompst2.setText(f);
                 final String t = dataSnapshot1.child("top").getValue().toString();
                 topst2.setText(t);
                 final String d = dataSnapshot1.child("date").getValue().toString();
                 final String tim = dataSnapshot1.child("time").getValue().toString();
                 datepst2.setText(d + ", " + tim);
                 String ds = dataSnapshot1.child("desc").getValue().toString();
                 desc2.setText(ds);
                 String p = dataSnapshot1.child("pfp").getValue().toString();
                 profp2.setVisibility(View.VISIBLE);
                 Glide.with(feedact.this).load(p).into(profp2);
                 nameV2.setText(n);
                 share2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intentt = new Intent(Intent.ACTION_SEND);
                         intentt.setType("text/plain");
                         String shareBody = "Check out this trip on HopOn by " + n + " going from " + f + " to " + t + " on " + d + " at " + tim;
                         //String shareSub="A trip from x to z by n";
                         //intentt.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                         intentt.putExtra(Intent.EXTRA_TEXT, shareBody);
                         startActivity(Intent.createChooser(intentt, "Share using"));
                     }
                 });

             }

         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });
     databaseReference2=FirebaseDatabase.getInstance().getReference().child("Post").child("josh Sandhu");
     final boolean[] check3 = {false};
     databaseReference2.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull final DataSnapshot dataSnapshot2) {
             if (dataSnapshot2.hasChild("date")) {
                 share3.setVisibility(View.VISIBLE);
                 final int[] voter3 = new int[1];
                 upvote3.setVisibility(View.VISIBLE);
                 voter3[0] = Integer.parseInt(dataSnapshot2.child("upvot").getValue().toString());
                 vote3.setText(Integer.toString(voter3[0]));
                 upvote3.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {


                         if (!(vote3.getText().toString()).equals("")) {
                             if (check3[0] == false) {
                                 voter3[0] += 1;
                                 vote3.setText(Integer.toString(voter3[0]));
                                 check3[0] = true;
                                 databaseReference2.child("upvot").setValue(Integer.toString(voter3[0]));
                                 String mai=dataSnapshot2.child("mail").getValue().toString();
                                 if(!mai.equals(acct.getEmail()))
                                 {
                                     naming name1=new naming(mai,acct.getDisplayName(),acct.getEmail());
                                     databaserefreq.setValue(name1);
                                 }
                             } else if (check3[0] == true) {
                                 voter3[0] -= 1;
                                 vote3.setText(Integer.toString(voter3[0]));
                                 check3[0] = false;
                                 databaseReference2.child("upvot").setValue(Integer.toString(voter3[0]));
                                 databaserefreq.removeValue();
                             }


                         }


                     }
                 });

                 post3.setVisibility(View.VISIBLE);
                 final String f = dataSnapshot2.child("fromp").getValue().toString();
                 frompst3.setText(f);
                 final String t = dataSnapshot2.child("top").getValue().toString();
                 topst3.setText(t);
                 final String d = dataSnapshot2.child("date").getValue().toString();
                 final String tim = dataSnapshot2.child("time").getValue().toString();
                 datepst3.setText(d + ", " + tim);
                 String ds = dataSnapshot2.child("desc").getValue().toString();
                 desc3.setText(ds);
                 String p = dataSnapshot2.child("pfp").getValue().toString();
                 profp3.setVisibility(View.VISIBLE);
                 Glide.with(feedact.this).load(p).into(profp3);
                 final String n = dataSnapshot2.child("nam").getValue().toString();
                 nameV3.setText(n);
                 share3.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intentt = new Intent(Intent.ACTION_SEND);
                         intentt.setType("text/plain");
                         String shareBody = "Check out this trip on HopOn by " + n + " going from " + f + " to " + t + " on " + d + " at " + tim;
                         //String shareSub="A trip from x to z by n";
                         //intentt.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                         intentt.putExtra(Intent.EXTRA_TEXT, shareBody);
                         startActivity(Intent.createChooser(intentt, "Share using"));
                     }
                 });

             }

         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });
     databaseReference3=FirebaseDatabase.getInstance().getReference().child("Post").child("Pranjali Jain");
     final boolean[] check4 = {false};
     databaseReference3.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull final DataSnapshot dataSnapshot3) {
             if (dataSnapshot3.hasChild("date")) {
                 share4.setVisibility(View.VISIBLE);
                 final int[] voter4 = new int[1];
                 upvote4.setVisibility(View.VISIBLE);
                 voter4[0] = Integer.parseInt(dataSnapshot3.child("upvot").getValue().toString());
                 vote4.setText(Integer.toString(voter4[0]));
                 upvote4.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {


                         if (!(vote4.getText().toString()).equals("")) {
                             if (check4[0] == false) {
                                 voter4[0] += 1;
                                 vote4.setText(Integer.toString(voter4[0]));
                                 check4[0] = true;
                                 databaseReference2.child("upvot").setValue(Integer.toString(voter4[0]));
                                 String mai=dataSnapshot3.child("mail").getValue().toString();
                                 if(!mai.equals(acct.getEmail()))
                                 {
                                     naming name1=new naming(mai,acct.getDisplayName(),acct.getEmail());
                                     databaserefreq.setValue(name1);
                                 }
                             } else if (check4[0] == true) {
                                 voter4[0] -= 1;
                                 vote4.setText(Integer.toString(voter4[0]));
                                 check4[0] = false;
                                 databaseReference3.child("upvot").setValue(Integer.toString(voter4[0]));
                                 databaserefreq.removeValue();
                             }


                         }


                     }
                 });

                 post4.setVisibility(View.VISIBLE);
                 final String f = dataSnapshot3.child("fromp").getValue().toString();
                 frompst4.setText(f);
                 final String t = dataSnapshot3.child("top").getValue().toString();
                 topst4.setText(t);
                 final String d = dataSnapshot3.child("date").getValue().toString();
                 final String tim = dataSnapshot3.child("time").getValue().toString();
                 datepst4.setText(d + ", " + tim);
                 String ds = dataSnapshot3.child("desc").getValue().toString();
                 desc4.setText(ds);
                 String p = dataSnapshot3.child("pfp").getValue().toString();
                 profp4.setVisibility(View.VISIBLE);
                 Glide.with(feedact.this).load(p).into(profp4);
                 final String n = dataSnapshot3.child("nam").getValue().toString();
                 nameV4.setText(n);
                 share4.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intentt = new Intent(Intent.ACTION_SEND);
                         intentt.setType("text/plain");
                         String shareBody = "Check out this trip on HopOn by " + n + " going from " + f + " to " + t + " on " + d + " at " + tim;
                         //String shareSub="A trip from x to z by n";
                         //intentt.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                         intentt.putExtra(Intent.EXTRA_TEXT, shareBody);
                         startActivity(Intent.createChooser(intentt, "Share using"));
                     }
                 });

             }

         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });
                 profle.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) { startActivity(new Intent(feedact.this, profile.class)); }
                 });
 }
    public void openfeed(){
        startActivity(new Intent(this,submit.class));
    }

}







