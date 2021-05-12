package ru.belnik;

import java.io.*;
import java.util.ArrayList;

public class Calculate {
    private static BufferedReader readerFile;
    private static BufferedReader readerConsole = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<String> polygonList = new ArrayList<>();
    private static ArrayList<String> pointsList = new ArrayList<>();
    private static String[] fileString;
    private static String str;
    private Point[] polygonArray;
    private Point[] pointsArray;
    private  File filePolygon;
    private  File filePoints;


    public void calculate() {
        System.out.println("Файл с координатами фигуры: ");
        filePolygon = readFile();
        initializeArrayList(filePolygon, polygonList);
        polygonArray = convertListToArray(polygonList);
        System.out.println("Файл с координатами точек: ");
        filePoints = readFile();
        initializeArrayList(filePoints, pointsList);
        pointsArray = convertListToArray(pointsList);
        locationPoint(polygonArray, pointsArray);
    }

    private  File readFile() {
        File file = null;
        try {
            file = new File(readerConsole.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private  void initializeArrayList(File file, ArrayList<String> arrayList) {

        try {
            readerFile = new BufferedReader(new FileReader(file));
            while (readerFile.ready()) {
                str = readerFile.readLine();
                if(!str.equals(null)) {
                    arrayList.add(str.replace("\\n", ""));
                }
            }
            } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private Point[] convertListToArray(ArrayList<String> arrayList) {
        int count = 0;
        double x = Double.MIN_VALUE;
        double y = Double.MIN_VALUE;
        Point[] array = new Point[arrayList.size()];
        for (String s : arrayList) {
            fileString = s.split(" ");
            x = Double.parseDouble(fileString[0]);
            y = Double.parseDouble(fileString[1]);
            array[count] = new Point(x, y);
            count++;
        }
        return array;
    }

    private static void locationPoint(Point[] polygon, Point[] points){
        String result = "3 - " + "Точка находится снаружи фигуры";
        int j = polygon.length - 1;
        boolean vertex = false;

        for (Point point : points) {
            for (int i = 0; i < polygon.length; i++) {
                if ((polygon[i].y > point.y) != (polygon[j].y > point.y) &&
                        (point.x < polygon[i].x + Math.abs(polygon[j].x - polygon[i].x) *
                                Math.abs(point.y - polygon[i].y) / Math.abs(polygon[j].y - polygon[i].y))) {
                    result = "2 - " + "Точка находится внутри фигуры";
                } else if(point.x == polygon[i].x && (point.y > polygon[i].x && point.y < polygon[i+1].x)) {
                    result = "1 - " + "Точка находится на стороне фигуры";
                    break;
                } else if(point.x == polygon[i].x && point.y == polygon[i].y) {
                    result = "0 - " + "Точка находится на вершине";
                    break;
                }
                j = i;
            }

            System.out.println(result);

            result = "3 - " + "Точка находится снаружи фигуры";
        }
    }
}

