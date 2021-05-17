import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

public class Client extends JFrame {

    Socket socket;

    BufferedReader br;
    PrintWriter pw;

    // declaration Components
    private JLabel heading=new JLabel("Client Area");
    private JTextArea messageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("Roboto",Font.PLAIN,20);

    public Client()
    {
        try {
            // System.out.println("sending request to server");
            // socket=new Socket("localhost",7777);
            // System.out.println("Connection done.");


            // br=new BufferedReader(new InputStreamReader
            // (socket.getInputStream()));

            // pw=new PrintWriter(socket.getOutputStream());

            createGUI();
            // startReading();
            // startWriting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createGUI() {
        this.setTitle("Client Messanger[End]");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        

    

        //coding for component

        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);

        heading.setIcon(new ImageIcon("chat.png"));
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);

        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        this.setLayout(new BorderLayout());

        this.add(heading,BorderLayout.NORTH);
        this.add(messageArea,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);


        this.setVisible(true);

        
    }

    public void startReading() {
        
        // creating Thread
     Runnable r1=()->{
        
        System.out.println("Reading started...");
        try {
            while(true)
            {
                
                    String msg=br.readLine();
                    
                    System.out.println("Server sent:"+msg);

                    if(msg.equals("exit"))
                    {
                        System.out.println("Server terminated the chat");
                        socket.close();
                        break;
                    }
                
            }

            
        } catch (Exception e) {
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
                        System.out.println("Client terminated the chat");
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
        new Client();
    }
}
