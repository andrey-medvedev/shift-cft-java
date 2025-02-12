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

    private final String PATH;

    public FileProcessorUtil(ArgumentsParser arguments, String path) {
        this.inputFiles = arguments.getInputFiles();
        this.outputPath = arguments.getOutputPath();
        this.prefix = arguments.getPrefix();
        this.appendMode = arguments.isAppendMode();
        this.shortStatistics = arguments.isShortStatistics();
        this.fullStatistics = arguments.isFullStatistics();
        this.PATH = path;
    }

    public void processFiles() {

        String path = outputPath == null ? PATH : outputPath;
        List<String> dataFromTxt = new ArrayList<>();
        List<String> allDAta = new ArrayList<>();

        // чтение файлов
        for(String inputFile : inputFiles) {
            dataFromTxt = FileReaderService.readFile(path, inputFile);
            for(String data : dataFromTxt) {
                allDAta.add(data);
            }
        }

        // Фильтрация данных по типам
        DataFilter dataFilter = new DataFilter();
        dataFilter.filterValues(allDAta);

        // Запись в выходные файлы
        FileWriterService.writeData(path, "result_int.txt", dataFilter.getIntList());
        FileWriterService.writeData(path, "result_float.txt", dataFilter.getFloatList());
        FileWriterService.writeData(path, "result_str.txt", dataFilter.getStrList());

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