package org.codecse.ccesports2019.ui.main;

import android.os.Bundle;
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
import org.codecse.ccesports2019.ResultData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results extends Fragment {
    private ListView resultListView;
    private ResultData data;
    private ArrayList<ResultData> ar;
    private void setRData(ArrayList<ResultData> data)
    {
        final List<Map<String,String>> schedule = new ArrayList<Map<String,String>>();
        Map<String,String> map;

        for(int i=0;i<data.size();i++) {
            String mainElt= data.get(i).getEName()+"\n";
            String subElt="First: "+data.get(i).getFirst()+"\nSecond: "+data.get(i).getSecond()+"\nThird: "+data.get(i).getThird()+"\n";
            map = new HashMap();
            map.put("var1", mainElt);
            map.put("var2", subElt);
            schedule.add(i,map);
        }


        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),schedule,android.R.layout.simple_list_item_2,new String[]{"var1","var2"},new int[]{android.R.id.text1,android.R.id.text2});
        resultListView.setAdapter(simpleAdapter);
    }
    @Nullable
    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.results,container,false);
        resultListView=view.findViewById(R.id.resultList);
        final FirebaseDatabase database = com.google.firebase.database.FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        DatabaseReference child = myRef.child("Results");
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data= new ResultData();
                ar= new ArrayList<ResultData>();
                for (DataSnapshot ds:dataSnapshot.getChildren()) {

                    data=ds.getValue(ResultData.class);
                    ar.add(data);

                }


                setRData(ar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }
}
