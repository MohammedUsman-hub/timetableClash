package FrontEnd;

import Database.DatabaseManager;
import Entities.Activity;
import Entities.Programme;
import Handlers.ModuleHandler;
import Handlers.ProgrammeHandler;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static Database.DatabaseManager.*;


public class GUI extends JFrame implements ActionListener {

    JButton button = new JButton("Set up/Manage Module"); // Creates button and gives it a name
    JButton button2 = new JButton("Set up/Manage Programmes");
    JButton buttonReload = new JButton("Reload Page");
    JButton button3 = new JButton("Display Timetable");
    JButton reloadPage = new JButton("Reload GUI");
    JButton checkClash = new JButton("Check for Clashes");

    JFrame frame = new JFrame(); // Creating frame



    JLabel label =  new JLabel();
    JPanel panel = new JPanel(); // Creating JPanel

    ArrayList<String> programmeList = (ArrayList) DatabaseManager.getProgrammeList();
    private Module m;
    private ModuleHandler handler = new ModuleHandler();
    private Programme programme;


    JComboBox combo1;
    JComboBox combo2;

    JRadioButton undergraduateRadioButton = new JRadioButton("Undergraduate");
    JRadioButton postgraduateRadioButton = new JRadioButton("Postgraduate");

    JRadioButton term1 = new JRadioButton("Term 1");
    JRadioButton term2 = new JRadioButton("Term 2");

    JTable table;


