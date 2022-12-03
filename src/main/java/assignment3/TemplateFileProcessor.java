package assignment3;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import assignment3.exceptionHandling.NullValueException;
import assignment3.exceptionHandling.EmptyContentException;

/**
 * @author muruganandham.d
 * Template File Processor class parse the whole template file and gets the values of the placeholder positions
 */
public class TemplateFileProcessor {
  /**
   * List that stores the integer value with the starting position of the square braces
   */
  private List<Integer> beginPosition;
  /**
   * List that stores the integer value with the ending position of the square braces
   */
  private List<Integer> endPosition;
  /**
   * String for the open placeholder in template file
   */
  private String begin =  "[[";
  /**
   * String for the close placeholder in template file
   */
  private String end = "]]";
  /**
   * List of integer values with open and close position values of the braces
   */
  private List<Integer[]> positionValues;
  /**
   *Beginning index value of the brackets to start the traversal in list
   */
  private int beginValue = 0;
  /**
   * Ending index value of the brackets for the traversal in list
   */
  private int endValue = 1;
  /**
   * position length of each brackets
   */
  private int length = 2;

  /**
   *Method process the string which is stored in the list and make it as a sentence like string
   * @param templateFileList list holds all the values in the template as each value
   * @return returns a one single string concatenating all the single string values
   * @throws Exception throws exception when the list is empty or null value
   */
  public String templateLineProcessor(List<String> templateFileList) throws Exception{
    StringBuilder endResult = new StringBuilder();
    if (templateFileList != null && !templateFileList.isEmpty()) {
      for (int DBObject = 0; DBObject < templateFileList.size(); DBObject++) {endResult.append(templateFileList.get(DBObject)).append(System.lineSeparator());}
      endResult.deleteCharAt(endResult.length() - 1);
    }
    if(templateFileList.isEmpty()){throw new EmptyContentException("The given template file is Empty and cannot be processed");}
    if(templateFileList == null){throw new NullValueException("The given template file is null and there is no content present");}
    //System.out.println(endResult.toString());
    return endResult.toString();
  }

  /**
   * Method returns the positions of the placeholder in the given template text file
   * @param templateValues gets the content of the template file as string
   * @return the value of the placeholder position after parsing the whole template file which is given
   * @throws Exception throws exception for empty file and null file of the template
   */

  public List<Integer[]> templatePlaceholderParser(String templateValues) throws Exception {

    positionValues = new ArrayList<>();
    beginPosition = new ArrayList<>();
    endPosition = new ArrayList<>();

    if (templateValues == null) {throw new NullValueException("There is no content in the provided file.");}
    if (templateValues.isEmpty()) {throw new EmptyContentException("Given template file content is empty.");}

    for (int posIndex = -1; (posIndex = templateValues.indexOf(begin, posIndex + 1)) != -1; posIndex++) {
      beginPosition.add(posIndex);}

    for (int posIndex = -1; (posIndex = templateValues.indexOf(end, posIndex + 1)) != -1; posIndex++) {
      endPosition.add(posIndex + end.length());}

    for (int index = 0; index < beginPosition.size(); index++) {
      Integer[] tempPosition = new Integer[length];
      tempPosition[beginValue] = beginPosition.get(index);
      tempPosition[endValue] = endPosition.get(index);
      positionValues.add(tempPosition);}

    return positionValues;
  }

  /**
   *Methods process the map value and make it as one complete string
   * @param templateValues is the input with values stored as map
   * @return returns as a one single string after performing concatenation of map values
   * @throws Exception throws exceptions when the given map is empty or null
   */
  public String templateValueProcessor(Map<Integer, String> templateValues) throws Exception {
    StringBuilder endValues = new StringBuilder();
    for (int DBObject = 0; DBObject < templateValues.size(); DBObject++) {
      endValues.append(templateValues.get(DBObject));
    }
    if (templateValues.isEmpty()) {throw new EmptyContentException("The list of string values from template file is empty.");}
    if (templateValues == null) {throw new NullValueException("The list of string values from template file is null");}
    //System.out.println(endValues.toString());
    return endValues.toString();
  }
}






