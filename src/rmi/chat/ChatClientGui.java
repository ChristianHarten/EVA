package rmi.chat;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ChatClientGui extends Application
{
    private String name;

    private TextField input;

    private TextArea output;

    private ChatServer server;

    public void start(Stage primaryStage)
    {
        List<String> args = getParameters().getUnnamed();
        name = args.get(0);
        try
        {
            server = (ChatServer) Naming.lookup("rmi://" + args.get(1) + "/" + args.get(2));
            output = new TextArea();
            ChatClient callback = new ChatClientImpl(name, output);
            if (!server.addClient(callback))
            {
                System.out.println("Registering failed!");
                System.exit(0);
            }
        }
        catch (Exception e)
        {
            System.out.println("Cannot connect to server");
            System.exit(0);
        }
        BorderPane root = new BorderPane();
        Insets ins = new Insets(10);
        root.setPadding(ins);

        input = new TextField();
        input.setOnAction(e -> asyncSend());
        root.setTop(input);
        BorderPane.setMargin(input, new Insets(8));
        output.setEditable(false);
        root.setCenter(output);
        BorderPane.setMargin(output, new Insets(8));
        Button bExit = new Button("Exit!");
        bExit.setMaxWidth(Double.MAX_VALUE);
        bExit.setOnAction(e -> System.exit(0));
        root.setBottom(bExit);
        BorderPane.setMargin(bExit, new Insets(8));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GUI Chat Client");
        primaryStage.show();
    }

    private void asyncSend()
    {
        String message = input.getText();
        new Thread(() -> send(message)).start();
        input.setText("");
    }

    private void send(String message)
    {
        try
        {
            server.sendMessage(name, message);
        }
        catch (RemoteException e)
        {
            System.err.println("RMI problems");
        }
    }

    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.out.println("arguments: nickname server " + "remoteObject");
            Platform.exit();
            return;
        }
        launch(args);
    }
}
