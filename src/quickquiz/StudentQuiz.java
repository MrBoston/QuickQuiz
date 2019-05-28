package quickquiz;

//<editor-fold defaultstate="collapsed" desc="Imported Items">
//swing libraries
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileFilter;
import javax.swing.JOptionPane;

//all event listeners
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//file read and write libraries
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.FileWriter;

//file dialog libraries 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

//CHAT RELATED ---------------------------
import java.net.*;
import java.io.*;
//----------------------------------------

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;


//</editor-fold>
/**
 *
 * @author Tariq Bosthan
 */
public class StudentQuiz extends JFrame implements ActionListener, MouseListener
{
    // MaxEntries, NumberOfEntries, currentEntries are global variables

    int maxEntries = 100;
    int numberOfEntries = 0;
    int currentEntry = 0;
    ArrayList<Object[]> dataValues;
    String[] Topic = new String[maxEntries];  
    String[] Question = new String[maxEntries];
    String[] A = new String[maxEntries];
    String[] B = new String[maxEntries];
    String[] C = new String[maxEntries];
    String[] D = new String[maxEntries];
    
//    qDataRecord[] QuestionInformation = new qDataRecord[maxEntries];
    
    //JFrame components and other data storage variables
    private JLabel lblQuickQuizByNetwork,  lblTopic, lblQuestion, lblA, lblB, lblC, lblD,lblYourAns, lblYourName,lblConnectionMessage, lblMessage;

    private JTextField txfAnswer;

    private JButton btnSubmit, btnExit;

   // private JTable tblTopicAndQuestions;

    private JTextArea txaTopic, txaQuestion, txaA, txaB, txaC, txaD, txaYourName;

    SpringLayout springLayout;
    
    //CHAT RELATED ---------------------------
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread2 client2 = null;
    private String serverName = "localhost";
    private int serverPort = 4444;
    
    //----------------------------------------
        
    
    
    public static void main(String[] args)
    {
        StudentQuiz QuickQuizNetowrkApplication = new StudentQuiz();
        QuickQuizNetowrkApplication.run();
    }

    /**
     * @param args the command line arguments
     */
    //<editor-fold defaultstate="collapsed" desc="Display Graphical User Interface">
    private void displayGUI()
    {
        //intializes all the frame's components
        springLayout = new SpringLayout();
        setLayout(springLayout);
        displayTextFields(springLayout);
        displayButtons(springLayout);
        displayLabels(springLayout);
        displayTextArea(springLayout);

    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="To make the application "Run"">
    private void run()
    {
        //sets up all the window based functions bounds, title, listeners, etc.
        setBounds(100, 200, 1000, 850);
        setTitle("Quick Quiz Network");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 230, 255));
        setPreferredSize(new Dimension(600, 450));
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
       

        displayGUI();
         connect(serverName, serverPort);
        

