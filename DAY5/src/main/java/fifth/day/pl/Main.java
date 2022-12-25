package fifth.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final Map<Integer, List<String>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {

     //   Path path = Paths.get("./DAY5/src/main/java/fifth/day/pl/smallInput5");
        Path path = Paths.get("./DAY5/src/main/java/fifth/day/pl/fifthData");
        List<String> list = Files.readAllLines(path);
        System.out.println(list);
        List<String> kol1 = new ArrayList<>();
        List<String> kol2 = new ArrayList<>();
        List<String> kol3 = new ArrayList<>();
        List<String> kol4 = new ArrayList<>();
        List<String> kol5 = new ArrayList<>();
        List<String> kol6 = new ArrayList<>();
        List<String> kol7 = new ArrayList<>();
        List<String> kol8 = new ArrayList<>();
        List<String> kol9 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String row = list.get(i);
            int len = row.length();
            if (len >= 2 && ' ' != row.charAt(1)) {
                kol1.add(String.valueOf(row.charAt(1)));
            }
            if (len >= 6 && ' ' != row.charAt(5)) {
                kol2.add(String.valueOf(row.charAt(5)));
            }
            if (len >= 10 && ' ' != row.charAt(9)) {
                kol3.add(String.valueOf(row.charAt(9)));
            }
            if (len >= 14 && ' ' != row.charAt(13)) {
                kol4.add(String.valueOf(row.charAt(13)));
            }
            if (len >= 18 && ' ' != row.charAt(17)) {
                kol5.add(String.valueOf(row.charAt(17)));
            }
            if (len >= 22 && ' ' != row.charAt(21)) {
                kol6.add(String.valueOf(row.charAt(21)));
            }
            if (len >= 26 && ' ' != row.charAt(25)) {
                kol7.add(String.valueOf(row.charAt(25)));
            }
            if (len >= 30 && ' ' != row.charAt(29)) {
                kol8.add(String.valueOf(row.charAt(29)));
            }
            if (len >= 34 && ' ' != row.charAt(33)) {
                kol9.add(String.valueOf(row.charAt(33)));
            }
        }
        map.put(1, kol1);
        map.put(2, kol2);
        map.put(3, kol3);
        map.put(4, kol4);
        map.put(5, kol5);
        map.put(6, kol6);
        map.put(7, kol7);
        map.put(8, kol8);
        map.put(9, kol9);
        List<Integer> times = new ArrayList<>();
        List<Integer> listFrom = new ArrayList<>();
        List<Integer> listTo = new ArrayList<>();
        for (int j = 10; j < list.size(); j++) {
            String[] array = list.get(j).split(" from ");
            times.add(Integer.parseInt(array[0].replace("move ", "")));
            String[] array2 = array[1].split(" to ");
            listFrom.add(Integer.parseInt(array2[0]));
            listTo.add(Integer.parseInt(array2[1]));
        }
//        System.out.println(times);
//        System.out.println(listFrom);
//        System.out.println(listTo);
//        System.out.println(times.size());
        for (int j = 0; j < times.size(); j++) {
            int ileSztuk = times.get(j);
            for (int k = ileSztuk - 1; k >= 0 ; k--) {
                String s = map.get(listFrom.get(j)).get(k); // part 1
            //    String s = map.get(listFrom.get(j)).get(0);  // part 2
                map.get(listTo.get(j)).add(0, s);   // part 1 and 2
            //    map.get(listFrom.get(j)).remove(0);  // part 1
                map.get(listFrom.get(j)).remove(k);  // part 2
            }
        }
        // commented (up) parts help to solve part 1
        // answer to both parts:
        System.out.println(
                kol1.get(0) + kol2.get(0) + kol3.get(0)
                + kol4.get(0) + kol5.get(0) + kol6.get(0)
                + kol7.get(0) + kol8.get(0) + kol9.get(0));
    }
}
