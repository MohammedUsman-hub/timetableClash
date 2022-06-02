package FrontEnd;

import Database.DatabaseManager;
import Entities.Programme;
import Handlers.ProgrammeHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class SetUpProgrammes extends JFrame implements ActionListener {

    private ProgrammeHandler handler = new ProgrammeHandler();
    private Programme pro;

    JFrame editTimetable = new JFrame();

    JPanel etBackground = new JPanel();

    JButton Button = new JButton("Create Programme");
    JButton Button2 = new JButton("Delete");


    JRadioButton post = new JRadioButton("Postgraduate");
    JRadioButton under = new JRadioButton("Undergraduate");

    ArrayList<String> programmeList = (ArrayList) DatabaseManager.getProgrammeList();


    JTextField textField = new JTextField();
    // JTextField textField2 = new JTextField();

    JTable table;


    SetUpProgrammes(){

        // Creating Frame
        editTimetable.setTitle("Set up/Manage Programme");
        editTimetable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editTimetable.setBounds(0,0, 600, 600);

        ImageIcon img = new ImageIcon("uog.png");
        editTimetable.setIconImage(img.getImage());


        //List for combo box
        String [] pr = programmeList.toArray(new String[0]);

        // Creating combo box
        JComboBox comboBox = new JComboBox(pr);
        comboBox.setBounds(20, 300, 330, 30);
        editTimetable.add(comboBox);



        //Creating text field
        new JTextField();
        textField.setBounds(150, 15, 150, 30);
        editTimetable.add(textField);


        // Creating radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(under);
        buttonGroup.add(post);

        under.setBounds(150, 60, 120, 30);
        post.setBounds(270, 60, 150, 30);

        editTimetable.add(under);
        editTimetable.add(post);


        //Creating Button
        new JButton();
        Button.setBounds(10, 100, 150, 25); // Create Programme
        Button.setFocusable(false);

        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var graduate = "";
                if (under.isSelected()){
                    graduate = "undergraduate";
                } else {
                    graduate = "postgraduate";
                }
                var pro = ProgrammeHandler.createProgramme(textField.getText(),0,0,graduate);
                ProgrammeHandler.saveProgramme(pro);
            }
        });

        Button2.setBounds(380, 330, 100, 25); // Delete button
        Button2.setFocusable(false);
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = comboBox.getSelectedItem().toString();
                ProgrammeHandler.deleteProgramme(selected);
            }
        });




        editTimetable.add(Button);
        editTimetable.add(Button2);




        //Creating Labels
        JLabel etLabel1 = new JLabel("Programme Name:");
        etLabel1.setBounds(5, 0, 200,50);
        etLabel1.setForeground(new Color(77, 195, 255));
        etLabel1.setFont(new Font("My Boli", Font.PLAIN,20));
        editTimetable.add(etLabel1);

        JLabel etLabel2 = new JLabel("Programme Type:");
        etLabel2.setBounds(5, 50, 130,50);
        etLabel1.setFont(new Font("My Boli", Font.PLAIN,15));
        etLabel2.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel2);

        JLabel etLabel3 = new JLabel("Select Programme:");
        etLabel3.setBounds(20, 280, 120, 20);
        etLabel3.setForeground(new Color(77, 195, 255));
        editTimetable.add(etLabel3);




        // Creates a background
        etBackground.setBackground(Color.red);
        etBackground.setOpaque(true);
        editTimetable.add(etBackground);


        editTimetable.setLayout(null);
        editTimetable.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
