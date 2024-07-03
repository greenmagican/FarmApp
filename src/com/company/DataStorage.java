package com.company;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    private Connection connection = null;

    /*** @param

     * @author Egemen Aksöz
     * // Connection with data storage
     */

    public DataStorage() {


        try {
            // STEP1 -- Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
            System.out.println("Database connected");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /*** @param

     * @author Egemen Aksöz
     * @param AnimalList,EmpList
     * // Write info to database
     */

    public void writeData(ArrayList<Animal> AnimalList, ArrayList<Employee> EmpList) throws SQLException, ParseException {
        String addAnimal = "INSERT INTO animal(tagNo,gender,dateOfBirth,purchased,type,Weight) VALUES (?,?,?,?,?,?)";
        String addEmployee = "INSERT INTO employee(empID,gender,dateOfBirth,type,BsCDegree,dateOfGraduation,expertiseLevel,previousFarmName,workExperience) VALUES (?,?,?,?,?,?,?,?,?)";
        int flag = -1;
        Boolean f = null;
        String check_tagNo = "SELECT * FROM animal WHERE tagNo=? ";
        String check_empID = "SELECT * FROM employee WHERE empID=? ";
        String dateRandom = "00/00/0000";
        java.util.Date dateNull = new SimpleDateFormat("mm/DD/yyyy").parse(dateRandom);

        PreparedStatement pst = null;
        PreparedStatement pstE = null;
        PreparedStatement traverse = null;
        PreparedStatement traverseEmp = null;
        pst = this.connection.prepareStatement(addAnimal);    // create statement for write
        pstE = this.connection.prepareStatement(addEmployee);
        traverse = this.connection.prepareStatement(check_tagNo); // create statement for checking tagNo exist or not
        traverseEmp = this.connection.prepareStatement(check_empID);
        for (Animal animal : AnimalList) {


            traverse.setInt(1, animal.getTagNo());
            ResultSet rs1 = traverse.executeQuery(); // execute query
            if (rs1.next()) {     // tagNo in db available or not checking...
                System.out.println("TagNo already exists in DB !!");
                flag = 1;

            }
        }
        if (flag != 1) {


            for (Animal animal : AnimalList) {

                if (animal instanceof Cow) {          // check if inside an animalList is cow or sheep


                    pst.setInt(1, animal.getTagNo());
                    pst.setString(2, animal.getGender());
                    pst.setDate(3, new java.sql.Date(animal.getDateOfBirth().getTime()));
                    pst.setBoolean(4, animal.isPurchased());
                    pst.setString(5, "c");
                    pst.setDouble(6, ((Cow) animal).getWeight());
                    int result = pst.executeUpdate();


                } else {


                    pst.setInt(1, animal.getTagNo());
                    pst.setString(2, animal.getGender());
                    pst.setDate(3, new java.sql.Date(animal.getDateOfBirth().getTime()));
                    pst.setBoolean(4, animal.isPurchased());
                    pst.setString(5, "s");
                    pst.setDouble(6, 0);     // if type is sheep I assigned weight is "0" means "null"
                    int result = pst.executeUpdate();


                }


            }


        }


        for (Employee employee : EmpList) {
            traverseEmp.setInt(1, employee.getEmpID());
            ResultSet rs2 = traverseEmp.executeQuery(); // execute query
            if (rs2.next()) {     // tagNo in db available or not checking...
                System.out.println("empID already exists in DB !!");
                flag = 1;

            }

        }


        if (flag != 1) {


            for (Employee employee : EmpList) {

                if (employee instanceof Veterinary) {          // check if inside an animalList is cow or sheep


                    pstE.setInt(1, employee.getEmpID());
                    pstE.setString(2, employee.getGender());
                    pstE.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));
                    pstE.setString(4, "v");
                    pstE.setBoolean(5, ((Veterinary)employee).isBScDegree());
                    pstE.setDate(6, new java.sql.Date(((Veterinary) employee).getDateOfGraduation().getTime()));
                    pstE.setInt(7, ((Veterinary) employee).getExpertiseLevel());
                    pstE.setString(8, "none");
                    pstE.setInt(9, 0);   // 0 means none
                    int result = pstE.executeUpdate();
                    //(java.sql.Date) ((Veterinary)employee).getDateOfGraduation());

                } else {


                    pstE.setInt(1, employee.getEmpID());
                    pstE.setString(2, employee.getGender());
                    pstE.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));
                    pstE.setString(4, "f");
                    pstE.setBoolean(5, false); // false means none for farmworker.
                    pstE.setDate(6, new java.sql.Date(dateNull.getTime()));
                    pstE.setInt(7, 0);
                    pstE.setString(8, ((FarmWorker) employee).getPreviousFarmName());
                    pstE.setInt(9, ((FarmWorker) employee).getWorkExperience());   // 0 means none
                    int result = pstE.executeUpdate();




                }


            }
        }

    }

    /*** @param

     * @author Egemen Aksöz
     * @param tagNo
     * // According to tagNo delete animal
     */

    public void readDataForDeleteAnimal(int tagNo) throws SQLException {

        String check_tagNo = "SELECT * FROM animal WHERE tagNo=? ";
        PreparedStatement traverse = null;
        traverse = this.connection.prepareStatement(check_tagNo);
        String deleteAnimal = "DELETE FROM animal WHERE tagNo=? ";
        PreparedStatement pst = null;
        pst = this.connection.prepareStatement(deleteAnimal);


        traverse.setInt(1, tagNo);
        ResultSet rs1 = traverse.executeQuery(); // execute query
        if (!rs1.next()) {     // tagNo in db available or not checking...
            System.out.println("TagNo doesn't exists in DB !!");


        }else{
            pst.setInt(1,tagNo);        // if tagNo exist delete corresponding animal.
            pst.executeUpdate();
            System.out.println("Corresponding animal has been deleted from DB.");
        }

    }

    /*** @param

     * @author Egemen Aksöz
     * @param empID
     * // According to empID delete employee from database
     */

    public void readDataForDeleteEmployee(int empID) throws SQLException {

        String check_empID = "SELECT * FROM employee WHERE empID=? ";
        PreparedStatement traverse = null;
        traverse = this.connection.prepareStatement(check_empID);
        String deleteAnimal = "DELETE FROM employee WHERE empID=? ";
        PreparedStatement pst = null;
        pst = this.connection.prepareStatement(deleteAnimal);


        traverse.setInt(1, empID);
        ResultSet rs1 = traverse.executeQuery(); // execute query
        if (!rs1.next()) {     // tagNo in db available or not checking...
            System.out.println("empID doesn't exists in DB !!");


        }else{
            pst.setInt(1,empID);        // if tagNo exist delete corresponding animal.
            pst.executeUpdate();
            System.out.println("Corresponding employee has been deleted from DB.");
        }

    }


    /*** @param

     * @author Egemen Aksöz
     *
     * /For listing all animals
     */


    public void readDataForListAnimal() throws SQLException {


        String ListAnimal = "SELECT * FROM animal";
        PreparedStatement traverse = null;
        traverse = this.connection.prepareStatement(ListAnimal);
        ResultSet rs = traverse.executeQuery();
        System.out.println("                    ANIMAL");
        System.out.println("--------------------------------------------------");
        System.out.println("tagNo  gender  dateOfBirth  purchased  type  Weight");

        while (rs.next()) {
            int tagNo = rs.getInt("tagNo");
            String gender = rs.getString("gender");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            boolean purchased = rs.getBoolean("purchased");
            String type = rs.getString("type");
            double Weight = rs.getDouble("Weight");
            System.out.println(tagNo+"     " +gender+"     "+ dateOfBirth+"     "+ purchased+ "     " + type+ "     " + Weight);
        }




    }

    /*** @param

     * @author Egemen Aksöz
     *
     * // List for employee
     */


    public void readForListEmployee() throws SQLException {

        String ListAnimal = "SELECT * FROM employee";
        PreparedStatement traverse = null;
        traverse = this.connection.prepareStatement(ListAnimal);
        ResultSet rs = traverse.executeQuery();
        System.out.println("                    EMPLOYEE");
        System.out.println("--------------------------------------------------");
        System.out.println("empID  gender  dateOfBirth  type  BsCDegree  dateOfGraduation expertiseLevel previousFarmName workExperience");

        while (rs.next()) {
            int empID = rs.getInt("empID");
            String gender = rs.getString("gender");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            String type = rs.getString("type");
            boolean BsCDegree = rs.getBoolean("BsCDegree");
            Date dateOfGraduation = rs.getDate("dateOfGraduation");
            int expertiseLevel = rs.getInt("expertiseLevel");
            String previousFarmName = rs.getString("previousFarmName");
            int workExperience = rs.getInt("workExperience");

            System.out.println(" "+empID+"       " +gender+"     "+ dateOfBirth+"    "+ type+ "     " + BsCDegree+ "       " + dateOfGraduation+ "           " + expertiseLevel + "          " + previousFarmName + "          " + workExperience);
        }


    }



    /*** @param

     * @author Egemen Aksöz
     *
     * // Serialize animals.dat
     */


    public void readDataForSecurity(ArrayList<Animal> AnimalList) throws IOException {


        ObjectOutputStream outAnimals = new ObjectOutputStream(new FileOutputStream("animals.dat"));

        for (Animal animal : AnimalList) {


            outAnimals.writeObject(animal);


        }

    }
}

