import assignment3.CommandLineMain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandLineMainTest {
  boolean createEmail;
  boolean createLetter;
  String testEmailTemplate;
  String testLetterTemplate;
  String testCsvFile;
  String emailOutputDir;
  String letterOutputDir;
  CommandLineMain newCmdProcessor;


  @BeforeEach
  public void setUp() throws Exception {
    createEmail = true;
    createLetter = true;
    testEmailTemplate = "email-template.txt";
    testLetterTemplate = "letter-template.txt";
    testCsvFile = "insurance-company-members_test.csv";
    emailOutputDir = "email";
    letterOutputDir = "letter";
    newCmdProcessor = new CommandLineMain();
  }

  /**
   * test successfully generate emails
   */
  @Test
  public void testGenerateEmail() throws IOException {
      newCmdProcessor.createEmail = createEmail;
      newCmdProcessor.emailTemplate = testEmailTemplate;
      newCmdProcessor.csvFile = testCsvFile;
      newCmdProcessor.outputDir = emailOutputDir;
  }

}
