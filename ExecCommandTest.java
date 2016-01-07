
/**
 * Write a description of ExecCommandTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;

public class ExecCommandTest {
    public void getExecOutput() {
        Process p;
        long fileMax = 0L;
        String s;
        try {
            p = Runtime.getRuntime().exec("cat /proc/sys/fs/file-max");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            s = stdInput.readLine();
            fileMax = Long.parseLong(s);
            p.waitFor();
        } catch (Exception e) {}
        System.out.println(fileMax);
    }
    public void runExec() {
        Process p;
        String s;
        long fileMax = 1600000L;
        String[] command = {"echo","160000",">","/proc/sys/fs/file-max"};
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            s = stdInput.readLine();
            System.out.println(s);
            p.waitFor();
        } catch (Exception e) {}
        getExecOutput();
    }
}
