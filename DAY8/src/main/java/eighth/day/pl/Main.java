package eighth.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class Main {
    static int[][] grid;

    public static void main(String[] args) throws IOException {
     //   Path path = Paths.get("./DAY8/src/main/java/eighth/day/pl/smallInput8");
        Path path = Paths.get("./DAY8/src/main/java/eighth/day/pl/eighthData");

        List<String> list = Files.readAllLines(path);
        System.out.println(list);
        int m = list.get(0).length(); // liczba kolumn
        int n = list.size(); // liczba wierszy
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] cyphers = list.get(i).split("");
            int[] numInRow = Arrays.stream(cyphers).map(Integer::parseInt).mapToInt(x -> x).toArray();
            grid[i] = Arrays.copyOf(numInRow, m);
        }
        // print grid:
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(grid[i][j]);
//            }
//            System.out.println();
//        }


        // part 1:


//        int highTreesNum = 0;
//        int leftCounter = 0;
//        int rightCounter = 0;
//        int upCounter = 0;
//        int downCounter = 0;
//        for (int i = 1; i < n - 1; i++) {
//            for (int j = 1; j < m - 1; j++) {
//                leftCounter = countLowerTrees(grid[i][j], 0, j, i, "horizontal");
//                if (leftCounter == j) {
//                    highTreesNum++;
//                    leftCounter = 0;
//                    continue; // tego elementu nie trzeba już wtedy sprawdzać w inne strony, bo jest widoczny z lewej
//                }
//                rightCounter = countLowerTrees(grid[i][j], j + 1, m, i, "horizontal");
//                if (rightCounter == m - 1 - j) {
//                    highTreesNum++;
//                    rightCounter = 0;
//                    continue;// tego elementu nie trzeba już wtedy sprawdzać w inne strony, bo jest widoczny z prawej
//                }
//                upCounter = countLowerTrees(grid[i][j], 0, i, j, "vertical");
//                if (upCounter == i) {
//                    highTreesNum++;
//                    upCounter = 0;
//                    continue;// tego elementu nie trzeba już wtedy sprawdzać w inne strony, bo jest widoczny z góry
//                }
//                downCounter = countLowerTrees(grid[i][j], i + 1, n, j, "vertical");
//                if (downCounter == n - 1 - i) {
//                    highTreesNum++;
//                    downCounter = 0;
//                    continue;// tego elementu nie trzeba już wtedy sprawdzać w inne strony, bo jest widoczny z dołu
//                }
//            }
//        }
//        System.out.println("wewnętrzne duże drzewa: " + highTreesNum);
//        System.out.println("brzegowe drzewa: " + (2 * n + 2 * (m - 2)));
//        int solution = 2 * n + 2 * (m - 2) + highTreesNum;
//        System.out.println("wszystkie razem: " + solution);


        // part 2:
        List<Integer> products = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                products.add(countProductOfDistancesInAllDirections(grid[i][j], i, j, n, m));
            }
        }
        System.out.println(products);
        OptionalInt maxDistance = products.stream().mapToInt(x -> x).max();
        if (maxDistance.isPresent()) {
            System.out.println(maxDistance.getAsInt());
        } else {
            System.out.println("No maximum");
        }

    }

    private static int countLowerTrees(int actualTree, int from, int to, int x, String direction) {
        int counter = 0;
        for (int k = from; k < to; k++) {
            int item = "horizontal".equals(direction) ? grid[x][k] : grid[k][x];
            if (actualTree <= item) {
                counter = 0;
                break;
            } else {
                counter++;
            }
        }
        return counter;
    }

    private static int countDistanceLeftOrUp(int actualTree, int from, int to, int x, String direction) {
        int distance = 0;
        for (int k = from; k >= to; k--) {
                int item = "horizontal".equals(direction) ? grid[x][k] : grid[k][x];
                if (actualTree <= item) {
                    distance++;
                    break;
                } else {
                    distance++;
                }
            }
            return distance;
        }

        private static int countDistanceRightOrDown( int actualTree, int from, int to, int x, String direction){
            int distance = 0;
            for (int k = from; k < to; k++) {
                int item = "horizontal".equals(direction) ? grid[x][k] : grid[k][x];
                if (actualTree <= item) {   //sprawdzamy, czy po lewej sa wszystkie mniejsze
                    distance++;
                    break;
                } else {
                    distance++;
                }
            }
            return distance;
        }

        private static int countProductOfDistancesInAllDirections ( int actualTree, int i, int j, int n, int m){
            int leftDistance = countDistanceLeftOrUp(actualTree, j - 1, 0, i, "horizontal");
            int rightDistance = countDistanceRightOrDown(actualTree, j + 1, m, i, "horizontal");
            int upDistance = countDistanceLeftOrUp(actualTree, i - 1, 0, j, "vertical");
            int downDistance = countDistanceRightOrDown(actualTree, i + 1, n, j, "vertical");
            return leftDistance * rightDistance * upDistance * downDistance;
        }
    }
