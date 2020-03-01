package controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.ControllerClass;
import models.Staff;
import models.admin;

import java.io.FileInputStream;
import java.util.ArrayList;

public class container implements ControllerClass {

    @FXML
    private Circle image;

    @FXML
    private Text id;

    @FXML
    private Text bd;

    @FXML
    private Text bp;

    @FXML
    private Text sex;

    @FXML
    private Text sfam;

    @FXML
    private Text natio;

    @FXML
    private Text grad;

    @FXML
    private Text function;

    @FXML
    private Text post;

    @FXML
    private Text cat;

    @FXML
    private Text echlo;

    @FXML
    private Text entee;

    @FXML
    private Text regim;

    @FXML
    private Text affi;

    @FXML
    private Text datere;

    @FXML
    private Text cin;

    @FXML
    private Text sectio;

    @FXML
    private Text dateem;

    @FXML
    private Button goBack;

    @FXML
    public Button Add;

    private  Staff stf;

    public SimpleBooleanProperty isAdded;

    public container() {
        isAdded.setValue(false);
    }
    public void display()
    {


    }
    @Override
    public void preloadData(ArrayList<Staff> staff, admin admin) {
        this.stf = staff.get(0);
        id.setText(String.valueOf(stf.getId()));
        bd.setText(stf.getBirth_day());
        bp.setText(stf.getBirth_place());
        sex.setText(String.valueOf(stf.getSex()));
        sfam.setText(stf.getSit_fam());
        natio.setText(stf.getNationalite());
        grad.setText(stf.getGrade());
        function.setText(stf.getFunctio_n());
        post.setText(stf.getPost());
        cat.setText(stf.getCategorie());
        echlo.setText(String.valueOf(stf.getEchelon()));
        entee.setText(stf.getEnt_effect());
        regim.setText(stf.getRegime_retraite());
        affi.setText(String.valueOf(stf.getAffil_recore()));
        dateem.setText(stf.getDate_embauche());
        datere.setText(stf.getDate_der_promo());
        cin.setText(stf.getCin());
        sectio.setText(stf.getSection_analytique());
        try {
            Image ima = new Image(new FileInputStream(stf.getImage()));
            image.setFill(new ImagePattern(ima));
        }catch (Exception e)
        {e.printStackTrace();}
        Add.setOnAction(event->{
            isAdded.set(true);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        });
        goBack.setOnAction(event->{
            isAdded.setValue(false);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        });
    }
}
