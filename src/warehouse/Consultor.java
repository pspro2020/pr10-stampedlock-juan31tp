package warehouse;

public class Consultor implements Runnable {

    Warehouse stock;
    private final int i;
    private int myCount;

    public Consultor(Warehouse stock, int i) {
        this.stock=stock;
        this.i=i;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(500);
                stock.countMyStock(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
