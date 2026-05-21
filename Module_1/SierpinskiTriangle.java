import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class SierpinskiTriangle extends Application {

    private int order = 0;

    @Override
    public void start(Stage primaryStage) {

        SierpinskiTrianglePane pane = new SierpinskiTrianglePane();

        // Create buttons
        Button btIncrease = new Button("+");
        Button btDecrease = new Button("-");

        // Label to show current order
        Label lblOrder = new Label("Order: 0");

        // Increase button action
        btIncrease.setOnAction(e -> {
            order++;
            pane.setOrder(order);
            lblOrder.setText("Order: " + order);
        });

        // Decrease button action
        btDecrease.setOnAction(e -> {

            if (order > 0) {
                order--;
                pane.setOrder(order);
                lblOrder.setText("Order: " + order);
            }
        });

        // Bottom controls
        HBox hBox = new HBox(10);

        hBox.getChildren().addAll(btDecrease, btIncrease, lblOrder);

        hBox.setAlignment(Pos.CENTER);

        // Main layout
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(pane);

        borderPane.setBottom(hBox);

        // Scene
        Scene scene = new Scene(borderPane, 400, 400);

        primaryStage.setTitle("Sierpinski Triangle");

        primaryStage.setScene(scene);

        primaryStage.show();

        // Repaint when resized
        pane.widthProperty().addListener(ov -> pane.paint());

        pane.heightProperty().addListener(ov -> pane.paint());
    }

    // Pane for displaying triangles
    static class SierpinskiTrianglePane extends Pane {

        private int order = 0;

        // Set new order
        public void setOrder(int order) {
            this.order = order;
            paint();
        }

        // Draw triangles
        protected void paint() {

            // Define triangle corners
            Point2D p1 = new Point2D(getWidth() / 2, 10);

            Point2D p2 = new Point2D(10, getHeight() - 10);

            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            // Clear pane
            this.getChildren().clear();

            // Draw recursive triangles
            displayTriangles(order, p1, p2, p3);
        }

        // Recursive method
        private void displayTriangles(int order,
                                      Point2D p1,
                                      Point2D p2,
                                      Point2D p3) {

            if (order == 0) {

                // Draw single triangle
                Polygon triangle = new Polygon();

                triangle.getPoints().addAll(
                        p1.getX(), p1.getY(),
                        p2.getX(), p2.getY(),
                        p3.getX(), p3.getY()
                );

                triangle.setStroke(Color.BLACK);

                triangle.setFill(Color.WHITE);

                this.getChildren().add(triangle);

            } else {

                // Midpoints
                Point2D p12 = p1.midpoint(p2);

                Point2D p23 = p2.midpoint(p3);

                Point2D p31 = p3.midpoint(p1);

                // Recursive calls
                displayTriangles(order - 1, p1, p12, p31);

                displayTriangles(order - 1, p12, p2, p23);

                displayTriangles(order - 1, p31, p23, p3);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}