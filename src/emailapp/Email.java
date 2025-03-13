package emailapp;

import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int defaultPasswordLength = 10;
    private int mailboxCapacity = 500;
    private String alternateEmail;
    public String companySuffix = "aeycompany.com";

    //Constructor to recieve the first name and last name
    public Email(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

        // Call a method asking for the department - return the department
        this.department = setDepartment();

        //Call a method that returns a random password
        this.password = randomPassword(defaultPasswordLength);
        System.out.println("Your Password: " + this.password);

        //Combine elements to generate the email
        email = this.firstName.toLowerCase()+"."+this.lastName.toLowerCase()+"@"+department+"."+companySuffix;
    }

    // Ask for Department
    private String setDepartment(){
        System.out.println("New worker: "+firstName+". Department Codes:\n1 for Sales\n2 for Development \n3 for Accounting \n0 for none\nEnter Department Code: ") ;
        Scanner in = new Scanner(System.in);
        int depChoice = in.nextInt();
        if(depChoice == 1){
            return "Sales";
        }
        else if(depChoice == 2){
            return "Development";
        }
        else if(depChoice == 3){
            return "Accounting";
        }
        else{
            return "none";
        }
    }

    // Genearate a random password
    private String randomPassword(int length){
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
        char [] password = new char[length];
        for(int i = 0; i < length; i++){
            int rand = (int) (Math.random()*passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }
    // Set the mailbox capacity
    public void setMailboxCapacity(int capacity){
        this.mailboxCapacity = capacity;
    }

    // Set the alternate email
    public void setAlternateEmail(String alternateEmail){
        this.alternateEmail = alternateEmail;
    }

    // change the password
    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    public int getMailboxCapacity(){
        return mailboxCapacity;
    }

    public String getAlternateEmail(){
        return alternateEmail;
    }

    public String getPassword(){
        return password;
    }
    public String showInfo(){
        return "Display Name: "+firstName + " " + lastName+
                "\nCompany Email: "+ email+
                "\nMailbox Capacity: "+ mailboxCapacity +"mb";
    }
}
