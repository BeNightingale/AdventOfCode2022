package ninth.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Main2 {
    //rozwiązanie drugiej części zadania
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./DAY9/src/main/java/ninth/day/pl/ninthData");
        // Path path = Paths.get("./DAY9/src/main/java/ninth/day/pl/smallInput9");
        List<String> list = Files.readAllLines(path);
        System.out.println(list);
        List<List<String>> moveList = new ArrayList<>();// lista zaplanowanych ruchów głowy;
        // Jej elementy to dwuelementowe listy; każda lista, to ruchy w jednym kierunku
        // element1 = kierunek przemieszczania, element2 = liczba kroków w daną stronę

        list.forEach(x -> {
            String[] array = x.split(" ");
            List<String> headMoves = new ArrayList<>(2);
            headMoves.add(array[0]); // kierunek (R,L,U,D)
            headMoves.add(array[1]); // liczba kroków
            //   Integer.valueOf(array[1])
            moveList.add(headMoves);
        });
        System.out.println(moveList); // lista zaplanowanych ruchów głowy;

        List<Integer> head = new ArrayList<>(2);// Pierwsza liczba: współrzędna x, druga liczba: współrzędna y; zaczynam od zera, wiersze i kolumny mogą być ujemne tj. w układzie współrzędnych
        //startowa pozycja głowy (0,0)
        head.add(0);
        head.add(0);
        List<List<Integer>> points = new ArrayList<>(9);
        IntStream.rangeClosed(1, 9).forEach(x -> {
            points.add(new ArrayList<>(2));
            points.get(x - 1).add(0, 0);
            points.get(x - 1).add(1, 0);
        });
        //start-wszystkie pointy=(0,0)
        Set<String> visited = new HashSet<>(); // zbiór różnych położeń ogona
        visited.add(Main.makeStringTailPosition(points.get(8)));

        for (List<String> move : moveList) {
            String direction = move.get(0);
            int stepsNumber = Integer.parseInt(move.get(1));
            for (int i = 0; i < stepsNumber; i++) {
                Main.oneHeadStep(head, direction);
                for (int j = 0; j < 9; j++) {
                    List<Integer> point;
                    if (j == 0) {
                        point = head;
                    } else {
                        point = points.get(j - 1);
                    }
                    movePointInChain(point, points.get(j), j == 8, visited);
                }
            }
        }
        System.out.println(visited);
        System.out.println("Liczba odwiedzonych pól: " + visited.size());
    }

    private static void movePointInChain(List<Integer> head, List<Integer> tail, boolean markLast, Set<String> visited) {
        boolean flag = Main.shouldTailMove(head, tail);
        if (flag) {
            Main.oneTailStep(head, tail);
            if (markLast) {
                visited.add(Main.makeStringTailPosition(tail));
            }
        }
    }
}
