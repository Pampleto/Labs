package Lab03.vista;
import Lab03.modelo.Estudiante;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {

    private JTextField txtCarnet;
    private JTextField txtNombre;
    private JTextField txtCarrera;
    private JButton btnGuardar;
    private JButton btnLimpiar;

   public VentanaRegistro(String carnet, String nombre, String carrera) {
        setTitle("Sistema de Gestión de Estudiantes — UNA");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("Inscripción de Estudiantes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Nunito", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(200, 16, 46));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 8, 8));

        panelFormulario.add(new JLabel("Carnet:"));
        txtCarnet = new JTextField();
        panelFormulario.add(txtCarnet);

        panelFormulario.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        panelFormulario.add(txtCarrera);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        btnLimpiar = new JButton("Limpiar");

        btnGuardar = new JButton("Guardar Estudiante");

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGuardar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {

            String carnet = txtCarnet.getText();
            String nombre = txtNombre.getText();
            String carrera = txtCarrera.getText();
        });

        if (carnet.isEmpty() || nombre.isEmpty() || carrera.isEmpty()) {

            // Muestra una ventana emergente de advertencia (WARNING)
            JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los campos.",
                    "Campos Incompletos",
                    JOptionPane.WARNING_MESSAGE);
        } else {

            // Crea el objeto Estudiante en la capa de modelo con los datos leídos
            Estudiante nuevo = new Estudiante(carnet, nombre,carrera);

            // Muestra una ventana emergente informativa con los datos del registro
            JOptionPane.showMessageDialog(this,
                    "Estudiante registrado con éxito:\n" + nuevo.toString(),
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        }

// Registra un oyente: se ejecuta al hacer clic en 'Limpiar'
        btnLimpiar.addActionListener(e -> {

            // Blanquea la caja del carnet
            txtCarnet.setText("");

            // Blanquea la caja del nombre
            txtNombre.setText("");

            // Blanquea la caja de la carrera
            txtCarrera.setText("");
        });
        this.add(panelPrincipal);

    }
}
