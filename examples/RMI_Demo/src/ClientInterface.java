import java.rmi.*;


public interface ClientInterface extends Remote {
	void alertNewUser(String name) throws RemoteException;
}
