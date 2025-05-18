import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FlyWorldLauncher {
    public static void main(String[] args) {
        // Always start GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame launcherFrame = new JFrame("Fly Away Home Launcher");
            launcherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            launcherFrame.setSize(300, 150);
            launcherFrame.setLocationRelativeTo(null);

            JButton startButton = new JButton("Start Game");
            startButton.setFont(new Font("Arial", Font.BOLD, 18));
            startButton.addActionListener((ActionEvent e) -> {
                String defaultWorldFile = "world0.txt";

                JFrame gameFrame = new JFrame("Fly Away Home");
                FlyWorldGUI gamePanel = new FlyWorldGUI(defaultWorldFile);

                gameFrame.getContentPane().add(gamePanel);
                gamePanel.setFocusable(true);
                gamePanel.requestFocusInWindow();

                gameFrame.setSize(600, 600);
                gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
            });

            launcherFrame.getContentPane().setLayout(new GridBagLayout());
            launcherFrame.getContentPane().add(startButton);
            launcherFrame.setVisible(true);
        });
    }
}
