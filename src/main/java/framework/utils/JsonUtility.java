package framework.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtility {

    public static boolean isJSON(String testString){
        LoggerUtility.info("JSON format validation");
        try {
            new JSONObject(testString);
            return true;
        } catch (JSONException ex) {
            try {
                new JSONArray(testString);
                return true;
            } catch (JSONException ex1) {
                return false;
            }
        }
    }
}