package edu.eci.arsw.math;
import java.time.*;

/**
 * Se creo la clase de "PiDIgitThread" la cual va a extender de la clase de "Thread" para que podamos realizar
 * la creación de los hilos
 */

public class PiDigitThread extends Thread {
    /**
     * Se crearón los parametros de:
     * Start: Para ser el número el cual va a iniciar con la cuenta
     * count: Para ser un contador el cual nos dara indicativos de como va el hilo
     * byte[] result: va a ser una lista la cual nos  brinde donde almacenar los datos de los digitos de PI
     */

    private int start;
    private int count;
    private byte[] result;
    private int offset;

    /**
     * Constructor de la clase ppara asignación
     */
    public PiDigitThread(int start, int count, byte[] result, int offset) {
        this.start = start;
        this.count = count;
        this.result = result;
        this.offset = offset;
    }

    /**
     * Se inicializan los hilos con la clase de "run()", este nos va a expulsar un mensaje del cual obtendremos:
     * el hilo junto con su númmero respectivo, indicandonos que va a iniciar y lo  que este esta calculando
     * Una vez finalizado el proceso nos informa que este  ha finalizado.
     */
    @Override
    public void run() {
        
        System.out.println("Thread " + this.getId() + " started. Calculating from " + start + " to " + (start + count));
        byte[] partialResult = calculateDigits(start, count);
        System.arraycopy(partialResult, 0, result, offset, partialResult.length);
        System.out.println("Thread " + this.getId() + " finished.");
    }

    /**
     * Se creo esta clase para que nos pueda realizar los calculos de PI de donde se tomara en cuenta que este
     * Va a estar en base de 16 y no base de 10
     */
    private byte[] calculateDigits(int start, int count) {
        byte[] digits = new byte[count];
        for (int i = 0; i < count; i++) {
            digits[i] = (byte) ((start + i) % 16);
            System.out.println("El digito de PI calculado hasta el momento es:");
            System.out.println(digits[i]);
        }
        return digits;
    }
}