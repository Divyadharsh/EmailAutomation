package assignment3;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author muruganandham.d
 * This class process the complete given csv file and parses the customer data to a list
 */
public class CustomerDataProcessor {

  /**
   * stores the result of the parser from the csv file
   */
  private List<String> resultLineParser = new ArrayList<>();
  /**
   * Array stores the split line
   */
  private String[] splitLine;
  /**
   * List of string stores the input file values
   */
  private List<String> fileInput = new ArrayList<>();
  /**
   * List of string that has the first line with position zero
   */
  private List<String> startOfFile = new ArrayList<>();
  /**
   * List of string that holds each customer data with comma separated values
   */
  private List<String> eachCustomerData = new ArrayList<>();
  /**
   * Map is stored with key value pairs of the associated template and values from the input file
   */
  private Set<Map<String, String>> customerInputData = new HashSet<>();

  /**
   * Method takes input as the csv file and returns the data in the provided input csv file as a list
   * @param inputFile is the input to the method with csv file name
   * @return the list of the customer data in the csv file.
   */
  public List<String> getCsvFile(String inputFile) {
    List<String> input = new ArrayList();
    String delimiter = ",";
    String line;
    try (BufferedReader br = new BufferedReader(new InputStreamReader((new FileInputStream(inputFile)),"UTF8"))){
      while((line = br.readLine()) != null){
        Arrays.asList(line.split(delimiter));
        input.add(line);}
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    } catch (Exception message){
      System.out.println(message);
    }
   // System.out.println("INPUT:"+input);
    return input;
  }

  /**
   * Method parse each csv line in the list
   * @param line each line from the csv file
   * @return returns the parsed line
   */
  public List<String> csvLineParser(String line) {
    splitLine = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
    List<String> tempArrayTokens = new ArrayList<String>(Arrays.asList(splitLine));
    resultLineParser = new ArrayList<>();
    for (String str : tempArrayTokens) {
      resultLineParser.add(str.replace("\"", ""));
    }
    return resultLineParser;
  }

  /**
   * Method associates the customer data with template file and parses the value in key value pair
   * @param fileName inputs the csv file name
   * @return the hash map with key value pair associated to template and csv input data values
   */

  public Set<Map<String, String>> inputCustomerDetails(String fileName) {
    fileInput = getCsvFile(fileName);
    int firstLine = 0;
    startOfFile = csvLineParser(fileInput.get(firstLine));
    for (int line = 1; line < fileInput.size(); line++) {
      eachCustomerData = csvLineParser(fileInput.get(line));
      Map<String, String> eachCustomerDataMap = new HashMap<>();
      for (int eachline = 0; eachline < startOfFile.size(); eachline++) {eachCustomerDataMap.put("[[" + startOfFile.get(eachline) + "]]", eachCustomerData.get(eachline));}
      customerInputData.add(eachCustomerDataMap);
    }
    //System.out.println("jbj"+customerInputData+"jj");
    return customerInputData;
  }
}

