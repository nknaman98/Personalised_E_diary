package simplejavatexteditor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Find extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    int startIndex=0;
    int select_start=-1;
    JLabel lab1;
    JTextField textF;
    JButton findBtn, findNext,findMain,cancel;
    private JTextArea txt;

    public Find(JTextArea text) {
        this.txt = text;

        lab1 = new JLabel("Find:");
        textF = new JTextField(30);
        findBtn = new JButton("KMP Find");
        findNext = new JButton("Find Next");
        findMain = new JButton("N Find");
        cancel = new JButton("Cancel");

 
        setLayout(null);

        // Set the width and height of the label
        int labWidth = 80;
        int labHeight = 20;

        // Adding labels
        lab1.setBounds(10,10, labWidth, labHeight);
        add(lab1);
        textF.setBounds(10+labWidth, 10, 120, 20);
        add(textF);
     

        // Adding buttons
        findBtn.setBounds(225, 6, 115, 20);
        add(findBtn);
        findBtn.addActionListener(this);

        findNext.setBounds(225, 50, 115, 20);
        add(findNext);
        findNext.addActionListener(this);

        findMain.setBounds(225, 28, 115, 20);
        add(findMain);
        findMain.addActionListener(this);


        cancel.setBounds(225, 94, 115, 20);
        add(cancel);
        cancel.addActionListener(this);


        // Set the width and height of the window
        int width = 360;
        int height = 160;

        // Set size window
        setSize(width,height);

        // center the frame on the frame
        setLocationRelativeTo(txt);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void find() {
        select_start = txt.getText().toLowerCase().indexOf(textF.getText().toLowerCase());
        if(select_start == -1)
        {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find \"" + textF.getText() + "\"!");
            return;
        }
        if(select_start == txt.getText().toLowerCase().lastIndexOf(textF.getText().toLowerCase()))
        {
            startIndex = 0;
        }
        int select_end = select_start + textF.getText().length();
        txt.select(select_start, select_end);
    }
    public int KMP(String pat, String txt ) {
    	int M = pat.length(); 
        int N = txt.length();
        int lps[] = new int[M]; 
        int j = 0; // index for pat[] 
        computeLPSArray(pat, M, lps);
        int i = 0; // index for txt[] 
        while (i < N) { 
            if (pat.charAt(j) == txt.charAt(i)) { 
                j++; 
                i++; 
            } 
            if (j == M) { 
                return i-j; 
                 
            } 
  
            
            else if (i < N && pat.charAt(j) != txt.charAt(i)) { 
                
                if (j != 0) 
                    j = lps[j - 1]; 
                else
                    i = i + 1; 
            } 
        } 
        return -1;
    }
        void computeLPSArray(String pat, int M, int lps[]) 
        { 
            // length of the previous longest prefix suffix 
            int len = 0; 
            int i = 1; 
            lps[0] = 0; // lps[0] is always 0 
      
            
            while (i < M) { 
                if (pat.charAt(i) == pat.charAt(len)) { 
                    len++; 
                    lps[i] = len; 
                    i++; 
                } 
                else  
                { 
                    
                    if (len != 0) { 
                        len = lps[len - 1]; 

                    
                    } 
                    else  
                    { 
                        lps[i] = len; 
                        i++; 
                    } 
                } 
            } 
        } 
    
    	
    
    public void findKMP() {
    	String pat=textF.getText().toLowerCase();
    	String texxt=txt.getText().toLowerCase();
    	
    	select_start = KMP(pat, texxt);
    
          if(select_start == -1)
        {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find \"" + textF.getText() + "\"!");
            return;
        }
        if(select_start == txt.getText().toLowerCase().lastIndexOf(textF.getText().toLowerCase()))
        {
            startIndex = 0;
        }
        int select_end = select_start + textF.getText().length();
        txt.select(select_start, select_end);
    	
    }

    public void findNext() {
        String selection = txt.getSelectedText();
        try
        {
            selection.equals("");
        }
        catch(NullPointerException e)
        {
            selection = textF.getText();
            try
            {
                selection.equals("");
            }
            catch(NullPointerException e2)
            {
                selection = JOptionPane.showInputDialog("Find:");
                textF.setText(selection);
            }
        }
        try
        {
            int select_start = txt.getText().toLowerCase().indexOf(selection.toLowerCase(), startIndex);
            int select_end = select_start+selection.length();
            txt.select(select_start, select_end);
            startIndex = select_end+1;

            if(select_start == txt.getText().toLowerCase().lastIndexOf(selection.toLowerCase()))
            {
                startIndex = 0;
            }
        }
        catch(NullPointerException e)
        {}
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == findBtn)
        {
           findKMP();
        }
        else if(e.getSource() == findNext)
        {
           findNext();
        }
        else if(e.getSource() == findMain)
        {
           find();
        }
        
        
        
        else if(e.getSource() == cancel)
        {
           this.setVisible(false);
        }
   }

}
