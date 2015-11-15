package main;

import java.util.List;

import main.channel.Channel;
import main.channel.Error;
import main.channel.ErrorGenerator;
import main.channel.Message;
import main.crc.CRC;

public class Main {

    public static final String INFORMATION_VECTOR = "11111010011";
    public static final String POLYNOMIAL = "x4+x+1";

    public static void main(String[] args) {
        Message message = new Message(INFORMATION_VECTOR);
        CRC crc = new CRC(POLYNOMIAL);

        Message encoded = crc.encode(message);

        // Длина закодированного сообщения
        int encodedMessageSize = message.length() + crc.getPower();

        // Считаем для каждой кратности ошибок количество
        // исправленных сообщений
        for(int i = 1; i < encodedMessageSize + 1; ++i) {

            // Число исправленных ошибок
            int correctedErrorCount = 0;

            // Кратность ошибки
            int errorMultiplicity = i;

            // Количество вариантов ошибок данной кратности
            int errorCount = (int) (factorial(encodedMessageSize) /
                                (factorial(encodedMessageSize - errorMultiplicity) * factorial(errorMultiplicity)));

            // Генерируем все возможные ошибки заданной кратности
            List<Error> errors = ErrorGenerator.generate(encodedMessageSize, errorMultiplicity);

            // Пытаемся отправлять сообщения по каналу связи
            // с разными ошибками и проверяем результат передачи
            for(Error error : errors) {
                Message response = Channel.transmit(encoded, error);
                Message decoded = crc.decode(response);

                // Если принятое сообщение равно отправленному,
                // ошибки исправить удалось
                if (message.equals(decoded)) {
                    correctedErrorCount++;
                }
            }

            float correctionCoefficient = correctedErrorCount / errors.size();

            System.out.println(
                            errorMultiplicity + " " +
                            errorCount + " " +
                            correctedErrorCount + " " +
                            correctionCoefficient
            );
        }
    }

    public static long factorial(long n) {
        long fact = 1;
        for (long i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}