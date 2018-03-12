package com.example.marcusmller.qa_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Jonas on 12.03.2018.
 */

public class Sender extends AsyncTask<Void,Void,String> {
    String urlAddress;
    String frage,user;

    public Sender(String urlAdress,String frage, String user){
        this.urlAddress = urlAdress;
        this.frage = frage;
        this.user = user;
    }
    @Override
    protected String doInBackground(Void... params) {
        return this.send();
    }

    private String send(){
        //Connect
        HttpsURLConnection con = Connector.connect(urlAddress);
        if(con == null){
            return null;
        }
        try {
            OutputStream outStr = con.getOutputStream();
            //Write
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStr,"UTF-8"));
            bw.write(new DataPackagerQuestion(frage,user).packData());

            //Release
            bw.flush();
            bw.close();
            outStr.close();

            //Succesful?
            int responseCode =con.getResponseCode();
            if(responseCode == con.HTTP_OK){
                //Get Exact Response
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                String line ;

                //Read Line by Line
                while((line=br.readLine())!= null){
                    response.append(line);
                }
                //Release
                br.close();

                return response.toString();
            }
            else{

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
