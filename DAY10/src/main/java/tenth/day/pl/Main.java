package tenth.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> list;
    static List<Move> moves = new ArrayList<>();
    static int[] spiritPosition = new int[3];

    public static void main(String[] args) throws IOException {
        //    Path path = Paths.get("./DAY10/src/main/java/tenth/day/pl/smallInput10");
        Path path = Paths.get("./DAY10/src/main/java/tenth/day/pl/tenthData");
        list = Files.readAllLines(path);
        //    System.out.println(list);
        generateMoveList();

        //part 1
//        int searchedSum = countSignalStrength(20) +
//                countSignalStrength(60) +
//                countSignalStrength(100) +
//                countSignalStrength(140) +
//                countSignalStrength(180) +
//                countSignalStrength(220);
//        System.out.println(countSignalStrength(60));
//        System.out.println(countSignalStrength(100));
//        System.out.println(countSignalStrength(140));
//        System.out.println(countSignalStrength(180));
//        System.out.println(countSignalStrength(220));
//        System.out.println("The sum of strengths is equal " + searchedSum);


        //part 2
        spiritPosition[0] = 0;
        spiritPosition[1] = 1;
        spiritPosition[2] = 2;
        writeSigns();

    }

    private static void generateMoveList() {
        list.forEach(x -> {
            String[] array = x.split(" ");
            Move move;
            if (array.length == 1) {
                move = new Move(1, 0);
            } else {
                move = new Move(2, Integer.parseInt(array[1]));
            }
            moves.add(move);
        });
    }

    private static int countSignalStrength(int cyclesNumberTobeDone) {
        int x = 1;
        int cyclesDone = 0;
        for (Move move : moves) {
            if (move.getCyclesNum() == 1) {
                cyclesDone++;
                if (cyclesDone == cyclesNumberTobeDone) {
                    break;
                }
                continue;
            }
            cyclesDone++;
            if (cyclesDone == cyclesNumberTobeDone) {
                break;
            }
            cyclesDone++;
            if (cyclesDone == cyclesNumberTobeDone) {
                break;
            }
            x += move.getIncreaseInValue();
        }
        if (cyclesDone < cyclesNumberTobeDone) {
            return -Integer.MAX_VALUE;
        }
        return cyclesNumberTobeDone * x;
    }

    // finds three coordinates of new position of the spirit = shifting spirit by "shift"
    public static void shiftSpiritPosition(int shift) {
        for (int i = 0; i < 3; i++) {
            spiritPosition[i] += shift;
        }
    }

    public static void writeSigns() {
        int writerPosition = 0;
        for (Move move : moves) {
            if (move.getCyclesNum() == 1) {
                writeCorrectSign(writerPosition);
                writerPosition++;
                writerPosition = writerPosition % 40;
            }

            if (move.getCyclesNum() == 2) {
                writeCorrectSign(writerPosition);
                writerPosition++;
                writerPosition = writerPosition % 40;
                writeCorrectSign(writerPosition);
                writerPosition++;
                writerPosition = writerPosition % 40;
                shiftSpiritPosition(move.getIncreaseInValue());
            }
        }
    }

    private static void writeCorrectSign(int writerPosition) {
        if (areWriterAndSpiritPositionsTouching(writerPosition)) {
            System.out.print("#");
            checkEndOfLine(writerPosition);
        } else {
            System.out.print(".");
            checkEndOfLine(writerPosition);
        }
    }

    private static boolean areWriterAndSpiritPositionsTouching(int writerPosition) {
        for (int spirit : spiritPosition) {
            if (spirit == writerPosition) {
                return true;
            }
        }
        return false;
    }

    private static void checkEndOfLine(int writerPosition) {
        if (writerPosition == 39) {
            System.out.println();
        }
    }
}
