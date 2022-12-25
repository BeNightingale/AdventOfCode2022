package eleventh.day.pl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static List<String> list;
    static List<Monkey> monkeys = new ArrayList<>();
    static long dividersProduct;

    public static void main(String[] args) throws IOException {
        //   Path path = Paths.get("./DAY11/src/main/java/eleventh/day/pl/smallInput11");
        Path path = Paths.get("./DAY11/src/main/java/eleventh/day/pl/eleventhData");
        list = Files.readAllLines(path);

        createMonkeysList();
//        Solution to part 1:
//        IntStream.rangeClosed(1, 20).forEach(x -> playOneRound(true));
//        writeAllMonkeysNumbersOfItemsInspected();
//        System.out.println("Monkeys business level in part 1 is equal to " + findMonkeyBusinessLevel());

        // Solution to part 2:
        dividersProduct = getDividersProduct();
        IntStream.rangeClosed(1, 10000).forEach(x -> playOneRound(false));
        writeAllMonkeysNumbersOfItemsInspected();
        System.out.println("Monkeys business level in part 2 is equal to " + findMonkeyBusinessLevel());
    }

    private static void createMonkeysList() {
        List<String> list1 = list.stream().filter(x -> !x.startsWith("Monkey")).filter(x -> !x.isEmpty()).collect(Collectors.toList());
        for (int i = 0; i < list1.size(); i += 5) {
            Monkey monkey = new Monkey();
            String[] numbers = list1.get(i).replace("  Starting items: ", "").split(", ");
            List<Long> longNumbers = Arrays.stream(numbers).map(Long::parseLong).collect(Collectors.toList());
            monkey.setItems(longNumbers);
            monkey.setItemsCounter(longNumbers.size());
            String[] function = list1.get(i + 1).replace("  Operation: new = old ", "").split(" ");
            LongUnaryOperator operator;
            if ("+".equals(function[0])) {
                operator = x -> x + Long.parseLong(function[1]);
            } else {
                operator = x -> x * ("old".equals(function[1]) ? x : Long.parseLong(function[1]));
            }
            monkey.setOperator(operator);
            long divider = Long.parseLong(list1.get(i + 2).replace("  Test: divisible by ", ""));
            monkey.setDivider(divider);
            LongPredicate predicate = x -> x % divider == 0;
            monkey.setPredicate(predicate);
            final int trueMonkeyNumber = Integer.parseInt(list1.get(i + 3).replace("    If true: throw to monkey ", ""));
            final int falseMonkeyNumber = Integer.parseInt(list1.get(i + 4).replace("    If false: throw to monkey ", ""));
            ToIntFunction<Boolean> whichMonkey = x -> x ? trueMonkeyNumber : falseMonkeyNumber;
            monkey.setWhichMonkey(whichMonkey);
            monkeys.add(monkey);
        }
    }

    private static void playOneRound(boolean isPart1) {
        for (Monkey monkey : monkeys) {
            for (Long item : monkey.items) {
                long newNum = monkey.getOperator().applyAsLong(item);
                if (isPart1) {
                    newNum = newNum / 3;
                }
                boolean checkResult = monkey.getPredicate().test(newNum);
                int monkeyToGet = monkey.getWhichMonkey().applyAsInt(checkResult);
                if (isPart1) {
                    monkeys.get(monkeyToGet).getItems().add(newNum);
                } else {
                    monkeys.get(monkeyToGet).getItems().add(newNum % dividersProduct);
                }
                long monkeyToGetCounter = monkeys.get(monkeyToGet).getItemsCounter() + 1;
                monkeys.get(monkeyToGet).setItemsCounter(monkeyToGetCounter);
            }
            monkey.setItems(new ArrayList<>());
        }
    }

    private static long getNumberOfItemsInspected(Monkey monkey) {
        return monkey.itemsCounter - monkey.getItems().size();
    }

    private static void writeNumberOfItemsInspected(Monkey monkey) {
        System.out.println("Number of inspected items = " + (getNumberOfItemsInspected(monkey)));
    }

    private static void writeAllMonkeysNumbersOfItemsInspected() {
        monkeys.forEach(Main::writeNumberOfItemsInspected);
    }

    private static long findMonkeyBusinessLevel() {
        List<Long> numbersOfInspectedItems = IntStream.rangeClosed(0, monkeys.size() - 1)
                .mapToLong(x -> getNumberOfItemsInspected(monkeys.get(x)))
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(numbersOfInspectedItems);
        return numbersOfInspectedItems.get(0) * numbersOfInspectedItems.get(1);
    }

    private static long getDividersProduct() {
        final int numberOfMonkeys = monkeys.size();
        return IntStream.rangeClosed(0, numberOfMonkeys - 1)
                .mapToLong(nr -> monkeys.get(nr).getDivider())
                .reduce(1, (div1, div2) -> div1 * div2);
    }
}
