import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Client implements Runnable{
    int id;
    ArrayBlockingQueue<Requete> lr;
    //int numReq;
    //int model;

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
        System.out.println("send request");
        System.out.println(lr.toString());
        //lr.insererRequeste(r);
        try {
            lr.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("waiting...");
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
        System.out.println("Request processed");
    }
}
