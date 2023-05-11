package tareapoo4;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente implements ActionListener {
    JFrame frame;
    JButton button;

    public Cliente() {
        frame = new JFrame("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Encender");
        button.addActionListener(this);
        button.setActionCommand("encender");

        frame.add(button);
        frame.setBounds(new java.awt.Rectangle(150, 200, 0, 0));
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("encender")) {
            try (Socket socket = new Socket("localhost", 12345)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream outputStream = socket.getOutputStream();

                button.setText("Apagar");
                button.setActionCommand("apagar");

                String response = reader.readLine();
                System.out.println("Servidor: " + response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            button.setText("Encender");
            button.setActionCommand("encender");
        }
    }


}
