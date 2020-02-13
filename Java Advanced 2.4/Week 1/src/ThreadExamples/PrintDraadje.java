package ThreadExamples;

public class PrintDraadje implements Runnable
{
    private String s;
    private int millisec, counter;
    private boolean doorgaan;

    public PrintDraadje(String s, int millisec)
    {
        this.s = s;
        this.millisec = millisec;
        this.counter = 0;
        doorgaan = true;
        Thread draad = new Thread(this);
        draad.start();
    }

    public void pleaseStop()
    {
        doorgaan = false;

        System.out.println(counter);
    }



    public void run()
    {
        while (doorgaan)
        {
            System.out.print(s);
            counter++;

            try
            {
                Thread.sleep(millisec);
            } catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
    }
}
