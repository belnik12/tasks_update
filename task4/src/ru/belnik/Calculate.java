package ru.belnik;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculate {
    private  BufferedReader readerConsole = new BufferedReader(new InputStreamReader(System.in));
    private BufferedReader readerFile;
    private ArrayList<String> listOfTime = new ArrayList<>();
    private LocalTime[] arrival;
    private LocalTime[] exit;

    public void calculate() {
        System.out.println("Путь к файлу: ");
        File file = readFile();
        initializeArrayList(file, listOfTime);
        convertToArrays(listOfTime);
        calculateMaxTimeInterval(arrival, exit);
    }

    private File readFile() {
        File file = null;
        try {
            file = new File(readerConsole.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private  void initializeArrayList(File file, ArrayList<String> listOfTime) {

        try {
            readerFile = new BufferedReader(new FileReader(file));
            while (readerFile.ready()) {
                String str = readerFile.readLine();
                if(!str.equals(null)) {
                    listOfTime.add(str.replace("\\n", ""));
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        for (String str : listOfTime) {
            System.out.println(str);
        }
    }

    private void convertToArrays(ArrayList<String> listOfTime) {
        arrival = new LocalTime[listOfTime.size()];
        exit = new LocalTime[listOfTime.size()];
        for (int i = 0; i < arrival.length; i++) {
            String str = listOfTime.get(i);
            arrival[i] = LocalTime.parse(str.substring(0, str.indexOf(" ")),
                    DateTimeFormatter.ofPattern("H:m"));
            exit[i] = LocalTime.parse(str.substring(str.indexOf(" ") + 1),
                    DateTimeFormatter.ofPattern("H:m"));
        }

        Arrays.sort(arrival);
        Arrays.sort(exit);
        System.out.println(Arrays.toString(arrival));
        System.out.println(Arrays.toString(exit));
    }

    private void calculateMaxTimeInterval(LocalTime[] arrival, LocalTime[] exit) {
        int in = 1;
        int max = 1;
        LocalTime timeArrival = arrival[0];
        LocalTime timeExit = exit[exit.length - 1];
        int minusExit = 1;
        int i = 1;
        int j = 0;
        int numbersOfInterval = arrival.length;

        while (i < numbersOfInterval && j < numbersOfInterval) {
//            int compare = arrival[i].compareTo(exit[j]);
//            if (compare == -1 || compare == 0) {   // if (arrival[i] <= exit[j])
            if(arrival[i].isBefore(exit[j])) {
                in++;
                if(in > max) {
                    max = in;
                    timeArrival = arrival[i];
                    timeExit = exit[exit.length - 1 - minusExit];
                    minusExit++;
                } else if (in < max) {
                    timeExit = arrival[i];
                }
                i++;
            } else {
                in--;
                j++;
            }
        }
        System.out.println(timeArrival + " " + timeExit);
    }

}
