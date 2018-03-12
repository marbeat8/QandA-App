package com.example.marcusmller.qa_app;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by Jonas on 12.03.2018.
 */

public class Connector {

    public static HttpsURLConnection connect(String urlAddress){

        try{
            URL url = new URL (urlAddress);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            // Set Properties
            con.setRequestMethod("POST");
            con.setConnectTimeout(200000);
            con.setReadTimeout(200000);
            con.setDoInput(true);
            con.setDoOutput(true);

            //Return
            return con;
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
