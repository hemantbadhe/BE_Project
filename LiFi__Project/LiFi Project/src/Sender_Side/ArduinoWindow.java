package Sender_Side;

import arduino.Arduino;
import arduino.PortDropdownMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArduinoWindow {
    static Arduino arduino;
    static JFrame frame = new JFrame("LiFi Controller");
    static JButton btnOn = new JButton("ON");
    static JButton btnOff = new JButton("OFF");
    static JButton submit = new JButton("Send");

    static JTextField textField = new JTextField();
    static JTextField receiverField = new JTextField();
    static JButton btnRefresh;



    public static void setUpGUI(){
        frame.setSize(600, 600);
        frame.setResizable(true);
        frame.setBackground(Color.black);
        frame.setForeground(Color.black);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        btnOff.setForeground(Color.RED);
        btnOn.setForeground(Color.GREEN);
        textField.setPreferredSize(new Dimension(200, 40));
        receiverField.setPreferredSize(new Dimension(200, 40));

        JPanel panel = new JPanel();
        panel.add(btnOn);
        panel.add(btnOff);
        panel.add(textField);
        panel.add(submit);
        panel.add(receiverField);
        frame.add(panel, BorderLayout.CENTER);
        populateMenu();
        frame.pack();
        frame.getContentPane();
        frame.setVisible(true);
    }

    public static void populateMenu() { //gets the list of available ports and fills the dropdown menu
        final PortDropdownMenu portList = new PortDropdownMenu();
        portList.refreshMenu();
        final JButton connectButton = new JButton("Connect");


        ImageIcon refresh = new ImageIcon("/home/hemant/Desktop/LiFi Project/refresh.png");
        btnRefresh = new JButton(refresh);

        JPanel topPanel = new JPanel();

        btnRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                portList.refreshMenu();

            }
        });
        topPanel.add(portList);
        topPanel.add(btnRefresh);
        topPanel.add(connectButton);

        connectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectButton.getText().equals("Connect")) {
                    arduino = new Arduino(portList.getSelectedItem().toString(), 9600);
                    if (arduino.openConnection()) {
                        connectButton.setText("Disconnect");
                        portList.setEnabled(false);
                        btnOn.setEnabled(true);
                        btnOff.setEnabled(true);
                        btnRefresh.setEnabled(false);
                        frame.pack();
                    }
                } else {
                    arduino.closeConnection();
                    connectButton.setText("Connect");
                    ;
                    portList.setEnabled(true);
                    btnOn.setEnabled(false);
                    btnRefresh.setEnabled(true);
                    btnOff.setEnabled(false);
                }
            }

        });
        frame.add(topPanel, BorderLayout.NORTH);
    }

}
