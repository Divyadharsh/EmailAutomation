import assignment3.CustomerDataProcessor;
import assignment3.TemplateFileProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateFileProcessorTest {

  private String templateInput;
  private Map<Integer, String> test;
  private Map<Integer, String> testNull;
  private Map<Integer, String> testEmpty;
  private List<String> templateList;
  private CustomerDataProcessor csvFileInput = new CustomerDataProcessor();
  private TemplateFileProcessor templateFileInput = new TemplateFileProcessor();
  private List<String> customerListEmpty;
  private List<String> customerListNull;
  private String resultTemplate;
  private String testResult;
  private List<Integer[]> resultPlaceholders;
  private String testtemplate;
  private String placeholderContent;
  private String placeholderNull;
  private String placeholderEmpty;


  @BeforeEach
  public void setUp() throws Exception {
    templateList = new ArrayList<>();
    resultPlaceholders = new ArrayList<>();
    test = new HashMap<>();
    test.put(0, "run\r\n");
    test.put(1, "main\r\n");
    test.put(2, "check\r\n");
    testEmpty = new HashMap<>();
    customerListEmpty = new ArrayList<>();
    templateInput = "providedEmailTemplate.txt";
    testtemplate = "email-template.txt";
    placeholderContent = templateFileInput.templateLineProcessor(csvFileInput.getCsvFile(testtemplate));
  }

  @Test
  public void testTemplateLineProcessor() throws Exception {
    templateList = csvFileInput.getCsvFile(templateInput);
    resultTemplate = templateFileInput.templateLineProcessor(templateList);
    //System.out.println(resultTemplate);
  }

  @Test
  public void testTemplateValueProcessor() throws Exception {
    testResult = templateFileInput.templateValueProcessor(test);
    //System.out.println(testResult);
  }

  @Test
  public void testEmptyArgumentListTemplateLineProcessor() {
    try {
      templateFileInput.templateLineProcessor(customerListEmpty);
    } catch (Exception message) {
      //System.out.println(message);
    }
  }

  @Test
  public void testNullValuesListTemplateLineProcessor() {
    try {
      templateFileInput.templateLineProcessor(customerListNull);
    } catch (Exception e) {
      //System.out.println(e);
    }
  }

  @Test
  public void testEmptyArgumentMapTemplateValueProcessor() {
    try {
      templateFileInput.templateValueProcessor(testEmpty);
    } catch (Exception message) {
      //System.out.println(message);
    }
  }

  @Test
  public void testNullValueArgumentMapTemplateValueProcessor() {
    try {
      templateFileInput.templateValueProcessor(testNull);
    } catch (Exception message) {
      //System.out.println(message);
    }
  }

  @Test
  public void testTemplatePlaceholderParser() throws Exception {
    resultPlaceholders = templateFileInput.templatePlaceholderParser(placeholderContent);
  }

  @Test
  public void testNullValueArgumentTemplatePlaceholderParser() {
    try {
      templateFileInput.templatePlaceholderParser(placeholderNull);
    } catch (Exception message) {
      //System.out.println(message);
    }
  }

  @Test
  public void testEmptyArgumentTemplatePlaceholderParser() {
    try {
      templateFileInput.templatePlaceholderParser(placeholderEmpty);
    } catch (Exception message) {
      //System.out.println(message);
    }
  }
}

