package tareapoo4;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    JFrame frame;
    JLabel blinker;

    public Servidor() {
        frame = new JFrame("Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        blinker = new JLabel();
        blinker.setPreferredSize(new Dimension(100, 100));
        blinker.setOpaque(true);
        blinker.setBackground(Color.WHITE);

        frame.add(blinker);
        frame.setBounds(new java.awt.Rectangle(0, 200, 0, 0));
        frame.pack();
        frame.setVisible(true);

        blink();
    }

    private void blink() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream outputStream = socket.getOutputStream();

                blinker.setBackground(Color.YELLOW);
                outputStream.write("Encendido\n".getBytes());
                outputStream.flush();

                Thread.sleep(500);

                blinker.setBackground(Color.WHITE);
                outputStream.write("Apagado\n".getBytes());
                outputStream.flush();

                socket.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
