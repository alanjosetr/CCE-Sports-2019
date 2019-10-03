package org.codecse.ccesports2019.ui.main;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.codecse.ccesports2019.R;
import org.codecse.ccesports2019.ScheduleData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule extends Fragment {
    private ListView scheduleListView;
    ScheduleData data;
    ArrayList<ScheduleData> ar;
    Map<String,String> map;
    void setData(ArrayList<ScheduleData> data)
    {
        final List<Map<String,String>> schedule = new ArrayList<Map<String,String>>();
        Map<String,String> map;

        for(int i=0;i<data.size();i++) {
            String mainElt= data.get(i).getName()+"   "+data.get(i).getTime();
            String subElt=data.get(i).getStatus();
            map = new HashMap();
            map.put("var1", mainElt);
            map.put("var2", subElt);
            schedule.add(i,map);
        }


        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),schedule,android.R.layout.simple_list_item_2,new String[]{"var1","var2"},new int[]{android.R.id.text1,android.R.id.text2});
        scheduleListView.setAdapter(simpleAdapter);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.schedule,container,false);
        scheduleListView=view.findViewById(R.id.schedulelistview);
        final FirebaseDatabase database = com.google.firebase.database.FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        DatabaseReference child = myRef.child("Schedule");
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data= new ScheduleData();
                ar= new ArrayList<ScheduleData>();
                for (DataSnapshot ds:dataSnapshot.getChildren()) {

                    data=ds.getValue(ScheduleData.class);
                    ar.add(data);

                }


                setData(ar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;

    }
}
