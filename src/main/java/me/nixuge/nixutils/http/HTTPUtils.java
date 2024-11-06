package me.nixuge.nixutils.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import me.nixuge.nixutils.misc.Pair;

public class HTTPUtils {
    @Deprecated
    private static Pair<Integer, String> sendRequest(String method, String url) throws Exception {
        return sendRequest(method, url, null);
    }
    @Deprecated
    private static Pair<Integer, String> sendRequest(String method, String url, String body) throws Exception {
        // Make objects
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Set properties
        con.setRequestMethod(method);
        con.setRequestProperty("User-Agent", "VelocityCore 0.0.1");
        con.setRequestProperty("Content-Type", "application/json");

        // Set body
        if (method == "POST" && body != null) {
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
            osw.write(body);
            osw.flush();
            osw.close();
            os.close();
        }
        
        // Read received body & convert to a hashmap
        BufferedReader br = null;
        if (con.getResponseCode() == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        StringBuilder responseBodyBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            responseBodyBuilder.append(line);
        }
        br.close();
        
        return new Pair<>(con.getResponseCode(), responseBodyBuilder.toString());
    }
    @Deprecated
    public static Pair<Integer, String> sendJsonPOST(String url, String jsonString) throws Exception {
        return sendRequest("POST", url, jsonString);
    }
    @Deprecated
    public static Pair<Integer, String> sendGET(String url) throws Exception {
        return sendRequest("GET", url);
    }
}
