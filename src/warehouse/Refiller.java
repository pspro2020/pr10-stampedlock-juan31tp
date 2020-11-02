package warehouse;

import java.util.Random;

public class Refiller implements Runnable {

    Warehouse stock;
    Random rnd = new Random();

    public Refiller(Warehouse warehouse) {
        this.stock=warehouse;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(2000);
                stock.addStock(rnd.nextInt(3)+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
