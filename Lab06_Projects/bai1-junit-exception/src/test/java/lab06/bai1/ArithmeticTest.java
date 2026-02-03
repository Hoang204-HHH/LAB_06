package lab06.bai1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticTest {

    @Test(expected = ArithmeticException.class)
    public void testPrintMessage_shouldThrowArithmeticException() {
        String message = "Fpoly exception";
        JunitMessage junitMessage = new JunitMessage(message);
        junitMessage.printMessage();
    }

    @Test
    public void testPrintHiMessage_shouldPrefixHi() {
        String message = "Fpoly exception";
        JunitMessage junitMessage = new JunitMessage(message);
        assertEquals("Hi!" + message, junitMessage.printHiMessage());
    }

    @Test
    public void testPrintHiMessage_calledTwice_shouldPrefixTwice() {
        JunitMessage junitMessage = new JunitMessage("X");
        assertEquals("Hi!X", junitMessage.printHiMessage());
        assertEquals("Hi!Hi!X", junitMessage.printHiMessage());
    }
}
