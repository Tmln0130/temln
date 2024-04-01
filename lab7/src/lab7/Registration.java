import java.util.Scanner;

public class Student {
    private String name;
    private String regNo;
    private int feesPaid;
    private int balance;
    private int[] marks = new int[5];
    private char[] grades = new char[5];
    private int totalMarks;
    private char totalGrade;

    public void setStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student name:");
        this.name = scanner.nextLine();
        System.out.println("Enter registration number:");
        this.regNo = scanner.nextLine();
    }

    public void setFees(int fullFees) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount of fees paid:");
        this.feesPaid = scanner.nextInt();
        this.balance = fullFees - this.feesPaid;
        if (this.balance == 0) {
            System.out.println("You have paid your school fees in full.");
        } else if (this.balance > 0) {
            System.out.println("You have a balance of: " + this.balance);
        } else {
            System.out.println("You have completed your fees payment and you have an overpayment of: " + this.balance);
        }
    }

    public void inputMarks() {
        Scanner scanner = new Scanner(System.in);
        String[] subjects = {"Maths", "English", "Kiswahili", "Science", "S/S and CRE"};
        for (int i = 0; i < subjects.length; i++) {
            boolean validInput = false;
            do {
                System.out.println("Enter marks for " + subjects[i] + ":");
                int mark = scanner.nextInt();
                if (mark >= 0 && mark <= 100) {
                    this.marks[i] = mark;
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Marks should be between 0 and 100.");
                }
            } while (!validInput);
        }
    }

    public void calculateGrades() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] >= 80) {
                grades[i] = 'A';
            } else if (marks[i] >= 60) {
                grades[i] = 'B';
            } else if (marks[i] >= 50) {
                grades[i] = 'C';
            } else if (marks[i] >= 40) {
                grades[i] = 'D';
            } else {
                grades[i] = 'E';
            }
        }
        totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        if (totalMarks >= 400) {
            totalGrade = 'A';
        } else if (totalMarks >= 300) {
            totalGrade = 'B';
        } else if (totalMarks >= 200) {
            totalGrade = 'C';
        } else if (totalMarks >= 100) {
            totalGrade = 'D';
        } else {
            totalGrade = 'E';
        }
    }

    public void displayStudentDetails() {
        System.out.println("Name: " + name);
        System.out.println("Reg. No: " + regNo);
        System.out.println("Fees paid: " + feesPaid);
        System.out.println("Fee balance: " + balance);
        System.out.println("KCPE Marks:");
        String[] subjects = {"Maths", "English", "Kiswahili", "Science", "S/S and CRE"};
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i] + " Grade: " + grades[i]);
        }
        System.out.println("Total Marks: " + totalMarks + " Grade: " + totalGrade);
    }

    public static void main(String[] args) {
        Student student = new Student();
        int fullFees = 26000;
        student.setStudentDetails();
        student.setFees(fullFees);
        student.inputMarks();
        student.calculateGrades();
        student.displayStudentDetails();
    }
}
