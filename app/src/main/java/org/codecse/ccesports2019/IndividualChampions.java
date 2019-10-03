package org.codecse.ccesports2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IndividualChampions extends AppCompatActivity {
     private DatabaseReference myRef;
     TextView boys,girls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_champions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Individual Champions");
        actionBar.setDisplayHomeAsUpEnabled(true);
        boys=(TextView)findViewById(R.id.boysct);
        girls=(TextView)findViewById(R.id.girlsct);
        final FirebaseDatabase database = com.google.firebase.database.FirebaseDatabase.getInstance();
        myRef = database.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String boysCham=dataSnapshot.child("Individual").child("Boys").getValue().toString();
                String girlsCham=dataSnapshot.child("Individual").child("Girls").getValue().toString();
                boys.setText(boysCham);
                girls.setText(girlsCham);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
