package com.company;





import org.json.simple.JSONArray;

import org.json.simple.parser.JSONParser;


import javax.crypto.spec.DHParameterSpec;


import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.*;
import java.util.Random;

public class ClientHandler implements Runnable {

    private Socket client;
    private  ObjectInputStream bn ;
    private  ObjectOutputStream out ;

    private   BigInteger  privateKey ,key;





    private   byte[] PKey;
    private Client cln;
    private static AlgorithmParameterGenerator paramGen;

    private static AlgorithmParameters params;
    private   static DHParameterSpec    dhSpec;

  private static  BigInteger prime ;
 private static   BigInteger  gen ;

    public ClientHandler(Socket client, Client cln,ObjectOutputStream out,ObjectInputStream bn, BigInteger prime , BigInteger gen) throws IOException {
        this.client = client;
        this.cln = cln;
       this. out =out;
        this . bn = bn;
        this.gen  = gen;
        this.prime = prime;
        try {
            paramGen = AlgorithmParameterGenerator.getInstance("DH");
            paramGen.init(1024);
            params = paramGen.generateParameters();
            dhSpec = (DHParameterSpec) params.getParameterSpec(DHParameterSpec.class);
        }catch (Exception e){e.printStackTrace();}

    }

    @Override
    public void run() {



        try {
            if (!cln.getIsloged())

            {
                if (!authentificat() )
            {
                out.close();
                try {
                    bn.close();
                } catch (Exception ex) {
                    System.err.println(ex.fillInStackTrace());
                }
                return ;
            }
            }
        CrypterDecrypter cr = new CrypterDecrypter();



                String tes = (String) bn.readObject();
                String reby  = ( String) cr.fromString(tes);
                JSONParser par = new JSONParser();

                String  bb = cr.Decrypt(reby, cln.getKey());



            JSONArray object = (JSONArray) par.parse(bb);


                String str = cln.Return(object).toString();

                String  bytes = cr.enCrypt(str, cln.getKey());
                String returned = cr.toString(bytes);

            if (object == null) {
                cln.setIsloged(false);
                cln.setJobDone(true);
                return ;
            }

                out.writeObject(returned);
                out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.fillInStackTrace());
        } finally {

            try {
                out.close();
                bn.close();
            } catch (Exception ex) {
                System.err.println(ex.fillInStackTrace());
            }
        }
    }


    public   boolean  authentificat() throws  Exception
    {

            try {

                Random randomGenerator = new Random();

                privateKey = new BigInteger(1024, randomGenerator);

                while (true) {

                    out.writeObject(prime);
                    out.flush();
                    out.writeObject(gen);

                    out.flush();

                    BigInteger calculatedClien = (BigInteger) bn.readObject();
                    BigInteger myke = gen.modPow(privateKey, prime);
                    System.out.println(myke);
                    out.writeObject(myke);
                    out.flush();

                    key = calculatedClien.modPow(privateKey, prime);


                    MessageDigest md = MessageDigest.getInstance("MD5");

                    PKey = md.digest(key.toByteArray());

                    cln.setKey(PKey);

                   String tes = (String) bn.readObject();
                    CrypterDecrypter cr = new CrypterDecrypter();
                    if (!(cr.Decrypt(tes, PKey).equals("done")))
                        throw new IllegalArgumentException("not au");

                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;

        }

    }



}
