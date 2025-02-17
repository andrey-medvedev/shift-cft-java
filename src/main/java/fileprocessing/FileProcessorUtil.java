// Чтение входных файлов
// Фильтрация данных по типам
// Сбор статистики
// Запись в выходные файлы

package fileprocessing;

import arguments.ArgumentsParser;
import datafilter.DataFilter;
import statisticscollection.*;
import java.util.List;
import java.util.ArrayList;

public class FileProcessorUtil {

    private List<String> inputFiles;
    private String outputPath;
    private String prefix;
    private boolean appendMode;
    private boolean shortStatistics;
    private boolean fullStatistics;

    public FileProcessorUtil(ArgumentsParser arguments) {
        this.inputFiles = arguments.getInputFiles();
        this.outputPath = arguments.getOutputPath();
        this.prefix = arguments.getPrefix();
        this.appendMode = arguments.isAppendMode();
        this.shortStatistics = arguments.isShortStatistics();
        this.fullStatistics = arguments.isFullStatistics();
    }

    public void processFiles() {

        List<String> dataFromTxt;
        List<String> allDAta = new ArrayList<>();

        // чтение файлов
        for(String inputFile : inputFiles) {
            dataFromTxt = FileService.readFile(inputFile);
            for(String data : dataFromTxt) {
                allDAta.add(data);
            }
        }

        // Фильтрация данных по типам
        DataFilter dataFilter = new DataFilter();
        dataFilter.filterValues(allDAta);

        // Запись в выходные файлы
        if(!dataFilter.getIntList().isEmpty()) {
            FileService.writeData(outputPath,prefix + "integers.txt", dataFilter.getIntList(), appendMode);
        }
        if (!dataFilter.getFloatList().isEmpty()) {
            FileService.writeData(outputPath, prefix + "floats.txt", dataFilter.getFloatList(), appendMode);
        }
        if (!dataFilter.getStrList().isEmpty()) {
            FileService.writeData(outputPath, prefix + "strings.txt", dataFilter.getStrList(), appendMode);
        }

        // Сбор и вывод статистики
        StatisticsCollectors statisticsCollectors = new StatisticsCollectors(
                dataFilter.getIntList().size(),
                dataFilter.getFloatList().size(),
                dataFilter.getStrList().size(),
                dataFilter.getIntList(),
                dataFilter.getFloatList(),
                dataFilter.getStrList()
        );
        if(shortStatistics) {
            statisticsCollectors.printShortStatistics();
        } else if (fullStatistics) {
            statisticsCollectors.printFullStatistics();
        }
    }
}