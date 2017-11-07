package seedu.address.logic.commands;

import java.io.BufferedWriter;
import java.io.*;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Created by nguyenminhquang on 11/7/17.
 */

/**
 * Export the contact list into XML file and store it at the specified input path
 */

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Export the contact list into XML file and store it at the specified input path \n"
            + "Parameters: [FILE_PATH]...\n"
            + "Example: " + COMMAND_WORD + " C:\\Users\\(username)\\Desktop\n" +
            "\n";

    private final String savedFilePath;

    public ExportCommand(String savedFilePath) {
        this.savedFilePath = savedFilePath;
    }

    @Override
    public CommandResult execute() {
        try {
            File file;
            file = new File(savedFilePath);


            File fXmlFile = new File(System.getProperty("user.dir") + "/data/addressbook.xml");
            System.out.println(System.getProperty("user.dir") + "/data/addressbook.xml");
            //PrintWriter output = null;
            //PrintWriter output = new PrintWriter(new FileWriter(file), "UTF-8");
            PrintWriter output = new PrintWriter(savedFilePath, "UTF-8");
            //File fXmlFile = new File("/Users/mkyong/staff.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            output.println("Contact List :");
            NodeList nList = doc.getElementsByTagName("persons");
            output.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                output.println("\nPerson :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    output.println("Name : "
                            + eElement.getElementsByTagName("name")
                            .item(0).getTextContent());
                    //output.println("Name : "
                      //      + eElement.getAttribute("name"));
                    output.println("Phone : "
                            + eElement.getElementsByTagName("phone")
                            .item(0).getTextContent());
                    output.println("Email : "
                            + eElement.getElementsByTagName("email")
                            .item(0).getTextContent());
                    output.println("Birthday : "
                            + eElement.getElementsByTagName("birthday")
                            .item(0).getTextContent());
                    output.println("Address : "
                            + eElement.getElementsByTagName("address")
                            .item(0).getTextContent());
                }

            }
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CommandResult("File exported at " + savedFilePath +" .");
        // export your data code here
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && this.savedFilePath.equals(((ExportCommand) other).savedFilePath)); // state check
    }
}