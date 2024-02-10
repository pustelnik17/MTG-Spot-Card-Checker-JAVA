import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CardManager {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print(parse());
    }
    private static List<String> parse() throws FileNotFoundException {
        List<String> result = FileReader.getLines();
        for(int i = 0; i < result.size(); i++){
            result.set(i, result.get(i).replaceFirst("[0-9]* ", ""));
        }
        return result;
    }
}
