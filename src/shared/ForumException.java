package shared;

/**
 * Server/Client Exception
 * 
 * @author Victor Nava
 * 
 */
public class ForumException extends java.rmi.RemoteException {
	static final long serialVersionUID = 1; //to keep the compiler happy;
	
	/**
	 * 
	 * @param msg
	 */
	public ForumException(String msg){
    	super(msg);
    }
}