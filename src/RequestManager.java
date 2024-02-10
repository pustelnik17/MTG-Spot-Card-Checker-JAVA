//import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class RequestManager {

    public static void fetch(String itemName) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://mtgspot.cn-panel.pl/products?s_title=" + encodeUrl(itemName) + "&limit=25&offset=0&order_by=title&sort_by=asc"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());



        System.out.println(response.body());

    }
    private static String encodeUrl(String s){
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }
}   