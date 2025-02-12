package org.cft.testtask;

import arguments.ArgumentsParser;
import com.beust.jcommander.JCommander;
import fileprocessing.FileProcessorUtil;

public class Main {

    public static void main(String[] args) {

         ArgumentsParser argumentsParser = new ArgumentsParser();
         try {
             JCommander
                     .newBuilder()
                     .addObject(argumentsParser)
                     .build()
                     .parse(args);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }

         FileProcessorUtil fileProcessorUtil = new FileProcessorUtil(argumentsParser,
                 "src/main/resources/");
         fileProcessorUtil.processFiles();

    }
}
