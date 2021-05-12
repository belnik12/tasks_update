package ru.belnik;

import java.io.*;
import java.util.ArrayList;

public class Calculate {
    private  BufferedReader readerConsole = new BufferedReader(new InputStreamReader(System.in));
    private  BufferedReader readerFile;
    private ArrayList<File> fileArrayList = new ArrayList<>();
    private ArrayList<String> timeList = new ArrayList<>();
    private ArrayList<Double> cash1 = new ArrayList<>();
    private ArrayList<Double> cash2 = new ArrayList<>();
    private ArrayList<Double> cash3 = new ArrayList<>();
    private ArrayList<Double> cash4 = new ArrayList<>();
    private ArrayList<Double> cash5 = new ArrayList<>();

    public void doIt() {
        System.out.println("Путь к папке: ");
        File folder = readFolder();
        pullOutFilesToArrayList(folder);
        initializeArrayList(fileArrayList);
        longerTimeInterval();

    }


    private File readFolder() {
        File folder = null;
        try {
            folder = new File(readerConsole.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folder;
    }

    private void pullOutFilesToArrayList(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                fileArrayList.add(fileEntry);

            }
        }
    }

    private void initializeArrayList(ArrayList<File> fileArrayList) {

        for (int i = 0; i < fileArrayList.size(); i++) {
            if(i == 0) {
                cash1 = returnCashArrayList(fileArrayList.get(i));
            } else if (i == 1) {
                cash2 = returnCashArrayList(fileArrayList.get(i));
            }  else if (i == 2) {
                cash3 = returnCashArrayList(fileArrayList.get(i));
            }  else if (i == 3) {
                cash4 = returnCashArrayList(fileArrayList.get(i));
            }  else if (i == 4) {
                cash5 = returnCashArrayList(fileArrayList.get(i));
            }
        }
    }

    private ArrayList<Double> returnCashArrayList(File file) {
        ArrayList<Double> cash = new ArrayList<>();

        try {
            readerFile = new BufferedReader(new FileReader(file));
            while (readerFile.ready()) {
                String str = readerFile.readLine();
                if (!str.equals(null)) {
                    cash.add(Double.parseDouble(str.replace("\\n", "")));
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return cash;
    }

    private void longerTimeInterval() {
        double sum = 0;
        for (int i = 0; i < cash1.size(); i++) {
            if(sum < cash1.get(i) + cash2.get(i) + cash3.get(i) + cash4.get(i) + cash5.get(i)) {
                sum = i + 1;
            }
        }
        System.out.println((int) sum);
    }
}
