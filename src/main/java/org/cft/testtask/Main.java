package org.cft.testtask;

import java.util.Scanner;
import util.io.FileWriterService;

public class Main {

    public static void main(String[] args) {

        int userChoice;
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.printf("""
                    Введите 1 для создания текстового файла
                    Введите 2 для выхода из программы
                    """);

            userChoice = input.nextInt();

            if (userChoice == 2) break;

            switch(userChoice) {
                case 1:
                    FileWriterService.createFile("in1.txt");
            }
        }
    }
}
