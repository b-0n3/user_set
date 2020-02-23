package models;


import javafx.application.Platform;

import javafx.stage.Stage;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TimerTask;

public class Pinger extends TimerTask  {
    private  admin admin;
    private Socket clientPing;
    Stage stage;
    TCPClient cons ;
    public Pinger(admin admin, Stage stage, TCPClient cons)
    {
        this.admin = admin;
        this.stage = stage ;
        this.cons = cons;

    }
    @Override
    public void run() {
        try {
            clientPing = new Socket("ec2-54-89-153-146.compute-1.amazonaws.com", 4441);

            OutputStream outt = this.clientPing.getOutputStream();
            InputStream inputStream = this.clientPing.getInputStream();

            ObjectOutputStream out = new ObjectOutputStream(outt);
            ObjectInputStream bn = new ObjectInputStream(inputStream);
            out.writeObject(admin.getUsername());
            String resp = (String) bn.readObject();
            if (! resp.equals("Ok"))
            {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/view/login_frame.fxml"));
//                Parent parent = loader.load();
//
//                Scene scene = new Scene(parent);
//
//                //get the stage from the event that was passed in
//                cons.loged = false;
//
//                stage.setTitle("Please log in there was a problem with the server -_-");
//                stage.setScene(scene);
//                stage.show();
                Platform.runLater(() -> {
                    cons.loged = false;
                    new  Main().start(stage);
                });
                this.cancel();

                return;
            }
        }catch (Exception e)
        {

            Platform.runLater(() -> {
                cons.loged = false;
                new  Main().start(stage);

            });

            e.printStackTrace();
            this.cancel();

        }
    }
}
