import Lab03.vista.VentanaRegistro;

import javax.swing.*;

void main() {
class Main1 {
    void main(String carnet, String nombre, String carrera) {
        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventana = new VentanaRegistro(carnet, nombre, carrera);
            ventana.setVisible(true);
        });
    }}
}