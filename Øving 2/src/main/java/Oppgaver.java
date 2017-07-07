import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Knut on 06.03.2017.
 */
public class Oppgaver {
    public static void main(String[] args) {
        //oppgave2();
        oppgave3();
    }

    private static void oppgave2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kontoManager");
        KontoDAO fasade = null;

        try {
            fasade = new KontoDAO(emf);

            //Opprette to kontoer til databasen
            Konto konto1 = new Konto(1234, 13.37, "Knut Kirkhorn");
            Konto konto2 = new Konto(12343, 100.23, "Ingunn Sund");

            fasade.opprettKonto(konto1);
            fasade.opprettKonto(konto2);

            //Finne kontoer med større saldo enn gitt beløp
            ArrayList<Konto> kontos = fasade.finnKontoerMedStorreSaldo(1.0);
            for (Konto k : kontos) {
                System.out.println(k.getEier());
            }

            //Endre eier av et av objektene og lagre til databasen
            Konto konto3 = konto1;
            String nyEier = "Knut Aasgaard Kirkhorn";
            fasade.endreEier(konto3, nyEier);

        } finally {
            emf.close();
        }
    }

    private static void oppgave3() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kontoManager");
        KontoDAO fasade = null;

        try {
            fasade = new KontoDAO(emf);

            //Konto konto1 = fasade.finnEier(1234);
            int kontonrFra = 1234;
            int kontonrTil = 12343;
            //Konto konto2 = fasade.finnEier(12343);
            double sumOverforing = 22.00;

            JOptionPane.showMessageDialog(null, "Stanser koden litt her");
            fasade.overforPenger(kontonrFra, kontonrTil, sumOverforing);
        } finally {
            emf.close();
        }
    }
}
