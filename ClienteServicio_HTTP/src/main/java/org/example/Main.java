package  org.example;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String url = "";
        String saveDirectory = "";
        String fileType;

        System.out.print("Elija el tipo de fichero a descargar: ");
        fileType = sc.next();

        HttpClient.downloadFilesOfType(url, fileType, saveDirectory);
    }
}


