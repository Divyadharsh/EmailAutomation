package assignment3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author muruganandham.d
 * Class generates the output of the program based on the field given either email/letter
 */
public class OutputEmailLetterGenerator {

  /**
   * Holder for final processed template
   */
  private Map<Integer, String> finalTemplateHolder;
  CustomerDataProcessor customerInputProcessor = new CustomerDataProcessor();
  /**
   * Holds customer data values for an output
   */
  private Set<Map<String, String>> dataVal;
  TemplateFileProcessor templateProcessor = new TemplateFileProcessor();
  /**
   * Holds brackets position in the template file from the parser
   */
  private Set<Integer> positions;
  /**
   * updates the values with the template given
   */
  private Map<Integer, String> matchTemplate;
  TemplateDataGenerator outputProcessor = new TemplateDataGenerator();
  /**
   * provides the updated template as an output
   */
  private String finalOutputTemplate;
  /**
   * suffix for the output file with the firstName
   */
  private String outputKeyName = "[[first_name]]";
  /**
   * suffix for the output file with the lastName
   */

  private String outputKeyName1 = "[[last_name]]";

  /**
   *
   * @param typeofOutput out type either email/letter
   * @param inputTemplate template text file input
   * @param inputFileName input csv file with customer data
   * @param outputFolderName output to be saved in a output directory name
   * @throws IOException handles the io exception
   */
  public void generateOutput(String typeofOutput, String inputTemplate, String inputFileName, String outputFolderName) throws Exception {
    System.out.println(typeofOutput);
    dataVal = new HashSet<>();
    dataVal = customerInputProcessor.inputCustomerDetails(inputFileName);
    finalTemplateHolder = new HashMap<>();
    finalTemplateHolder = outputProcessor.finalParsedOutputFile(inputTemplate);
    positions = new HashSet<>();
    positions = outputProcessor.finalPositionValues(inputTemplate);

    //matches the template and data of the csv file
    for (Map<String, String> currentVal : dataVal) {matchTemplate = new HashMap<>(finalTemplateHolder);
      for (Integer index : positions) {
        String pos = matchTemplate.get(index);
        //System.out.println(pos);
        matchTemplate.put(index, currentVal.get(pos));}
      finalOutputTemplate = templateProcessor.templateValueProcessor(matchTemplate);
      //System.out.println(finalOutputTemplate);

      //output file format
      String outputFileName = typeofOutput + "_" + currentVal.get(outputKeyName)+"_"+currentVal.get(outputKeyName1) + ".txt";

      //final output generates to the respective output folder directory
      BufferedWriter finalOutput = new BufferedWriter(new OutputStreamWriter
          (new FileOutputStream(outputFolderName + File.separator + outputFileName), "UTF8"));
      finalOutput.write(finalOutputTemplate);
      finalOutput.flush();
      finalOutput.close();
    }
  }
}

