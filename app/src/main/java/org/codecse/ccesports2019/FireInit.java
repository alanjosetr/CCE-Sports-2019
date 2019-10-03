package org.codecse.ccesports2019;


import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class FireInit extends Application {
    private DatabaseReference mDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}
