package com.example.marcusmller.qa_app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

import android.widget.SearchView;

public class MainActivity extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "Q&A");
        adapter.addFragment(new FragmentTwo(), "Links");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //Fragen aus Datenbank laden
        getQuestionFromDB();
    }
    //Fragen aus DB laden
    private void getQuestionFromDB(){
        FragenAusDatenbank dbAbfrage = new FragenAusDatenbank();
        String dbResponse = "";
        dbResponse = dbAbfrage.doInBackground();
        ArrayList<String> arrListFrage,arrListFragenUser = new ArrayList<String>();
        arrListFragenUser = dbAbfrage.jsonToArrList(dbResponse,"User");
        arrListFrage = dbAbfrage.jsonToArrList(dbResponse,"Frage");
        Log.d("Test: ",""+arrListFrage.size());
        for(String str : arrListFragenUser){
            Log.d("Test: ",str);
        }
        for(int i =0;i<arrListFrage.size(); i++){
            //Frage aus db in Liste schreiben
            FragmentOne.list.add(arrListFragenUser.get(i)+" am DatumFehltNoch:\n"+arrListFrage.get(i));
        }
    }
    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item =menu.findItem(R.id.search);
        SearchView searchView =(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                FragmentOne.adapter.getFilter().filter(newText);
                FragmentTwo.adapter.getFilter().filter(newText);

               return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    //Itemauswahl in der Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            //öffne Fenster AboutUS
            startActivity(new Intent(MainActivity.this, About.class));
        }
        if (id == R.id.bewerten) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScxqs3RL82bOAAVEUb4T5qZilYdpIKSnDRU1QlVdd_9zmvyzw/viewform?usp=sf_link"));
            startActivity(browserIntent);           // Browser oeffnen
        }
        //Aktualiesieren
        if (id == R.id.refresh) {


        }
        return super.onOptionsItemSelected(item);
    }
}

