import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 500;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private final JTextComponent log1 = new JTextComponent() {
        @Override
        public void paste() {
            super.paste();
        }
    };
    private boolean isServerWorking;

    public ServerWindow() {
        File file = CreateFile();
        ReadFile(file);
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                System.out.println("Server stopped " + isServerWorking);
                log.append("stopped \n");

            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                System.out.println("Server started " + isServerWorking);
                log.append("started \n");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(3, 1));
        add(log);
        add(btnStart);
        add(log1);

        setVisible(true);
    }

    public File CreateFile() {
        File file;
        try {
            file = new File("D:\\file.txt");
            if (file.createNewFile()) {
                System.out.println("File created");
            } else System.out.println("File already exists");
            return file;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    public void ReadFile(File file){
        try (FileReader reader = new FileReader(file)){
            int c;
            while ((c = reader.read())!= -1){
                System.out.println((char) c);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
