package controllers;

import com.gluonhq.charm.glisten.animation.FlipOutXTransition;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Search_cont implements ControllerClass {
    @FXML
    private TextField SearchField;

    @FXML
    private Button search;

    @FXML
    private Button goBack;

    @FXML
    private VBox Box;
    @FXML
    private ProgressIndicator progress;

    private  ExecutorService ser =  Executors.newFixedThreadPool(1);

    private admin admin;
    private   SimpleBooleanProperty hell = new SimpleBooleanProperty();
    Stage stage = new Stage();

    public class cart extends  VBox{
        private  String post;
        private  int id;
       private File Image;

        @FXML
        private Text idText;
        @FXML
        private ImageView Im;
        private  Staff staff;


        public cart( int id , File image , Staff staff ){
            this.staff =staff;
            this.id = id;
            this.Image = image;

            idText = new Text();
            Im = new ImageView();
            doFinal();
        }
        public void doFinal()
        {
            boolean showed = false;
            this.setSpacing(30.0);
            this.setAlignment(Pos.CENTER);
            Im.setFitHeight(200);
            Im.setFitWidth(216);
            idText.setText(String.valueOf(this.id));

            try {
                javafx.scene.image.Image image = new Image(new FileInputStream(this.Image));

                Im.setImage(image);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.getChildren().addAll(Im,idText);

            this.setOnMouseClicked(evet->{

                Platform.runLater(()->{


                        try {
//                            if (!hell.get()) {
//
//                                hell.set(true);
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/view/staffContainer.fxml"));
                                Parent parent = loader.load();

                                StaffContainer cn = new StaffContainer();
                                cn = loader.getController();
                                ArrayList<Staff> stf = new ArrayList<>();
                                stf.add(staff);
                                cn.preloadData(stf, null);

                                Scene scene = new Scene(parent);
                                stage.setScene(scene);
                                stage.setOnCloseRequest(en -> {
                                    hell.set(false);
                                });
                                stage.show();
                          //  }

                        }catch (Exception e)
                        {e.printStackTrace();}
                        });
            });
        }

    }
    public class CartHolder extends HBox {
            private  cart[] carts;
            public CartHolder(cart[] carts){
                this.carts = carts;
                doFinal();
            }
            public void doFinal()
            {
                this.setSpacing(20);
                this.setAlignment(Pos.CENTER);
                this.prefHeight(120);

                this.minHeight(120);
                this.maxHeight(120);
                this.maxWidth(120);

                for (cart cr : carts) {
                    if (cr != null)
                        this.getChildren().add(cr);
                }
            }
    }
    private  ArrayList<CartHolder> cartHolders;
    private  void setbox()
    {
        if (cartHolders != null)
        if ( cartHolders.size() > 0)
            for(CartHolder cth : cartHolders)
                Box.getChildren().add(cth);
    }
    private  void remov()
    {
        if (cartHolders != null)

        {
            if ( cartHolders.size() > 0)
            for(CartHolder cth : cartHolders) {
                Box.getChildren().remove(cth);

            }
            cartHolders.clear();
        }
            if(Box.getChildren().size() > 0)
            Box.getChildren().remove(0,Box.getChildren().size());
            System.gc();
    }



    public class Searcher implements  Runnable  {
        private String toFind;
        private admin admin;
        public Searcher(String toFind,admin admin)
        {
            this.admin = admin;
            this.toFind = toFind;
        }



        public void run()  {


            if (!toFind.isEmpty())
            {
                progress.setProgress(0.10F);
                JsonWriterAndReader jws= new JsonWriterAndReader();
                TCPClient con = new TCPClient();
                progress.setProgress(0.12F);
                JSONArray ar = new JSONArray();
                ar.add(jws.createHeader("pull",toFind));
                try {progress.setProgress(0.19F);

                    con.send(ar, this.admin.getUsername());
                    JSONArray returned = con.rec();
                    progress.setProgress(0.25F);
                    if (((String)((JSONObject)((JSONObject)returned.get(0)).get("res")).get("res")).equals("Accepted")) {
                        progress.setProgress(0.30F);
                        ArrayList<Staff> hell = jws.getArraylistOfStaffs(new ArrayList<Staff>(), (JSONArray) returned.get(1));
                        Platform.runLater(()-> {
                            remov();
                            progress.setProgress(0.35F);
                            showStaffs(hell);
                            progress.setProgress(1F);
                        });
                        progress.setProgress(1F);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


        }
    }

    public void showStaffs(ArrayList<Staff>array){
        if ( array.size() == 0) {
            Text hgl = new Text("nothing to show up");
            hgl.setId("hell");
            Box.getChildren().add(hgl);
            return ;
        }
        progress.setProgress(0.40F);
        cartHolders = new ArrayList<CartHolder>();
        Iterator<Staff> cellIterator = array.iterator();
        float ff = 0.40F;
        while (cellIterator.hasNext())
        {
            progress.setProgress(ff);
            ff += (1 /array.size() + 10);
            cart[] carts = new cart[3];
            for(int i= 0; i < 3 ;i++)
            {
                Staff staff;
                try {
                    staff =  cellIterator.next();
                }catch (Exception e)
                {
                    e.printStackTrace();
                    staff = null;
                }

                if (staff == null)
                {
                    cartHolders.add(new CartHolder(carts));
                    setbox();
                    return ;
                }
                carts[i] =getCards(staff);
            }
            cartHolders.add(new CartHolder(carts));
        }
        setbox();
    }
    @FXML
    public void initialize() {

        hell.set(false);
        goBack.setOnAction(event -> {
            adminDashbordCont con = new adminDashbordCont();


            SceneChanger sc = new SceneChanger();
            try {


                sc.changeScenes(event, "/view/adminDashbord.fxml", "Drag here", null, con, admin);
            }catch (Exception e)
            {e.printStackTrace();}
        });

        SearchField.setOnKeyPressed(event->{
            if(SearchField.getText().length() > 1){
                progress.setProgress(0F);
                ser.shutdown();
                ser = null;
                ser = Executors.newFixedThreadPool(1);
            Thread t = new Thread(new Searcher(SearchField.getText(),admin));
                progress.setProgress(0F);
            ser.execute(t);

            }

        });

        search.setOnAction(evet->{
//            remov();
//            if (!SearchField.getText().isEmpty())
//            {
//                JsonWriterAndReader jws= new JsonWriterAndReader();
//                TCPClient con = new TCPClient();
//
//                JSONArray ar = new JSONArray();
//                ar.add(jws.createHeader("pull",SearchField.getText()));
//                try {
//                    con.send(ar, this.admin.getUsername());
//                    JSONArray returned = con.rec();
//                    if (((String)((JSONObject)((JSONObject)returned.get(0)).get("res")).get("res")).equals("Accepted")) {
//
//                        ArrayList<Staff> array = jws.getArraylistOfStaffs(new ArrayList<Staff>(), (JSONArray) returned.get(1));
//                         if ( array.size() == 0) {
//                             Text hgl = new Text("nothing to show up");
//                             hgl.setId("hell");
//                             Box.getChildren().add(hgl);
//                            return;
//                         }
//                        cartHolders = new ArrayList<CartHolder>();
//                        Iterator<Staff> cellIterator = array.iterator();
//                        while (cellIterator.hasNext())
//                        {
//                            cart[] carts = new cart[3];
//                            for(int i= 0; i < 3 ;i++)
//                            {
//                                Staff staff;
//                                try {
//                                   staff =  cellIterator.next();
//                                }catch (Exception e)
//                                {
//                                    e.printStackTrace();
//                                    staff = null;
//                                }
//
//                                if (staff == null)
//                                {
//                                    cartHolders.add(new CartHolder(carts));
//                                    setbox();
//                                    return ;
//                                }
//                              carts[i] =getCards(staff);
//                            }
//                            cartHolders.add(new CartHolder(carts));
//                        }
//                        setbox();
//
//                    }
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }

            ser.shutdown();
            ser = null;
            ser = Executors.newFixedThreadPool(1);
            Thread t = new Thread(new Searcher(SearchField.getText(),admin));
            progress.setProgress(0F);
            ser.execute(t);

        });

    }

    public cart getCards(Staff staff)
    {
        cart cart = new cart(staff.getId(), staff.getImage(), staff);
        return cart;
    }
    @Override
    public void preloadData(ArrayList<Staff> staff, models.admin admin) {
        this.admin = admin;
    }
}
