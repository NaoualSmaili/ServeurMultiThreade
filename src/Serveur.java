public class Serveur implements Runnable{

    int ref;
    int numR;
    RequeteReponse rr;
    int resultat;

    public Serveur(RequeteReponse rr) {
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
            r=rr.extraireRequete();
            new Thread(new Servant(r)).start();
        }
    }
}
