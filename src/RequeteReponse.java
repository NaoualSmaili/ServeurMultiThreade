import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RequeteReponse {
    Requete[] listeR;
    int premier, dernier;
    int occupe;

    final ReentrantLock l = new ReentrantLock();
    final Condition ecrire = l.newCondition();
    final Condition lire = l.newCondition();

    public RequeteReponse(int taille) {
        this.listeR = new Requete[taille];
        this.premier = 0;
        this.dernier = 0;
        this.occupe = 0;
    }

    @Override
    public String toString() {
        return "RequeteReponse{" +
                "premier=" + premier +
                ", dernier=" + dernier +
                ", occupe=" + occupe +
                '}';
    }

    void insererRequeste(Requete r){
        l.lock();
        try{
            while(occupe== listeR.length){
                System.out.println("");
                try {
                    ecrire.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                listeR[premier]=r;
                occupe++;
                premier++;
                if(premier==listeR.length){
                    premier=0;
                }
                lire.signalAll();
            }
        }finally {
            l.unlock();
        }
    }

    Requete extraireRequete(){
        Requete vert;
        l.lock();
        try{
            while (occupe==0){
                System.out.println("");
                try{
                    lire.await();

                }catch (InterruptedException e){
                    System.out.println("erreur");
                }
            }
            vert=listeR[dernier];
            occupe--;
            dernier++;
            if(dernier==listeR.length){
                dernier=0;
            }
            ecrire.signalAll();
        }finally {
            l.unlock();
        }
        return vert;
    }

}
