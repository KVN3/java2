import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{

    public static void main(String[] args)
    {
        Client dailyAdviceClient = new Client();
        dailyAdviceClient.makeConnection("localhost", 4242);
    }

    @SuppressWarnings("resource")
    public void makeConnection(String ip, int port)
    {
        try
        {
            Socket socket = new Socket(ip, port);
            PrintStream printWriter = new PrintStream(socket.getOutputStream());
            printWriter.println("message Accepted: Wat is de dag vandaag?");
            printWriter.flush();
            Scanner reader = new Scanner(socket.getInputStream());
            String message = reader.nextLine();
            System.out.println(message);
        } catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}