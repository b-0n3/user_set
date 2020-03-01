package models;


import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class BlackList extends HBox {

    public BlackList( )
    {

        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.prefHeight(120);

        this.minHeight(120);
        this.maxHeight(120);
        this.maxWidth(120);

    }
    public void push(BlackCard cart)
    {
        this.getChildren().add(cart);
    }

}
