package com.example.marcusmller.qa_app;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Jonas on 12.03.2018.
 */

public class DataPackagerAnswer {
    String antwort, user,frageID;

    public DataPackagerAnswer(String antwort, String user,String frageID) {
        this.antwort = antwort;
        this.user = user;
        this.frageID = frageID;
    }


    public String packData() {
        JSONObject jObj = new JSONObject();
        StringBuffer packedData = new StringBuffer();
        try {
            jObj.put("antwort", antwort);
            jObj.put("user", user);
            jObj.put("frageID", frageID);

            Boolean firstValue = true;
            Iterator it = jObj.keys();
            do {
                String key = it.next().toString();
                String value = jObj.get(key).toString();
                if (firstValue) {
                    firstValue = false;
                } else {
                    packedData.append("&");
                }
                packedData.append(URLEncoder.encode(key, "UTF-8"));
                packedData.append("=");
                packedData.append(URLEncoder.encode(value, "UTF-8"));
            } while (it.hasNext());
            return packedData.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
