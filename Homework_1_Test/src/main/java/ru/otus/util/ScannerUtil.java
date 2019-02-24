package ru.otus.util;

import java.util.Scanner;

public class ScannerUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String enterString() {
        return scanner.next();
    }

    public static int enterInt() {
        return scanner.nextInt();
    }

    public static void closeScanner() {
        scanner.close();
    }

}
