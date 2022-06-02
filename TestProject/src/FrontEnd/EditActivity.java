package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Clash_Detection.KotlinClash;
import Database.DatabaseManager;
import Entities.Programme;
import Entities.Module;
import Handlers.ActivityHandler;
import Handlers.ProgrammeHandler;


public class EditActivity extends JFrame implements ActionListener {

    JFrame frame = new JFrame();

    JButton button = new JButton("Create Activity");
    JButton button2 = new JButton("Delete Activity");

    ArrayList<String> ActivityList = (ArrayList) DatabaseManager.getActivityList();

    EditActivity(String moduleName, Programme programme){
        frame.setTitle("Edit Activity");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0,0, 600, 600);

        ImageIcon img = new ImageIcon("uog.png");
        frame.setIconImage(img.getImage());


        Module module = new Module(DatabaseManager.getModuleFromName(moduleName));
        String modulename = module.getName();



        //List for combo box
        String [] list = {"Lecture", "LAB", "Seminar", "Workshop", "Tutorial"};
        String [] list2 = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String [] list3 = {"9 - 10", "10 - 11", "11 - 12", "12 - 13", "13 - 14", "14 - 15", "15 - 16",
                "16 - 17", "17 - 18", "18 - 19", "19 - 20", "20 - 21"};
        //Creating Combo box
        JComboBox comboBox = new JComboBox(list);
        comboBox.setBounds(230, 180, 100, 30);
        frame.add(comboBox);

        JComboBox comboBox1 = new JComboBox(list2);
        comboBox1.setBounds(230, 220, 100, 30);
        frame.add(comboBox1);


        JComboBox comboBox2 = new JComboBox(list3);
        comboBox2.setBounds(230, 260, 100, 30);
        frame.add(comboBox2);











        // String Activity list
        String[] pr = ActivityList.toArray(new String[0]);

        // Creating combobox for activity list
        JComboBox comboBox12 = new JComboBox(pr);
        comboBox12.setBounds(130, 400, 200, 30);
        frame.add(comboBox12);


        // Creating Buttons
        new JButton();
        button.setFocusable(false);//create activity
        button.setBounds(400, 225, 130, 20);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var type = comboBox.getSelectedItem().toString();
                var day = comboBox1.getSelectedIndex();
                var time = comboBox2.getSelectedIndex();
                String modname = moduleName;
                var act = ActivityHandler.createActivity(modname, type, day, time);
                /*
                if (KotlinClash.checkClash(act) == true) {
                    JOptionPane.showMessageDialog(null, "There is a clash with the created Activity", "ERROR" ,JOptionPane.WARNING_MESSAGE);
                } else */
                ActivityHandler.saveActivity(act);


            }

        });

        button2.setFocusable(false);//delete activity
        button2.setBounds(400, 400, 130, 20);
        frame.add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = comboBox12.getSelectedItem().toString();
                ActivityHandler.deleteActivity(selected);
            }
        });

        String compulsoryString = "";

        if (module.getCompulsory() == 0) compulsoryString = "Yes";
        else compulsoryString = "No";




        //Creating Label
        JLabel label90 = new JLabel("Programme Name: " + programme.getName() +
                " | Year: " + programme.getYear() +
                " | Term: " + programme.getTerm() +
                " | Module Name: " + moduleName +
                " | Compulsory: "  + compulsoryString );
        label90.setBounds(20, 0, 1000, 25);
        label90.setForeground(new Color(77, 195, 255));
        label90.setFont(new Font("My Boli", Font.PLAIN,15));
        frame.add(label90);




        JLabel label = new JLabel("Activity 1:");
        label.setBounds(130, 155, 100, 25);
        label.setForeground(new Color(77, 195, 255));
        frame.add(label);

        JLabel label1 = new JLabel("Activity Type:");
        label1.setBounds(130, 180, 100, 25);
        label1.setForeground(new Color(77, 195, 255));
        frame.add(label1);

        JLabel label2 = new JLabel("Activity Day:");
        label2.setBounds(130, 220, 100, 25);
        label2.setForeground(new Color(77, 195, 255));
        frame.add(label2);

        JLabel label3 = new JLabel("Activity Time:");
        label3.setBounds(130, 260, 100, 25);
        label3.setForeground(new Color(77, 195, 255));
        frame.add(label3);



        JLabel label15 = new JLabel("Select Module Activity:");
        label15.setBounds(130, 370, 140, 25);
        label15.setForeground(new Color(77, 195, 255));
        frame.add(label15);

        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
