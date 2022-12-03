package assignment3;
import static java.lang.System.out;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.IOException;

/**
 * @author muruganandham.d
 * class holds all the values for the command line and calls the email/letter generator based
 * on the commands given in the command line argument
 */

public class CommandLineMain {

  /**
   * value provided that email is generated
   */
  @Option(name = "-createEmailTemplate", aliases ="--email", usage = "only generate emails messages")
  public boolean createEmail;
  /**
   * value for the email template is provided
   */
  @Option(name="-emailTemplateValue", aliases ="--email-template", usage="accept filename that uses email template")
  public String emailTemplate;
  /**
   * value provided that letter is generated
   */
  @Option(name = "-createLetterTemplate", aliases="--letter",  usage = "only generate letters")
  public boolean createLetter;
  /**
   * value for the letter template is provided
   */
  @Option(name="-letterTemplateValue", aliases ="--letter-template",  usage="accept filename that uses letter template")
  public String letterTemplate;
  /**
   * value for the output directory is provided
   */
  @Option(name ="-outputDirectory", aliases="--output-dir",  usage="accept the name of the output folder")
  public String outputDir;
  /**
   * value provided for the input csv file
   */
  @Option(name="--csvFile", aliases="--csv-file", usage="accepts the name of the csv file")
  public String csvFile;

  /**
   * args 4j doMain function calls the command line arguments and validate the given commands
   * @param arguments takes the given command line arguments
   * @throws IOException handles when IO exception occurs
   */
  private void doMain(final String[] arguments) throws IOException
  {
    final CmdLineParser parser = new CmdLineParser(this);
    if (arguments.length < 1)
    {
      parser.printUsage(out);
      System.exit(-1);
    }
    try
    {
      parser.parseArgument(arguments);
    }
    catch (CmdLineException clEx)
    {
      out.println("ERROR: Unable to parse command-line options: " + clEx);
    }

    if(csvFile==null) {System.err.printf("Error Message : Enter the command for csv file");}
    if(outputDir==null) {System.err.printf("Error Message: Enter the command for Output directory folder");}
    if((createEmail)&&(emailTemplate==null)) {System.err.printf("Error Message: Enter the command for email template processing");}
    if((createLetter)&&(letterTemplate==null)) {System.err.printf("Error Message: Enter the command for letter template processing");}

    System.out.println("The value of the given CSV file is : "+ csvFile);
    System.out.println("The provided Letter Template is : "+ letterTemplate);
    System.out.println("The Output directory is : "+ outputDir);
    System.out.println("The provided email Template is : "+ emailTemplate);
    System.out.println("Value of email generation is: "+ createEmail);
    System.out.println("Value of letter generation is: "+ createLetter);

    OutputEmailLetterGenerator commandlineValues = new OutputEmailLetterGenerator();
    try {
      if (createEmail) {
        commandlineValues.generateOutput("email", emailTemplate, csvFile, outputDir);
      }
      if (createLetter) {
        commandlineValues.generateOutput("letter", letterTemplate, csvFile, outputDir);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
  /**
   * Executable function demonstrating Args 4j command-line processing.
   *
   * @param arguments Command-line arguments to be processed with Args 4j.
   */
  public static void main(final String[] arguments)
  {
    final CommandLineMain instance = new CommandLineMain();
    try
    {
      instance.doMain(arguments);
    }
    catch (IOException ioEx)
    {
      out.println("ERROR: I/O Exception encountered: " + ioEx);
    }
  }
}