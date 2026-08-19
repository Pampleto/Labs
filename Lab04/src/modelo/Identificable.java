package modelo;

public interface Identificable<k> {
    String getCodigo();
    String getNombre();
    k getCategoria();
    double calcularPrecio();
    String getDetalle();
}
