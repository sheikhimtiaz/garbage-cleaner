/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GarbageCleaner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import static javafx.print.Paper.C;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

/**
 *
 * @author sheikhimtiaz
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private JFXButton scanNow;

    @FXML
    private JFXButton clean;

    @FXML
    private JFXButton exit;

    @FXML
    private Label label;
    
    @FXML
    private ListView<String> listView=new ListView<>();
    
    Stage window;
    
    ObservableList<String> data=FXCollections.observableArrayList("");
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(event.getSource()==scanNow){
            
            List<String> results = new ArrayList<String>();
            
            File[] folder = new File("C:\\Windows\\Temp").listFiles();
            File[] folder2 = new File("C:\\Users\\SHEIKH~1\\AppData\\Local\\Temp").listFiles();
            for (File file : folder) {
                if (file.isFile()) {
                    System.out.println("File " + file.getName());
                    results.add(file.getName());
                }
            }
            for(File file : folder2){
                if(file.isFile()){
                    results.add(file.getName());
                }
            }
            
            
            listView.getItems().addAll(results);
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            label.setText("Scan complete!");
    
        }
        
        
        else if(event.getSource()==clean){
            
            String message="";
            ObservableList<String> movies;
            movies=listView.getSelectionModel().getSelectedItems();
            message = movies.stream().map((m) -> m+"\n").reduce(message, String::concat);
            /*for(String m : movies){
                message += m+"\n";
            }*/
            System.out.println(message);
            listView.setItems(data);
            
            
            listView.getItems().clear();
            //listView.getSelectionModel().clearSelection();
            
            label.setText("Cleaned!");
            
            
            File root = new File("C:\\Windows\\Temp");
            File[] Files = root.listFiles();
            if(Files != null) {
                int j;
                for(j = 0; j < Files.length; j++) {
                   System.out.println(Files[j].getAbsolutePath());
                   Files[j].setWritable(true);
                  System.out.println(Files[j].delete());
                  
                }
            }
            File root2 = new File("C:\\\\Users\\\\SHEIKH~1\\\\AppData\\\\Local\\\\Temp");
            File[] Files2 = root2.listFiles();
            if(Files != null) {
                int j2;
                for(j2 = 0; j2 < Files2.length; j2++) {
                   System.out.println(Files[j2].getAbsolutePath());
                   Files[j2].setWritable(true);
                  System.out.println(Files[j2].delete());
                }
            }
            
            label.setText("Cleaned!");
        }
        
        
        else if(event.getSource()==exit){
            
            boolean res;
            res=ConfirmBox.display("TrashCleanup","sure?");
            if(res)
                window.close();
        }
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setItems(data);
    }    

    
}
