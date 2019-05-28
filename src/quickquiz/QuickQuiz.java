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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

//file dialog libraries 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

//Chat Related
import java.io.*;
import java.net.*;
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
public class QuickQuiz extends JFrame implements ActionListener, MouseListener
{
    // MaxEntries, NumberOfEntries, currentEntries are global variables

    int maxEntries = 100;
    int numberOfEntries = 0;
    int currentEntry = 0;
    String fileName = "QuizQuestions.txt";
    ArrayList<Object[]> dataValues;
    
//    qDataRecord[] QuestionInformation = new qDataRecord[maxEntries];
    
    //JFrame components and other data storage variables
    private JLabel lblSortBy, lblQuickQuizByNetwork, lblCorrectAns, lblLinkedList,
            lblBinaryTree, lblPreOrder, lblInOrder, lblPostOrder, lblTopic,
            lblQuestion, lblA, lblB, lblC, lblD, lblMessage, lblConnectionMessage;

    private JTextField txfCorrectAnswer;

    private JButton btnQNS, btnTopic, btnQuestion, btnExit, btnSend, btnDisplay, btnPrODisplay,
            btnPrOSave, btnIODisplay, btnIOSave, btnPoODisplay, btnPoOSave;

   // private JTable tblTopicAndQuestions;

    private JTextArea txaLinkedList, txaBinaryTree, txaTopic, txaQuestion, txaA, txaB, txaC, txaD;

    SpringLayout springLayout;
    JTable table;
    MyModel wordModel;
    DList dlist;
    BinaryTree theTree = new BinaryTree();
    
        //CHAT RELATED ---------------------------
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread1 client = null;
    private String serverName = "localhost";
    private int serverPort = 4444;
   
    
    public static void main(String[] args)
    {
        QuickQuiz QuickQuizNetowrkApplication = new QuickQuiz();
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
        //displayTable(springLayout);
        QuestionTable(springLayout);

    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Running the application">
    private void run()
    {
        //sets up all the window based functions bounds, title, listeners, etc.
        setBounds(100, 200, 1000, 850);
        setTitle("Quick Quiz Network");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 230, 255));
        setPreferredSize(new Dimension(1000, 850));
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
        

