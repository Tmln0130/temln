package lab7;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkistra {
    public static void main(String[] args) {
        // 1. ArrayList үүсгэх
        ArrayList<Integer> myList = createList();
        System.out.println("Created list: " + myList);

        // 2. Элементүүдийн нийлбэр 
        int sum = calculateSum(myList);
        System.out.println("Элементүүдийн нийлбэр: " + sum);

        // 3. Элементүүдийг эрэмбэлэх
        ArrayList<Integer> reversedList = sortDescList(myList);
        System.out.println("Элементүүдийг эрэмбэлэх: " + reversedList);

        // 4. Макс утга
        int maxElement = findMaxElement(myList);
        System.out.println("Макс утга: " + maxElement);

        // 5. Элементүүдийн квадрат
        ArrayList<Integer> squaredList = squareElements(myList);
        System.out.println("Элементүүдийг квадрат: " + squaredList);

        // 6. Сондгой тоо
        ArrayList<Integer> evenNumbers = filterEvenNumbers(myList);
        System.out.println("Сондгой тоо: " + evenNumbers);
    }

    // 1. ArrayList үүсгэх
    public static ArrayList<Integer> createList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(8);
        list.add(5);
        return list;
    }

    // 2. Элементүүдийн нийлбэр олгох
    public static int calculateSum(ArrayList<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    // 3. Элементүүдийг эрэмбэлэх
    public static ArrayList<Integer> sortDescList(ArrayList<Integer> list) {
        ArrayList<Integer> sortedDesc = new ArrayList<>(list);
        Collections.sort(sortedDesc, Collections.reverseOrder());
        return sortedDesc;
    }

    // 4. Макс утга
    public static int findMaxElement(ArrayList<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // 5. Элементүүдийг квадрат
    public static ArrayList<Integer> squareElements(ArrayList<Integer> list) {
        ArrayList<Integer> squared = new ArrayList<>();
        for (int num : list) {
            squared.add(num * num);
        }
        return squared;
    }

    // 6. Сондгой тоо
    public static ArrayList<Integer> filterEvenNumbers(ArrayList<Integer> list) {
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        for (int num : list) {
            if (num % 2 == 1) {
                evenNumbers.add(num);
            }
        }
        return evenNumbers;
    }//4:34
}
