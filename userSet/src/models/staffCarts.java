package models;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class staffCarts extends HBox {
    public Button show;
    public Button AddToblacklist;
    public Text id;
    public ImageView image;
    public Staff staff;
    public static int index;
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
