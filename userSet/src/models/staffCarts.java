package models;

import controllers.Deletestaff;
import controllers.container;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class staffCarts extends HBox {
    public Button show;
    public Button AddToblacklist;
    public Text id;
    public ImageView image;
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
            FXMLLoader loader = new FXMLLoader();
            try {
                Parent pn =   loader.load(getClass().getResource("./view/Container.fxml"));
                container cn = loader.getController();
                ArrayList<Staff> stf = new ArrayList<>();
                stf.add(this.staff);
                cn.preloadData(stf , Deletestaff.admin);
                Deletestaff.stage.setScene(new Scene(pn));
                Deletestaff.stage.show();
                cn.Add.setText("to BlackList");
                cn.isAdded.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if ( newValue == true)
                            toBlackList();
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        AddToblacklist.setOnAction(event->{

            toBlackList();
        });

}

    private void toBlackList() {
        Deletestaff.toBlackList(this);
    }

}
