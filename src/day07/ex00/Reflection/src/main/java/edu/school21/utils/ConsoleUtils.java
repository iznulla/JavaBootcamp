package edu.school21.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtils {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static String readL(){
        while (true) {
            try {
                String buf = bufferedReader.readLine();
                if (buf != null)
                    return buf;
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
    }
}
