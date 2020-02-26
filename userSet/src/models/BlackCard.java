package models;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BlackCard extends HBox {
    public Button show;
    public Button undo;
    public Text id;
    public ImageView image;
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
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.load(getClass().getResource("./view/Container.fxml"));
                ControllerClass cn = loader.getController();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        undo.setOnAction(event->{


        });
    }


}


