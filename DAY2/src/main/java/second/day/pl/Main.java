package second.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Map<String, Integer> scoreMap = Map.of(
            "A X", 4,
            "A Y", 8,
            "A Z", 3,
            "B X", 1,
            "B Y", 5,
            "B Z", 9,
            "C X", 7,
            "C Y", 2,
            "C Z", 6
    );
    private static final Map<String, Integer> scoreMap2 = Map.of(
            "A X", 3,
            "A Y", 4,
            "A Z", 8,
            "B X", 1,
            "B Y", 5,
            "B Z", 9,
            "C X", 2,
            "C Y", 6,
            "C Z", 7
    );
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("./DAY2/src/main/java/second/day/pl/secondData");
    //    Path path = Paths.get("./DAY2/src/main/java/second/day/pl/smallInput2");
        List<String> list = Files.readAllLines(path);
        System.out.println(list);
        int sum1 = list.stream().mapToInt(scoreMap::get).sum();
        //answer to part 1:
        System.out.println("Game score: " + sum1);
        int sum2 = list.stream().mapToInt(scoreMap2::get).sum();
        //Answer to part 2:
        System.out.println("Game score: " + sum2);
    }
}
