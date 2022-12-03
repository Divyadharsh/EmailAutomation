import assignment3.OutputEmailLetterGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutputEmailLetterGeneratorTest {

  private String inputTemplate;
  private String inputFileName;
  private String outputFolderName;
  private String typeofOutput;
  OutputEmailLetterGenerator testOutputGenerator;


  @BeforeEach
  public void before() throws Exception {
    testOutputGenerator = new OutputEmailLetterGenerator();
    inputTemplate = "email-template.txt";
    inputFileName = "insurance-company-members.csv";
    outputFolderName = "email";
    typeofOutput = "email";
  }

  @AfterEach
  public void after() throws Exception {
  }

  @Test
  public void testOutputRun() throws Exception {
    //String typeofOutput, String inputTemplate, String inputFileName, String outputFolderName
    testOutputGenerator.generateOutput(typeofOutput,inputTemplate,  inputFileName, outputFolderName);
  }
}
