package lab7;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Bin {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ачааны тоо: ");
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Алдаа: Ачааны тоо 0-с их байх ёстой.");
                return;
            }

            int[] items = new int[n];

            System.out.println("Ачааны утгуудыг оруулна уу:");
            for (int i = 0; i < n; i++) {
                System.out.print("Ачаа " + (i + 1) + ":");
                items[i] = scanner.nextInt();         
            }

            System.out.print("Машины даац: ");
            int binCapacity = scanner.nextInt();

            if (binCapacity <= 0) {
                System.out.println("Алдаа: Машины даац 0-с их байх ёстой.");
                return;
            }
            List<List<Integer>> bins = firstFit(items, binCapacity);
            List<List<Integer>> binsFirstFit = nextFit(items, binCapacity);
            
         // Бүх ачааны даацыг шалгах
            for (List<Integer> bin : bins) {
                int binCapacitySum = bin.stream().mapToInt(Integer::intValue).sum();
                if (binCapacitySum > binCapacity) {
                    System.out.println("Алдаа: Машины даац хэтэрсэн.");
                    return;
                }
            }

            System.out.println("Эхлэн дүүргэлт:");
            printSolution(bins);
            
            System.out.println("Сайн дүүргэлт:");
            printSolution(binsFirstFit);

            scanner.close();
        } catch (InputMismatchException e) {
            System.out.println("Алдаа: Оруулсан мэдээлэл буруу байна. Тоо оруулна уу.");
        }
    }

    
    // Сайн дүүргэлт
    public static List<List<Integer>> nextFit(int[] items, int binCapacity) {
        List<List<Integer>> bins = new ArrayList<>();

        int BinIndex = 0;

        for (int item : items) {
            if (bins.size() <= BinIndex) {
                bins.add(new ArrayList<>());
            }

            List<Integer> currentBin = bins.get(BinIndex);

            if (fitsInBin(item, currentBin, binCapacity)) {
                currentBin.add(item);
            } else {
                BinIndex++;
                bins.add(new ArrayList<>());
                bins.get(BinIndex).add(item);
            }
        }

        return bins;
    }
    // Эхлэн дүүргэлт
        public static List<List<Integer>> firstFit(int[] items, int binCapacity) {
            List<List<Integer>> bins = new ArrayList<>();

            for (int item : items) {
                boolean itemPlaced = false;

                for (List<Integer> bin : bins) {
                    if (fitsInBin(item, bin, binCapacity)) {
                        bin.add(item);
                        itemPlaced = true;
                        break;
                    }
                }

                if (!itemPlaced) {
                    List<Integer> newBin = new ArrayList<>();
                    newBin.add(item);
                    bins.add(newBin);
                }
            }

            return bins;
        }

   // Багтаамж тооцох
    private static boolean fitsInBin(int item, List<Integer> bin, int binCapacity) {
        int binSum = bin.stream().mapToInt(Integer::intValue).sum();
        return binSum + item <= binCapacity;
    }

    // Дүүргэлтийн гаргасан утгыг хэвлэх
    private static void printSolution(List<List<Integer>> bins) {
        int binNumber = 1;
        for (List<Integer> bin : bins) {
            System.out.print("M" + binNumber + ": ");
            for (int item : bin) {
                System.out.print(item + " ");
            }
            System.out.println();
            binNumber++;
        }
        System.out.println("M=" + bins.size() + " ба " + bins.size() + " машин шаардлагатай.");
    }
}
