package tenth.day.pl;

public class Move {
    private final int cyclesNum;
    private final int increaseInValue;

    public Move(int cyclesNum, int increaseValue) {
        this.cyclesNum = cyclesNum;
        this.increaseInValue = increaseValue;
    }

    public int getCyclesNum() {
        return cyclesNum;
    }

    public int getIncreaseInValue() {
        return increaseInValue;
    }

    @Override
    public String toString() {
        return "Move{" +
                "cyclesNum=" + cyclesNum +
                ", increaseValue=" + increaseInValue +
                '}';
    }
}
