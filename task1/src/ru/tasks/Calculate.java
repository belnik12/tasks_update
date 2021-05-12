package ru.tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Calculate {
    private static final double PERCENTILE = 90.0;
    private static BufferedReader reader;
    private static BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void initializationAndCalculation() {
        System.out.println("Файл: ");
        File file = null;
        try {
            file = new File(reader1.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialization(file);
        calculateAllMethods();
    }

    private static void initialization(File file) {  //str.replace("\\n", "")
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                String str = reader.readLine();
                int x = Integer.parseInt(str.replace("\\n", ""));
                if( -32768 <= x && x <= 32767) {
                    arrayList.add(x);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(arrayList);

    }

    public static void calculateAllMethods() {
        calculatePercentile(arrayList);
        calculateMedian(listToArray(arrayList));
        calculateMax(listToArray(arrayList));
        calculateMin(listToArray(arrayList));
        calculateArithmeticMean(listToArray(arrayList));
    }

    private static void calculatePercentile (ArrayList<Integer> arrayList) {
        double n = PERCENTILE / 100 * (arrayList.size() - 1) + 1;
        int x = (int) Math.round(n);
        double percentile = arrayList.get(x - 1) + (Math.abs(n-x)) * (arrayList.get(x) - arrayList.get(x-1)) ;

        System.out.println(String.format("%.2f", percentile));
    }

    private static void calculateMedian(double[] value) {

        double[] copyValue = value.clone();
        Arrays.sort(copyValue);

        if (copyValue.length % 2 == 0)
        {
            System.out.println(String.format("%.2f", (copyValue[copyValue.length / 2] + copyValue[copyValue.length / 2 - 1]) / 2f));
        } else {
            System.out.println(String.format("%.2f", copyValue[copyValue.length / 2]));
        }
    }

    private static void calculateMax(double[] value) {
        System.out.println(String.format("%.2f", value[value.length - 1]));
    }


    private static void calculateMin(double[] value) {
        System.out.println(String.format("%.2f", value[0]));
    }

    private static void calculateArithmeticMean(double[] value) {
        double arithmeticMean = 0;
        double sum = 0;
        for (int i = 0; i < value.length; i++) {
            sum += value[i];
        }
        arithmeticMean = sum / value.length;

        System.out.println(String.format("%.2f", arithmeticMean));
    }

    private static double[] listToArray(ArrayList<Integer> arrayList) {
        double[] numbers = new double[arrayList.size()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = arrayList.get(i);
        }
        return numbers;
    }
}
