import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Knut on 20.03.2017.
 */
public interface Utstyr extends Remote {
    int finnNr() throws RemoteException;
    String finnBetegnelse() throws RemoteException;
    String finnLeverandør() throws RemoteException;
    int finnPåLager() throws RemoteException;
    int finnNedreGrense() throws RemoteException;
    int finnBestKvantum() throws RemoteException;
    boolean endreLagerbeholdning(int endring) throws RemoteException;
    void settNedreGrense(int nyNedreGrense) throws RemoteException;
}
