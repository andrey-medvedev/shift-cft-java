package org.cft.testtask;

import arguments.ArgumentsParser;
import com.beust.jcommander.JCommander;
import fileprocessing.FileProcessorUtil;
import validator.ArgumentsValidator;

public class Main {

    public static void main(String[] args) {

         ArgumentsParser argumentsParser = new ArgumentsParser();
         try {
             JCommander
                     .newBuilder()
                     .addObject(argumentsParser)
                     .build()
                     .parse(args);
         } catch (Exception ignored) {}

        ArgumentsValidator.validate(argumentsParser);

         FileProcessorUtil fileProcessorUtil = new FileProcessorUtil(argumentsParser);
         fileProcessorUtil.processFiles();

    }
}
