package arguments;

import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за парсинг аргументов командной строки.
 */
@Getter
@Setter
public class ArgumentsParser {

    @Parameter(names = "-l", description = "Input files")
    private List<String> inputFiles = new ArrayList<>();

    @Parameter(names = "-o", description = "Output path")
    private String outputPath;

    @Parameter(names = "-p", description = "Output file prefix")
    private String prefix;

    @Parameter(names = "-a", description = "Append mode")
    private boolean appendMode = false;

    @Parameter(names = "-s", description = "Short statistics")
    private boolean shortStatistics = false;

    @Parameter(names = "-f", description = "Full statistics")
    private boolean fullStatistics = false;
}
