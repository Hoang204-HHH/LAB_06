package lab06.bai1;

public class JunitMessage {
    private String message;

    public JunitMessage(String message) {
        this.message = message;
    }

    /**
     * In message và cố tình gây lỗi chia cho 0 để demo bắt ngoại lệ.
     */
    public void printMessage() {
        System.out.println(message);
        int divide = 1 / 0; // sẽ ném ArithmeticException
        System.out.println(divide); // unreachable
    }

    /**
     * Prefix "Hi!" rồi in/return message.
     */
    public String printHiMessage() {
        message = "Hi!" + message;
        System.out.println(message);
        return message;
    }
}
