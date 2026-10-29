package main;

import javax.swing.SwingUtilities;
import login.LoginGUI; 
    
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
}