        //default file to be read
        // readDataFile("QuickQuizData.csf", "Quick Quiz");
        //sets the window so that it cannot be resized
        setResizable(false);
        //makes the window visible
        setVisible(true);
        dlist = new DList("", 0, 0);
        readDataFile(fileName);
        linkedListPrint();
        connect(serverName, serverPort);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Display Labels">
    private void displayLabels(SpringLayout layout)
    {
        lblSortBy = LibraryComponents.LocateAJLabel(this, layout, "Sort By:", 30, 350, 14, 204, 51, 255, 255, 230, 255);
        lblQuickQuizByNetwork = LibraryComponents.LocateAJLabel(this, layout, "Quick Quiz By Network", 300, 2, 30, 153, 0, 153, 255, 230, 255);
        lblCorrectAns = LibraryComponents.LocateAJLabel(this, layout, "Correct Answer:", 520, 380, 15, 204, 51, 255, 255, 230, 255);
        lblLinkedList = LibraryComponents.LocateAJLabel(this, layout, "Linked List:", 10, 425, 15, 204, 51, 255, 255, 230, 255);
        lblBinaryTree = LibraryComponents.LocateAJLabel(this, layout, "Binary Tree:", 10, 555, 15, 204, 51, 255, 255, 230, 255);
        lblPreOrder = LibraryComponents.LocateAJLabel(this, layout, "Pre-Order", 81, 720, 19, 204, 51, 255, 255, 230, 255);
        lblInOrder = LibraryComponents.LocateAJLabel(this, layout, "On-Order", 362, 720, 19, 204, 51, 255, 255, 230, 255);
        lblPostOrder = LibraryComponents.LocateAJLabel(this, layout, "Post-Order", 643, 720, 19, 204, 51, 255, 255, 230, 255);
        lblTopic = LibraryComponents.LocateAJLabel(this, layout, "Topic:", 530, 60, 14, 204, 51, 255, 255, 230, 255);
        lblQuestion = LibraryComponents.LocateAJLabel(this, layout, "Question:", 530, 80, 14, 204, 51, 255, 255, 230, 255);
        lblA = LibraryComponents.LocateAJLabel(this, layout, "A:", 530, 148, 14, 204, 51, 255, 255, 230, 255);
        lblB = LibraryComponents.LocateAJLabel(this, layout, "B:", 530, 184, 14, 204, 51, 255, 255, 230, 255);
        lblC = LibraryComponents.LocateAJLabel(this, layout, "C:", 530, 220, 14, 204, 51, 255, 255, 230, 255);
        lblD = LibraryComponents.LocateAJLabel(this, layout, "D:", 530, 256, 14, 204, 51, 255, 255, 230, 255);
        lblConnectionMessage = LibraryComponents.LocateAJLabel(this, layout, "Connection Message:", 530, 292, 14, 204, 51, 255, 255, 230, 255);
        lblMessage = LibraryComponents.LocateAJLabel(this, layout, "Connection Message:", 530, 320, 14, 204, 51, 255, 255, 230, 255);

    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Display Buttons">
    private void displayButtons(SpringLayout layout)
    {
        btnQNS = LibraryComponents.LocateAJButton(this, this, layout, "QNS", 100, 350, 70, 20);
        btnTopic = LibraryComponents.LocateAJButton(this, this, layout, "Topic", 171, 350, 70, 20);
        btnQuestion = LibraryComponents.LocateAJButton(this, this, layout, "Question", 242, 350, 90, 20);
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 30, 380, 200, 20);
        btnSend = LibraryComponents.LocateAJButton(this, this, layout, "Send", 760, 380, 200, 20);
        btnDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 877, 550, 80, 25);
        btnPrODisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 40, 750, 90, 25);
        btnPrOSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 131, 750, 90, 25);
        btnIODisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 322, 750, 90, 25);
        btnIOSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 413, 750, 90, 25);
        btnPoODisplay = LibraryComponents.LocateAJButton(this, this, layout, "ODisplay", 604, 750, 90, 25);
        btnPoOSave = LibraryComponents.LocateAJButton(this, this, layout, "OSave", 695, 750, 90, 25);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Display Text Fields">
    //Code to display the text field/s
    private void displayTextFields(SpringLayout layout)
    {
        txfCorrectAnswer = LibraryComponents.LocateAJTextField(this, layout, 2, 655, 380);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Display Text Area =">
    //Used to set the location and sice of the text areas
    private void displayTextArea(SpringLayout layout)
    {
        txaLinkedList = LibraryComponents.LocateAJTextArea(this, layout, 10, 455, 5, 86);
        txaBinaryTree = LibraryComponents.LocateAJTextArea(this, layout, 10, 580, 5, 86);
        txaTopic = LibraryComponents.LocateAJTextArea(this, layout, 600, 60, 1, 35);
        txaQuestion = LibraryComponents.LocateAJTextArea(this, layout, 600, 80, 4, 35);
        txaA = LibraryComponents.LocateAJTextArea(this, layout, 600, 148, 2, 35);
        txaB = LibraryComponents.LocateAJTextArea(this, layout, 600, 184, 2, 35);
        txaC = LibraryComponents.LocateAJTextArea(this, layout, 600, 220, 2, 35);
        txaD = LibraryComponents.LocateAJTextArea(this, layout, 600, 256, 2, 35);
        txaQuestion.setPreferredSize(new Dimension (4,35));
        txaQuestion.setWrapStyleWord(true);
        txaQuestion.setLineWrap(true);
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="JTable Code">
    //---------------------------------------------------------------------------------------------------
    // Source: http://www.dreamincode.net/forums/topic/231112-from-basic-jtable-to-a-jtable-with-a-tablemodel/
    // class that extends the AbstractTableModel
    //---------------------------------------------------------------------------------------------------
    // Code to create the JTable
    public void QuestionTable(SpringLayout myPanelLayout)
    {
        // Create a panel to hold all other components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        add(topPanel);

        // Create column names
        String columnNames[] =
        {
            "#", "Topic", "Question"
        };

        // Create some data
        dataValues = new ArrayList();

        // constructor of JTable model
        wordModel = new MyModel(dataValues, columnNames);

        // Create a new table instance
        table = new JTable(wordModel);

        // Configure some of JTable's paramters
        table.isForegroundSet();
        table.setShowHorizontalLines(false);
        add(table);

        // Change the text and background colours
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionBackground(new Color(255,192,203));

        // Add the table to a scrolling pane, size and locate
        JScrollPane scrollPane = table.createScrollPaneForTable(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(450, 290));
        myPanelLayout.putConstraint(SpringLayout.WEST, topPanel, 10, SpringLayout.WEST, this);
        myPanelLayout.putConstraint(SpringLayout.NORTH, topPanel, 60, SpringLayout.NORTH, this);
//        table.setRowSelectionAllowed(true);
//        table.setCellSelectionEnabled(false);
          table.addMouseListener(this);
//        table.setCellSelectionEnabled(true);
//        table.setColumnSelectionAllowed(false);
//        table.setRowSelectionAllowed(false);
                
    }

    class MyModel extends AbstractTableModel
    {

        ArrayList<Object[]> al;

        // the headers
        String[] header;

        // constructor 
        MyModel(ArrayList<Object[]> obj, String[] header)
        {
            // save the header
            this.header = header;
            // and the data
            al = obj;
        }

        // method that needs to be overload. The row count is the size of the ArrayList
        public int getRowCount()
        {
            return al.size();
        }

        // method that needs to be overload. The column count is the size of our header
        public int getColumnCount()
        {
            return header.length;
        }

        // method that needs to be overload. The object is in the arrayList at rowIndex
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return al.get(rowIndex)[columnIndex];
        }

        // a method to return the column name 
        public String getColumnName(int index)
        {
            return header[index];
        }

        // a method to add a new line to the table
        void add(String jtQuestionID, String jtTopic, String jtQuestion)
        {
            // make it an array[2] as this is the way it is stored in the ArrayList
            // (not best design but we want simplicity)
            String[] str = new String[3];
            str[0] = jtQuestionID;
            str[1] = jtTopic;
            str[2] = jtQuestion;
            al.add(str);
            // inform the GUI that I have change
            fireTableDataChanged();
        }
    }
    
    public void getQuestionData()
    {
         // Get the selected row from the table.
          int selectedRow = table.getSelectedRow();
          // Finally set this to your JTextField.
         txaTopic.setText(dataValues.get(selectedRow)[1].toString());
         txaQuestion.setText(dataValues.get(selectedRow)[2].toString());
         txaA.setText(dataValues.get(selectedRow)[3].toString());
         txaB.setText(dataValues.get(selectedRow)[4].toString());
         txaC.setText(dataValues.get(selectedRow)[5].toString());
         txaD.setText(dataValues.get(selectedRow)[6].toString());
         txfCorrectAnswer.setText(dataValues.get(selectedRow)[7].toString());
         
         

    }
  

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Read Data File">
// used to read the Questions Data File for the program
    private void readDataFile(String fileName)
    {
        try
        {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int i = 0;
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split(",");
                dataValues.add(new Object[]
                {
                    temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]
                });
                dlist.head.append(new LLNode(temp[1], Integer.parseInt(temp[0]), Integer.parseInt(temp[0])));
                theTree.addNode(Integer.parseInt(temp[0]), temp[1]);
                wordModel.fireTableDataChanged();
                i++;
                 
            }
            numberOfEntries = i;
            br.close();
            in.close();
            fstream.close();
            table.repaint();
        } catch (Exception e)
        {
            System.err.println("Error Reading File: " + e.getMessage());
        }

    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Sorts Functions">
    // Used to sort the Question ID Column in the table
    public static void bubbleSort(ArrayList<Object[]> arr)
    {

        for (int j = 0; j < arr.size(); j++)
        {
            for (int i = j + 1; i < arr.size(); i++)
            {
                if ((arr.get(i)[0]).toString().compareToIgnoreCase(arr.get(j)[0].toString()) < 0)
                {
                    Object[] words = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, words);
                }
            }
            System.out.println(arr.get(j)[0] + " - " + arr.get(j)[1]);
        }
    }


