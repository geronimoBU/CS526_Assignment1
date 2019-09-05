package PersonManagement;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
/**
 * CS526 - Hw1 
 * Date: 9/17/2017 
 * @author alexgeronimo
 */
public class PersonManagement {
    
    // Main 
    public static void main(String[] args) {
        
        //Load .txt, set array size, create Person objects (lines) 
        Person[] people;
        try (Scanner input = checkFile("person_info.txt")) {
            int num_ppl = getLines(input);
            people = new Person[num_ppl];
            int i;
            for (i=0; input.hasNextLine(); i++) {
                
                String line = input.nextLine();
                if (i == 0){continue;}
                
                else {
                    String[] person = new String[3];
                    person = line.split(",\\s+");
                    String name = person[0];
                    String email = person[1];
                    int age = Integer.parseInt(person[2]);
                    
                    Person p = new Person(name, email, age);
                    people[i-1] = p;
                    
                }   
            }
        }
      
        // Create Menu, handle input  
        boolean menu = true; 
        
        while (menu == true){
            Scanner in = new Scanner(System.in);
            System.out.println("Choose an Option: "+"\n"+ "1. Person Info"+ "\n"
            + "2. Update Email"+ "\n"+ "3. Update Age"+ "\n"+ "4. Exit");
            int menu_option = checkMenuInput(in);

            // Handle menu options 
            boolean bool = false;
            
                if (menu_option == 1){
                    String menu_1in = userEnterName();
                    
                    
                    for (Person p : people) {
                        if (menu_1in.equals(p.getName())){
                            p.getInfo();
                            bool = true; 
                            break; 
                        }
                    }
                    if (bool == false) {
                        System.out.println("Error: Name not found"+"\n");
                    }
                }
                    
                else if ( menu_option == 2){
                    String menu_2in = userEnterName();
                   
                    Scanner in_2 = new Scanner(System.in);
                    System.out.println("Please enter new email address: ");
                    String email_in = in_2.nextLine();
                    
                    for (Person p : people) {
                        if (menu_2in.equals(p.getName())){
                            p.setEmail(email_in);
                            p.getInfo();
                            bool = true; 
                            break; 
                        }
                    }
                    if (bool == false) {
                        System.out.println("Error: Name not found"+"\n");
                    }
                }
                
                else if ( menu_option == 3){ 
                    String menu_3in = userEnterName();
                    int age_int = userEnterAge();
                   
                    for (Person p : people) {
                        if (menu_3in.equals(p.getName())){
                            p.setAge(age_int);
                            p.getInfo();
                            bool = true; 
                            break; 
                        }
                    }
                    if (bool == false) {
                        System.out.println("Error: Name not found"+"\n");
                    }
                }
                
                else if ( menu_option == 4){
                   System.out.println("Good Bye!");
                   menu = false; 
                }
        } 
    }
    //************ End Main *********************
    
    /**
     * Reads first line of .txt - returns integer value 
     * @param in_file
     * @return int first row
     */
    public static int getLines(Scanner in_file){
        int num_lines = in_file.nextInt();
        return num_lines;}   
    
    
    
    /**
     * Check if user input is integer, handle exception with recursion 
     * @param in Scanner object 
     * @return int from user input 
     */
    public static int checkMenuInput(Scanner in){
        String menu_in = in.next();
        try {
            int menu_int = Integer.parseInt(menu_in);
            return menu_int;
        }
        
        catch (NumberFormatException ex) {
            System.out.println("Please enter a 1, 2, 3, or 4." + "\n");}
            System.out.println("Choose an Option: "+"\n"+ "1. Person Info"+ "\n"
            + "2. Update Email"+ "\n"+ "3. Update Age"+ "\n"+ "4. Exit");
            int menu_int_again = checkMenuInput(in);
            return menu_int_again; 
    }
    
    
    /**
     * Check if file exists 
     * @param String to file
     * @return Scanner object 
     */
    public static Scanner checkFile(String file){
        Scanner input;
        try {
            input = new Scanner(new File(file));
            return input;
        } 
        catch (FileNotFoundException ex){
            ex.printStackTrace();
            return null;
        }
        
    }
    
    
    /**
     * Prompt user to enter full name
     * @return String user input 
     */
    public static String userEnterName(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter full name: ");
        String name_in = in.nextLine().trim();
        return name_in;
    }
    
    
    /**
     * Prompt user to enter age integer, check if integer, handle with recursion 
     * @return int age 
     */
    public static int userEnterAge(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter new age: ");
        String name_in = in.nextLine().trim();
        
        try {
            int age_int = Integer.parseInt(name_in);
            return age_int;
        }
        catch (NumberFormatException ex) {
            System.out.println("Error: Please enter Integer");
            int age_int_again = userEnterAge();
            return age_int_again; 
        }
    }
}
       