package org.codecse.ccesports2019;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import org.codecse.ccesports2019.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1,false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.exit:https://play.google.com/store/apps/details?id=
                        finishAffinity();
                        System.exit(0);
                        break;

                    case R.id.ic:
                        Intent intent3 = new Intent(MainActivity.this,IndividualChampions.class);
                        startActivity(intent3);
                        break;

                    case R.id.feed:
                        Intent intent = new Intent(MainActivity.this,FeedBack.class);
                        startActivity(intent);
                        break;

                    case R.id.share:
                        Intent shareIntent=  new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT,"Download CCE Sports 2019 app at: https://play.google.com/store/apps/details?id=org.codecse.ccesports2019");
                        shareIntent.setType("text/plain");
                        startActivity(shareIntent);
                        break;

                    case R.id.abtus:
                        Intent intent2 = new Intent(MainActivity.this,About_Us.class);
                        startActivity(intent2);
                        break;


                }
                return false;
            }
        });



    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}