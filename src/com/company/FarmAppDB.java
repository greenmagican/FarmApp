package com.company;

import javax.swing.*;
import java.io.*;
import java.security.DigestInputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class FarmAppDB {

    public static void main(String[] args) throws SQLException, ParseException, IOException, ClassNotFoundException, NoSuchAlgorithmException {

        ArrayList<Animal> AnimalList = new ArrayList<Animal>();
        ArrayList<Employee> empList = new ArrayList<Employee>();


        MessageDigest mdigest = MessageDigest.getInstance("MD5");
        MessageDigest mdigestForCheck = MessageDigest.getInstance("MD5");
        DataStorage data = new DataStorage();

        CheckThread ch = new CheckThread(mdigestForCheck);


        menu(AnimalList, empList, data, mdigest,mdigestForCheck,ch);


    }


    public static void menu(ArrayList<Animal> AnimalList, ArrayList<Employee> empList, DataStorage data, MessageDigest digest,MessageDigest digestForCheck,CheckThread ch) throws ParseException, IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {




          // Create a new md5 when db loaded again with thread.

        boolean forever = true;

        ch.start();

        while (forever) {



// menu implementation.


            System.out.println("1) Add Cow ");
            System.out.println("2) Delete Animal ");
            System.out.println("3) Add Sheep ");
            System.out.println("4) Add Vet ");
            System.out.println("5) Delete Employee ");
            System.out.println("6) Add Farm Worker");
            System.out.println("7) Delete List Save and Quit ");

            int vetId;
            int tagNo;
            int empId;

            int selection;



            //System.out.println(scan.nextLine());

             // create new md5 when loaded again
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter option: ");
            selection = scanner.nextInt();









            switch (selection) {


                case 1:

                    addCow(AnimalList);
                    break;
                case 2:
                    System.out.println("Please enter tagNo that you want to delete: ");

                    tagNo = scanner.nextInt();

                    deleteAnimal(AnimalList, tagNo);
                    data.readDataForDeleteAnimal(tagNo);

                    break;
                case 3:
                    addSheep(AnimalList);
                    break;
                case 4:

                    addVet(empList);
                    break;
                case 5:
                    System.out.println("Please enter the empID that you want to delete: ");
                    empId = scanner.nextInt();
                    deleteEmployee(empList, empId);
                    data.readDataForDeleteEmployee(empId);

                    break;
                case 6:
                    addFarmWorker(empList);
                    break;


                case 7:


                    data.writeData(AnimalList, empList);
                    data.readDataForListAnimal();
                    data.readForListEmployee();
                    data.readDataForSecurity(AnimalList);
                    String md5 = encryptString(digest);





                    System.out.println(md5);


                    //System.exit(0);

                    //listAnimal(AnimalList);
                    break;




                default:
                    System.out.println("Incorrect Choice !!");
            }


        }
    }
    /**
     * @param AnimalList All animals holding just one list called AnimalList.

     * @author Egemen Aksöz
     * // Add new cow to the list of cows maintaned.
     */

    public static void addCow(ArrayList<Animal> AnimalList) throws ParseException, IOException, SQLException {


        int tagNo;
        String gender, dateOfBirth;
        boolean purchased;
        double Weight;


        // Take information about cows, then added into the cowList.
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the tagNo of Cow: ");
        tagNo = scan.nextInt();


        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the gender of Cow (m/f):  ");
        gender = scanner.nextLine();

        System.out.println("Please enter dateOfBirth of the cow (mm/DD/yyyy) : ");
        dateOfBirth = scanner.nextLine();


        System.out.println("Please enter the purchased situation of the cow: ");
        purchased = scanner.nextBoolean();

        System.out.println("Please enter the weight of the cow: ");
        Weight = scanner.nextDouble();


        Date date = new SimpleDateFormat("mm/DD/yyyy").parse(dateOfBirth);


        Cow cow = new Cow(tagNo, gender, date, purchased, Weight, Cow.getType());

        AnimalList.add(cow);

        // create object

    }


    /**
     * @param AnimalList All animals holding just one list called AnimalList.

     * @author Egemen Aksöz
     * // Add new Sheep to the list of cows maintaned.
     */


    public static void addSheep(ArrayList<Animal> AnimalList) throws ParseException {

        int tagNo;
        String gender, dateOfBirth;
        boolean purchased;
        double Weight;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the tagNo of Sheep: ");
        tagNo = scan.nextInt();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the gender of Sheep (m/f):  ");
        gender = scanner.nextLine();

        System.out.println("Please enter dateOfBirth of the Sheep (mm/DD/yyyy) : ");
        dateOfBirth = scanner.nextLine();


        System.out.println("Please enter the purchased situation of the Sheep: ");
        purchased = scanner.nextBoolean();


        Date date = new SimpleDateFormat("mm/DD/yyyy").parse(dateOfBirth);


        Sheep sheep = new Sheep(tagNo, gender, date, purchased, Sheep.getType());

        AnimalList.add(sheep);


    }

    /**
     * @param EmpList All employee holding just one list called AnimalList.

     * @author Egemen Aksöz
     * // Add new employee to the list of cows maintaned.
     */
    public static void addVet(ArrayList<Employee> EmpList) throws ParseException {

        int empID, expertiseLevel;
        String gender, dateOfBirth, dateOfGraduation;
        boolean BScDegree;


        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the empID of vet: ");
        empID = scan.nextInt();


        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the gender of vet (m/f):  ");
        gender = scanner.nextLine();

        System.out.println("Please enter dateOfBirth of the vet (mm/DD/yyyy): ");
        dateOfBirth = scanner.nextLine();


        System.out.println("Please enter the dateofGraduation of the vet: ");
        dateOfGraduation = scanner.nextLine();


        System.out.println("Please enter the BsCDegree situation of vet: ");
        BScDegree = scanner.nextBoolean();


        System.out.println("Please enter the expertiseLevel of the vet: ");
        expertiseLevel = scanner.nextInt();


        Date dateBirth = new SimpleDateFormat("mm/DD/yyyy").parse(dateOfBirth);
        Date dateGrad = new SimpleDateFormat("mm/DD/yyyy").parse(dateOfGraduation);

        Veterinary vet = new Veterinary(empID, gender, dateBirth, BScDegree, dateGrad, expertiseLevel);

        EmpList.add(vet);


    }


    /**
     * @param EmpList All employee holding just one list called AnimalList.

     * @author Egemen Aksöz
     * // Add new FarmWorker to the list of cows maintaned.
     */


    public static void addFarmWorker(ArrayList<Employee> EmpList) throws ParseException {

        int empID, workExperience;
        String gender, dateOfBirth, previousFarmName;


        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the empID of vet: ");
        empID = scan.nextInt();


        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the gender of vet (m/f):  ");
        gender = scanner.nextLine();

        System.out.println("Please enter dateOfBirth of the vet (mm/DD/yyyy): ");
        dateOfBirth = scanner.nextLine();


        System.out.println("Please enter the previousFarmName of the employee: ");
        previousFarmName = scanner.nextLine();


        System.out.println("Please enter the workExperience of the employee: ");
        workExperience = scanner.nextInt();


        Date dateBirth = new SimpleDateFormat("mm/DD/yyyy").parse(dateOfBirth);

        FarmWorker farm = new FarmWorker(empID, gender, dateBirth, previousFarmName, workExperience);

        EmpList.add(farm);


    }

    /**
     * @param AnimalList All animals holding just one list called AnimalList.

     * @author Egemen Aksöz
     * // Delete animal
     */
    public static void deleteAnimal(ArrayList<Animal> AnimalList, int tagNo) {

        int result;
        //int flag = 0;


        Iterator<Animal> cowIterator = AnimalList.iterator(); // iterate AnimalList by using while iterator


        while (cowIterator.hasNext()) {
            // check corresponding animal instanceof cow or not.


            Animal cow2 = cowIterator.next();
            result = cow2.getTagNo();               // hold tagNo, then checking about matching, If yes, remove it from list.
            if (result == tagNo) {

                cowIterator.remove();
                //flag = 1;

            }


        }

    }
    /**
     * @param EmpList All employee holding just one list called AnimalList.

     * @author Egemen Aksöz
     * // delete employee from the list of cows maintaned.
     */

    public static void deleteEmployee(ArrayList<Employee> EmpList, int empID) {


        int result;
        //int flag = 0;


        Iterator<Employee> employeeIterator = EmpList.iterator(); // iterate AnimalList by using while iterator


        while (employeeIterator.hasNext()) {
            // check corresponding animal instanceof cow or not.


            Employee employee = employeeIterator.next();
            result = employee.getEmpID();               // hold tagNo, then checking about matching, If yes, remove it from list.
            if (result == empID) {

                employeeIterator.remove();
                //flag = 1;

            }

        }


    }

    /**
     * @param AnimalList All employee holding just one list called AnimalList.

     * @author Egemen Aksöz
     * // List Animaş
     */
    public static void listAnimal(ArrayList<Animal> AnimalList) {


        for (Animal animal : AnimalList) {
            if (animal instanceof Cow) {
                System.out.println(animal.toString());
                System.out.println("Age of Cow: " + animal.getAge(animal.getDateOfBirth()));
                System.out.println("Type of Animal:" + Cow.getType());


            } else {
                System.out.println(animal.toString());
                System.out.println("Type of Animal:" + Cow.getType());
            }

            //for loop for iterate cowList then print details include AGE


        }
    }

    /**
     * @param digest for encryption I use digest

     * @author Egemen Aksöz
     * // encryption of animal.dat
     */
    public static String encryptString(MessageDigest digest) throws NoSuchAlgorithmException, IOException {

        FileInputStream fis = new FileInputStream("animals.dat");
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        ;

        // close the input stream
        fis.close();

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


        String path = System.getProperty("user.dir") + "\\" + "md5.txt";

        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(file.length() == 0){
            FileWriter writer1 = new FileWriter("md5.txt");
            BufferedWriter b = new BufferedWriter(writer1);
            b.write(sb.toString());
            b.newLine();
            b.close();
            writer1.close();

        }

        while(true){
            assert scan != null;

            if (!scan.hasNextLine()) break;
            // check regenerating md5 match with in my external file md5

            if(scan.nextLine().equals(sb.toString())){

                FileWriter writer1 = new FileWriter("md5.txt");
                BufferedWriter b = new BufferedWriter(writer1);
                b.write(sb.toString());
                b.newLine();
                b.close();
                writer1.close();

                //System.out.println("DataBase has not been changed !");

            }
            else{


                //System.out.println("DataBase has been changed !");
            }


        }// append all md5 to external file to ease to find key




        //PrintWriter wk = new PrintWriter(writer1);        // write md5 into another external file called "md5.txt"


        return sb.toString();


    }




}









