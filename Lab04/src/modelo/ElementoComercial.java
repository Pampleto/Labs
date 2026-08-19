package modelo;

public abstract class ElementoComercial<k> implements Identificable<k> {
    protected String codigo;
    protected String nombre;
    protected double precioBase;
    protected k categoria;

    public ElementoComercial(String codigo, String nombre, k categoria, double precioBase){
        this.codigo=codigo;
        this.nombre=nombre;
        this.categoria=categoria;
        this.precioBase=precioBase;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public k getCategoria() {
        return categoria;
    }


}
