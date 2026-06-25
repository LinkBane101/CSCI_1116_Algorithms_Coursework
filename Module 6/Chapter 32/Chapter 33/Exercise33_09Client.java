import javafx.application.Application;
import javafx.application.Platform;

import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Exercise33_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();

  private DataInputStream inputFromServer;
  private DataOutputStream outputToServer;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 400, 300);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later

    //Connect to server on a background thread
    new Thread(() -> connectToServer()).start();

    //Send a message
    taClient.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        sendMessage();
        e.consume();
      }
    });
  }
  //Connect to Server
  private void connectToServer() {
    try {
    Socket socket = new Socket("localhost", 8000);

    inputFromServer = new DataInputStream(socket.getInputStream());
    outputToServer = new DataOutputStream(socket.getOutputStream());

    Platform.runLater(() -> 
        taServer.appendText("Connected to server.\n"));

        //keep recieving messages from the server
        while (true) {
          String message = inputFromServer.readUTF();
          Platform.runLater(() -> 
              taServer.appendText("S: " + message + "\n"));
        }
  }
  catch (IOException ex) {
    Platform.runLater(() ->
        taServer.appendText("Connection closed or server error.\n"));
  }
}

//Message function
private void sendMessage() {
  try {
    String message = taClient.getText().trim();

    if (message.length() == 0) {
      taClient.clear();
      return;
    }
    if (outputToServer != null) {
      outputToServer.writeUTF(message);
      outputToServer.flush();
      taServer.appendText("C: " + message + "\n");
      taClient.clear();
    }
    else {
      taServer.appendText("No Client connected yet.\n");
      taClient.clear();
    }
  }
    catch (IOException ex) {
      taServer.appendText("Error sending message.\n");
    }
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
