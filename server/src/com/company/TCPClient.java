package com.company;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;


public class TCPClient {

    private  Socket client;
    private  BufferedReader in;
   // private static PrintWriter out;
    private  BigInteger prime, gen, privateKey, key;

    private  AlgorithmParameterGenerator paramGen;
    private  AlgorithmParameters params;
    private  DHParameterSpec dhSpec;
   // private static BufferedReader bn;

  private  ObjectInputStream bn ;
    private  ObjectOutputStream out ;
    private  static SecretKeySpec PKey;

    public static void main(String[] args) throws Exception {
       new TCPClient().conn();
    }

    public void conn()throws Exception
    {
        client = new Socket("ec2-3-134-114-56.us-east-2.compute.amazonaws.com", 4444);
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("connected");

        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();

        bn = new ObjectInputStream(inputStream);
        out = new ObjectOutputStream(outputStream);
        System.out.println("connected to  : " +  client.getInetAddress());

        try {
            if (!authentificat()) {
                in.close();
                out.close();
                client.close();
                return ;
            }
            while (true) {

                String request = in.readLine();
                if (request.equals("exit")) break;
                System.out.println(request);
                out.writeObject(request);
                out.flush();
                String ret =String.valueOf( bn.readObject());
                if (ret == null) break;
                System.out.println(ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
            client.close();
        }

    }

    public  boolean authentificat() throws Exception {
        try {



            paramGen = AlgorithmParameterGenerator.getInstance("DH");
            paramGen.init(1024);
            params = paramGen.generateParameters();
            dhSpec = (DHParameterSpec) params.getParameterSpec(DHParameterSpec.class);
            System.out.println("1");
            Random randomGenerator = new Random();
            System.out.println("2");
            privateKey = new BigInteger(1024, randomGenerator);
            System.out.println("3");
            while (true)
            {

                prime = (BigInteger ) bn .readObject();

                gen =  (BigInteger ) bn .readObject();

                BigInteger  myke = gen.modPow(privateKey, prime);
                System.out.println( "this is my key  :" + myke);
                out.writeObject(myke);
                out.flush();

                //out.print(gen);
                BigInteger serverKe = (BigInteger) bn.readObject();





                key = serverKe.modPow(privateKey, prime);
                MessageDigest md = MessageDigest.getInstance("MD5");

                byte[] bytes = md.digest(key.toByteArray());

                PKey = new SecretKeySpec(bytes, "AES");

                System.out.println("this is key   " + PKey);
                CrypterDecrypter cr = new CrypterDecrypter();
               String Todec = cr.enCrypt("done", bytes);

                out.writeObject(Todec);

                return true;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }


    }


}
