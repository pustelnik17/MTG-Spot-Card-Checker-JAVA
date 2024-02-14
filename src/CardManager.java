import Requests.RequestManager;
import Requests.ResponseCallBack;

import java.util.ArrayList;
import java.util.List;

public class CardManager {
    public static void main(String[] args) throws Exception {
        List<String> data = parseInputString(FileReader.getLines("C:\\Users\\golde\\IdeaProjects\\MTGSpot\\src\\Resources\\data.txt"));
        List<List<Float>> response = new ArrayList<>();
        RequestManager.fetch(data, new ResponseCallBack() {
            @Override
            public void onResponse(List<Float> list) {
                response.add(list);
            }

            @Override
            public void onError(Exception exception) {
                System.out.println("FETCH ERROR");
            }
        });
        for (List<Float> item : response)
            System.out.println(item);
    }

    private static List<String> parseInputString(List<String> result) {
        result.replaceAll(s -> s.replaceFirst("[0-9]* ", ""));
        return result;
    }
}
