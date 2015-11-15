package main.channel;

import main.BitArray;

/**
 * Сообщение
 */
public class Message extends BitArray {
    public Message(String str) {
        super(str);
    }

    public Message(boolean[] vector) {
        super(vector);
    }
}
