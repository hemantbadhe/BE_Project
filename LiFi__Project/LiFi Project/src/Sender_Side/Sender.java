package Sender_Side;

import arduino.Arduino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Sender_Side.ArduinoWindow.*;

public class Sender {



    public static void main(String[] args) {

        ArduinoWindow a = new ArduinoWindow();
        Arduino arduino = new Arduino();
        TextToBinary textToBinary = new TextToBinary();
        a.setUpGUI();

        btnOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arduino.serialWrite('1');

            }
        });
        btnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arduino.serialWrite('0');
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                System.out.println(message); //Display Message
                textToBinary.textToBinary(message);
                textField.setText("");
               // System.out.println("Binary is: "+binary);//Display Binary on console
            }
        });
    }


}


