/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.PrintWriter;

/**
 *
 * @author hallgeir
 */
public class Student {
    private int snr; 
    private String firstName;
    private String lastName;
    
    public Student(int snr, String firstName, String lastName)
    {
        this.snr = snr;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public void print(PrintWriter out)
    {
        out.println("Skriver ut fra StudentKlassen <br> ");
        out.println("Snr er " +snr +"<br>");
        out.println("FirstName " +firstName +"<br>");
        out.println("LastName " +lastName +"<br>");
    }
    
    
    public int getSnr()
    {
        return snr;
    }
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }
    
}
