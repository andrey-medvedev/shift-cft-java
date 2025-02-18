import arguments.ArgumentsParser;
import com.beust.jcommander.JCommander;
import fileprocessing.FileProcessor;
import util.validator.ArgumentsValidatorUtil;

/**
 * Главный класс программы, который обрабатывает входные аргументы,
 * выполняет их валидацию и запускает обработку файлов.
 */
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
             System.err.println("Ошибка парсинга аргументов: " + e.getMessage());
         }

        if (!ArgumentsValidatorUtil.validate(argumentsParser)) {
            System.err.println("Ошибка валидации аргументов. Программа завершена.");
            return;
        }

         new FileProcessor(argumentsParser).processFiles();
    }
}
