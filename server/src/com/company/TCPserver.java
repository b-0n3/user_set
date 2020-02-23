package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.crypto.spec.DHParameterSpec;
import javax.swing.plaf.LabelUI;
import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TCPserver {
    private static ServerSocket server;
    private static Socket client;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
   private  Hashtable<String ,Client> clientss = new Hashtable<>();
    boolean pingerisLuncher = false;
    private static ExecutorService pool =  Executors.newFixedThreadPool(150);
    private static ExecutorService checker =  Executors.newFixedThreadPool(1);
    private static ExecutorService Lunchers =  Executors.newFixedThreadPool(150);
    private  AlgorithmParameterGenerator paramGen;

    private AlgorithmParameters params;
    private    DHParameterSpec dhSpec;
    private static BigInteger prime;
    private static   BigInteger  gen;

    public  class pinger implements  Runnable{
        ServerSocket Pinger;
        Socket clientPing;
        @Override
        public void run() {
            try {
                Pinger = new ServerSocket(4441);
                while (true)
                {
                    try {
                        clientPing = Pinger.accept();
                        InputStream inputStream = this.clientPing.getInputStream();
                        OutputStream outt = this.clientPing.getOutputStream();

                        ObjectInputStream bn = new ObjectInputStream(inputStream);
                        ObjectOutputStream out = new ObjectOutputStream(outt);
                        String username = (String) bn.readObject();
                        if (clientss.get(username) != null) {
                            clientss.get(username).setTim(15);
                            out.writeObject("Ok");
                        } else {
                            out.writeObject("Ko");
                        }
                    }catch (Exception e)
                    {
                        Pinger.close();
                        Pinger = new ServerSocket(4441);
                    }
                }
            }catch (Exception e )
            {
                e.printStackTrace();
            }
        }
    }
    public class Luncher implements  Runnable{
        private  Socket client;


        public Luncher (Socket client)
        {

                this.client = client;
        }
        @Override
        public void run() {
            try {
                InputStream inputStream = this.client.getInputStream();
                OutputStream outt = this.client.getOutputStream();

                ObjectInputStream bn = new ObjectInputStream(inputStream);
                ObjectOutputStream out = new ObjectOutputStream(outt);
                String username = (String) bn.readObject();
                System.out.println(username);
                if (username != null)
                    if (clientss.get(username) == null)
                        clientss.put(username, new Client(username, client.getInetAddress().toString()));

                if (username != null) {
                    if (clientss.get(username).getIsloged())
                        out.writeObject(new JsonFileReaderAndWriter().createHeader("logged"));
                    else
                        out.writeObject(new JsonFileReaderAndWriter().createHeader("notLoged"));
                    if(((String)((JSONObject)(((JSONObject)bn.readObject()).get("req"))).get("req")).equals("Ok")) {
                        ClientHandler cli = new ClientHandler(client, clientss.get(username), out, bn, prime, gen);

                        clients.add(cli);
                        pool.execute(cli);
                    }
                    }

                if ( !pingerisLuncher ){
                    pool.execute(new pinger());
                    pingerisLuncher = true;
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public  class clientChecker implements  Runnable{


        @Override
        public void run() {

            while (true)
            {
                Set<String> keys = clientss.keySet();
                for(String key: keys )
                {
                    if (clientss.get(key).isJobDone())
                    {
                        File logs = new File("/root/logs/"+clientss.get(key).getAdmin().getUsername()+"/");
                        if(logs.exists())
                        {
                            for(File file :logs.listFiles())
                            {
                                file.delete();
                            }
                            logs.delete();
                        }
                        File toDo = new File("/root/toDo/"+clientss.get(key).getAdmin().getUsername()+"/");
                        if(toDo.exists())
                        {
                            for(File file : toDo.listFiles())
                            {
                                file.delete();
                            }
                            toDo.delete();
                        }
                        System.out.println("delleting admin");
                        clientss.remove(key);
                    }
                }

            }
        }
    }


    public static  void main(String[] args)
    {
            new TCPserver().start();
    }

    public void   start() {

        try {

                File sss = new File("/root/sss/");
                if (!sss.exists())
                    sss.mkdirs();
                File images = new File("/root/sss/images/");
                if (!images.exists())
                    images.mkdirs();
                File Staff = new File("/root/sss/images/staffs/");
                if(!Staff.exists())
                    Staff.mkdirs();
                File admins = new File("/root/sss/images/admins/");
                if(!admins.exists())
                    admins.mkdirs();
                admins = null;
                Staff = null;
                sss = null;
                images = null;
                System.gc();
                paramGen = AlgorithmParameterGenerator.getInstance("DH");
                paramGen.init(1024);
                params = paramGen.generateParameters();
                dhSpec = (DHParameterSpec) params.getParameterSpec(DHParameterSpec.class);
            gen = dhSpec.getG();
            prime = dhSpec.getP();
            server = new ServerSocket(4444);

            checker.execute(new clientChecker());

            while (true) {
                client = server.accept();
                Luncher ln = new Luncher(client);
                Lunchers.execute(ln);
                System.gc();

            }
        }catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

    }
}
