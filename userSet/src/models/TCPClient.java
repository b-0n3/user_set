package models;


import com.mysql.cj.xdevapi.JsonParser;
import com.sun.tools.javac.main.Option;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.print.DocFlavor;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Random;


public class TCPClient  {

    private  Socket client;

   // private static PrintWriter out;
    private  BigInteger prime, gen, privateKey, key;

    private  AlgorithmParameterGenerator paramGen;
    private  AlgorithmParameters params;
    private  DHParameterSpec dhSpec;
   // private static BufferedReader bn;

  private  ObjectInputStream bn ;
    private  ObjectOutputStream out ;
    private  static byte[] PKey;

    public static boolean loged;

    private  static String uu  = null;


    public void send( JSONArray object, String userame)throws Exception
    {
        this.uu = userame;

        client = new Socket("ec2-54-89-153-146.compute-1.amazonaws.com", 4444);




        OutputStream outputStream = client.getOutputStream();
        InputStream inputStream = client.getInputStream();

        out = new ObjectOutputStream(outputStream);
        bn = new ObjectInputStream(inputStream);

        out.writeObject(userame);

        out.flush();
        JSONObject array = (JSONObject)bn.readObject();
        if(((String)((JSONObject)((array).get("res"))).get("res")).equals("logged") &&
                ((JSONObject)((JSONObject)object.get(0)).get("req")).get("req").equals("check Password"))
        {
            out.writeObject(new JsonWriterAndReader().createHeader("Ko"));
            throw new IllegalArgumentException("Already logged!!");
        }
        else
            out.writeObject(new JsonWriterAndReader().createHeader("Ok"));

        try {
            if (!loged)
            {
                if (!authentificat()) {

                    Logout();
                    return ;
                }
            }

            CrypterDecrypter cr = new CrypterDecrypter();

            String bytes = cr.enCrypt(object.toString(), PKey);
            String toSend = cr.toString(bytes);
            if ( toSend == null)
            {
               Logout();
               return ;
            }
            out.writeObject(toSend);
            out.flush();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {



        }

    }
    public JSONArray rec()
    {
        JSONArray array = null;
        try{
            CrypterDecrypter cr = new CrypterDecrypter();
            String tes = (String ) bn.readObject();
            String reby  = (String) cr.fromString(tes);
            if (tes == null) {
                Logout();
                return array;
            }

            JSONParser par = new JSONParser();
            array = (JSONArray) par.parse(cr.Decrypt(reby, PKey));

        }catch (Exception e)
        {



        }
        return array;
    }

    public  boolean authentificat() throws Exception {

        try {



            paramGen = AlgorithmParameterGenerator.getInstance("DH");
            paramGen.init(1024);
            params = paramGen.generateParameters();
            dhSpec = (DHParameterSpec) params.getParameterSpec(DHParameterSpec.class);

            Random randomGenerator = new Random();

            privateKey = new BigInteger(1024, randomGenerator);



                prime = (BigInteger ) bn .readObject();

                gen =  (BigInteger ) bn .readObject();

                BigInteger  myke = gen.modPow(privateKey, prime);

                out.writeObject(myke);
                out.flush();

                //out.print(gen);
                BigInteger serverKe = (BigInteger) bn.readObject();





                key = serverKe.modPow(privateKey, prime);
                MessageDigest md = MessageDigest.getInstance("MD5");

               PKey = md.digest(key.toByteArray());




                CrypterDecrypter cr = new CrypterDecrypter();
             String Todec = cr.enCrypt("done", PKey);

                out.writeObject(Todec);

                return true;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
      }



    public void Logout() throws  Exception
    {
        if ( loged) {

            out.close();
            client.close();

        }
        }

    public String getUu() {
        return this.uu;
    }
}
