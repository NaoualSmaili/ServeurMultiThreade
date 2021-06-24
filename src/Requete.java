public class Requete {
    Client client;
    int valeur;
    int num;
    static int id;

    public Requete(Client client, int valeur) {
        this.client = client;
        this.valeur = valeur;
        ++id;
        if(id%3 !=0){
            num=1;
        }else {
            num = 2;
        }
    }

    @Override
    public String toString() {
        return "Requeste{" +
                "client=" + client +
                ", idR=" + id +
                ", valeur=" + valeur +
                ", num=" + num +
                '}';
    }
}
