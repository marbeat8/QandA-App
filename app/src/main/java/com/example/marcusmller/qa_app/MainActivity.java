package com.example.marcusmller.qa_app;


import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        try {
            getQuestionFromDB();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //Fragen aus DB laden
    private static void getQuestionFromDB() throws ParseException {
        try {
            //Fuer Fragen
            FragenAusDatenbank dbAbfrage = new FragenAusDatenbank("https://84-23-78-37.blue.kundencontroller.de:8443/reader.php");
            dbAbfrage.setDestMethod("allEntrys");
            dbAbfrage.setCOLUMN("");
            dbAbfrage.setTABLE("");
            dbAbfrage.setCONDITION("");
            String dbResponse = "";
            dbResponse = dbAbfrage.doInBackground();
            //ArrayListen fuer Fragen
            ArrayList<String> arrListFrage, arrListFragenUser, arrListZeit, arrListID = new ArrayList<String>();
            arrListFragenUser = dbAbfrage.jsonToArrList(dbResponse, "User");
            arrListFrage = dbAbfrage.jsonToArrList(dbResponse, "Frage");
            arrListZeit = dbAbfrage.jsonToArrList(dbResponse, "time");
            arrListID = dbAbfrage.jsonToArrList(dbResponse, "FrageID");
            //Fuer Antworten
            dbAbfrage.setURL("https://84-23-78-37.blue.kundencontroller.de:8443/getAnswer.php");
            dbResponse = dbAbfrage.doInBackground();
            //ArrayListen fuer Antworten
            ArrayList<String> arrListAWFrageID,arrListAWAntwort,arrListAWUser, arrListAWTime= new ArrayList<String>();
            arrListAWAntwort = dbAbfrage.jsonToArrList(dbResponse,"Antwort");
            arrListAWUser = dbAbfrage.jsonToArrList(dbResponse,"User");
            arrListAWFrageID = dbAbfrage.jsonToArrList(dbResponse,"FrageID");
            arrListAWTime = dbAbfrage.jsonToArrList(dbResponse,"time");
            for (int i = 0; i < arrListFrage.size(); i++) {
                //Datum umwandeln dazu erst aus DB auslesen und anschliessend ins richtige Format umwandeln
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = dateFormat.parse(arrListZeit.get(i));
                SimpleDateFormat dateFormatGer = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY);
                //Pruefen ob es eine Antwort fuer die Frage gibt
                if (arrListAWFrageID.contains(arrListID.get(i)) )
                {
                    int indexOfAnswer = arrListAWFrageID.indexOf(arrListID.get(i));
                    //Datum umwandeln dazu erst aus DB auslesen und anschliessend ins richtige Format umwandeln
                    SimpleDateFormat dateFormatAW = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dateAW = dateFormatAW.parse(arrListAWTime.get(indexOfAnswer));
                    SimpleDateFormat dateFormatGerAW = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY);
                    //Frage und Antwort aus DB in Liste schreiben
                    FragmentOne.list.add(arrListFragenUser.get(i) + " am " + dateFormatGer.format(date) + " Uhr:\n" + arrListFrage.get(i)+ " ✔" + "\n\r am " +dateFormatGerAW.format(dateAW)+ " Uhr ⇒ " + arrListAWAntwort.get(indexOfAnswer) + " (" + arrListAWUser.get(indexOfAnswer) + ")");
                    //FragmentOne.adapter.add(arrListFragenUser.get(i) + " am " + dateFormatGer.format(date) + " Uhr:\n" + arrListFrage.get(i)+ " ✔" + "\n\r am " +dateFormatGerAW.format(dateAW)+ " Uhr ⇒ " + arrListAWAntwort.get(indexOfAnswer) + " (" + arrListAWUser.get(indexOfAnswer) + ")");
                }
                else {
                    //Frage aus db in Liste schreiben
                    FragmentOne.list.add(arrListFragenUser.get(i) + " am " + dateFormatGer.format(date) + " Uhr:\n" + arrListFrage.get(i));
                    //FragmentOne.adapter.add(arrListFragenUser.get(i) + " am " + dateFormatGer.format(date) + " Uhr:\n" + arrListFrage.get(i));
                }
            }

        }
        catch (ParseException pE) {
            Log.d("Error",pE.getMessage().toString());
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
        //Aktualisieren
        if (id == R.id.refresh) {
            refreshListview();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void refreshListview(){
        FragmentOne.adapter.clear();
        try {
           FragmentOne.list.clear();
           FragmentOne.adapter.clear();
            getQuestionFromDB();
            FragmentOne.adapter.notifyDataSetChanged();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

