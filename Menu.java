package com.thomas;

import java.sql.Time;
import java.util.*;
import static com.thomas.Illness.*;
import static com.thomas.Skill.*;

public class Menu {

    private final static Scanner scanner = new Scanner(System.in);
    static Map<Illness, Skill> connection = new HashMap<>();
    private static Patient patient;

    public static void makeConnection(){
        connection.put(TOOTHACHE, DENTISTRY);
        connection.put(HEADACHE, OTOLARYNGOLOGY);
        connection.put(MYOPIA, ORTHOPAEDICS);
        connection.put(FRACTURE, OPHTHALMOLOGY);
    }

    public static void showMainPage() {
        System.out.println("- Create Patient Info: Please enter 1\n"
                + "- Make Appointment: Please enter 2\n"
                + "- Cancel Appointment: Please enter 3\n"
                + "- Show Appointment: Please enter 4\n"
                + "- End panel: Please enter any other number\n"
                + "- Enter: ");
        Integer option;
        boolean validOptionEntered = false;
        while (!validOptionEntered) {
            try {
                option = scanner.nextInt();
                validOptionEntered = true;
                switch (option) {
                    case 1:
                        Menu.createPatientInfo();
                        break;
                    case 2:
                        System.out.println("Please enter your id:");
                        int id = scanner.nextInt();
                        Menu.makeAppointment(PatientRecords.records.get(id));
                        break;
                    case 3:
                        Menu.cancelAppointment();
                        break;
                    case 4:
//                        Menu.enterDoctorMenu();
                        Menu.showAppointment();
                        break;
//            case -1:
                    default:
                        System.out.println("Thanks for using booking appointment system");
                        System.exit(0);
                }

            } catch (InputMismatchException e) {

                System.out.println("Please enter a number.");
                scanner.next();

            }


        }
    }

//        if (option == 1) {
//            Menu.createPatientInfo();
//        } else if (option == 2) {
//            Menu.makeAppointment();
//        } else if (option == 3){
//            Menu.cancelAppointment();
//        } else{
//
//        }

    public static void createPatientInfo() {
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your age: ");
//        Integer age = scanner.nextInt();
        Integer age = null;
        Integer id = null;
//        boolean validAgeEntered = false;
        while (age == null) {
            try {
                age = scanner.nextInt();
//                validAgeEntered = true;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Error...please enter a number\n" + "Enter your age again: ");
            }
        }
        System.out.println("Enter your id: ");
//        Integer id = scanner.nextInt();
//        boolean validIdEntered = false;
        while (id == null) {
            try {
                id = scanner.nextInt();
//                validIdEntered = true;
            } catch (InputMismatchException e) {
                System.out.println(id);
                scanner.next();
                System.out.println("Error...please enter a number\n" + "Enter your id again: ");
            }
        }
        patient = new Patient(name, age, id);
        PatientRecords.addPatient(patient);
        backToMainPage();
//        Menu.makeAppointment(patient);
    }

    public static void makeAppointment(Patient patient) {
        System.out.println("Enter your illness: (TOOTHACHE,HEADACHE,MYOPIA,FRACTURE)");
        String illness = scanner.next();
        Skill neededSkill = null;
        try {
            neededSkill = connection.get(Illness.valueOf(illness));
        } catch (IllegalArgumentException e) {
            System.out.println("Error...please enter an illness above: ");
            Menu.makeAppointment(patient);
        }
        List<Doctor> doctors = new ArrayList<>();
//        for (Map.Entry<Integer, Doctor> entry : InitiateData.map.entrySet()) {
//            if (entry.getValue().getSkills().contains(neededSkill)) {
//                doctors.add(entry.getValue());
//            }
//        }
        for (Doctor doctor : InitiateData.map.values()) {
            if (doctor.getSkills().contains(neededSkill)) {
                doctors.add(doctor);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Doctor doctor : doctors) {
            sb.append(doctor.name).append(", ");
        }
        System.out.println("Doctors with your needed skills: " + sb.toString() + "Please enter one of the doctors name.");
        String enterDoctor = scanner.next();
        for (Doctor doctor : InitiateData.map.values()) {
            if (enterDoctor.equals(doctor.name)) {
                if (patient == null) {
                    System.out.println("You haven't create patient info, please create first\n" + "================================================");
                    createPatientInfo();
                } else {
                doctor.makeAppointment(new Appointment(patient, Illness.valueOf(illness), new Date()));
                break;
                }
            }
            else {
                System.out.println("Please enter a valid doctor name\n"+ "================================================");
                System.out.println("Please enter your id:");
                int id = scanner.nextInt();
                Menu.makeAppointment(PatientRecords.records.get(id));
            }
        }
        backToMainPage();
    }

    public static void cancelAppointment() {
        System.out.println("Please enter your id: ");
        boolean validIdEntered = false;
        while(!validIdEntered) {
            try {
                int id = scanner.nextInt();
                validIdEntered = true;
                boolean allDoctorsNoAppointment = true;
                for (Doctor doctor : InitiateData.map.values()) {
                    for (int i = 0; i < doctor.getAppointments().size(); i++) {
                        List<Appointment> appointments = doctor.getAppointments();
                        if (appointments.size() > 0) {
                            allDoctorsNoAppointment = false;
                            if (appointments.get(i).getPatient().getId() == id) {
                                System.out.println("Appointment : " + appointments.get(i) + " has been canceled.");
                                appointments.remove(i);
                            }
                        }
                    }

                }
                if (allDoctorsNoAppointment) {
                    System.out.println("All Doctors Do Not Have Appointment");
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Error...Please enter a number.");



            }
        }

        backToMainPage();
    }

//    public static void enterDoctorMenu() {
//        System.out.println("- Show Appointment: Please enter 1\n"
//                + "- End panel: Please enter any other number\n"
//                + "- Enter: ");
//        Integer option;
//        boolean validOptionEntered = false;
//        while (!validOptionEntered) {
//            try {
//                option = scanner.nextInt();
//                switch (option) {
//                    case 1:
//                        Menu.showAppointment();
//                        break;
//                    default:
//                        System.out.println("Thanks for using booking appointment system");
//                        System.exit(0);
//                }
//            } catch (InputMismatchException e) {
//            scanner.next();
//            System.out.println("Please enter a number.");
//
//        }
//
//        }
//    }

    public static void showAppointment(){
//        System.out.println(InitiateData.map.values());//each doctor
        for (Doctor doctor : InitiateData.map.values()) {
            List<Appointment> appointments = doctor.getAppointments();
            if (appointments.size() > 0) {
                for (Appointment appointment : appointments) {
                    System.out.println(doctor.name + ": " + appointment);
                }
            }
        }
        backToMainPage();
    }

    private static void backToMainPage() {
        System.out.println("enter any key to go back to main page...");
        scanner.next();
        showMainPage();

    }
}
