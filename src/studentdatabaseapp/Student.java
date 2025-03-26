package studentdatabaseapp;

import java.util.*;

public class Student {

    private String firstName;
    private String lastName;
    private int gradeYear;
    private String studentId;
    private String courses = "";
    private int tuitionBalance =0;
    private static int costOfCourse=600;
    private static int id = 1000;
    //Constructor prompts user to enter name and year
    Student(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first name: ");
        this.firstName = sc.nextLine();

        System.out.print("Enter the last name: ");
        this.lastName = sc.nextLine();

        System.out.print("1 - Freshmen\n2 - Sophmore\n3 - Junior\n4 - Senior\nEnter student class level :");
        this.gradeYear = sc.nextInt();
        setStudentId();

    }

    // Generate an ID
    private void setStudentId(){
        //grade level +ID
        this.studentId = gradeYear+""+id;
        id++;
    }
    //Enroll in courses
    public void enrollStudent(){
        //get inside a loop , user hits 0
        do {
            System.out.print("Enter course to Enroll (Q or q to quit ): ");
            Scanner sc = new Scanner(System.in);
            String course = sc.nextLine();

            if (!course.equalsIgnoreCase("Q") || !course.equalsIgnoreCase("q")) {
                courses = courses + "\n  " + course;
                tuitionBalance = tuitionBalance + costOfCourse;
            }
            else{
                System.out.println("BREAK");
                break;
            }

        }while (true);


    }

    //View balance
    public void viewBalance(){
        System.out.println("Your balance : "+tuitionBalance);
    }

    //Pay tuition

    public void payTuition(){
        viewBalance();
        System.out.print("Enter your payment: ");
        Scanner sc = new Scanner(System.in);
        int payment = sc.nextInt();
        tuitionBalance = tuitionBalance - payment;
        System.out.println("Thank you for your payment of "+payment);
        viewBalance();
    }

    //Show status
    public String toString(){
        return firstName+" "+lastName+
                "\nGrade Level: "+gradeYear+
                "\nStudent ID: "+studentId+
                "\nCourses Enrolled:"+courses+
                "\nTuition Balance:"+tuitionBalance;
    }


}

