package fileprocessing;

import arguments.ArgumentsParser;
import datafilter.DataFilter;
import statisticscollection.StatisticsCollectors;
import util.fileservice.FileServiceUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки файлов. Выполняет чтение данных из входных файлов, их фильтрацию,
 * запись отфильтрованных данных в выходные файлы и вывод статистики.
 */
public class FileProcessor {

    private final List<String> inputFiles;
    private final String outputPath;
    private final String prefix;
    private final boolean appendMode;
    private final boolean shortStatistics;
    private final boolean fullStatistics;

    /**
     * Создает экземпляр {@code FileProcessor} на основе переданных аргументов.
     *
     * @param arguments объект {@link ArgumentsParser}, содержащий параметры обработки файлов.
     */
    public FileProcessor(ArgumentsParser arguments) {
        this.inputFiles = arguments.getInputFiles();
        this.outputPath = arguments.getOutputPath();
        this.prefix = arguments.getPrefix();
        this.appendMode = arguments.isAppendMode();
        this.shortStatistics = arguments.isShortStatistics();
        this.fullStatistics = arguments.isFullStatistics();
    }

    /**
     * Выполняет обработку файлов: чтение, фильтрацию, запись и статистику.
     */
    public void processFiles() {
        List<String> allData = new ArrayList<>();

        // Чтение файлов
        for (String inputFile : inputFiles) {
            allData.addAll(FileServiceUtil.readFile(inputFile));
        }

        // Фильтрация данных
        DataFilter dataFilter = new DataFilter();
        dataFilter.filterValues(allData);

        // Запись файлов
        writeFilteredData(dataFilter);

        // Вывод статистики
        printStatistics(dataFilter);
    }

    /**
     * Записывает отфильтрованные данные в соответствующие файлы.
     *
     * @param dataFilter объект {@link DataFilter}, содержащий разделенные данные по типам.
     */
    private void writeFilteredData(DataFilter dataFilter) {
        if (!dataFilter.getIntList().isEmpty()) {
            FileServiceUtil.writeData(outputPath, prefix + "integers.txt", dataFilter.getIntList(), appendMode);
        }
        if (!dataFilter.getFloatList().isEmpty()) {
            FileServiceUtil.writeData(outputPath, prefix + "floats.txt", dataFilter.getFloatList(), appendMode);
        }
        if (!dataFilter.getStrList().isEmpty()) {
            FileServiceUtil.writeData(outputPath, prefix + "strings.txt", dataFilter.getStrList(), appendMode);
        }
    }

    /**
     * Выводит статистику по обработанным данным, если включены соответствующие флаги.
     *
     * @param dataFilter объект {@link DataFilter}, содержащий отфильтрованные данные.
     */
    private void printStatistics(DataFilter dataFilter) {
        if(!shortStatistics && !fullStatistics) return;

        StatisticsCollectors statistics = new StatisticsCollectors(
                dataFilter.getIntList().size(),
                dataFilter.getFloatList().size(),
                dataFilter.getStrList().size(),
                dataFilter.getIntList(),
                dataFilter.getFloatList(),
                dataFilter.getStrList()
        );

        if (shortStatistics) statistics.printShortStatistics();
        if (fullStatistics) statistics.printFullStatistics();
    }
}