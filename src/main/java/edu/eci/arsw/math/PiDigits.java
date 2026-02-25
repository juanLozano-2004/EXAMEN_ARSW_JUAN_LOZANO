package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.List;

public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return.
     * @param N The number of threads to use.
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int N) {
        if (start < 0 || count < 0 || N <= 0) {
            throw new RuntimeException("Parametros invalidos");
        }

        byte[] digits = new byte[count];
        int chunkSize = (int) Math.ceil((double) count / N);

        List<PiDigitThread> threads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int StartThread = start + i * chunkSize;
            int EndThread = Math.min(StartThread + chunkSize, start + count);
            if (StartThread >= EndThread) break;

            PiDigitThread thread = new PiDigitThread(StartThread, EndThread - StartThread, digits, StartThread - start);
            threads.add(thread);
            thread.start();
        }

        for (PiDigitThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return digits;
    }
}
