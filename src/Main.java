import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new VoteMachine ("localhost", 9888).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}