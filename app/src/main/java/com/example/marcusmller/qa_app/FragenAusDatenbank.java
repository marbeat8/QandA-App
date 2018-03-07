package com.example.marcusmller.qa_app;


import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FragenAusDatenbank extends AsyncTask<String, Void, String> {

    public static final String AUTHKEY = "test321";

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";

    private static final String DESTINATION_METHOD = "allEntrys";

    private ArrayAdapter arrayAdapter;
    private TextView textView;
    private ListView listView;
    private  URLConnection conn;

    public FragenAusDatenbank(TextView textView) {
        this.textView = textView;
    }
    public FragenAusDatenbank(ListView listView) {
        this.listView = listView;
    }

    @Override
    public String doInBackground(String... params) {
        try {
            openConnection();
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Öffnet eine Verbindung {@link URLConnection}.
     * @throws IOException
     */
    private void openConnection() throws IOException{
        //StringBuffer für das zusammensetzen der URL
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("authkey", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(AUTHKEY, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        URL url = new URL("https://84-23-78-37.blue.kundencontroller.de:8443/reader.php");
        conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(dataBuffer.toString());
        wr.flush();
    }

    /**
     * Ließt das Ergebnis aus der geöffneten Verbindung.
     * @return liefert ein String mit dem gelesenen Werten.
     * @throws IOException
     */
    private String readResult()throws IOException{
        String result = null;
        //Lesen der Rückgabewerte vom Server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        //Solange Daten bereitstehen werden diese gelesen.
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if(!isBlank(result)) {
            //String[] arr = result.split("\\|");
            this.textView.setText(result);
            String[] rarray= result.split("\\|",-1);
            //arrayAdapter = new ArrayAdapter(this, R.layout.activity_main, rarray);
            //ArrayAdapter<String> adapter;
            //adapter = new ArrayAdapter<String>(this,
                   // android.R.layout.simple_list_item_1, android.R.id.text1, rarray);
            //listView.setAdapter(arrayAdapter);
            //this.listView(rarray);
        }
    }

    private boolean isBlank(String value){
        return value == null || value.trim().isEmpty();
    }


}