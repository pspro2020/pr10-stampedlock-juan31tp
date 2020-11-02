package warehouse;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.locks.StampedLock;

public class Warehouse {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final StampedLock stpLock = new StampedLock();

    ArrayList<Integer> stock = new ArrayList<>();

    public void addStock(int i) {
        long stamp = stpLock.writeLock();
        try{
            stock.add(i);
            System.out.printf(LocalTime.now().format(dtf) + " -> " + Thread.currentThread().getName() + " added the product " + i + " to the stock \n");
        } finally {
            stpLock.unlock(stamp);
        }

    }

    public void countMyStock(int i) {
        int myStock;

        long stamp = stpLock.tryOptimisticRead();

        if(!stpLock.validate(stamp)){
            stamp = stpLock.readLock();
            try {
                myStock = countStock(i);
                System.out.printf(LocalTime.now().format(dtf) + " -> " + Thread.currentThread().getName() + " counted his stock and said the amount is " + myStock + "\n");
            } finally {
                stpLock.unlockRead(stamp);
            }
        }
    }

    private int countStock(int i) {
        int myStock = 0;

        for(int thisProduct : stock){
            if (i==thisProduct){
                myStock++;
            }
        }

        return myStock;
    }
}
