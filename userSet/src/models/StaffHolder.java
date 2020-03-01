package models;


import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class StaffHolder extends HBox {






    /**
     y
     *
     *
     *
     *    |cart 0| |cart 1| |cart 2|
     *
     * ******************* x
     *
     * */

    public StaffHolder( )
    {
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.prefHeight(120);

        this.minHeight(120);
        this.maxHeight(120);
        this.maxWidth(120);

    }
    public void push(staffCarts cart)
    {
        this.getChildren().add(cart);
    }

}