    public GUI() {

        // Creates frame, frame title, and option to exit frame via 'X' button
        frame.setTitle("University Of Greenwich Timetable");
        frame.setBounds(0, 0, 950, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Changing and setting Frame Icon
        ImageIcon img = new ImageIcon("uog.png");
        frame.setIconImage(img.getImage());



        //System.out.println(programmeList + "TEST");

        //Create list for combo box
        String [] pr = programmeList.toArray(new String[0]);

        //SwingUtilities.updateComponentTreeUI(frame); //Reloads frame



        // Creating Combo box
        ButtonGroup ButtonGroup0 = new ButtonGroup();
        ButtonGroup0.add(undergraduateRadioButton);
        ButtonGroup0.add(postgraduateRadioButton);
        undergraduateRadioButton.setSelected(true);

        undergraduateRadioButton.setBounds(250, 25, 120, 20);
        postgraduateRadioButton.setBounds(120, 25, 120, 20);

        frame.add(undergraduateRadioButton);
        frame.add(postgraduateRadioButton);

        String[] years = {"Year 1", "Year 2", "Year 3"};


        combo1 = new JComboBox(pr);
        combo1.setBounds(550, 22, 140, 25);

        combo2 = new JComboBox(years);
        combo2.setBounds(850, 22, 110, 25);


        frame.add(combo1);
        frame.add(combo2);






        // Creating Radio Buttons
        ButtonGroup ButtonGroup = new ButtonGroup();
        ButtonGroup.add(term1);
        ButtonGroup.add(term2);
        term1.setSelected(true);

        term1.setBounds(1100, 25, 70, 20);
        term2.setBounds(1200, 25, 70, 20);

        frame.add(term1);
        frame.add(term2);



        // Creating a button and setting its size
        new JButton();
        button.setBounds(700, 620, 170, 25);
        button.setFocusable(false);
        button.addActionListener(this);


        button2.setBounds(350, 620, 200, 25);
        button2.setFocusable(false);
        button2.addActionListener(this);

        reloadPage.setBounds(200, 90, 150, 20);
        reloadPage.setFocusable(false);
        reloadPage.addActionListener(this);

        checkClash.setBounds(450, 90, 150, 20);
        checkClash.setFocusable(false);
        checkClash.addActionListener(this);


        // Adding button to frame
        frame.add(button);
        frame.add(button2);
        frame.add(reloadPage);
        frame.add(buttonReload);
        frame.add(checkClash);





        // Adding text
        JLabel Textlabel = new JLabel("Grad Type:"); // Creating JLabel
        Textlabel.setForeground(new Color(77, 195, 255)); // Student Timetable
        Textlabel.setBounds(30, 25, 100, 20);


        JLabel TextLabel2 = new JLabel("Term:");
        TextLabel2.setForeground(new Color(77, 195, 255)); // Term
        TextLabel2.setBounds(1050, 10, 50, 50);


        JLabel TextLabel3 = new JLabel("Programme:");
        TextLabel3.setForeground(new Color(77, 195, 255)); // Programme
        TextLabel3.setBounds(450, 25, 100, 20);


        JLabel TextLabel4 = new JLabel("Select Year:");
        TextLabel4.setForeground(new Color(77, 195, 255)); // Year
        TextLabel4.setBounds(760, 25, 80, 20);



        // Table labels
        JLabel tableLabel = new JLabel("Module Name");
        tableLabel.setBounds(395, 140, 100, 100);
        tableLabel.setForeground(new Color(77, 195, 255));
        frame.add(tableLabel);

        JLabel tableLabel1 = new JLabel("Day");
        tableLabel1.setBounds(500, 140, 50, 100);
        tableLabel1.setForeground(new Color(77, 195, 255));
        frame.add(tableLabel1);

        JLabel tableLabel2 = new JLabel("Activity");
        tableLabel2.setBounds(553, 140, 100, 100);
        tableLabel2.setForeground(new Color(77, 195, 255));
        frame.add(tableLabel2);

        JLabel tableLabel3 = new JLabel("Time");
        tableLabel3.setBounds(640, 140, 80, 100);
        tableLabel3.setForeground(new Color(77, 195, 255));
        frame.add(tableLabel3);

        Textlabel.setLayout(null);
        frame.add(Textlabel);   // Displays label values to the frame.
        frame.add(TextLabel2);
        frame.add(TextLabel3);
        frame.add(TextLabel4);





        //Old static data
        /*

        String [] cols = {"Module Name", "Day", "Activity", "Time"};

        Object [][] data = {

                {"JVM", "Monday", "Lecture", "9-10"},
                {"JVM", "Monday", "LAB","10-11"},
                {"Maths", "Tuesday", "Lecture", "13-14"},
                {"Maths", "Tuesday", "Tutorial", "14-15"}
        };

         */



        //Button
        button3.setBounds(30, 90, 150, 20); // Display Timetable
        button3.setFocusable(false);
        button3.addActionListener(this);
        frame.add(button3);





        // Creates a background color
        label.setBackground(Color.DARK_GRAY);
        label.setOpaque(true);
        frame.add(label);   // Displays label values to the frame.


        frame.setVisible(true); // Opens GUI



    }

    public static void main(String[] args) {

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reloadPage){
            GUI guiReload = new GUI();
            frame.dispose();
        }
        if(e.getSource() == button){
            //Getting the programme values from the gui user inputs
            String name = combo1.getSelectedItem().toString();
            Integer year = combo2.getSelectedIndex()+1;
            String graduate = "";
            Integer term = 0;

            if (undergraduateRadioButton.isSelected()) graduate = "undergraduate";
            else graduate = "postgraduate";
            if (term1.isSelected()) term = 1;
            else term = 2;

            programme = new Programme(graduate, name, year, term);

            SetUpModule window1 = new SetUpModule(programme);
        }

        if(e.getSource() == button2){
            SetUpProgrammes sup = new SetUpProgrammes();
        }

        if(e.getSource() == button3){
            //Getting the programme values from the gui user inputs
            String name = combo1.getSelectedItem().toString();
            Integer year = combo2.getSelectedIndex()+1;
            String graduate = "";
            Integer term = 0;

            if (undergraduateRadioButton.isSelected()) graduate = "undergraduate";
            else graduate = "postgraduate";
            if (term1.isSelected()) term = 1;
            else term = 2;

            programme = new Programme(graduate, name, year, term);
            Integer programme_id = getProgrammeId(programme);
            List<String> moduleList = getModuleListWhereProgrammeId(programme_id);

            String [] cols = {"Module Name", "Day", "Activity", "Time"};
            Object [][] data = {
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
                    {"", "", "",""},
            };

            for (int i=0; i < moduleList.size(); i++){

                Activity act = getActivityFromModuleName(moduleList.get(i));
                String day = "";
                String time = "";
                switch (act.getDay()){
                    case 0:
                        day = "Monday";
                        break;
                    case 1:
                        day = "Tuesday";
                        break;
                    case 2:
                        day = "Wednesday";
                        break;
                    case 3:
                        day = "Thursday";
                        break;
                    case 4:
                        day = "Friday";
                        break;
                }
                switch (act.getTime()){
                    case 0:
                        time = "09-10";
                        break;
                    case 1:
                        time = "10-11";
                        break;
                    case 2:
                        time = "11-12";
                        break;
                    case 3:
                        time = "12-13";
                        break;
                    case 4:
                        time = "13-14";
                        break;
                    case 5:
                        time = "14-15";
                        break;
                    case 6:
                        time = "15-16";
                        break;
                    case 7:
                        time = "16-17";
                        break;
                    case 8:
                        time = "17-18";
                        break;
                    case 9:
                        time = "18-19";
                        break;
                    case 10:
                        time = "19-20";
                        break;
                    case 11:
                        time = "20-21";
                        break;
                }

                data[i][0] = act.getModulename().toString();
                data[i][1] = day;
                data[i][2] = act.getType();
                data[i][3] = time;

            }
            //Create table
            table = new JTable(data, cols);
            //table.setPreferredScrollableViewportSize(new Dimension(1000, 150));
            //table.setFillsViewportHeight(true);

            table.setShowHorizontalLines(false);
            //  table.changeSelection(0,0, true, false);
            table.setBounds(400, 200, 1000, 150);

            // Centering all cells
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
            DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
            DefaultTableCellRenderer centerRenderer3 = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );

            JScrollPane scrollPane = new JScrollPane(table);
            //table.setVisible(false);
            frame.add(table);
            frame.add(scrollPane);
            table.selectAll();
            table.setVisible(true);
        }






    }
}




