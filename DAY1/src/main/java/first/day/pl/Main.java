package first.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("./DAY1/src/main/java/first/day/pl/firstData");
   //     Path path = Paths.get("./DAY1/src/main/java/first/day/pl/smallInput1");
        List<String> list = Files.readAllLines(path);
        System.out.println(list);
        List<Integer> sums = new ArrayList<>();
        list = list.stream().dropWhile(""::equals).collect(Collectors.toList());
        System.out.println(list);
        int sum = 0;
        for (String l : list) {
            if (!"".equals(l)) {
                sum += Integer.parseInt(l);
                if (l.equals(list.get(list.size() - 1))) {
                    sums.add(sum);
                }
            } else {
                sums.add(sum);
                sum = 0;
            }
        }
        int maximum = sums.stream().mapToInt(x -> x).max().getAsInt();
        //answer to part 1:
        System.out.printf("Maximum = %s%n", maximum);

        Collections.sort(sums);
        Collections.reverse(sums);
        System.out.println(sums.get(0)); // it is also answer to part 1
        //answer to part 2:
        System.out.println(sums.get(0) + sums.get(1) + sums.get(2));
    }
}
