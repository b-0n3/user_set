package models;

import org.json.simple.JSONArray;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class CrypterDecrypter {

    public CrypterDecrypter()
    {

    }
    private static byte[] ivBytes = {

            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00
    };

    public  String enCrypt(String text, byte [] keyBytes) throws IOException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            IllegalBlockSizeException,
            BadPaddingException {


        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(text.getBytes("UTF-8"));

        return this.toString(encryptedBytes);
    }

    public  String Decrypt(String text,byte[] keyBytes ) throws IOException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            IllegalBlockSizeException,
            BadPaddingException, ClassNotFoundException {
        byte[] textBytes = (byte [])fromString(text);



        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);

        byte[] decodedBytes = cipher.doFinal(textBytes);

        return new String(decodedBytes, "UTF-8");
    }

    public  Object fromString( String s ) throws IOException,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    public  String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}