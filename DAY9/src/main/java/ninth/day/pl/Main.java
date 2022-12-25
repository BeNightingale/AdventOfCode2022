package ninth.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //  Path path = Paths.get("./DAY9/src/main/java/ninth/day/pl/ninthData");
        Path path = Paths.get("./DAY9/src/main/java/ninth/day/pl/smallInput9");
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
        List<Integer> tail = new ArrayList<>(2);
        //startowa pozycja ogona (0,0)
        tail.add(0);
        tail.add(0);
        Set<String> visited = new HashSet<>(); // zbiór różnych położeń ogona
        visited.add(makeStringTailPosition(tail));
        // start(0,0) H=T
        for (List<String> move : moveList) {
            String direction = move.get(0);
            int stepsNumber = Integer.parseInt(move.get(1));
            for (int i = 0; i < stepsNumber; i++) {
                oneHeadStep(head, direction);
                boolean flag = shouldTailMove(head, tail);
                if (flag) {
                    oneTailStep(head, tail);
                    visited.add(makeStringTailPosition(tail));
                }
            }
        }
        System.out.println(visited);
        System.out.println("Liczba odwiedzonych pól: " + visited.size());

    }

    /**
     * Metoda sprawdza względne położenie head i tail, ocenia, czy ogon ma wykonać ruch (zwraca wtedy true), czy nie (false)
     * nie musi wykonywać ruchu, gdy dotyka głowy
     *
     * @param head głowa-lista ze współrzędnymi głowy
     * @param tail ogon-lista ze współrzędnymi ogona
     * @return czy ogon ma wykonać ruch ku głowie-true; nie ma wykonywać ruchu-false
     */
    static boolean shouldTailMove(List<Integer> head, List<Integer> tail) {
        int tailX = tail.get(0);
        int tailY = tail.get(1);
        int headX = head.get(0);
        int headY = head.get(1);
        boolean compareColumns = headY == tailY || headY == tailY - 1 || headY == tailY + 1;
        if (headX == tailX && compareColumns) {
            return false;
        }
        if (headX == tailX - 1 && compareColumns) {
            return false;
        }
        return headX != tailX + 1 || !compareColumns;
    }

    static void oneHeadStep(List<Integer> head, String direction) {
        int x = head.get(0);
        int y = head.get(1);
        switch (direction) {
            case "R" -> head.set(0, x + 1);
            case "L" -> head.set(0, x - 1);
            case "U" -> head.set(1, y + 1);
            case "D" -> head.set(1, y - 1);
            default -> System.out.println("Unpredicted direction");
        }
    }

    static String makeStringTailPosition(List<Integer> tail) { // zapisuje stringa "xy"
        return "x=" + tail.get(0).toString() + " y=" + tail.get(1).toString();
    }

    // głowa wykonała ruch; sprawdzono, że ogon jest w takim stanie, że ma wykonać ruch
    // ogon wykonuje ruch po ocenie wzajemnego położenia
    static void oneTailStep(List<Integer> head, List<Integer> tail) {
        int tailX = tail.get(0);
        int tailY = tail.get(1);
        int headX = head.get(0);
        int headY = head.get(1);
        if (headX == tailX + 2 && headY == tailY) {
            tail.set(0, tailX + 1);
        }
        if (headX == tailX - 2 && headY == tailY) {
            tail.set(0, tailX - 1);
        }
        if (headX == tailX && headY == tailY + 2) {
            tail.set(1, tailY + 1);
        }
        if (headX == tailX && headY == tailY - 2) {
            tail.set(1, tailY - 1);
        }
        if ((headX == tailX + 1 && headY == tailY + 2)
                || (headX == tailX + 2 && headY == tailY + 1)
                || (headX == tailX + 2 && headY == tailY + 2)
        ) {
            tail.set(0, tailX + 1);
            tail.set(1, tailY + 1);
        }
        if ((headX == tailX - 1 && headY == tailY + 2)
                || (headX == tailX - 2 && (headY == tailY + 1 || headY == tailY + 2))
        ) {
            tail.set(0, tailX - 1);
            tail.set(1, tailY + 1);
        }
        if ((headX == tailX - 2 && headY == tailY - 1)
                || (headX == tailX - 1 && headY == tailY - 2)
                || (headX == tailX - 2 && headY == tailY - 2)
        ) {
            tail.set(0, tailX - 1);
            tail.set(1, tailY - 1);
        }
        if ((headX == tailX + 1 && headY == tailY - 2)
                || (headX == tailX + 2 && (headY == tailY - 1 || headY == tailY - 2))
        ) {
            tail.set(0, tailX + 1);
            tail.set(1, tailY - 1);
        }
    }
}
