import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FourValuesFileHandler {
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());
    private static int maxValue = Integer.MIN_VALUE;
    private static int minValue = Integer.MAX_VALUE;
    private static long totalValue = 0;
    private static int counter = 0;

    public static void main(String[] args) throws FileNotFoundException {

        long startTime = System.currentTimeMillis();
        FourValuesFileHandler finder = new FourValuesFileHandler();
        Scanner scanner = new Scanner(new File(Constants.ABSOLUTE_FILE_PATH +
                Constants.FILE_NAME));

        while (scanner.hasNextInt()) {
            int currentNumber = scanner.nextInt();
            finder.addNumber(currentNumber);

            if (currentNumber > maxValue) {
                maxValue = currentNumber;
            }

            if (currentNumber < minValue) {
                minValue = currentNumber;
            }

            totalValue = totalValue + currentNumber;
            counter = counter + 1;
        }
        scanner.close();

        System.out.println("Maximum value: " + maxValue);
        System.out.println("Minimum value: " + minValue);
        System.out.println("Median value: " + finder.findMedian());
        System.out.println("Average value: " + 1.0 * totalValue / counter);

        System.out.println((double) (System.currentTimeMillis() - startTime) +
                "  - time (milliseconds) with using PriorityQueue");
    }

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
}
