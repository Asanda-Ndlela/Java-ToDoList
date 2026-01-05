//This Program is a simple command line To-Do list application

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class ToDoList{

    private static final String FILE_NAME = "To-Do-List.txt";

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.println("Hello There! Welcome to my to-do list app.");
        while(true){
            System.out.println("1. Create a new list");
            System.out.println("2. Edit an existing list");
            System.out.println("3. Show current list");
            System.out.println("0. Exit Program");
            System.out.print("Enter your choice: ");
            String option = input.nextLine();

            if(option.equals("1")){
                createList(input);
            }
            else if(option.equals("2")){
                editList(input);
            }
            else if(option.equals("3")){
                showList();
            } 
            else if(option.equals("0")){
                System.out.println("Thank you for using the To-Do list app. Have a great Day.");
                break;
            } else{
                System.out.println("Invalid Input");
            }
        }
        input.close();
    }

    //Method to create a new To-Do List
    public static void createList(Scanner input){

        try{
        
            PrintWriter writer = new PrintWriter(FILE_NAME);
            int count = 1;

            while(true){
                System.out.print("Enter item number " + count + " or press 0 to stop: ");
                String value = input.nextLine();
                
                if(value.equals("0")) {
                    break;
                }
                else if(value.trim().isEmpty()){
                    System.out.println("Task cannot be empty.");
                    continue;
                }else{
                    writer.println(count + ". " + value);
                    count++;
                }
            }
            writer.close();

        } catch(IOException e){
            System.out.println("File error: " + e.getMessage());
        }
        System.out.println("\nYour to-do list:\n ");
        showList();
    }

    //Method to edit your To-Do list
    public static void editList(Scanner input){
        try{
            File myFile = new File(FILE_NAME);
            if(!myFile.exists()){
                System.out.println("File does not exist. Please create one first.");
                return;
            }
            int count = 1;

            Scanner fileReader = new Scanner(myFile);
            String lastLine = null;
            while(fileReader.hasNextLine()) {
            lastLine = fileReader.nextLine();
            }

            if(lastLine != null && !lastLine.trim().isEmpty()) {
                String[] parts = lastLine.split("\\.");
                try {
                    count = Integer.parseInt(parts[0].trim()) + 1;
                } catch(NumberFormatException e) {
                    count++;
                }
            }
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true));
            while(true) {
                System.out.print("Enter item number " + count + " or press 0 to stop: ");
                String value = input.nextLine();
                if(value.equals("0")) {
                break;
                }
                else if(value.trim().isEmpty()){
                    System.out.println("Task cannot be empty.");
                    continue;
                } else {
                writer.println(count + ". " + value);
                count++;                    }
            }

            fileReader.close();
            writer.close();

        }catch(IOException e){
            System.out.println("File error: " + e.getMessage());
        }
        showList();
        System.out.println();
    }

    //Method to print out your To-Do-list
    public static void showList(){
        try{
            File myFile = new File(FILE_NAME);
            if(!myFile.exists()){
                System.out.println("File does not exist");
                return;
            }

            Scanner fileReader = new Scanner(myFile);
            while(fileReader.hasNextLine()){
                System.out.println(fileReader.nextLine());
            }

            fileReader.close();
        } catch(IOException e){
            System.out.println("File error: " + e.getMessage());
        }   
        System.out.println();     
    }
}