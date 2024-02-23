import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindMedianFromFile {
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    private static int maxValue = Integer.MIN_VALUE;
    private static int minValue = Integer.MAX_VALUE;
    private static double meanValue = 0;
    private static int counter = 0;

    public void addNumber(int number) {
        if (maxHeap.isEmpty() || number < maxHeap.peek()) {
            maxHeap.add(number);
        } else {
            minHeap.add(number);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return 0.5 * (maxHeap.peek() + minHeap.peek());
        } else {
            return (double)maxHeap.peek();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long m = System.currentTimeMillis();
        FindMedianFromFile finder = new FindMedianFromFile();
        Scanner scanner = new Scanner(new File("W:/10m.txt"));
        while (scanner.hasNextInt()) {
            int currentNumber = scanner.nextInt();
            finder.addNumber(currentNumber);

            if (currentNumber > maxValue) {
                maxValue = currentNumber;
            }

            if (currentNumber < minValue) {
                minValue = currentNumber;
            }

            meanValue = meanValue + currentNumber;
            counter = counter + 1;
        }
        scanner.close();

        System.out.println("Maximum Value: " + maxValue);
        System.out.println("Minimum Value: " + minValue);
        System.out.println("The median is: " + finder.findMedian());
        System.out.println("Average value: " + meanValue / counter);

        System.out.println("minHeap.size() is: " + minHeap.size());
        System.out.println("maxHeap.size() is: " + maxHeap.size());


        System.out.println((double) (System.currentTimeMillis() - m) +  "  - time (millisec) with using PriorityQueue");

    }
}
