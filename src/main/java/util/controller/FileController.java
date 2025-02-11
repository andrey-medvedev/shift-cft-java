// Координирует работу классов, работающих с файлами

package util.controller;

import util.io.FileReaderService;
import util.io.FileWriterService;
import java.util.Scanner;

public class FileController {

    private final FileWriterService fileWriterService;
    private final FileReaderService fileReaderService;
    private final Scanner scanner;

    public FileController(String path) {
        this.fileWriterService = new FileWriterService(path);
        this.fileReaderService = new FileReaderService(path);
        this.scanner = new Scanner(System.in);
    }

    public void processingOfUserInputData() {

        int userChoice;

        while (true) {
            System.out.print("""
                    Введите 1 для вывода текущих файлов
                    Введите 2 для создания текстового файла
                    Введите 3 для записи в текстовый файл
                    Введите 4 для вывода данных из файла
                    Введите 5 для выхода из программы
                    """);

            userChoice = scanner.nextInt();

            if (userChoice == 5) break;

            switch(userChoice) {
                case 1:
                    returnCurrentFiles();
                    break;
                case 2:
                    createFile();
                    break;
                case 3:
                    writeDataInFile();
                    break;
                case 4:
                    readDataFromFile();
                    break;
                default:
                    System.out.println("Неккоректный ввод");
            }
        }
        scanner.close();
    }

    private void createFile() {
        System.out.println("Введите имя создаваемого файла:");
        scanner.nextLine();
        String fileName = scanner.nextLine();
        fileWriterService.createFile(fileName);
    }

    private void writeDataInFile() {
        System.out.println("Введите имя создаваемого файла:");
        scanner.nextLine();
        String fileName = scanner.nextLine();

        System.out.println("Введите данные:");
        String data = scanner.nextLine();
        fileWriterService.writeData(fileName, data);
    }

    private void returnCurrentFiles() {
        fileReaderService.returnExistingFile();
    }

    private void readDataFromFile() {
        System.out.println("Введите имя файла, который хотите прочитать:");
        scanner.nextLine();
        String fileName = scanner.nextLine();
        fileReaderService.readFile(fileName);
    }
}
