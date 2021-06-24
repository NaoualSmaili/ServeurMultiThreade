import java.util.Arrays;
import java.util.Random;

public class RequeteReponse {
    Requete[] listeR;
    int resultat;

    Random r = new Random();

    public RequeteReponse(int taille) {
        this.listeR = new Requete[taille];
        resultat = r.nextInt(1000);
    }

    @Override
    public String toString() {
        return "RequeteReponse{" +
                "listeR=" + Arrays.toString(listeR) +
                ", resultat=" + resultat +
                '}';
    }
}