// Used to sort the Topics Column in the table
    public static void insertionSort(ArrayList<Object[]> arr)
    {
        int j; // the number of items sorted so far
        Object[] key; // the item to be inserted
        int i;
        for (j = 1; j < arr.size(); j++)
        {
            key = arr.get(j);
            //for (i = j - 1; (i >= 0) && (arr.get(i) < key); i--) // Smaller values are moving up
            for (i = j - 1; (i >= 0) && ((arr.get(i)[1]).toString().compareToIgnoreCase(key[1].toString()) > 0); i--) // Smaller values are moving up
            {
                arr.set((i + 1), arr.get(i));
            }
            arr.set((i + 1), key); // Put the key in its proper location
        }
    }

    // Selection Sort Method for Descending Order
    public static void questionSelectionSort(ArrayList<Object[]> arr)
    {
        int i, j, first;
        Object[] temp;
        for (i = arr.size() - 1; i > 0; i-- )
        {
            first = 0; //initialize to subscript of first element
            for (j = 1; j <= i; j++) //locate smallest element between positions 1 and i.
            {
                if ((arr.get(j)[2]).toString().compareToIgnoreCase(arr.get(first)[2].toString()) > 0)
                {
                    first = j;
                }
            }
            temp = arr.get(first); //swap smallest found with element in position i.
            arr.set((first), arr.get(i));
            arr.set((i), temp);
        }
    }

