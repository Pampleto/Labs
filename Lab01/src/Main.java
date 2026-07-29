import java.util.ArrayList;
 public class Main(){
public static double calcularPromedio(ArrayList<Estudiante> lista) {
    if (lista.isEmpty()){
        return 0.0;
    }

    double suma=0.0;

    for (Estudiante estudiante: lista){
        suma += estudiante.getNota();
    }

    return suma/lista.size();
}

public static void main() {
    ArrayList<Estudiante> estudiantes = new ArrayList<>();
    estudiantes.add(new Estudiante("Daniel",20,87.5));

    double promedio= calcularPromedio(estudiantes);
    System.out.println("El promedio es:" + promedio);
    System.out.println("");
    for(Estudiante e: estudiantes){

    }
}
 }
