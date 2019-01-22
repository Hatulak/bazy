package client;

import javax.persistence.EntityManager;
import javax.swing.*;

public class Main {

    private static EntityManager em;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainClient mainClient = new MainClient();
                mainClient.setVisible(true);
            }
        });
    }
}
