package warehouse;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        Thread[] consultors = new Thread[3];
        for (int i=0; i<consultors.length;i++){
            consultors[i]=new Thread(new Consultor(warehouse, i+1));
        }

        Thread refiller = new Thread(new Refiller(warehouse));

        refiller.start();

        //Wait to let it have some stock before consultors count
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<consultors.length; i++){
            consultors[i].start();
        }
    }
}
