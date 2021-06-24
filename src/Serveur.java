import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Serveur implements Runnable {
    ArrayBlockingQueue<Requete> rr;
    public Serveur(ArrayBlockingQueue<Requete> rr) {
        this.rr = rr;
    }

    void soumettre(Requete r, ArrayBlockingQueue<Requete> lr) {
        //System.out.println("Requests list before inserting "+lr.toString());
        try {
            lr.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void traiterRequete() throws InterruptedException {
        Requete r;
        r = rr.take();
        /*switch (r.num) {
            case 1: {
                int time = new Random().nextInt((5000 - 600) + 1) + 600;
                Thread.sleep(time);
            }
            case 2: {
                while (true) {
                    Thread.sleep(10000);
                }
            }
        }*/
        new Thread(new Servant(r)).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                traiterRequete();
            }
        } catch (InterruptedException e) {
            System.out.println("Server interrupted");
        }
    }
}
