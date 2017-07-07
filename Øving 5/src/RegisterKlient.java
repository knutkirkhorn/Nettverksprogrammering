import java.rmi.Naming;

import static javax.swing.JOptionPane.showInputDialog;

/**
 * Created by Knut on 20.03.2017.
 */
public class RegisterKlient {
    public static void main(String[] args) {
        try {
            Register register = (Register) Naming.lookup("rmi://localhost/registerTest");

            System.out.println(register.lagDatabeskrivelse());
            String lestInput = showInputDialog("Skriv inn id til utstyret du vil endre...");

            while (!lestInput.equals("")) {
                int lagerNummer = Integer.parseInt(lestInput);

                String mengdeLest = showInputDialog("Skriv inn nytt antall som er på lager...");
                int mengde = Integer.parseInt(mengdeLest);

                register.endreLagerbeholdning(lagerNummer, mengde);

                System.out.println(register.lagDatabeskrivelse());

                lestInput = showInputDialog("Skriv inn id til utstyret du vil endre...");
            }

            System.out.println(register.lagDatabeskrivelse());
            System.out.println(register.lagBestillingsliste());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
