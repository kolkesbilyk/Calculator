package sample;

public class SingActionManager {
    private static SingAction INSTANCE;

    public static SingAction getInstance(Sign sign) {
        switch (sign.getSign()) {
            case "+" -> {
                INSTANCE = new Addition();
                break;
            }
            case "-" -> {
                INSTANCE = new Subtraction();
                break;
            }
            case "*" -> {
                INSTANCE = new Multiplication();
                break;
            }
            case "/" -> {
                INSTANCE = new Division();
                break;
            }
        }
        return INSTANCE;
    }
}
