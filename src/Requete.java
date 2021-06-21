public class Requete {
    Client client;
    int valeur;

    public Requete(Client client, int valeur) {
        this.client = client;
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Requete{" +
                "client=" + client +
                ", valeur=" + valeur +
                '}';
    }
}
