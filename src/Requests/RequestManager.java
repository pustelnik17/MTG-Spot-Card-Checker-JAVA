package Requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Requests.ResponseCallBack;
import org.json.*;

public class RequestManager {
    public static void fetch(List<String> data, ResponseCallBack responseCallBack) throws InterruptedException {
        System.out.println(Thread.activeCount());
        List<Thread> requestThreads = new ArrayList<>();
        for (String itemName : data){
            Thread tempThread = new Thread(() -> {
                try {
                    String request = "https://mtgspot.cn-panel.pl/products?s_title=" + URLEncoder.encode(itemName, StandardCharsets.UTF_8) + "&limit=25&offset=0&order_by=title&sort_by=asc";
                    HttpURLConnection conn = (HttpURLConnection) new URL(request).openConnection();
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line, text = "";
                    while((line = br.readLine()) != null) {
                        text = line;
                    }
                    br.close();
                    responseCallBack.onResponse(getDataListFromJson(text));
                } catch (IOException e) {
                    responseCallBack.onError(e);
                }
            });
            requestThreads.add(tempThread);
            tempThread.start();
        }
        for (Thread thread : requestThreads){
            thread.join();
        }
        System.out.println(Thread.activeCount());
    }

    private static List<Float> getDataListFromJson(String string) {
        JSONArray responseData = new JSONObject(string).getJSONArray("data");
        List<Float> result = new ArrayList<>();
        for (int i=0; i<responseData.length(); i++){
            JSONObject temp = (JSONObject) responseData.get(i);
            if ((int) temp.get("stock") > 0 && temp.get("rarity") != "Tip Card") {
                result.add(Float.parseFloat(temp.get("price").toString()));
            }
        }
        return result;
    }

//    private static HashMap<String, Double> getMinimalPrices(String string) {
//        HashMap<String, Double> result = new HashMap<>();
//        JSONArray responseData = new JSONObject(string).getJSONArray("data");
//        List<do> prices = new ArrayList<>();
//        for (int i=0; i<responseData.length(); i++){
//            JSONObject temp = (JSONObject) responseData.get(i);
//            if ((int) temp.get("stock") > 0 && temp.get("rarity") != "Tip Card") {
//                prices.add((double) temp.get("price"));
//            }
//            for (String s : prices)
//                System.out.println(s.getClass().getName());
//        }
//        return result;
//    }
}   