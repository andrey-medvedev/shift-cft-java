package org.cft.testtask;

import util.controller.FileController;

public class Main {

    public static void main(String[] args) {
        FileController controller = new FileController("src/main/resources/");
        controller.processingOfUserInputData();
    }
}
