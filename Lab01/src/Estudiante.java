public class Estudiante {

       private String nombre;
       private int edad;
       private double nota;
       private static int totalEstudiantes=0;

    public Estudiante(String n,int e,double no) {
       this.nombre=n;
        this.edad=e;
        this.nota=no;

        totalEstudiantes++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getNota() {
        return nota;
    }

    public static int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    @Override
    public String toString(){
        return "Estudiante: "+nombre +
    }

}
