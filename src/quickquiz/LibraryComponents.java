/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
/**
 *
 * @author student
 */
public class LibraryComponents 
{

    
    public static JLabel LocateAJLabel(JFrame myJFrame, SpringLayout myJLabelLayout, String JLabelCaption, int x, int y, int F,  int rF, int gF, int bF, int rB, int gB, int bB)
    {
        JLabel myJLabel = new JLabel(JLabelCaption);
        myJFrame.add(myJLabel); 
        myJLabelLayout.putConstraint(SpringLayout.WEST, myJLabel, x, SpringLayout.WEST, myJFrame);
        myJLabelLayout.putConstraint(SpringLayout.NORTH, myJLabel, y, SpringLayout.NORTH, myJFrame);
        myJLabel.setFont(new Font ("Century Gothic", Font.PLAIN, F));
        myJLabel.setForeground(new Color (rF, gF, bF));
        myJLabel.setOpaque(true);
        myJLabel.setBackground(new Color (rB, gB, bB));
        return myJLabel;
    }
   
    
    public static JTextField LocateAJTextField(JFrame myJFrame, SpringLayout myJTextFieldLayout, int width, int x, int y)
    {
        JTextField myJTextField = new JTextField(width);
        myJFrame.add(myJTextField); 
        myJTextFieldLayout.putConstraint(SpringLayout.WEST, myJTextField, x, SpringLayout.WEST, myJFrame);
        myJTextFieldLayout.putConstraint(SpringLayout.NORTH, myJTextField, y, SpringLayout.NORTH, myJFrame);
        return myJTextField;
    }
    
    
    public static JButton LocateAJButton(JFrame myJFrame, ActionListener myActnLstnr, SpringLayout myJButtonLayout, String  JButtonCaption, int x, int y, int w, int h)
    {    
        JButton myJButton = new JButton(JButtonCaption);
        myJFrame.add(myJButton);
        myJButton.addActionListener(myActnLstnr);
        myJButtonLayout.putConstraint(SpringLayout.WEST, myJButton, x, SpringLayout.WEST, myJFrame);
        myJButtonLayout.putConstraint(SpringLayout.NORTH, myJButton, y, SpringLayout.NORTH, myJFrame);
        myJButton.setPreferredSize(new Dimension(w,h));
        myJButton.setFont(new Font ("Century Gothic", Font.PLAIN, 12));
        return myJButton;
    }
    
    
    public static JTextArea LocateAJTextArea(JFrame myJFrame, SpringLayout myLayout, int x, int y, int w, int h)
    {    
        JTextArea myJTextArea = new JTextArea(w,h);
        myJFrame.add(myJTextArea);
        myLayout.putConstraint(SpringLayout.WEST, myJTextArea, x, SpringLayout.WEST, myJFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myJTextArea, y, SpringLayout.NORTH, myJFrame);
        myJTextArea.setWrapStyleWord(true);
        myJTextArea.setEditable(false);
        myJTextArea.setLineWrap(true);
        return myJTextArea;
    }
    
    public static JTable LocateAJTable(JFrame myJFrame, SpringLayout myLayout, int x, int y, int w, int h)
    {
        JTable myJTable = new JTable(w,h);
        myJFrame.add(myJTable);
        myLayout.putConstraint(SpringLayout.WEST, myJTable, x, SpringLayout.WEST, myJFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myJTable, y, SpringLayout.NORTH, myJFrame);
        return myJTable;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
