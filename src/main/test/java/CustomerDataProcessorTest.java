import assignment3.CustomerDataProcessor;
import assignment3.exceptionHandling.EmptyContentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class CustomerDataProcessorTest {

  private CustomerDataProcessor result;
  private String inputFile;
  private String testFileExist;

  private String inputFileName;

  private String emptyFileCheck;

  private List<String> csvListResult;

  private Set<Map<String, String>> customerDataset = new HashSet<>();

  String inputline =
      "\"Art\",\"Venere\",\"Chemel, James L Cpa\",\"8 W Cerritos Ave #54\",\"Bridgeport\",\"Gloucester\",\"NJ\",\"08014\",\"856-636-8749\",\"856-264-4130\",\"art@venere.org\",\"http://www.chemeljameslcpa.com\"";


  @BeforeEach
  public void setUp() throws Exception {
    inputFile = "membersTestInput.csv";
    testFileExist = "membersTest.csv";
    emptyFileCheck = "emptyInputTest.csv";
    inputFileName = "customerDataCheck.csv";
    result = new CustomerDataProcessor();
    customerDataset = new HashSet<>();
  }

  @AfterEach
  public void after() throws Exception {
  }

  //proper line with contents check
  @Test
  public void readInputFile() throws Exception {
    csvListResult = result.getCsvFile(inputFile);
    for (int i = 0; i < csvListResult.size(); i++) {
      System.out.println(csvListResult.get(i));
    }
  }

  //File not found exception check
  @Test
  public void inputFileNotFound() throws Exception {
    result.getCsvFile(testFileExist);
  }

  //Exception handling for IOException
  @Test
  public void inputFileIOException() throws IOException {
    final RandomAccessFile raFile = new RandomAccessFile(inputFile, "rw");
    raFile.getChannel().lock();
    result.getCsvFile(inputFile);
  }

  //parses delimiter(,) and double quote list of lines into individual words/linen
  @Test
  public void testParse() throws Exception {
    List<String> tokens = result.csvLineParser(inputline);
    for (String str : tokens) {
      System.out.println(str);
    }
  }


  //Associate values from csv file to template file
  @Test
  public void testCustomerDetails() throws Exception {
    customerDataset = result.inputCustomerDetails(inputFileName);
    for (Map mapValues : customerDataset) {
      Map<String, String> val = new HashMap<>(mapValues);
      for (String key : val.keySet()) {
        System.out.println(key + "," + val.get(key));
      }
    }
  }

}

