import java.io.FileNotFoundException;
import java.util.List;

public class CardManager {
    public static void main(String[] args) throws Exception {
        List<String> data = parse(FileReader.getLines("C:\\Users\\golde\\IdeaProjects\\MTGSpot\\src\\data.txt"));
        for (String datum : data) {
            RequestManager.fetch(datum);
        }
    }
    private static List<String> parse(List<String> result) {
        result.replaceAll(s -> s.replaceFirst("[0-9]* ", ""));
        return result;
    }
}
