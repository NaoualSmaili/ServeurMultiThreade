import java.util.Random;

public class Client implements Runnable{
    int id;
    RequeteReponse lr;
    //int numReq;
    //int model;

    Random generateur = new Random();
    Object reveil = new Object();

    static int cpt=1;
    static final Object mutex = new Object();

    public Client(RequeteReponse lr) {
        this.lr = lr;
        synchronized (mutex){
            id=cpt++;
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
        lr.insererRequeste(r);
        System.out.println("waiting...");
        synchronized (reveil){
            try {
                reveil.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Request processed");
    }
}
