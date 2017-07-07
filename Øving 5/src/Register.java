import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Knut on 20.03.2017.
 */
public interface Register extends Remote {
    boolean regNyttUtstyr(int startNr, String startBetegnelse, String startLeverandør, int startPåLager, int startNedreGrense) throws RemoteException;
    int endreLagerbeholdning(int nr, int mengde) throws RemoteException;
    int finnUtstyrindeks(int nr) throws RemoteException;
    String lagBestillingsliste() throws RemoteException;
    String lagDatabeskrivelse() throws RemoteException;
}
