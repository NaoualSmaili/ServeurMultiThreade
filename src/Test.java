import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    static int nbrRequetes;
    static int nbrClients;

    public Test() {
    }

    public static void main(String[] args) {

        try{
            nbrRequetes=1;
            nbrClients=5;
        }catch (ArrayIndexOutOfBoundsException e){
            return;
        }

        ArrayBlockingQueue<Requete> rr = new ArrayBlockingQueue<Requete>(nbrRequetes, true);

        Thread server = new Thread(new Serveur(rr));
        server.start();

        for (int i= 0; i<nbrClients; i++){
            new Thread(new Client(rr,server)).start();
        }


    }
}
