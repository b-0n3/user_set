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

import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Deletestaff implements ControllerClass {


    @FXML
    private Button goBack;

    @FXML
    private TextField SearchField;

    @FXML
    private ProgressIndicator prog;

    @FXML
    private Button search;
    @FXML
    private Button Delete;
    @FXML
    private static VBox staffBox;

    @FXML
    private static VBox BlackList;

    public static Stage stage;
    public static admin admin;
    private static ArrayList<staffCarts> StaffHOlders = new ArrayList<>();
    private static ArrayList<BlackCard> blackLists = new ArrayList<>();
    private static TCPClient con;
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

                JsonWriterAndReader jws= new JsonWriterAndReader();
                con = new TCPClient();

                JSONArray ar = new JSONArray();
                ar.add(jws.createHeader("pull",toFind));
                try {
                    con.send(ar, this.admin.getUsername());
                    JSONArray returned = con.rec();

                    if (((String)((JSONObject)((JSONObject)returned.get(0)).get("res")).get("res")).equals("Accepted")) {

                        ArrayList<Staff> hell = jws.getArraylistOfStaffs(new ArrayList<Staff>(), (JSONArray) returned.get(1));
                        if (hell.size() > 0)
                            Platform.runLater(()-> {

                               for(Staff staff :hell)
                               {
                                   StaffHOlders.add(new staffCarts(staff));
                               }
                               Draw();

                            });
                        else
                        {
                            staffBox.getChildren().add(new Text("no Result founded !"));
                        }

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


        }
    }
    private static ExecutorService ser =  Executors.newFixedThreadPool(1);

public static void toBlackList(staffCarts cart)
{
    blackLists.add(new BlackCard(cart.staff));
    StaffHOlders.remove(cart);
    Draw();

}
public static void toWhiteList(BlackCard blackCard)
{
    blackLists.remove(blackCard);
    StaffHOlders.add(new staffCarts(blackCard.staff));
    Draw();
}

public static void Draw()
{
    staffBox.getChildren().remove(0 , staffBox.getChildren().size());
    BlackList.getChildren().remove(0,BlackList.getChildren().size());
    Iterator<staffCarts> itr = StaffHOlders.iterator();
    while ( itr.hasNext())
    {
        StaffHolder holder = null;
        try {
            holder = new StaffHolder();
            for ( int  i = 0 ; i < 3 ;i++) {
                staffCarts cart = itr.next();
                holder.push(cart);
            }
            staffBox.getChildren().add(holder);
        }
        catch (Exception e)
        {
            if ( holder != null)
                if ( holder.getChildren().size() > 0)
                    staffBox.getChildren().add(holder);
        }

        }
    Iterator<BlackCard> itr2 = blackLists.iterator();
    while ( itr.hasNext())
    {
        BlackList holder = null;
        try {
            holder = new BlackList();
            for ( int  i = 0 ; i < 3 ;i++) {
                BlackCard cart = itr2.next();
                holder.push(cart);
            }
            staffBox.getChildren().add(holder);
        }
        catch (Exception e)
        {
            if ( holder != null)
                if ( holder.getChildren().size() > 0)
                    staffBox.getChildren().add(holder);
        }

    }
}

    @Override
    public void preloadData(ArrayList<Staff> staff, models.admin admin) {
        this.admin = admin;
        staffBox.setAlignment(Pos.CENTER);
        BlackList.setAlignment(Pos.CENTER);

        SearchField.setOnKeyPressed(event->{
            if(SearchField.getText().length() > 1){

                ser.shutdown();
                ser = null;
                ser = Executors.newFixedThreadPool(1);
                Thread t = new Thread(new Searcher(SearchField.getText(),admin));

                ser.execute(t);

            }

        });
        search.setOnAction(evet->{

            ser.shutdown();
            ser = null;
            ser = Executors.newFixedThreadPool(1);
            Thread t = new Thread(new Searcher(SearchField.getText(),admin));

            ser.execute(t);

        });
        goBack.setOnAction(event -> {
            adminDashbordCont con = new adminDashbordCont();


            SceneChanger sc = new SceneChanger();
            try {


                sc.changeScenes(event, "/view/adminDashbord.fxml", "DashBord", null, con, admin);
            }catch (Exception e)
            {e.printStackTrace();}
        });

        Delete.setOnAction(Event->{
            ser.shutdown();
            Platform.runLater(()-> {
                ArrayList<Staff> stf = new ArrayList<>();
                StaffHOlders.forEach(s -> stf.add(s.staff));

                JSONArray js = new JSONArray();
                JsonWriterAndReader jsw = new JsonWriterAndReader();
                js.add(jsw.createHeader("delete"));
                js.add(jsw.getstaffArrayToDelete(stf));
               try {
                   con.send(js, admin.getUsername());
               }catch (Exception e)
               {e.printStackTrace();}
            });




            adminDashbordCont con = new adminDashbordCont();


            SceneChanger sc = new SceneChanger();
            try {


                sc.changeScenes(Event, "/view/adminDashbord.fxml", "DashBord", null, con, admin);
            }catch (Exception e)
            {e.printStackTrace();}

        });
        }
    }



