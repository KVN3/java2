import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DailyClient
{
    public void go()
    {
        try
        {
            Socket s = new Socket("127.0.0.1", 4242);

            Scanner reader = new Scanner(s.getInputStream() );
            String message = reader.nextLine();

            PrintWriter pr = new PrintWriter(s.getOutputStream());
            pr.println("Message accepted: " + message);
            pr.flush();

//            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
//            BufferedReader reader = new BufferedReader(streamReader);
//
//            String advice = reader.readLine();
//            System.out.println("Why don't you " + advice);
//
//            reader.close();

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args)
    {

        DailyClient client = new DailyClient();
        client.go();
    }
}
