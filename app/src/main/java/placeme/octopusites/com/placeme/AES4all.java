package placeme.octopusites.com.placeme;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES4all {

    public static Cipher getAESCBCEncryptor(byte[] keyBytes, byte[] IVBytes, String padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IVBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/"+padding);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher;
    }
    
    public static Cipher getAESCBCDecryptor(byte[] keyBytes, byte[] IVBytes, String padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IVBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/"+padding);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher;
    } 

    public static Cipher getAESECBEncryptor(byte[] keyBytes, String padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/"+padding);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher;
    }
    
    public static Cipher getAESECBDecryptor(byte[] keyBytes, String padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/"+padding);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher;
    }
    
    public static byte[] encrypt(Cipher cipher, byte[] dataBytes) throws Exception{
        ByteArrayInputStream bIn = new ByteArrayInputStream(dataBytes);
        CipherInputStream cIn = new CipherInputStream(bIn, cipher);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        int ch;
        while ((ch = cIn.read()) >= 0) {
          bOut.write(ch);
        }
        return bOut.toByteArray();
    } 

    public static byte[] decrypt(Cipher cipher, byte[] dataBytes) throws Exception{
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
        cOut.write(dataBytes);
        cOut.close();
        return bOut.toByteArray();    
    } 

    
    public static byte[] demo1encrypt(byte[] keyBytes, byte[] ivBytes, String sPadding, byte[] messageBytes) throws Exception {
        Cipher cipher = getAESCBCEncryptor(keyBytes, ivBytes, sPadding); 
        return encrypt(cipher, messageBytes);
    }

    public static byte[] demo1decrypt(byte[] keyBytes, byte[] ivBytes, String sPadding, byte[] encryptedMessageBytes) throws Exception {
        Cipher decipher = getAESCBCDecryptor(keyBytes, ivBytes, sPadding);
        return decrypt(decipher, encryptedMessageBytes);
    }
    
    public static byte[] demo2encrypt(byte[] keyBytes, String sPadding, byte[] messageBytes) throws Exception {
        Cipher cipher = getAESECBEncryptor(keyBytes, sPadding); 
        return encrypt(cipher, messageBytes);
    }

    public static byte[] demo2decrypt(byte[] keyBytes, String sPadding, byte[] encryptedMessageBytes) throws Exception {
        Cipher decipher = getAESECBDecryptor(keyBytes, sPadding);
        return decrypt(decipher, encryptedMessageBytes);
    }




     static String OtoString( Serializable o ,String digest1,String digest2) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();

        String objectString=new String(SimpleBase64Encoder.encode(baos.toByteArray()));

         byte[] demoKeyBytes=SimpleBase64Encoder.decode(digest1);
         byte[] demoIVBytes=SimpleBase64Encoder.decode(digest2);
         String sPadding = "ISO10126Padding";

         byte[] objBytes = objectString.getBytes("UTF-8");
         byte[] objEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, objBytes);
         String encobj=new String(SimpleBase64Encoder.encode(objEncryptedBytes));

        return  encobj;

    }

     static Object fromString( String s,String digest1,String digest2) throws Exception
     {

         byte[] demoKeyBytes=SimpleBase64Encoder.decode(digest1);
         byte[] demoIVBytes=SimpleBase64Encoder.decode(digest2);
         String sPadding = "ISO10126Padding";

         byte[] EncryptedBytes = SimpleBase64Encoder.decode(s);
         byte[] DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, EncryptedBytes);
         String objectString = new String(DecryptedBytes);

        byte[] data = SimpleBase64Encoder.decode(objectString);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    static String Encrypt(String string,String digest1,String digest2) throws Exception
    {
        byte[] demoKeyBytes=SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes=SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        byte[] objBytes = string.getBytes("UTF-8");
        byte[] objEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, objBytes);

        return new String(SimpleBase64Encoder.encode(objEncryptedBytes));
    }
    public static String Decrypt(String string, String digest1, String digest2) throws Exception
    {
        byte[] demoKeyBytes=SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes=SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        byte[] EncryptedBytes = SimpleBase64Encoder.decode(string);
        byte[] DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, EncryptedBytes);
        return new String(DecryptedBytes);
    }
    


}
