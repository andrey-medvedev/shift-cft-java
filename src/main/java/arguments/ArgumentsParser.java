// класс для парсинга аргументов

package arguments;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsParser {

    @Parameter(names = "-l", description = "Input files", required = true)
    private List<String> inputFiles = new ArrayList<>();

    @Parameter(names = "-o", description = "Output path", required = false)
    private String outputPath;

    @Parameter(names = "-p", description = "Output file prefix", required = false)
    private String prefix;

    @Parameter(names = "-a", description = "Append mode", required = false)
    private boolean appendMode = false;

    @Parameter(names = "-s", description = "Short statistics")
    private boolean shortStatistics = false;

    @Parameter(names = "-f", description = "Full statistics")
    private boolean fullStatistics = false;

    public List<String> getInputFiles() {
        return this.inputFiles;
    }

    public String getOutputPath() {
        return this.outputPath;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public boolean isAppendMode() {
        return this.appendMode;
    }

    public boolean isShortStatistics() {
        return this.shortStatistics;
    }

    public boolean isFullStatistics() {
        return this.fullStatistics;
    }
}
