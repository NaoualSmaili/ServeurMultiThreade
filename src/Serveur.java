import java.util.concurrent.ArrayBlockingQueue;

public class Serveur implements Runnable{

    int ref;
    int numR;
    //RequeteReponse rr;
    ArrayBlockingQueue<Requete> rr;
    int resultat;

    public Serveur(ArrayBlockingQueue<Requete> rr) {
        this.rr = rr;
    }

    void soumettre(){

    }

    void traiterRequete(){

    }


    @Override
    public void run() {
        Requete r;
        while(true){
            //r=rr.extraireRequete();
            try {
                r=rr.take();
                new Thread(new Servant(r)).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
