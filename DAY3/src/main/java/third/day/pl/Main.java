package third.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class Main {
    private static final Map<String, Integer> lettersValueMap = Map.ofEntries(
            entry("a",1),
            entry("b",2),
            entry("c",3),
            entry("d",4),
            entry("e",5),
            entry("f",6),
            entry("g",7),
            entry("h",8),
            entry("i",9),
            entry("j",10),
            entry("k",11),
            entry("l",12),
            entry("m",13),
            entry("n",14),
            entry("o",15),
            entry("p",16),
            entry("q",17),
            entry("r",18),
            entry("s",19),
            entry("t",20),
            entry("u",21),
            entry("v",22),
            entry("w",23),
            entry("x",24),
            entry("y",25),
            entry("z",26),
            entry("A",27),
            entry("B",28),
            entry("C",29),
            entry("D",30),
            entry("E",31),
            entry("F",32),
            entry("G",33),
            entry("H",34),
            entry("I",35),
            entry("J",36),
            entry("K",37),
            entry("L",38),
            entry("M",39),
            entry("N",40),
            entry("O",41),
            entry("P",42),
            entry("Q",43),
            entry("R",44),
            entry("S",45),
            entry("T",46),
            entry("U",47),
            entry("V",48),
            entry("W",49),
            entry("X",50),
            entry("Y",51),
            entry("Z",52)
    );

    public static void main(String[] args) throws IOException {
       // Path path = Paths.get("./DAY3/src/main/java/third/day/pl/smallInput3");
        Path path = Paths.get("./DAY3/src/main/java/third/day/pl/thirdData");
        List<String> list = Files.readAllLines(path);
        System.out.println(list);

        //część pierwsza zadania
        int suma = 0;
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            int lenItem = item.length();
            String left = item.substring(0, lenItem / 2);
            String right = item.substring(lenItem / 2);
            List<String> leftList = Arrays.stream(left.split("")).collect(Collectors.toList());
            List<String> rightList = Arrays.stream(right.split("")).collect(Collectors.toList());
            leftList.retainAll(rightList);
            System.out.println(leftList);
            suma += lettersValueMap.get(leftList.get(0));
        }
        System.out.println(suma);

        //część druga zadania
        System.out.println("-----------------------------------------");
        List<String> commonLetters = new ArrayList<>();
        for (int i = 0; i < list.size(); i +=3) {
            List<List<String>> threeWords = new ArrayList<>();
            threeWords.add(Arrays.stream(list.get(i).split("")).collect(Collectors.toList()));
            threeWords.add(Arrays.stream(list.get(i + 1).split("")).collect(Collectors.toList()));
            threeWords.add(Arrays.stream(list.get(i + 2).split("")).collect(Collectors.toList()));
            threeWords.get(0).retainAll(threeWords.get(1));
            threeWords.get(0).retainAll(threeWords.get(2));
            commonLetters.add(threeWords.get(0).get(0));
        }
        System.out.println(commonLetters);
        int sum = commonLetters.stream().mapToInt(lettersValueMap::get).sum();
        System.out.println(sum);
    }
}
