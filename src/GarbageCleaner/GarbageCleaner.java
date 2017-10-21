/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GarbageCleaner;
import com.jfoenix.controls.JFXButton;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author sheikhimtiaz
 */
public class GarbageCleaner extends Application {
    
    
    @FXML
    private JFXButton exit;
    
    Stage window;
    
    @Override
    public void start(Stage stage) throws Exception {
        window=stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setTitle("Garbage Cleaner 1.1.4");
        stage.getIcons().add(new Image(GarbageCleaner.class.getResourceAsStream("icon.png" ))); 
        stage.show();
        
        Runtime.getRuntime().exec("attrib -r myFile");
        
        stage.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void closeProgram(){
        boolean ans=ConfirmBox.display("Garbage Cleanup","Are you sure?");
        if(ans)
            window.close();
    }
    
}
