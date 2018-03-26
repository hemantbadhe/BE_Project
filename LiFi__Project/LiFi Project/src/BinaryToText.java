


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

class sBinaryToText {
    public static String toText(String input) {

        StringBuilder sb = new StringBuilder(); // Some place to store the chars

        Arrays.stream( // Create a Stream
                input.split("(?<=\\G.{8})") // Splits the input string into 8-char-sections (Since a char has 8 bits = 1 byte)
        ).forEach(s -> // Go through each 8-char-section...
                sb.append((char) Integer.parseInt(s, 2)) // ...and turn it into an int and then to a char
        );

        String output = sb.toString(); // Output text (t)
        System.out.println(output);
        return output;

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String a = "01001000011001010110110001101100011011110010000001010111011011110111001001101100011001000010111000101110001011100010111000100001";
        toText(a);

    }
}