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
import models.*;
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
    private ArrayList<models.BlackList> blackLists = new ArrayList<>();




}


