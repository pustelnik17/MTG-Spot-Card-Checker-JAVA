import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class RequestManager {

    public static void fetch(String itemName) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://mtgspot.cn-panel.pl/products?s_title=" + encodeUrl(itemName) + "&limit=25&offset=0&order_by=title&sort_by=asc"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());


//        JSONObject json = json(response.body());
//
        System.out.println(getList(response.body()));

    }
    private static String encodeUrl(String s){
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    public static List<String> getList(String string) {
        JSONArray responseData = new JSONObject(string).getJSONArray("data");
        List<String> result = new ArrayList<>();
        for (int i=0; i<responseData.length(); i++){
            JSONObject temp = (JSONObject) responseData.get(i);
            if ((int) temp.get("stock") > 0 && temp.get("rarity") != "Tip Card") {
                result.add(temp.get("price").toString());
            }
        }
        return result;
    }
}   