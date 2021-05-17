import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    ServerSocket serverSocket;
    Socket socket;

    BufferedReader br;
    PrintWriter pw;

    public Server()
    {
        try {
            
            serverSocket=new ServerSocket(7777);
            System.out.println("Server is ready to connection...");
            System.out.println("waiting...");
            socket=serverSocket.accept();

            br=new BufferedReader(new InputStreamReader
            (socket.getInputStream()));

            pw=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public void startReading() {
        
        // creating Thread
     Runnable r1=()->{
        
        System.out.println("Reading started...");
        try
        {
            while(true)
            {
                
                    String msg=br.readLine();

                    if(msg.equals("exit"))
                    {
                        System.out.println("Client terminated the chat");
                        socket.close();
                        break;
                    }
                   
                    System.out.println("Client sent:"+msg);

                }  

        }catch(Exception e)
        {
           // e.printStackTrace();
           System.out.println("Connection is closed");
        }
    };

        new Thread(r1).start();

    }

    public void startWriting() {

          // creating Thread
     Runnable r2=()->{

        System.out.println("Writing started...");
        try {
            while(!socket.isClosed())
            {
                BufferedReader br1=new BufferedReader(new
                InputStreamReader(System.in) );
                String content= br1.readLine();
                
                pw.println(content);
                pw.flush();

                
                if(content.equals("exit"))
                {
                    System.out.println("Server terminated the chat");
                    socket.close();
                    break;
                }
            
                
            } 
            System.out.println("Connection is closed");
            
        } catch (Exception e) {
           // e.printStackTrace();
           System.out.println("Connection is closed");
        }

        };

        new Thread(r2).start();
    }

   

    public static void main(String[] args) {
        new Server();
    }
}
