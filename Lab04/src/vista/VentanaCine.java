package vista;

import modelo.FuncionPelicula;
import modelo.ProductoDulceria;
import modelo.TipoFormato;
import modelo.TipoProducto;
import servicio.CineServicio;

import javax.swing.*;
import java.lang.classfile.ClassFile;
import java.util.Optional;

public class VentanaCine extends JFrame {
    private JPanel panelRaiz;
    private JPanel panelEncabezado;
    private JLabel lblTitulo;
    private JTabbedPane tabPrincipal;
    private JPanel TabCartelera;
    private JPanel TabDulceria;
    private JPanel pnlFormPelicula;
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtDuracion;
    private JTextField txtSala;
    private JTextField txtCapacidad;
    private JComboBox cbxFormato;
    private JTextField txtPrecio;
    private JScrollPane pnlTablaCartelera;
    private JTextArea txaCartelera;
    private JPanel pnlBotones;
    private JButton btnRegistrarPeli;
    private JButton btnMostrarCatalogo;
    private JButton btnVenderBoletos;
    private JButton btnAplicarPromocion;
    private JTextField txtCodDulceria;
    private JTextField txtNomDulceria;
    private JComboBox cbxTipoDulceria;
    private JTextField txtPrecioDulceria;
    private JButton btnRegistrarDulceria;
    private JButton btnMostrarCatalogoDulceria;
    private JButton btnVenderDulceria;
    private JButton btnPromoDulceria;
    private JTextArea txaDulceria;

    private final CineServicio cineServicio= new CineServicio();

    public VentanaCine(){
        setTitle("NovaCinemas - Gestion de cine");
        setContentPane(panelRaiz);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,650);

        setLocationRelativeTo(null);

        cbxFormato.setModel(new DefaultComboBoxModel<>(TipoFormato.values()));

        cbxTipoDulceria.setModel(new DefaultComboBoxModel<>(TipoProducto.values()));

        configurarEventos();
    }

    private void configurarEventos(){
        btnRegistrarPeli.addActionListener(e-> {
            try {
                String codigo = txtCodigo.getText().trim();
                String titulo = txtTitulo.getText().trim();
                int duracion = Integer.parseInt(txtDuracion.getText().trim());
                int sala = Integer.parseInt(txtSala.getText().trim());
                int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                TipoFormato formato = (TipoFormato) cbxFormato.getSelectedItem();
                FuncionPelicula pelicula = new FuncionPelicula(codigo, titulo, formato, precio, sala, duracion, capacidad);

                cineServicio.registrarPelicula(pelicula);

                actualizarCartelera();
                limpiarCamposCartelera();
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Revise los campos numericos", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        btnVenderBoletos.addActionListener(e -> {
            String codigo= JOptionPane.showInputDialog(this, "Ingrese el codigo de la funcion");

            if(codigo == null || codigo.isBlank()){
                return;
            }

            Optional<FuncionPelicula> resultado= cineServicio.buscarPelicula(codigo.trim());

            if (resultado.isEmpty()){
                JOptionPane.showMessageDialog(this, "No se encontro una funcion con el codigo" + codigo, "Sin Resultados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            FuncionPelicula pelicula= resultado.get();

            String textoCantidad= JOptionPane.showInputDialog(this, "Pelicula: " + pelicula.getNombre() + "\nButacas Disponibles: " + pelicula.getAsientosDisponibles() + "\n\nCantidad de Boletos: ");

            if (textoCantidad == null || textoCantidad.isBlank()){
                return;
            }

            try{
                int cantidad= Integer.parseInt(textoCantidad.trim());

                Double total= cineServicio.venderBoletos(codigo.trim(), cantidad);

                if (total == null){
                    JOptionPane.showMessageDialog(this, "No se pudo realizar la venta \n" + "Verifique la cantidad y las butacas disponibles.", "Venta no realizada", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(this, String.format("Venta realizada correctamente." + "\n\nPelicula: %s" + "\n\n Boletos: %d" + "\nTotal: $%.2f", pelicula.getNombre(), cantidad, total ), "Taquilla", JOptionPane.INFORMATION_MESSAGE);

                actualizarCartelera();
            } catch (NumberFormatException ex){
               JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRegistrarDulceria.addActionListener(e -> {
            try {
                String codigo= txtCodDulceria.getText().trim();
                String nombre= txtNomDulceria.getText().trim();
                double precio= Double.parseDouble(txtPrecioDulceria.getText().trim());
                TipoProducto tipo= (TipoProducto) cbxTipoDulceria.getSelectedItem();

                ProductoDulceria producto= new ProductoDulceria(codigo, nombre, tipo, precio);

                cineServicio.registrarProducto(producto);

                actualizarDulceria();

                limpiarCamposDulceria();

            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Ingrese un precio valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVenderDulceria.addActionListener(e -> {
            String codigo= JOptionPane.showInputDialog(this, "Ingrese el codigo del producto o combo");

            if (codigo == null || codigo.isBlank()){
                return;
            }

            Optional<ProductoDulceria> resultado= cineServicio.buscarProducto(codigo.trim());

            if (resultado.isEmpty()){
                JOptionPane.showMessageDialog(this, "No se encontro un producto con el codigo:" + codigo, "Sin Resultados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ProductoDulceria producto= resultado.get();
            String textoCantidad= JOptionPane.showInputDialog(this, "producto: " + producto.getNombre() + "\nTipo: " + producto.getCategoria() + "\nPrecio: " + String.format("%.2f", producto.calcularPrecio()) + "\nCantidad: ");

            if (textoCantidad == null || textoCantidad.isBlank()){
                return;
            }

            try{
                int cantidad= Integer.parseInt(textoCantidad.trim());

                Double total= cineServicio.venderProducto(codigo.trim(), cantidad);

                if (total == null){
                    JOptionPane.showMessageDialog(this, "venta realizada.", "Dulceria", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }


            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
