import java.net.URI; 
import java.net.URL; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner; 
import javafx.application.Application; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.ComboBox; 
import javafx.scene.control.Label; 
import javafx.scene.control.TextField; 
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.GridPane; 
import javafx.stage.Stage; 
 
public class App extends Application { 
    private Map<String, Integer>[] mapForBoy = new HashMap[10]; 
    private Map<String, Integer>[] mapForGirl = new HashMap[10]; 
 
    private Button btFindRanking = new Button("Find Ranking"); 
    private ComboBox<Integer> cboYear = new ComboBox<>(); 
    private ComboBox<String> cboGender = new ComboBox<>(); 
    private TextField tfName = new TextField(); 
    private Label lblResult = new Label(); 
 
    @Override 
    public void start(Stage primaryStage) { 
        GridPane gridPane = new GridPane(); 
 
        gridPane.add(new Label("Select a year:"), 0, 0); 
        gridPane.add(new Label("Boy or girl?"), 0, 1); 
        gridPane.add(new Label("Enter a name:"), 0, 2); 
 
        gridPane.add(cboYear, 1, 0); 
        gridPane.add(cboGender, 1, 1); 
        gridPane.add(tfName, 1, 2); 
        gridPane.add(btFindRanking, 1, 3); 
 
        gridPane.setAlignment(Pos.CENTER); 
        gridPane.setHgap(5); 
        gridPane.setVgap(5); 
 
        BorderPane borderPane = new BorderPane(); 
        borderPane.setCenter(gridPane); 
        borderPane.setBottom(lblResult); 
        BorderPane.setAlignment(lblResult, Pos.CENTER); 
 
        Scene scene = new Scene(borderPane, 370, 160); 
        primaryStage.setTitle("Exercise21_11"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
 
        for (int year = 2001; year <= 2010; year++) { 
            cboYear.getItems().add(year); 
        } 
 
        cboYear.setValue(2001); 
 
        cboGender.getItems().addAll("Male", "Female"); 
        cboGender.setValue("Male"); 
 
        loadData(); 
 
        btFindRanking.setOnAction(e -> findRanking()); 
    } 
 
    private void loadData() { 
        try { 
            for (int year = 2001; year <= 2010; year++) { 
                int index = year - 2001; 
 
                mapForBoy[index] = new HashMap<>(); 
                mapForGirl[index] = new HashMap<>(); 
 
                String address = "https://liveexample.pearsoncmg.com/data/babynamesranking" 
                        + year + ".txt"; 
 
                URL url = URI.create(address).toURL(); 
                Scanner input = new Scanner(url.openStream()); 
 
                while (input.hasNextLine()) { 
                    String line = input.nextLine(); 
                    Scanner lineScanner = new Scanner(line); 
 
                    if (lineScanner.hasNextInt()) { 
                        int ranking = lineScanner.nextInt(); 
 
                        String boyName = lineScanner.next(); 
                        lineScanner.next(); 
     
                        String girlName = lineScanner.next(); 
                        lineScanner.next(); 
     
                        mapForBoy[index].put(boyName.toLowerCase(), ranking); 
                        mapForGirl[index].put(girlName.toLowerCase(), ranking); 
                    } 
                    lineScanner.close(); 
                } 
                input.close(); 
                System.out.println("Loaded " + mapForBoy[index].size() + " boy names and " +  
            mapForGirl[index].size() + " girl naes for " + year); 
            } 
            lblResult.setText("Baby name data loaded."); 
        } 
        catch (Exception ex) { 
            lblResult.setText("Error loading baby name data."); 
            ex.printStackTrace(); 
        } 
    } 
 
    private void findRanking() { 
        int year = cboYear.getValue(); 
        String gender = cboGender.getValue(); 
        String name = tfName.getText().trim(); 
 
        if (name.length() == 0) { 
            lblResult.setText("Please enter a name."); 
            return; 
        } 
 
        int index = year - 2001; 
        String key = name.toLowerCase(); 
 
        Map<String, Integer> selectedMap; 
 
        if (gender.equals("Male")) { 
            selectedMap = mapForBoy[index]; 
        } 
        else { 
            selectedMap = mapForGirl[index]; 
        } 
 
        if (selectedMap == null || selectedMap.isEmpty()) { 
            lblResult.setText("The data for " + year + " was not loaded."); 
            return; 
        } 
 
        if (selectedMap.containsKey(key)) { 
            int ranking = selectedMap.get(key); 
 
            if (gender.equals("Male")) { 
                lblResult.setText("Boy name " + name + " is ranked #" + ranking + " in year " + year); 
            } 
            else { 
                lblResult.setText("Girl name " + name + " is ranked #" + ranking + " in year " + year); 
            } 
        } 
        else { 
            lblResult.setText("The name " + name + " is not ranked in year " + year); 
        } 
    } 
 
    public static void main(String[] args) { 
        launch(args); 
    } 
}
