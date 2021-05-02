package by.academy.home.connection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {


    public String hashMaker (String str){
        MessageDigest md5;

        {
            String result = "";
            try {
                md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(str.getBytes());
                StringBuilder stringBuilder = new StringBuilder();
                for (byte b: bytes) {
                    stringBuilder.append(String.format("%02X",b));
                }
                result = String.valueOf(stringBuilder);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return result;
        }

    }


}
