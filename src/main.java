import java.io.IOException;

/**
 * Created by teddy on 10/17/17.
 */
public class main {

    public static void main(String[] args) {
       // String filename = main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
      //  System.out.println(filename);
        try {
            Runtime.getRuntime().exec(new String[]{"terminal"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
