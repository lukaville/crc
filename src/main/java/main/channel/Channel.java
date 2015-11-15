package main.channel;

import java.util.Arrays;

/**
 * Модель канала передачи данных
 */
public class Channel {
    public static Message transmit(Message message, Error errors) {
        if (message.length() != errors.length()) {
            throw new IllegalArgumentException("Message and error vector must be the same length");
        }

        boolean[] receivedMessage = Arrays.copyOf(message.getVector(), message.length());
        for(int i = 0; i < message.length(); ++i) {
            receivedMessage[i] ^= errors.getVector()[i];
        }

        return new Message(receivedMessage);
    }
}
