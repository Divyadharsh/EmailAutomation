package assignment3;
import java.util.List;
import java.util.Map;
import java.util.Set;
import assignment3.exceptionHandling.EmptyContentException;
import assignment3.exceptionHandling.NullValueException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author muruganandham.d
 * Class generates the data for the given input template text file
 */
public class TemplateDataGenerator {

  /**
   * List stores the data from the customerDataProcessor class
   */
  private List<String> customerDataResult;
  CustomerDataProcessor customerData = new CustomerDataProcessor();
  TemplateFileProcessor templateProcessor = new TemplateFileProcessor();
  /**
   * String to host the result from File Combine utility.
   */
  private String resultTemplate;
  /**
   * The counter for traversal
   */
  private int countValue;
  /**
   * value of the starting position
   */
  private int startPos = 0;
  /**
   * key values of all the brackets with its position in the template.
   */
  private Set<Integer> positionKeys;
  /**
   * Index value for the starting of the square bracket
   */
  private int beginPos = 0;
  /**
   * List of all positions with beginning and ending positions
   */
  private List<Integer[]> position;

  /**
   * Index val for the closing of the square bracket
   */
  private int endPos = 1;

  /**
   * complete parsed template file map values
   */
  private Map<Integer, String> finalParsedTemplateOutput;

  /**
   * Given a template file name, break down the template into placeholders and non-placeholders
   *
   * @param fileName String value of the template file name.
   * @throws Exception NullArgumentException or EmptyArgumentException.
   */
  public void parseTemplate(String fileName) throws Exception {
    positionKeys = new HashSet<>();
    countValue = 0;
    customerDataResult = new ArrayList<>();

    //calling customerDataGenerator class to get the parsed csv file input
    customerDataResult = customerData.getCsvFile(fileName);
    finalParsedTemplateOutput = new HashMap<>();

    //calling the template processor to get the value of list and map stored values
    resultTemplate = templateProcessor.templateLineProcessor(customerDataResult);
    position = templateProcessor.templatePlaceholderParser(resultTemplate);

    finalParsedTemplateOutput.put(countValue, resultTemplate.substring(startPos, position.get(startPos)[beginPos]));

    countValue = countValue+1;

    finalParsedTemplateOutput.put(countValue, resultTemplate.substring(position.get(startPos)[beginPos],
        position.get(startPos)[endPos]));positionKeys.add(countValue);

    countValue = countValue+1;

    //Tracks the last position of the placeholder, current placeholder start position and current placeholder end position

    for (int index = 1; index < position.size(); index++) {
      int lastPosition = position.get(index - 1)[endPos];
      int currentBeginPos = position.get(index)[beginPos];
      int currentEndPos = position.get(index)[endPos];
      finalParsedTemplateOutput.put(countValue, resultTemplate.substring(lastPosition, currentBeginPos));
      countValue++;
      finalParsedTemplateOutput.put(countValue, resultTemplate.substring(currentBeginPos, currentEndPos));
      positionKeys.add(countValue);
      countValue++;
    }

    int lastPlaceholderClosingQuote = position.get(position.size() - 1)[endPos];
    if (lastPlaceholderClosingQuote != resultTemplate.length())
    {
      finalParsedTemplateOutput.put(countValue, resultTemplate.substring(lastPlaceholderClosingQuote, resultTemplate.length() - 1));
    }
  }

  /**
   * Method process the template fileName and stores in the hashmap with key value pairs
   *
   * @param templateInput string with the given template filename text
   * @return return hashmap with key value pairs of the stored content
   * @throws Exception handles exception for null and empty file
   */
  public Map<Integer, String> finalParsedOutputFile(String templateInput) throws Exception {
    if (templateInput == null) {throw new NullValueException("Provided fileName is null.");}
    if (templateInput.isEmpty()) {throw new EmptyContentException("Provided fileName is empty.");}

    //system.out.println(templateInput);
    this.parseTemplate(templateInput);

    return this.finalParsedTemplateOutput;
  }

  /**
   * Method process the template fileName and gives the position values of the brackets given in the template
   *
   * @param templateInput string with the given input file
   * @return returns all the position values
   * @throws Exception handles exception for null and empty file
   */
  public Set<Integer> finalPositionValues(String templateInput) throws Exception {
    if (templateInput.isEmpty()) {throw new EmptyContentException("Given file is Empty. Provide the file with contents");}
    if (templateInput == null) {throw new NullValueException("Given file is Null. Provide the file with contents");}

    this.parseTemplate(templateInput);
    //System.out.println(templateInput);
    return this.positionKeys;
  }

}


