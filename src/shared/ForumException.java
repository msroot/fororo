package shared;

public class ForumException extends java.rmi.RemoteException {
	static final long serialVersionUID = 1; //to keep the compiler happy;
	
	public ForumException(String msg){
    	super(msg);
    }
}