        //default file to be read
        // readDataFile("QuickQuizData.csf", "Quick Quiz");
        //sets the window so that it cannot be resized
        setResizable(false);
        //makes the window visible
        setVisible(true);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Display Labels">
    private void displayLabels(SpringLayout layout)
    {
        lblQuickQuizByNetwork = LibraryComponents.LocateAJLabel(this, layout, "Quick Quiz By Network", 130, 2, 30, 153, 0, 153, 255, 230, 255);
        lblYourName = LibraryComponents.LocateAJLabel(this, layout, "Your Name:", 10, 74, 14, 204, 51, 255, 255, 230, 255);
        lblTopic = LibraryComponents.LocateAJLabel(this, layout, "Topic:", 10, 110, 14, 204, 51, 255, 255, 230, 255);
        lblQuestion = LibraryComponents.LocateAJLabel(this, layout, "Question:", 10, 148, 14, 204, 51, 255, 255, 230, 255);
        lblA = LibraryComponents.LocateAJLabel(this, layout, "A:", 10, 184, 14, 204, 51, 255, 255, 230, 255);
        lblB = LibraryComponents.LocateAJLabel(this, layout, "B:", 10, 220, 14, 204, 51, 255, 255, 230, 255);
        lblC = LibraryComponents.LocateAJLabel(this, layout, "C:", 10, 256, 14, 204, 51, 255, 255, 230, 255);
        lblD = LibraryComponents.LocateAJLabel(this, layout, "D:", 10, 292, 14, 204, 51, 255, 255, 230, 255);
        lblYourAns = LibraryComponents.LocateAJLabel(this, layout, "Your Answer:", 10, 328, 14, 204, 51, 255, 255, 230, 255);
        lblConnectionMessage = LibraryComponents.LocateAJLabel(this, layout, "Connection Message:", 10, 362, 14, 204, 51, 255, 255, 230, 255);
        lblMessage = LibraryComponents.LocateAJLabel(this, layout, "Connection Message:", 10, 390, 14, 204, 51, 255, 255, 230, 255);
        }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Display Buttons">
    private void displayButtons(SpringLayout layout)
    {
        btnSubmit = LibraryComponents.LocateAJButton(this, this, layout, "Submit", 150, 328, 100, 20);
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 395, 328, 100, 20);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Display Text Fields">
    //Code to display the text field/s
    private void displayTextFields(SpringLayout layout)
    {
        txfAnswer = LibraryComponents.LocateAJTextField(this, layout, 2, 110, 328);
        txfAnswer.setEditable(true);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Text Area Dimesions and location code">
    //Used to set the location and sice of the text areas
    private void displayTextArea(SpringLayout layout)
    {
        txaYourName = LibraryComponents.LocateAJTextArea(this, layout, 110, 74, 2, 35);
        txaTopic = LibraryComponents.LocateAJTextArea(this, layout, 110, 110, 2, 35);
        txaQuestion = LibraryComponents.LocateAJTextArea(this, layout, 110, 148, 4, 35);
        txaA = LibraryComponents.LocateAJTextArea(this, layout, 110, 184, 2, 35);
        txaB = LibraryComponents.LocateAJTextArea(this, layout, 110, 220, 2, 35);
        txaC = LibraryComponents.LocateAJTextArea(this, layout, 110, 256, 2, 35);
        txaD = LibraryComponents.LocateAJTextArea(this, layout, 110, 292, 2, 35);
        txaQuestion.setPreferredSize(new Dimension (4,35));
        txaQuestion.setWrapStyleWord(true);
        txaQuestion.setLineWrap(true);
        txaYourName.setEditable(true);
        txaTopic.setEditable(true);
        txaQuestion.setEditable(true);
        txaA.setEditable(true);
        txaB.setEditable(true);
        txaC.setEditable(true);
        txaD.setEditable(true);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Buttons actionPerformed">
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == btnSubmit)
        {
            send();
        }
        //Exits the program when clicked, does not save anything
        if (e.getSource() == btnExit)
        {
            System.exit(0);
            send();
        }
    }

    //</editor-fold>
    
     public void connect(String serverName, int serverPort)
    {
        System.out.println("Establishing connection. Please wait ...");
        try
        {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            open();
        }
        catch (UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch (IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }
     
    public void DisplayHandleData(String HandleData)
    {
        try
        {
            String[] temp1 = HandleData.split(": ");
            String[] temp2 = temp1[1].split(",");
     
            txaTopic.setText(temp2[0].toString());
            txaQuestion.setText(temp2[1].toString());
            txaA.setText(temp2[2].toString());
            txaB.setText(temp2[3].toString());
            txaC.setText(temp2[4].toString());
            txaD.setText(temp2[5].toString());

        }
        catch (Exception e)
        {
            System.err.println("Error Reading File: " + e.getMessage());
        }
        
    }

    private void send()
    {
        String Answer = txfAnswer.getText().toUpperCase();
        try
        {
            streamOut.writeUTF("student" + ", " + Answer); //.getText());
            streamOut.flush();
        }
        catch (IOException ioe)
        {
            System.out.println("Sending error: " + ioe.getMessage());
            close();
        }
    }

    public void handle(String msg)
    {
        if (msg.equals(".bye"))
        {
            System.out.println("Good bye. Press EXIT button to exit ...");
            close();
        }
        else
        {
            System.out.println("Handle: " + msg);
            System.out.println(msg);
        }
        String HandleData = msg;
        DisplayHandleData(HandleData);
    }

    public void open()
    {
        try
        {
            streamOut = new DataOutputStream(socket.getOutputStream());
            client2 = new ChatClientThread2(this, socket);
        }
        catch (IOException ioe)
        {
           System.out.println("Error opening output stream: " + ioe);
        }
    }

    public void close()
    {
        try
        {
            if (streamOut != null)
            {
                streamOut.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException ioe)
        {
            System.out.println("Error closing ...");
        }
        client2.close();
        client2.stop();
    }


    //<editor-fold defaultstate="collapsed" desc="Key and Mouse Events">


    public void mouseClicked(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        
    }

   
    public void mouseReleased(MouseEvent e)
    {
   
    }

    public void mouseEntered(MouseEvent e)
    {
  
    }

    public void mouseExited(MouseEvent e)
    {
   
    }

//</editor-fold>
  
   
}
