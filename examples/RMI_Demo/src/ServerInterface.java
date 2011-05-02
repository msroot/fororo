import java.rmi.*;


public interface ServerInterface extends Remote{
	void register(ClientInterface client) throws RemoteException;
}
