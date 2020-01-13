package dev.aksarok.rpgGame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static float parseFloat(String number) {
        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing string to float.");
            return 0;
        }
    }

    public static int randomInt(int min, int max) {
        try {
            return (int) (Math.random() * ((max - min) + 1)) + min;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
