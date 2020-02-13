import java.util.ArrayList;
import java.util.Random;

public class BubbleSort
{
    public int[] array = new int[1000];
    private Random rnd = new Random();

    private boolean finished;

    public synchronized boolean isFinished()
    {
        return finished;
    }

    public synchronized void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public BubbleSort()
    {
        long startTime = System.nanoTime();

        fillArray();
        print();

        System.out.println("\n");

        //bubbleSort();
        //print();

        finished = false;
        for (int i = 0; i < 5000; i++)
        {
            Thread thread = new Thread(new Sorter(this));
            thread.start();
        }

        print();

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("\n" + totalTime);

        // Without threads: ~15.000.000
        // With threads, unoptimized: ~350.000.000
    }

    private void fillArray()
    {
        for (int i = 0; i < array.length; i++)
        {
            array[i] = rnd.nextInt(1000);
        }
    }

    public void bubbleSort()
    {
        try
        {
            for (int j = 0; j < array.length; j++)
            {
                for (int i = 1; i < array.length - j; i++)
                    if (array[i - 1] > array[i])
                    {
                        wissel(i - 1, i);
                    }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        //finished = true;
    }

    public synchronized void wissel(int largerIndex, int smallerIndex)
    {
        int temp = array[largerIndex];
        array[largerIndex] = array[smallerIndex];
        array[smallerIndex] = temp;
    }

    private void print()
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) { new BubbleSort(); }
}
