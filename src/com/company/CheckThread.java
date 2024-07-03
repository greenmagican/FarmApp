package com.company;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;




public class CheckThread extends Thread {


    /**
     * @param digest for a create new md5 when database laod again

     * @author Egemen Aksöz
     * // Thread operation
     */
    public CheckThread(MessageDigest digest) {

        super();

    }

    public void run() {


        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("animals.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while (true) {
            try {
                assert fis != null;
                if ((bytesCount = fis.read(byteArray)) == -1) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert digest != null;
            digest.update(byteArray, 0, bytesCount);
        }
        ;

        // close the input stream
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert digest != null;
        byte[] bytes = digest.digest();


        StringBuilder sb = new StringBuilder();

        // loop through the bytes array
        for (int i = 0; i < bytes.length; i++) {

            // the following line converts the decimal into
            // hexadecimal format and appends that to the
            // StringBuilder object
            sb.append(Integer
                    .toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        // finally we return the complete hash


        File file = new File("md5.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String path = System.getProperty("user.dir") + "\\" + "md5.txt";    // append all md5 to external file to ease to find key


        String md5 = sb.toString();

        Thread.yield();


        Scanner scan = null;

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while(true){
            assert scan != null;
            if (!scan.hasNextLine()) break;
            // check regenerating md5 match with in my external file md5

            if(scan.nextLine().equals(md5)){
                System.out.println("DataBase has not been changed !");

            }
            else{
                System.out.println("DataBase has been changed !");
            }


        }


        //PrintWriter wk = new PrintWriter(writer1);        // write md5 into another external file called "md5.txt"


    }


}
