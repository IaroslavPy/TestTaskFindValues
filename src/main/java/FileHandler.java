import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileHandler {
    public static void main(String[] args) {
        long m = System.currentTimeMillis();
        String filePath = "W:/10m.txt";
        try {
            processFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((double) (System.currentTimeMillis() - m) + " time (millisec) with using ArrayList");
    }

    private static void processFile(String filePath) throws IOException {

        int bufferSize = 4096;
        int maxValue;
        int minValue;
        double medianValue;
        double meanValue = 0;
        List<Integer> allValues = new ArrayList<>();
        List<Integer> currentIncreaseSequence = new ArrayList<>();
        List<Integer> currentDecreaseSequence = new ArrayList<>();
        List<Integer> longestIncreaseSequence = new ArrayList<>();
        List<Integer> longestDecreaseSequence = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath), bufferSize)) {
            String stringLine;
            while ((stringLine = reader.readLine()) != null) {
                String[] values = stringLine.split("\\s+");
                for (String value : values) {
                    int currentNumber = Integer.parseInt(value);

                    if (currentIncreaseSequence.isEmpty() ||
                            currentNumber > currentIncreaseSequence.get(currentIncreaseSequence.size() - 1)) {
                        currentIncreaseSequence.add(currentNumber);
                    } else {
                        if (currentIncreaseSequence.size() > longestIncreaseSequence.size()) {
                            longestIncreaseSequence = new ArrayList<>(currentIncreaseSequence);
                        }
                        currentIncreaseSequence.clear();
                        currentIncreaseSequence.add(currentNumber);
                    }

                    if (currentDecreaseSequence.isEmpty() ||
                            currentNumber < currentDecreaseSequence.get(currentDecreaseSequence.size() - 1)) {
                        currentDecreaseSequence.add(currentNumber);
                    } else {
                        if (currentDecreaseSequence.size() > longestDecreaseSequence.size()) {
                            longestDecreaseSequence = new ArrayList<>(currentDecreaseSequence);
                        }
                        currentDecreaseSequence.clear();
                        currentDecreaseSequence.add(currentNumber);
                    }

                    meanValue = meanValue + currentNumber;

                    allValues.add(currentNumber);
                }
            }
        }

        if (currentIncreaseSequence.size() > longestIncreaseSequence.size()) {
            longestIncreaseSequence = new ArrayList<>(currentIncreaseSequence);
        }

        if (currentDecreaseSequence.size() > longestDecreaseSequence.size()) {
            longestDecreaseSequence = new ArrayList<>(currentDecreaseSequence);
        }

        meanValue = meanValue / allValues.size();

        Collections.sort(allValues);

        minValue = allValues.get(0);
        maxValue = allValues.get(allValues.size() - 1);

        if (allValues.size() % 2 == 0) {
            medianValue = 0.5 * (allValues.get(allValues.size() / 2 - 1) + allValues.get(allValues.size() / 2));
        } else {
            medianValue = allValues.get(allValues.size() / 2);
        }

        System.out.println("Maximum Value: " + maxValue);
        System.out.println("Minimum Value: " + minValue);
        System.out.println("Median Value: " + medianValue);
        System.out.println("Average value: " + meanValue);
        System.out.println("The longest Increasing Sequence (Max): " + longestIncreaseSequence);
        System.out.println("The longest Decreasing Sequence (Min): " + longestDecreaseSequence);
    }
}
