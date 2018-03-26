package Sender_Side;

import static Sender_Side.ArduinoWindow.arduino;

public class TextToBinary {

    static void textToBinary(String message) {
        byte[] bytes = message.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }

        arduino.serialWrite(binary.toString(), binary.length(), 1350);
        System.out.println(binary);
    }
}
