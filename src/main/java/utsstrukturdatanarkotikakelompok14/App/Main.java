package utsstrukturdatanarkotikakelompok14.App;
import utsstrukturdatanarkotikakelompok14.View.AplikasiPutusanGUI;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            new AplikasiPutusanGUI().setVisible(true);
        });
    }
}
