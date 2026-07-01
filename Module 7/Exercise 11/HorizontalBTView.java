import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class HorizontalBTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 15; // Tree node radius
    private double hGap = 70;   // Gap between levels horizontally

    public HorizontalBTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        getChildren().clear();

        
        if(tree.getRoot() != null) {
            displayTree(tree.getRoot(), hGap, getHeight() / 2, getHeight() /4);
        }
    }
  /**
     * Displays a subtree horizontally.
     * The root starts on the left.
     * Children move to the right.
     * Left child is drawn above.
     * Right child is drawn below.
     */
        private void displayTree(BST.TreeNode<Integer> root,
                double x, double y, double vGap) {

                if (root.left != null) {
                    //Draw a line to the left child.
                    getChildren().add(new Line(x + hGap, y - vGap, x, y));

                    //Draw the left subtree recursively
                    displayTree(root.left, x + hGap, y - vGap, vGap / 2);
                }
                
                if (root.right != null) {
                    //Draw a line to the right child.
                    getChildren().add(new Line(x + hGap, y + vGap, x, y));

                    //Draw the right subtree recursively
                    displayTree(root.right, x + hGap, y + vGap, vGap / 2);
                }
        //Display node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Text text = new Text(x-6, y+4, root.element + "");
        getChildren().addAll(circle, text);
        }
}
