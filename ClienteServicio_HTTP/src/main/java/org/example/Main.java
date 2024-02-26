package  org.example;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String url = "https://www.webempresa.com/blog/error-403-que-es-y-como-solucionarlo.html";
        String saveDirectory = "/home/danipr/Descargas";
        String fileType;

        System.out.print("Elija el tipo de fichero a descargar: ");
        fileType = sc.next();

        HttpClient.downloadFilesOfType(url, fileType, saveDirectory);
    }
}


