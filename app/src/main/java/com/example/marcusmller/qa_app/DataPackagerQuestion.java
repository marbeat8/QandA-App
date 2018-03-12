package com.example.marcusmller.qa_app;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Jonas on 12.03.2018.
 */

public class DataPackagerQuestion {
    String frage, user;

    public DataPackagerQuestion(String frage, String user) {
        this.frage = frage;
        this.user = user;
    }


    public String packData() {
        JSONObject jObj = new JSONObject();
        StringBuffer packedData = new StringBuffer();
        try {
            jObj.put("Frage", frage);
            jObj.put("User", user);

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
