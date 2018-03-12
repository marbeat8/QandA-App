package com.example.marcusmller.qa_app;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FragenAusDatenbank extends AsyncTask<String, Void, String> {

    public static final String AUTHKEY = "test321";

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";

    private static String DESTINATION_METHOD = "allEntrys";
    private static String COLUMN = "";
    private static String TABLE = "";
    private static String CONDITION = "";
    private String url = "";

    private ArrayAdapter arrayAdapter;
    private  URLConnection conn;

    public FragenAusDatenbank(String urlTmp) {
        this.url = urlTmp;
    }
    public FragenAusDatenbank(){ this.url = "https://84-23-78-37.blue.kundencontroller.de:8443/reader.php";}

    protected void setDestMethod(String method){ DESTINATION_METHOD = method; }
    protected void setCOLUMN(String column){ COLUMN = column; }
    protected void setTABLE(String table){ TABLE = table; }
    protected void setCONDITION(String conditionn){ CONDITION = conditionn; }
    protected String getDestinationMethod() { return DESTINATION_METHOD; }

    @Override
    /**
     * Ruft die Methoden fuer die Rueckgabe des Strings auf
     */
    public String doInBackground(String... params) {
        try {
            openConnection();
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void setURL(String urlTmp){
        this.url = urlTmp;
    }
    /**
     * Wandelt den JSON String in eine ArrayList um
     */
    protected ArrayList<String> jsonToArrList(String response, String column) {
        ArrayList<String> arrList = new ArrayList<String>();
        try {
            JSONArray jArray = new JSONArray(response);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                arrList.add(json_data.getString(column));
            }
            return arrList;

        } catch (Exception e) {
            e.printStackTrace();
            arrList.add(e.toString());
            return arrList;
        }
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
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("column", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(COLUMN, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("table", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(TABLE, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("condition", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(CONDITION, "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        URL url = new URL(this.url);
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