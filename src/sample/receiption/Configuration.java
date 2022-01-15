package sample.receiption;

import sample.patient.ThePatient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Configuration {
    public static String ADMIN_AUTH_FILE_PATH = "src/sample/receiption/adminauth.txt";
    public static String PATIENT_FILE_PATH = "src/sample/patient/patientData/newUsers.txt";
    public static Admin LOGGED_IN_USER;
    
    public static List<Admin> GetAdminList() {
        List<Admin> admins = new ArrayList<Admin>();
        {
            try {
                File file = new File(Configuration.ADMIN_AUTH_FILE_PATH);
                Scanner scanner = new Scanner(file);
                
                for (int i = 0; scanner.hasNext(); i++) {
                    String inline = scanner.next();
                    
                    String[] line = inline.split(";;");
                    
                    Admin rAdmin = new Admin();
                    rAdmin.setID(line[0]);
                    rAdmin.setFirstName(line[1]);
                    rAdmin.setLastName(line[2]);
                    rAdmin.setEmail(line[3]);
                    rAdmin.setPass(line[4]);
                    rAdmin.setDate(line[5]);
                    rAdmin.setGender(line[6]);
                    admins.add(rAdmin);
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            return admins;
        }
    }
    
    public static List<ThePatient> GetPatientList() {
        
        ArrayList<ThePatient> patients = new ArrayList<ThePatient>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(PATIENT_FILE_PATH));
            String patient;
            while (scanner.hasNext()) {
                patient = scanner.nextLine();
                String[] allInfo = patient.split(";;");
                patients.add(new ThePatient(allInfo[0], allInfo[1], allInfo[2], allInfo[3], allInfo[4], allInfo[5], allInfo[6], allInfo[7], allInfo[8]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return patients;
    }
    
    public static String generateID() {
//        return UUID.randomUUID().toString();
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}