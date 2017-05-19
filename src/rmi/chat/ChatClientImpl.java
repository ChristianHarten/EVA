package rmi.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient
{
    private static final long serialVersionUID = 1L;

    private String name;

    private TextArea output;

    public ChatClientImpl(String name, TextArea output) throws RemoteException
    {
        this.name = name;
        this.output = output;
    }

    public String getName() throws RemoteException
    {
        return name;
    }

    public void print(String msg) throws RemoteException
    {
        Platform.runLater(() -> output.appendText(msg + "\n"));
    }
}
