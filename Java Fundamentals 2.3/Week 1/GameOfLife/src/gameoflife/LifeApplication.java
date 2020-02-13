package gameoflife;



public class LifeApplication
{

    public static void main(String[] args)
    {

        LifeModel lifeModel = new LifeModel();


        while (lifeModel.getCount() > 0)
        {
            lifeModel.volgendeGeneratie();
            lifeModel.toon();
            System.out.println();
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                // Empty catch, maar bedoeld om een animatie te zien die een beetje bij te houden is
            }
        }
    }
}
