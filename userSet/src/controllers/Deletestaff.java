package controllers;

import com.gluonhq.charm.glisten.control.ProgressIndicator;
import com.sun.deploy.panel.AbstractRadioPropertyGroup;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Staff;
import models.admin;
import org.apache.poi.hssf.record.formula.functions.T;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Deletestaff {
    private Stage stage;

    @FXML
    private Button goBack;

    @FXML
    private TextField SearchField;

    @FXML
    private ProgressIndicator prog;

    @FXML
    private Button search;

    @FXML
    private VBox staffBox;

    @FXML
    private VBox BlackList;

    private ArrayList<StaffHolder> StaffHOlders = new ArrayList<>();
    private ArrayList<BlackList> blackLists  = new ArrayList<>();


    /** this class represent the BlackList
     * it shall hold three blackCard
     * */
    public class BlackList extends  VBox{
        public BlackCard
                blackcard[];
        public BlackList( BlackCard[ ] black)
        {

                this.blackcard = black;

        }
    }

    /**
     *
     * this will be the class that hold tree staff cards
*/

public class StaffHolder extends VBox {

    private staffCarts[]  staffCarts;

    public StaffHolder(staffCarts[] StaffCarts)
    {
        this.staffCarts = StaffCarts;

    }

    public void doFinal()
    {


    }
    }

 /**
  * this class represent staff cart
  * */

 public  class staffCarts extends HBox
 {
        public Button show;
        public Button AddToblacklist;
        public Text id;
        public  ImageView image;
        public Staff staff;
        public  staffCarts(Staff staff){
            this.staff = staff;
            show = new Button();
            AddToblacklist = new Button();
            id = new Text();
            image = new ImageView();

            AddToblacklist. setText("Add To BlackList");
            show.setText("Show");
        }
        public  void doFinal()
        {
            this.setSpacing(30.0);
            this.setAlignment(Pos.CENTER);
            image.setFitHeight(200);
            image.setFitWidth(216);
            id.setText(String.valueOf(staff.getId()));

            try {
                javafx.scene.image.Image image = new Image(new FileInputStream(staff.getImage()));

                this.image.setImage(image);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();


            }

            show.setOnAction(event->{


                });
            AddToblacklist.setOnAction(event->{


            });

        }
 }

public class BlackCard extends  HBox{
    public Button show;
    public Button undo;
    public Text id;
    public  ImageView image;
    public Staff staff;
     public BlackCard(Staff staff)
     {
         this.staff = staff;
         show = new Button();
         undo = new Button();
         id = new Text();
         image = new ImageView();

        undo.setText("Undo");
        show.setText("Show");
     }
     public void doFinal()
     {
         this.setSpacing(30.0);
         this.setAlignment(Pos.CENTER);
         image.setFitHeight(200);
         image.setFitWidth(216);
         id.setText(String.valueOf(staff.getId()));

         try {
             javafx.scene.image.Image image = new Image(new FileInputStream(this.staff.getImage()));

             this.image.setImage(image);
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }

         show.setOnAction(event->{


         });
         undo.setOnAction(event->{

             if ()
         });
     }


}


}