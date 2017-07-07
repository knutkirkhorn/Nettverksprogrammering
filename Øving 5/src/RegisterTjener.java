import java.rmi.Naming;

/**
 * Created by Knut on 20.03.2017.
 */
public class RegisterTjener {
    public static void main(String[] args) {
        try {
            Register register = new RegisterImpl();
            String objektnavn = "registerTest";
            Naming.rebind(objektnavn, register);

            //Opprette noen utstyrsobjekter i registeret
            register.regNyttUtstyr(1, "Sko", "H&M", 10, 9);
            register.regNyttUtstyr(2, "Jakke", "H&M", 11, 9);
            register.regNyttUtstyr(3, "Hatt", "H&M", 12, 9);
            register.regNyttUtstyr(4, "Frakk", "H&M", 20, 19);
            register.regNyttUtstyr(5, "Lue", "H&M", 50, 29);

            System.out.println(register.lagDatabeskrivelse());


            System.out.println("RMI-objekt er registrert med registerobjektet: " + objektnavn);
            javax.swing.JOptionPane.showMessageDialog(null, "Trykk OK for å stoppe tjeneren.");
            Naming.unbind(objektnavn);
            System.exit(0);
        } catch (Exception e) {

        }

    }
}
