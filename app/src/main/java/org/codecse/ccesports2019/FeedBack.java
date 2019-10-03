package org.codecse.ccesports2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {
    private EditText feedBackedtxt;
    SharedPreferences sharedPreferences;
    public void submit(View view)
    {
        String feedback= feedBackedtxt.getText().toString();
        if(feedback.isEmpty())
            feedBackedtxt.setError("Enter something");
        else {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("FeedBack");
            myRef.push().setValue(feedback);
            Toast.makeText(this, "Feedback Submitted Successfully. Thank you", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putBoolean("feed",true).apply();
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("FeedBack");
        actionBar.setDisplayHomeAsUpEnabled(true);
        feedBackedtxt = (EditText)findViewById(R.id.feedbackview);
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        Boolean hasFeedback= sharedPreferences.getBoolean("feed",false);
        if(hasFeedback)
        {
            feedBackedtxt.setEnabled(false);
            Button sub= (Button)findViewById(R.id.submited);
            sub.setEnabled(false);
            sub.setAlpha(0.5f);
            new AlertDialog.Builder(this,R.style.MyAlertstyle2)
                    .setMessage("You have already submitted Feedback")

                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