//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Buttons actionPerformed">
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == btnQNS)
        {
            bubbleSort(dataValues);
            table.repaint();
        }

        if (e.getSource() == btnTopic)
        {
            insertionSort(dataValues);
            table.repaint();
        }

        if (e.getSource() == btnQuestion)
        {
            questionSelectionSort(dataValues);
            table.repaint();
        }

        if (e.getSource() == btnExit)
        {
            
        }

        if (e.getSource() == btnSend)
        {
            
            send();
        }

        if (e.getSource() == btnDisplay)
        {

        }

        if (e.getSource() == btnPrODisplay)
        {
            theTree.traversalString = "";
            txaBinaryTree.append(" ");
            theTree.preorderTraverseTree(theTree.root);
            txaBinaryTree.setText("Pre-Order:  " + theTree.traversalString);
        }

        if (e.getSource() == btnPrOSave)
        {

        }

        if (e.getSource() == btnIODisplay)
        {
            theTree.traversalString = "";
            txaBinaryTree.append(" ");
            theTree.inOrderTraverseTree(theTree.root);
            txaBinaryTree.setText("In Order:  " + theTree.traversalString);
        }

        if (e.getSource() == btnIOSave)
        {

        }

        if (e.getSource() == btnPoODisplay)
        {
            theTree.traversalString = "";
            txaBinaryTree.append(" ");
            theTree.postOrderTraverseTree(theTree.root);
            txaBinaryTree.setText("Post Order:  " + theTree.traversalString);
        }

        if (e.getSource() == btnPoOSave)
        {

        }
        //Exits the program when clicked, does not save anything
        if (e.getSource() == btnExit)
        {
            System.exit(0);
        }
    }

    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Key and Mouse Events">


    public void mouseClicked(MouseEvent e)
    {
        currentEntry = table.getSelectedRow();
        getQuestionData();
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
  
   public void linkedListPrint()
    {                  // print content of list
        if (dlist.head.next == dlist.head)
        {             // list is empty, only header LLNode
            txaLinkedList.setText("list empty");
            return;
        }
        txaLinkedList.setText("list content = ");
        for (LLNode current = dlist.head.next; current != dlist.head.prev; current = current.next)
        {
            txaLinkedList.append(current.qQuestionNumber + " - " + current.qTopic + ",  ");
        }
        txaLinkedList.append("");
    }
   
//<editor-fold defaultstate="collapsed" desc="ChatClient">
   public void connect(String serverName, int serverPort)
    {
        println("Establishing connection. Please wait ...");
        try
        {
            socket = new Socket(serverName, serverPort);
            println("Connected: " + socket);
            open();
        }
        catch (UnknownHostException uhe)
        {
            println("Host unknown: " + uhe.getMessage());
        }
        catch (IOException ioe)
        {
            println("Unexpected exception: " + ioe.getMessage());
        }
    }

    private void send()
    {
        try
        {
            String Topic = txaTopic.getText().toString();
            String Question = txaQuestion.getText().toString();
            String A = txaA.getText().toString();
            String B = txaB.getText().toString();
            String C = txaC.getText().toString();
            String D = txaD.getText().toString();
            streamOut.writeUTF(Topic + "," + Question + "," + A + "," + B + "," + C + "," + D);
 
            streamOut.flush();
        }
        catch (IOException ioe)
        {
            println("Sending error: " + ioe.getMessage());
            close();
        }
    }

    public void handle(String msg)
    {
        if (msg.equals(".bye"))
        {
            println("Good bye. Press EXIT button to exit ...");
            close();
        }
        else
        {
            println(msg);
        }
    }

    public void open()
    {
        try
        {
            streamOut = new DataOutputStream(socket.getOutputStream());
            client = new ChatClientThread1(this, socket);
        }
        catch (IOException ioe)
        {
            println("Error opening output stream: " + ioe);
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
            println("Error closing ...");
        }
        client.close();
        client.stop();
    }

    void println(String msg)
    {
        lblMessage.setText(msg);
    }

    public void getParameters()
    {
        serverName = "localhost";
        serverPort = 4444;        
    }


//</editor-fold>

}
