package sixth.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

      //  Path path = Paths.get("./DAY6/src/main/java/sixth/day/pl/smallInput6");
        Path path = Paths.get("./DAY6/src/main/java/sixth/day/pl/sixthData");
        String signal = Files.readString(path);
        System.out.println(signal);
        String[] letters = signal.split("");
//        int numberOfSigns = 0;
        int messageMarker = 0;

        for (int i = 0; i < letters.length - 13; i++) {  // -3 w pierwszej części zamiast -13
            // part 1:
//            List<String> distinct = Stream.of(letters[i], letters[i + 1], letters[i + 2], letters[i + 3]).distinct().toList();
//            if (distinct.size() == 4) {
//                numberOfSigns = i + 4;
//               // break;
//            }
            final int n = i;
            List<String> distinct2 = IntStream.rangeClosed(0, 13).mapToObj(x -> letters[n + x])
                    .distinct().toList();
            if (distinct2.size() == 14) {
                messageMarker = i + 14;
                break;
            }

        }
        // part 1:
        //  System.out.println(numberOfSigns);
        // part 2:
        System.out.println(messageMarker);
    }
}
