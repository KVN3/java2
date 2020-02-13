package ThreadExamples;

import java.util.Scanner;


public class ThreadApplication
{


    // Geen methodes oproepen hier ???
    //draadje1.pleaseStop();

    public static void main(String[] args)
    {
        new ThreadApplication();

        PrintDraadje draadje1 = new PrintDraadje("!", 1000);
        PrintDraadje draadje2 = new PrintDraadje("_", 100);

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.print("Thread 1: ");
        draadje1.pleaseStop();
        System.out.print("Thread 2: ");
        draadje2.pleaseStop();
    }
}
