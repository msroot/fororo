public interface ChatServer extends java.rmi.Remote {
    public int addChatListener(CallBack c, String userName)
        throws java.rmi.RemoteException;
    public void removeChatListener(String userName, String reason)
        throws java.rmi.RemoteException;
    public void sendChatMessage(String sender, String message)
        throws java.rmi.RemoteException;
}
