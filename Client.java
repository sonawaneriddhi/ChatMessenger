////////////////////////////////////////////////////////////////////////
//
//      File Name   : Client.java
//
//      Description : Connects to the server via socket to send and 
//                    receive messages in the chat application.
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

import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

///////////////////////////////////////////////////////////////////////
//
//  Client side GUI
//
///////////////////////////////////////////////////////////////////////

class Client implements ActionListener
{
    //Characteristics
    JFrame fobj;
    JLabel Lbl1, ResultLabel;
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

    public Client(String title, int width , int height, Socket sobj)
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

        ResultLabel.setText("Myself : " + str2);

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
                ResultLabel.setText("Server has ended the chat");
                break;    
            }

            ResultLabel.setText("Server : " + str1);

            no = no + 20;
        }
    }
}

///////////////////////////////////////////////////////////////////////
//
//  Entry point function of project (main)
//
///////////////////////////////////////////////////////////////////////

class ChatClientGUI
{   
    public static void main(String A[])
    {
        try
        {
            /* 
                For using two different devices :
                    Connect to same network using wifi or LAN cable between your two devices
                    Get the ip address from the server device 
                    Paste it below in place of hocalhost
                    and Run the server first then client
                Note : For Windows allow an Java/Port through Firewall (As Windows Firewall may BLOCK this) 
            */
            Socket sobj = new Socket("localhost",5100);

            Client mobj = new Client("Client",500,600,sobj);
            
            mobj.display();
        }
        catch(Exception eobj)
        {}
    }
}