package FrontEnd;

import Database.DatabaseManager;
import Entities.Programme;
import Handlers.ModuleHandler;
import Handlers.ProgrammeHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SetUpModule extends JFrame implements ActionListener {

    JFrame editTimetable = new JFrame();

    JLabel etBackground = new JLabel();

    JButton etButton = new JButton("Create Module");
    JButton etButton2 = new JButton("Delete Module");
    JButton etButton3 = new JButton("Edit Activity");

    ArrayList<String> moduleList = (ArrayList) DatabaseManager.getModuleList();

    JTextField textField = new JTextField();


    JRadioButton radioButton = new JRadioButton("Compulsory");
    JRadioButton radioButton2 = new JRadioButton("Not Compulsory");

    SetUpModule(Programme programme){

        //System.out.println(programme.getName() + "TE QIVA ROBT");
        Integer programmeId = DatabaseManager.getProgrammeId(programme);

        // Creating Frame
        editTimetable.setTitle("Set up/Manage Module");
        editTimetable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editTimetable.setBounds(0,0, 600, 600);

        ImageIcon img = new ImageIcon("uog.png");
        editTimetable.setIconImage(img.getImage());




        // Creates a background

        etBackground.setBackground(Color.darkGray);
        etBackground.setOpaque(true);
        editTimetable.add(etBackground);




        // Creating text field
        textField.setBounds(120, 120, 280, 25);
        editTimetable.add(textField);



        // List for combo box
        final String[][] pr = {moduleList.toArray(new String[0])};
        // Creating Combo Box
        JComboBox comboBox = new JComboBox(pr[0]);
        comboBox.setBounds(20, 300, 350, 20);
        editTimetable.add(comboBox);



        //Creating Button
        new JButton();
        etButton.setBounds(250, 210, 150, 25); // Create module button
        etButton.setFocusable(false);
        etButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var compulsory = 0;
                if (radioButton.isSelected()){
                    compulsory = 0;
                } else {
                    compulsory = 1;
                }
                var mod = ModuleHandler.createModule(textField.getText(),compulsory, programmeId);
                ModuleHandler.saveModule(mod);
            }
        });


        etButton2.setBounds(375, 450, 180, 25); // Delete Module button
        etButton2.setFocusable(false);
        etButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = comboBox.getSelectedItem().toString();
                ModuleHandler.deleteModule(selected);
            }
        });

        etButton3.setBounds(375, 480, 180, 25);
        etButton3.setFocusable(false);
        etButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==etButton3){
                    Programme pr = programme;
                    String moduleName = comboBox.getSelectedItem().toString();
                    EditActivity ed = new EditActivity(moduleName, pr);
                }
            }
        });

        editTimetable.add(etButton);
        editTimetable.add(etButton2);
        editTimetable.add(etButton3);




        // Creating radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton);
        buttonGroup.add(radioButton2);
        radioButton.setBounds(120, 160, 100, 20); // comp
        radioButton2.setBounds(230, 160, 120, 20); // not comp

        editTimetable.add(radioButton);
        editTimetable.add(radioButton2);







        //Creating Labels
        JLabel etLabel1 = new JLabel("Programme Name:" + "  " + programme.getName() + "             " + programme.getGraduate());
        etLabel1.setBounds(20, 0, 370,80);
        etLabel1.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel1);

        JLabel etLabel2 = new JLabel("Compulsory:");
        etLabel2.setBounds(20, 120, 200,100);
        etLabel2.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel2);

        JLabel etLabel3 = new JLabel("Year:" + programme.getYear());
        etLabel3.setBounds(20, 30, 100,100);
        etLabel3.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel3);

        JLabel etLabel4 = new JLabel("Term:" + programme.getTerm());
        etLabel4.setBounds(150, 30, 100,100);
        etLabel4.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel4);

        JLabel etLabel5 = new JLabel("Module Name:");
        etLabel5.setBounds(20, 120, 120,20);
        etLabel5.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel5);

        JLabel etLabel6 = new JLabel("Select Module:");
        etLabel6.setBounds(20, 280, 120, 20);
        etLabel6.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel6);




        editTimetable.setLayout(null);
        editTimetable.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
