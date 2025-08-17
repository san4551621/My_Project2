import java.util.ArrayList;
import java.util.Scanner;

public class ToDolist {

    //using private as we don't want other classes to use the Arraylist and Scanner here:Encapsulation
    private static ArrayList<String> tasks = new ArrayList();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) { 
            displaymenu();

            int choice=sc.nextInt();

            sc.nextLine();
            switch (choice) {
                case 1:
                    addtask();
                    break;
                case 2:
                    viewtask();
                    break;
                case 3:
                    marktaskcomplete();
                    break;

                case 4:
                    deletetask();
                    break;

                case 5:
                    System.out.println("Exiting the application! Goodbye");
                    return;
                default:
                    System.out.println("Invalid selection! Please try again");
                    break;
            }
        }
    }
    public static void displaymenu(){
        System.out.print("\n------------To Do List------------");
        System.out.print("\n 1: Add a new task in your list");
        System.out.print("\n2: View all tasks");
        System.out.print("\n3: Mark task as complete");
        System.out.print("\n4: Delete task");
        System.out.print("\n5: Exit");
        System.out.println("\nEnter your choice");
    }
    public static void addtask(){
        System.out.println("Enter a task:");
        String task=sc.nextLine();
        tasks.add(task);
        System.out.print("Task added successfully");
    }
    public static void viewtask(){
        if(tasks.isEmpty()){
            System.out.println("No tasks available");
            return;
        }
        System.out.println("Your tasks are:");
        for(int i=0;i<tasks.size();i++){
            System.out.println((i+1)+ ":"+tasks.get(i));
        }

    }
    public static void marktaskcomplete(){
        if(tasks.isEmpty()){
            System.out.println("No tasks available");
            return;
        }
        viewtask();

        System.out.println("Enter the task you want to mark as complete:");
        int marktask=sc.nextInt();
        sc.nextLine();
        if(marktask<=0 || marktask>=tasks.size()){
            System.out.println("Invalid task:");
            return;
        }
        else{
            String Completedtask=tasks.get(marktask-1)+"[Completed]";
            tasks.set(marktask-1,Completedtask);
            System.out.println("Task marked as completed successfully");
            viewtask();
        }
    }
    public static void deletetask(){
        if(tasks.isEmpty()){
            System.out.println("No tasks available");
            return;
        }
        viewtask();

        System.out.println("Enter the task you want to delete:");
        int marktask=sc.nextInt();
        sc.nextLine();
        if(marktask<=0 || marktask>=tasks.size()){
            System.out.println("Invalid task:");
            return;
        }
        else{
            tasks.remove(marktask-1);
            System.out.println("Task deleted successfully");
        }
    }
}