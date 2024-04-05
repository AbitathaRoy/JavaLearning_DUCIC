import java.util.*;
import java.io.*;

public class file_handling
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("Students.txt");
        int c = 0;
        if (file.createNewFile())
        {
            System.out.println("File created");
            FileWriter writer = new FileWriter("Students.txt", true); // Append to existing file
            String formattedData = String.format("%5s %-19s %5s %5s %13s %2s\n", "Serial", "Name", "Age", "Gender", "Contact No.", "E-mail");
            writer.write(formattedData);
            writer.close();
            c = 1;
        }
        else
        {
            System.out.println("File already exists");

            // trying to capture the last serial number
            BufferedReader reader = new BufferedReader(new FileReader("Students.txt"));
            String line = "";
            String line1 = "";
            while(true)
            {
                line1 = reader.readLine();
                if(line1 != null)
                    line = line1;
                else
                    break;

                // System.out.println(line);
            }
            reader.close();

            String serial_num = "";
            for(int i = 0; i < line.length(); i++)
            {
                if((int)(line.charAt(i)) >= 48 && (int)(line.charAt(i)) <= 57)
                {
                    while((int)(line.charAt(i)) >= 48 && (int)(line.charAt(i)) <= 57)
                    {
                        serial_num = serial_num + line.charAt(i++);
                    }
                    break;
                }
            }

            // System.out.println(c);
            c = Integer.parseInt(serial_num) + 1;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\nStudent Data Cell, DU CIC.");
            System.out.println("1. Add an entry");
            System.out.println("2. Delete an entry");
            System.out.println("3. Display data");
            System.out.println("4. Search for a student using registered e-mail");
            System.out.println("0. Exit program\n");

            short choice = Short.parseShort(in.readLine());

            switch (choice) {
                default:
                    System.out.println("Invalid input!");
                    break;

                case 1: // input a new student entry
                    System.out.println("Student name:");
                    String s_name = in.readLine();
                    System.out.println("Student age:");
                    short s_age = Short.parseShort(in.readLine());
                    System.out.println("Student gender (M/F/O):");
                    String s_gender = in.readLine();
                    System.out.println("Contact number:");
                    String s_number = in.readLine();
                    System.out.println("E-mail:");
                    String s_email = in.readLine();

                    try {
                        FileWriter writer1 = new FileWriter("Students.txt", true);
                        BufferedWriter writer2 = new BufferedWriter(writer1);

                        //pre-allocating width to each data element for proper presentation
                        String formattedData = String.format("%5s %-20s %5s %3s %15s %-9s\n", c++, s_name, s_age, s_gender, s_number, s_email);

                        writer2.write(formattedData);
                        writer2.close();
                    } catch (Exception e) {
                        System.out.println("Failed to write data to file: " + e.getMessage());
                    }

                    System.out.println("Entry added successfully!");
                    break;

                case 2:
                    // Code for deleting an entry
                    break;

                case 3: // print all the data in the file
                    System.out.println("Student Data:\n");
                    BufferedReader reader = new BufferedReader(new FileReader("Students.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    reader.close();
                    break;

                case 4: // search for a particular student using registered e-mail
                    System.out.println("Enter the registered e-mail:");
                    String s_email_toSearch = in.readLine();

                    try {
                        reader = new BufferedReader(new FileReader("Students.txt"));
                        int temp = 0;
                        while ((line = reader.readLine()) != null) {
                            String parts[] = line.split(" ");

                            s_email = line.substring(53);

                            if (s_email.equals(s_email_toSearch)) {
                                temp++;
                                System.out.println("Student Found:");
                                System.out.println("ID: " + line.substring(0, 5).trim());
                                System.out.println("Name: " + line.substring(6, 29).trim());
                                System.out.println("Age: " + line.substring(30, 32).trim());
                                System.out.println("Gender: " + line.substring(33, 36).trim());
                                System.out.println("Phone Number: " + line.substring(37, 52).trim());
                                System.out.println("Email: " + s_email_toSearch);
                            }
                        }

                        if (temp == 0)
                            System.out.println("No student registered against the entered email found.");
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }

                    break;

                case 0:
                    System.out.println("Exiting program...");
                    System.exit(0);
            }
        }
    }

    void print_test(String str)
    {
        System.out.println(str);
    }
}
