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

        RequeteReponse rr = new RequeteReponse(nbrRequetes);

        for (int i= 0; i<nbrClients; i++){
            new Thread(new Client(rr)).start();
        }

        new Thread(new Serveur(rr)).start();
    }
}
