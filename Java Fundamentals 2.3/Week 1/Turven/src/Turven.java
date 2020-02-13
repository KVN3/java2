import java.util.*;

public class Turven
{
    public static void main(String[] args)
    {
        int[] aantalKeer = new int[18];

        for(int i = 0; i < 200; i++)
        {
            aantalKeer[werp()]++;
        }

        printKeren(aantalKeer);
    }

    private static int werp()
    {
        Random generator = new Random();
        return generator.nextInt(17) + 1;
    }

    private static void printKeren(int[] aantalKeer)
    {
        String amountOutput;

        for(int i = 2; i < aantalKeer.length; i++)
        {
            amountOutput = "";
            System.out.printf("%2d:", (i + 1));

            for(int x = 0; x < aantalKeer[i]; x++){
                amountOutput += "X";
            }

            System.out.println(" " + amountOutput);
        }
    }
}
