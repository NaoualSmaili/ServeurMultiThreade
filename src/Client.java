import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Client implements Runnable{
    int id;
    ArrayBlockingQueue<Requete> lr;

    Random generateur = new Random();
    Object reveil = new Object();

    static int cpt=1;
    static final Object mutex = new Object();
    static int nbrClients = 0;
    Thread server;

    public Client(ArrayBlockingQueue<Requete> lr, Thread server) {
        this.lr = lr;
        this.server=server;
        synchronized (mutex){
            id=cpt++;
            nbrClients++;
        }
    }

    void requeteServie(){
        synchronized (reveil){
            reveil.notifyAll();
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                '}';
    }

    @Override
    public void run() {
        Requete r = new Requete(this, generateur.nextInt(1000));
        System.out.println(this.toString()+"send request");
        //System.out.println("Requests list before inserting "+lr.toString());
        try {
            lr.put(r);
            //System.out.println("Requests list after inserting "+lr.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.toString()+"is waiting...");
        synchronized (reveil){
            try {
                reveil.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (mutex){
            nbrClients--;
            if(nbrClients==0){
                System.out.println("server must be interrupted");
                server.interrupt();
            }
        }
        System.out.println(r.toString() + "Request processed");
    }
}
