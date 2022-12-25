package fourth.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

      //  Path path = Paths.get("./DAY4/src/main/java/fourth/day/pl/smallInput4");
        Path path = Paths.get("./DAY4/src/main/java/fourth/day/pl/fourthData");
        List<String> list = Files.readAllLines(path);
        System.out.println(list);
        int numberOfContained = 0;
        int overlappingNumber = 0;
        for (String value : list) {
            String[] twoElves = value.split(",");
            final String[] stringNum1 = twoElves[0].split("-");
            Set<Integer> set1 = IntStream
                    .rangeClosed(Integer.parseInt(stringNum1[0]), Integer.parseInt(stringNum1[1]))
                    .boxed()
                    .collect(Collectors.toSet());
            final String[] stringNum2 = twoElves[1].split("-");
            Set<Integer> set2 = IntStream
                    .rangeClosed(Integer.parseInt(stringNum2[0]), Integer.parseInt(stringNum2[1]))
                    .boxed()
                    .collect(Collectors.toSet());
            if (set1.containsAll(set2) || set2.containsAll(set1)) {
                numberOfContained++;
            }
            overlappingNumber += searchForOverlapping(set1, set2);
        }
        // answer to part 1:
        System.out.println("Liczba par, gdzie elf zawiera elfa: " + numberOfContained);

        System.out.println("------------------------");
        // answer to part 2:
        System.out.println("Liczba par z częścia wspólną: " + overlappingNumber);

    }
    static int searchForOverlapping(Set<Integer> set1, Set<Integer> set2) {
        set1.retainAll(set2);
        if (!set1.isEmpty()) {
            return 1;
        }
        return 0;
    }
}
