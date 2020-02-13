package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyServer implements Runnable
{

    private int counter = 0;
    String[] adviceList = {"Take smaller bites.", "Go gfor the tight jeans, no they do NOT make you look fat.",
            "One word: inappropriate!", "Just for today,be honest. Tell your boss what you *really* think.",
            "You might want to rethink that haircut."};

    public static void main(String[] args)
    {
        DailyServer dailyAdviceServer = new DailyServer();
        dailyAdviceServer.intialize();
    }

    private void intialize()
    {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        ServerSocket serverSocket = null;
        Socket sock = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            serverSocket = new ServerSocket(4242);
            System.out.println("Server started...");
            while (true)
            {
                sock = serverSocket.accept();
                counter++;
                System.out.println("Client %d is connecting...");

                isr = new InputStreamReader(sock.getInputStream());
                br = new BufferedReader(isr);
                String message = br.readLine();
                System.out.println(message);

                if (message != null)
                {
                    PrintStream pStream = new PrintStream(sock.getOutputStream());
                    String advice = getAdvice();
                    pStream.println(advice);
                    pStream.flush();
                    pStream.close();
                }
            }
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            try
            {
                br.close();
                isr.close();
                sock.close();
                serverSocket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private String getAdvice()
    {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

}
