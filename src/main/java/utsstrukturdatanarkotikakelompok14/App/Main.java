package utsstrukturdatanarkotikakelompok14.App;
import gui.AplikasiPutusanGUI;
import javax.swing.*;

public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ignored) {}

    SwingUtilities.invokeLater(() -> {
        new AplikasiPutusanGUI().setVisible(true);
    });
}
