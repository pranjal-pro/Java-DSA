import java.util.*;

public class ObjectOrientedProgramming {
    public Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println(100 / 0);
        } catch (ArithmeticException e) {
            System.out.println(e);
        } finally {
            System.out.println("Hi");
        }
        Pen pen = new Pen();
        pen.brand = "cello";
        pen.setColor("blue");
        pen.getColor();
    }
}

class Pen {
    String color;
    int tip = 11;
    String brand;

    void getColor() {
        System.out.println(this.color);
    }

    void setColor(String color) {
        this.color = color;
    }
}

class Student {
    StringBuilder name;
    StringBuilder rollNumber;
    float cgpa;
    int marks[] = new int[5];

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public void setName(StringBuilder name) {
        this.name = name;
    }

    public void setRollNumber(StringBuilder rollNumber) {
        this.rollNumber = rollNumber;
    }

    public float getCgpa() {
        return cgpa;
    }

    public int[] getMarks() {
        return marks;
    }

    public StringBuilder getName() {
        return name;
    }

    public StringBuilder getRollNumber() {
        return rollNumber;
    }

    Student() {
        System.out.println("Constructot is called");
    }

    Student(Student from) {
        this.cgpa = from.cgpa;
        this.name = from.name;
        this.rollNumber = from.rollNumber;
        for (int i = 0; i < from.marks.length; i++)
            this.marks[i] = from.marks[i];
    }
}

class BankAccount {
    Scanner sc = new Scanner(System.in);
    String userName;
    private int accountNumber;
    private int password;

    BankAccount(String name) {
        this.userName = name;
        Random random = new Random();
        this.accountNumber = random.nextInt(9999);
        System.out.println("Set a Password");
        this.password = sc.nextInt();
        System.out.println("Account Opened Sucessfully");
        passbook();
        System.out.println("Your Password is :" + password);
    }

    BankAccount() {
        System.out.print("Enter your name: ");
        this.userName = sc.next();
        Random random = new Random();
        this.accountNumber = random.nextInt(9999);
        System.out.println("Set a Password");
        this.password = sc.nextInt();
        System.out.println("Account Opened Sucessfully");
        passbook();
        System.out.print("Your Password is :" + password);
    }

    void setPassword() {
        System.out.print("Enter Username: ");
        String checkName = sc.next();
        if (this.userName.equalsIgnoreCase(checkName) == false) {
            System.out.println("Wrong Name");
            return;
        }
        System.out.print("Enter Account Number: ");
        int checkAccountNumber = sc.nextInt();
        if (this.accountNumber != checkAccountNumber) {
            System.out.println("Wrong AccountNumber");
            return;
        }
        System.out.print("Enter New Password: ");
        password = sc.nextInt();
        System.out.println("Password Updated Sucessfully");
    }

    void passbook() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Account Holder Name: " + this.userName);
    }
}

interface Interface1 {
    final public static String color1 = "red";
}

interface Interface2 {
    final public static String color2 = "red";
}

class MultipleInheritance implements Interface1, Interface2 {
}

abstract class AbstractClass implements Interface1 {
    void function() {
    }

    abstract void abstractFunction();

    AbstractClass() {
    }
}

class Derived extends AbstractClass {
    void abstractFunction() {
        System.out.println(color1);
    }
}