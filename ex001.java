import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ex001 {
  static Logger logger;

  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

    // получние json строки
    String file1 = "JsonData.json";
    String s1 = GetJsonData(file1);

    // запись фразы json в файл
    String file2 = "JsonPhraseToFile.txt";
    WriteJsonPhraseToFile(s1, file2);

    String file3 = "LoggerJsonPhraseToFile.txt";
    WriteToLoggerFile(s1, file3);

    String file4 = "SortArr.txt";
    SortArr(file4);

  }

  static String GetJsonData(String file) throws FileNotFoundException, IOException, ParseException {

    Object o = new JSONParser().parse(new FileReader(file));

    JSONObject j = (JSONObject) o;

    String Surname = (String) j.get("фамилия");
    String Mark = (String) j.get("оценка");
    String Subject = (String) j.get("предмет");

    // System.out.println("фамилия :" + Surname);
    // System.out.println("оценка :" + Mark);
    // System.out.println("предмет: " + Subject);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Студент " + Surname);
    stringBuilder.append(" получил " + Mark);
    stringBuilder.append(" по предмету " + Subject + " ");

    return stringBuilder.toString();

  }

  static void WriteJsonPhraseToFile(String s, String file) {

    try (FileWriter writer = new FileWriter(file, false)) {
      writer.write(s);
      writer.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  static void WriteToLoggerFile(String s, String file) {

    try {
      Logger logger = Logger.getAnonymousLogger();
      FileHandler fileHandler = new FileHandler("Logger.txt", true);
      logger.addHandler(fileHandler);
      try (FileWriter writer = new FileWriter(file, false)) {
        writer.write(s);
        writer.flush();
        logger.log(Level.INFO, "запись успешно создана");
      } catch (Exception e) {
        e.printStackTrace();
        logger.log(Level.WARNING, e.getMessage());
      }
      fileHandler.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  static void SortArr(String file) {

    int[] array = new int[] { 2, 3, 22, 1, 5, 9, 6, 1, 7 };

    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println("");

    boolean isSorted = false;
    while (!isSorted) {
      isSorted = true;
      for (int i = 1; i < array.length; i++) {
        if (array[i] < array[i - 1]) {
          int temp = array[i];
          array[i] = array[i - 1];
          array[i - 1] = temp;
          isSorted = false;
        }
      }
    }

    String s = "";

    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
      s += array[i] + " ";
    }

    try {
      Logger logger = Logger.getAnonymousLogger();
      FileHandler fileHandler = new FileHandler("Logger2.txt", true);
      logger.addHandler(fileHandler);
      logger.log(Level.INFO, "массив отсортирован", s);
      fileHandler.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}