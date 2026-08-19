package modelo;

public class ProductoDulceria extends ElementoComercial<TipoProducto>{
    public ProductoDulceria(String codigo, String nombre, TipoProducto categoria, double precioBase) {
        super(codigo, nombre, categoria, precioBase);
    }

    @Override
    public double calcularPrecio(){
        if (categoria==TipoProducto.Combo){
            return precioBase*0.90;
        }
        return precioBase;
    }

    @Override
    public String getDetalle(){
        return String.format("codigo: %s\n Producto: %s\n Tipo: %s\n Precio: %.2f", codigo, nombre, categoria, calcularPrecio());
    }
}
