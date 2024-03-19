package lab7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Student> studentQueue = new Queue<>();

        while (true) {
            System.out.println("\nХийх үйлдэлээ сонгоно уу:");
            System.out.println("1. Нэмэх");
            System.out.println("2. Хасах");
            System.out.println("3. Эхэнд");
            System.out.println("4. Хэмжээ");
            System.out.println("5. Харуулах");
            System.out.println("6. Цэвэрлэх");
            System.out.println("7. Гарах");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Оюутны нэрээ оруулна уу:");
                    scanner.nextLine();
                    String studentName = scanner.nextLine();
                    System.out.println("Оюутны кодоо оруулна уу:");
                    String studentCode = scanner.nextLine();
                    Student student = new Student(studentName, studentCode);
                    studentQueue.enqueue(student);
                    System.out.println(studentName + " амжилттай нэмэгдлээ.");
                    break;
                case 2:
                    Student dequeuedStudent = studentQueue.dequeue();
                    if (dequeuedStudent != null)
                        System.out.println("Хасагдсан оюутан: " + dequeuedStudent.getStudentName());
                    else
                        System.out.println("Дараалал хоосон байна.");
                    break;
                case 3:
                    Student frontStudent = studentQueue.front();
                    if (frontStudent != null)
                        System.out.println("Эхний оюутан: " + frontStudent.getStudentName());
                    else
                        System.out.println("Дараалал хоосон байна.");
                    break;
                case 4:
                    System.out.println("Дараалалын хэмжээ: " + studentQueue.size());
                    break;
                case 5:
                    studentQueue.display();
                    break;
                case 6:
                    studentQueue.clear();
                    System.out.println("Дараалал цэвэрлэгдлээ.");
                    break;
                case 7:
                    System.out.println("Гарч байна...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Буруу сонголт.");
            }
        }
    }
}
// hi 
