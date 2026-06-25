// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    new Thread(() -> startServer()).start();
  }
    //Platform safely updates the GUI
  private void startServer() {
    try {
      ServerSocket serverSocket = new ServerSocket(8000);
      Platform.runLater(() ->
      ta.appendText("Exercise33_01Server started at " + new Date() + "\n"));

      while (true) {
        Socket socket = serverSocket.accept();
        Platform.runLater(() ->
          ta.appendText("Connected to a client at " + new Date() + "\n"));
        new Thread(() -> handleClient(socket)).start();
      }
    }
    catch (IOException ex) {
      Platform.runLater(() ->
        ta.appendText("Server error: " + ex.getMessage() + "\n"));
      ex.printStackTrace();
    }
  }

  private void handleClient(Socket socket) {
    try {
      DataInputStream inputFromClient =
        new DataInputStream(socket.getInputStream());
      DataOutputStream outputToClient =
        new DataOutputStream(socket.getOutputStream());

    while (true) {
      double annualInterestRate = inputFromClient.readDouble();
      int numberOfYears = inputFromClient.readInt();
      double loanAmount = inputFromClient.readDouble();

      Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

      double monthlyPayment = loan.getMonthlyPayment();
      double totalPayment = loan.getTotalPayment();

      outputToClient.writeDouble(monthlyPayment);
      outputToClient.writeDouble(totalPayment);
      outputToClient.flush();

      Platform.runLater(() -> {
        ta.appendText("Annual Interest Rate: " + annualInterestRate + "\n");
        ta.appendText("Numebr of years: " + numberOfYears + "\n");
        ta.appendText("Loan Ammount: " + loanAmount + "\n");
        ta.appendText("Monthly Payment: " + monthlyPayment + "\n");
        ta.appendText("Total Payment: " + totalPayment + "\n\n");
      });
    }
  }
  catch (IOException ex) {
    Platform.runLater(() ->
        ta.appendText("Client disconnected.\n"));
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
