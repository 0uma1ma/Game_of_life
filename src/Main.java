import com.sun.javafx.application.PlatformImpl;

public class Main {
    public static void main(String[] args){
        PlatformImpl.startup(() -> {
            new Frame();
        });
    }
}