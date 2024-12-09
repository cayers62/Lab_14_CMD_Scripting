import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan
{
    public static void main(String[] args)
    {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec ="";
        File user = null;
        String message = "Usage: java FileScan <msg>\n Enter a file name:" + user;



        if(args.length > 0)
        {
            String file = args[0];
            user = new File(args[0]);

        }
        if(!user.exists())
        {
            System.out.println("Error File not found: " + user.getAbsolutePath());
            return;
        }
        else {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

            } else {
                System.out.println("No file selected.");
                return;
            }
        }

            try(BufferedReader reader = Files.newBufferedReader(selectedFile.toPath()))
            {


                    // Variables for counters
                    int line = 0;
                    int word = 0;
                    int charCount = 0;

                    System.out.println("Contents of file:");
                    System.out.println("_________________");

                    //Reading the file
                    while ((rec = reader.readLine()) != null) {

                        line++;
                        word += rec.split("\\s+").length; //Split by whitespaces to count words.
                        charCount += rec.length(); //Counting Characters in the lines

                        System.out.printf("\n %4d -%60s ", line, rec);
                    }


                    // Print summary report
                    System.out.println("\n\nSummary Report:");
                    System.out.println("---------------------");
                    System.out.println("File Name: " + selectedFile.getName());
                    System.out.println("Number of Lines: " + line);
                    System.out.println("Number of Words: " + word);
                    System.out.println("Number of Characters: " + charCount);
                    System.out.println("\nData file read successfully!");
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found!!");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



    }

}


