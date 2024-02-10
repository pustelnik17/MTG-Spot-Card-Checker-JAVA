import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static List<String> getLines() throws FileNotFoundException {
        List<String> result = new ArrayList<>();
        File file = new File("C:\\Users\\golde\\IdeaProjects\\MTGSpot\\src\\data.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNextLine()){
            result.add(scan.nextLine());
        }
        return result;
    }
}
