package eleventh.day.pl;

import java.util.List;
import java.util.function.*;

public class Monkey {
    List<Long> items;
    LongUnaryOperator operator;
    long divider;
    LongPredicate predicate;
    ToIntFunction<Boolean> whichMonkey;
    long itemsCounter;

    public long getItemsCounter() {
        return itemsCounter;
    }

    public void setItemsCounter(long itemsCounter) {
        this.itemsCounter = itemsCounter;
    }

    public Monkey() {
        //empty body
    }
    public Monkey(List<Long> items, LongUnaryOperator operator, long divider,  LongPredicate predicate, ToIntFunction<Boolean> whichMonkey) {
        this.items = items;
        this.operator = operator;
        this.divider = divider;
        this.predicate = predicate;
        this.whichMonkey = whichMonkey;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public LongUnaryOperator getOperator() {
        return operator;
    }

    public void setOperator(LongUnaryOperator operator) {
        this.operator = operator;
    }

    public long getDivider() {
        return divider;
    }

    public void setDivider(long divider) {
        this.divider = divider;
    }

    public LongPredicate getPredicate() {
        return predicate;
    }

    public void setPredicate(LongPredicate predicate) {
        this.predicate = predicate;
    }

    public ToIntFunction<Boolean> getWhichMonkey() {
        return whichMonkey;
    }

    public void setWhichMonkey(ToIntFunction<Boolean> whichMonkey) {
        this.whichMonkey = whichMonkey;
    }


    @Override
    public String toString() {
        return "Monkey{" +
                "items=" + items +
                "divider" + divider +
                "itemsCounter=" + itemsCounter +
                '}';
    }
}
