public class Sorter implements Runnable
{
    private BubbleSort bubbleSort;

    public Sorter(BubbleSort bubbleSort)
    {
        this.bubbleSort = bubbleSort;
    }


    public void run()
    {
        boolean erIsGewisseld;

        for (int j = 0; j < bubbleSort.array.length; j++)
        {
            erIsGewisseld = false;

            if (bubbleSort.isFinished()){
                break;
            }


            for (int i = 1; i < bubbleSort.array.length - j; i++)
            {
                if (bubbleSort.array[i - 1] > bubbleSort.array[i])
                {
                    bubbleSort.wissel(i - 1, i);
                    erIsGewisseld = true;
                }
            }

            if(!erIsGewisseld)
                bubbleSort.setFinished(true);
        }
    }
}
