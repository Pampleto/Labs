import Lab03.vista.VentanaRegistro;

import javax.swing.*;
public class Main1 {
void main() {
    SwingUtilities.invokeLater(() -> {
        VentanaRegistro ventana = new VentanaRegistro();
        ventana.setVisible(true);
    });
}}