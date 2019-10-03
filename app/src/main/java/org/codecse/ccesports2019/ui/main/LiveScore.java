package org.codecse.ccesports2019.ui.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.codecse.ccesports2019.R;

public class LiveScore extends Fragment {
    DatabaseReference myRef;
    private TextView txtTarus,txtLupus,txtCetus,txtPegasus;
    private ImageView trophy[];
    private View view;
    @Nullable

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    void initialtizeView()
    {
        trophy= new ImageView[4];
        trophy[0]=(ImageView)view.findViewById(R.id.CetusT);
        trophy[1]=(ImageView)view.findViewById(R.id.LupusT);
        trophy[2]=(ImageView)view.findViewById(R.id.PegasusT);
        trophy[3]=(ImageView)view.findViewById(R.id.TaurusT);
        for(int i=0;i<4;i++)
            trophy[i].setVisibility(View.INVISIBLE);

        txtTarus=(TextView)view.findViewById(R.id.score_Tarus);
        txtLupus=(TextView)view.findViewById(R.id.score_Lupus);
        txtPegasus=(TextView)view.findViewById(R.id.score_Pegasus);
        txtCetus=(TextView)view.findViewById(R.id.score_Cetus);

    }
    void activateTrophy(int s[])
    {
        int maxp=0,i;
        for(i=0;i<4;i++)
            trophy[i].setVisibility(View.INVISIBLE);
        for(i=0;i<4;i++)
          if(s[i]>s[maxp] )
              maxp=i;
        for(i=0;i<4;i++)
          if(s[i]==s[maxp])
              trophy[i].setVisibility(View.VISIBLE);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=  inflater.inflate(R.layout.livescore,container,false);
        final  FirebaseDatabase database = com.google.firebase.database.FirebaseDatabase.getInstance();
        myRef = database.getReference("");
        initialtizeView();
        final ProgressDialog dialog=new ProgressDialog(getActivity(),R.style.MyAlertstyle);
        dialog.setCancelable(false);
        dialog.setTitle("Please Wait");
        dialog.setMessage("Loading....");
        dialog.show();
        if(!isNetworkAvailable(getContext()))
        {
            new AlertDialog.Builder(getActivity(),R.style.MyAlertstyle_)
                    .setTitle("Internet Required")
                    .setMessage("Please turn on your data")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        }
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String T= dataSnapshot.child("TeamScore").child("Taurus").getValue().toString();
                String C= dataSnapshot.child("TeamScore").child("Cetus").getValue().toString();
                String P= dataSnapshot.child("TeamScore").child("Pegasus").getValue().toString();
                String L= dataSnapshot.child("TeamScore").child("Lupus").getValue().toString();
                int scores[] = new int[4];
                try {
                    scores[0]=Integer.parseInt(C);
                    scores[1]=Integer.parseInt(L);
                    scores[2]=Integer.parseInt(P);
                    scores[3]=Integer.parseInt(T);
                }
                catch (Exception e)
                {
                    for(int i =0; i<4;i++)
                        scores[i]=0;
                }

                txtTarus.setText(T);
                txtPegasus.setText(P);
                txtCetus.setText(C);
                txtLupus.setText(L);

                activateTrophy(scores);
                dialog.cancel();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  view;
    }
}
