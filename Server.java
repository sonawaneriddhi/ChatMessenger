////////////////////////////////////////////////////////////////////////
//
//      File Name   : Server.java
//
//      Description : Listens for incoming client connections and 
//                    handles message communication over a socket.
//
//      Author      : Riddhi Dattatraya Sonawane
//
//      Date        : 21/11/2025
//
///////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////
//
//  Header files inclusion
//
///////////////////////////////////////////////////////////////////////

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;

///////////////////////////////////////////////////////////////////////
//
//  Server side GUI
//
///////////////////////////////////////////////////////////////////////

class Server implements ActionListener
{
    //Characteristics
    JFrame fobj;
    JLabel Lbl1, ResultLabel, imageLabel;
    JButton bobj1;
    JTextField tobj1;
    Socket sobj;
    PrintStream pobj;                             
    BufferedReader bobj; 
    String str1,str2;
    static int no;

    static
    {
        no = 200;
    }

    public Server(String title, int width , int height, Socket sobj)
    {
        this.sobj = sobj;

        try
        {
            pobj = new PrintStream(sobj.getOutputStream());
            bobj = new BufferedReader(new InputStreamReader(sobj.getInputStream()));
        }
        catch(Exception eobj)
        {}

        str1 = null;
        str2 = null;

        fobj = new JFrame(title);

        Lbl1 = new JLabel("Message :");
        Lbl1.setBounds(50,50,100,30);

        tobj1 = new JTextField();
        tobj1.setBounds(150,50,100,30);

        bobj1 = new JButton("SEND");
        bobj1.setBounds(100,100,100,30);

        fobj.add(Lbl1);
        fobj.add(tobj1);
        fobj.add(bobj1);

        bobj1.addActionListener(this);
        fobj.setLayout(null);
        fobj.setSize(width,height);

        fobj.setVisible(true);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent aobj)
    {
        str2 = tobj1.getText();
        pobj.println(str2);
        ResultLabel = new JLabel("");
        ResultLabel.setBounds(50,(int)no,440,30);
        fobj.add(ResultLabel);

        if(str2.equals("end"))
        {
            ResultLabel.setText("You have ended the chat");
            return;
        }

        ResultLabel.setText("Myself : "+ str2);

        no = no + 20;
    
    }

    public void display() throws IOException
    {
        while(true)
        {
            str1 = bobj.readLine();

            ResultLabel = new JLabel("");
            ResultLabel.setBounds(50,(int)no,440,30);
            fobj.add(ResultLabel);
            
            if(str1.equals("end"))
            {
                ResultLabel.setText("Client has ended the chat");
                break;    
            }

            ResultLabel.setText("Client : "+ str1);

            no = no + 20;
        }
    }
}

///////////////////////////////////////////////////////////////////////
//
//  Entry point function of project (main)
//
///////////////////////////////////////////////////////////////////////

class ChatServerGUI
{   
    public static void main(String A[])
    {
        try
        {
            ServerSocket ssobj = new ServerSocket(5100);
            Socket sobj = ssobj.accept();

            Server mobj = new Server("Server",500,600,sobj);

            mobj.display();
        }
        catch(Exception eobj)
        {}
    }
}