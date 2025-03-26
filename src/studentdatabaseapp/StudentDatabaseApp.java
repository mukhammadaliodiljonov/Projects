package studentdatabaseapp;

import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String[] args) {

        // Ask how manystudents we want to add
        System.out.println("Enter number of new students to enroll: ");
        Scanner sc = new Scanner(System.in);
        int numofstudents = sc.nextInt();
        Student [] students = new Student[numofstudents];



        //Create n number of new Students
        for (int i = 0; i < numofstudents; i++) {
            students[i] = new Student();
            students[i].enrollStudent();
            students[i].payTuition();
            System.out.println(students[i].toString());
        }
    }
}

