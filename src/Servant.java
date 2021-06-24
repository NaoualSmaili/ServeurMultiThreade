public class Servant implements Runnable{
    Requete r;

    public Servant(Requete r) {
        this.r = r;
    }

    @Override
    public void run() {
        System.out.println("Servant starting...");
        try {
            Thread.sleep(r.valeur);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Warn client "+ r.client.toString());
        r.client.requeteServie();
    }
}
