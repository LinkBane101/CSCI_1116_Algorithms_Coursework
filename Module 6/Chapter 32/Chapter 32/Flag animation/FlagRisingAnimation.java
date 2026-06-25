import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlagRisingAnimation extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();

        // Add an image view and add it to pane
        ImageView imageView = new ImageView("us.gif");
        imageView.setX(100);
        imageView.setY(200);

        pane.getChildren().add(imageView);

        // Create a thread to animate the flag rising
        Thread animationThread = new Thread(() -> {
            try {
                // Repeat the animation 5 times, like the original PathTransition version
                for (int cycle = 0; cycle < 5; cycle++) {

                    // Move the flag from y = 200 up to y = 0
                    for (int y = 200; y >= 0; y--) {
                        final int currentY = y;

                        Platform.runLater(() -> {
                            imageView.setY(currentY);
                        });

                        Thread.sleep(50);
                    }

                    // Move flag back to the bottom before the next cycle
                    Platform.runLater(() -> {
                        imageView.setY(200);
                    });

                    Thread.sleep(300);
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        animationThread.setDaemon(true);
        animationThread.start();

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("FlagRisingAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}