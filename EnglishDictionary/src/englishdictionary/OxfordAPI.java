/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package englishdictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author shsel
 */
public class OxfordAPI {

    protected static String searchWord(String word) {

        String language = "en";
        String word_to_lowercase = word.toLowerCase();

        return "https://od-api.oxforddictionaries.com/api/v1/entries/" + language + "/" + word_to_lowercase;
    }

    protected static String retrieveData(String... params) {

        String app_id = "d2410595";
        String app_key = "bcd88f607bf222a2e586ed7c51b65a26";

        try {
            URL url = new URL(params[0]);

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringbuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                stringbuilder.append(line + "\n");
            }

            //return stringbuilder.toString();
            JSONObject json = new JSONObject(stringbuilder.toString());
            JSONArray array = json.getJSONArray("results");
            JSONObject json1 = array.getJSONObject(0);
            JSONArray array2 = json1.getJSONArray("lexicalEntries");
            JSONObject json2 = array2.getJSONObject(0);
            JSONArray array3 = json2.getJSONArray("entries");
            JSONObject json3 = array3.getJSONObject(0);
            JSONArray array4 = json3.getJSONArray("etymologies");
            String lexicalCategory = json2.getString("lexicalCategory");
            return "Can be a " + lexicalCategory + "\n" 
                    + array4.getString(0) + "\n" 
                    +"Type: "+ json1.getString("type");

        } catch (IOException | JSONException e) {
            return e.toString();
        }
    }
}
