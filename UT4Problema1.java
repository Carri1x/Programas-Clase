package ut4problema1;

import java.util.Scanner;

/**
 *
 * @author ÄLVARO CARRIÓN ROMERO
 */
public class UT4Problema1 {

    final private static Scanner input = new Scanner(System.in);
    final public int MIL = 1000;
    private String[] titulos = new String[MIL];
    final public String FIN_PROGRAMA = "FIN DEL PROGRAMA";

    public static void main(String[] args) {
        UT4Problema1 programa = new UT4Problema1();
        programa.inicio();
    }

    public void inicio() {
        int opcion;
        titulos[0] = "Cien años de soledad";
        titulos[1] = "Don Quijote de la Mancha";
        titulos[2] = "El principito";
        titulos[3] = "Crimen y castigo";
        titulos[4] = "El señor de los anillos";
        titulos[5] = "Orgullo y prejuicio";
        titulos[6] = "Matar a un ruiseñor";
        titulos[7] = "La Odisea";
        titulos[8] = "Dracula";
        titulos[9] = "El retrato de Dorian Gray";
        titulos[10] = "Fahrenheit";
        titulos[11] = "El gran Gatsby";
        titulos[12] = "En busca del tiempo perdido";
        titulos[13] = "Los miserables";
        titulos[14] = "El nombre de la rosa";
        titulos[15] = "La sombra del viento";
        titulos[16] = "Rayuela";
        titulos[17] = "La metamorfosis";
        titulos[18] = "Madame Bovary";
        titulos[19] = "La casa de los espiritus";
        do{
            System.out.println("\nGESTIÓN DE TÍTULOS DE BIBLIOTECA");
            System.out.println("---------------------------------");
            System.out.println("1.- Añadir un título");
            System.out.println("2.- Eliminar un título");
            System.out.println("3.- Buscar un título");
            System.out.println("4.- Ver todos los títulos");
            System.out.println("5.- Salir");
            opcion = validarNumero();
            menu(opcion);
        }while(opcion != 5); //AQUÍ HAGO QUE NO TERMINE EL PROGRAMA HASTA QUE EL USUARIO NO ELIGA EL NÚMERO 5    
    }                           //SI NO HAGO ESTO NO SE ME QUEDABAN REFLEJADOS LOS TITULOS QUE IBA MODIFICANDO

    public void menu(int numero) {
        switch (numero) {
            case 1:// OPCIÓN AÑADIR TITULO
                // Aquí estoy reutilizando los métodos previamente creados guardandolos en variables.
                String nombre = titulo();
                boolean encontrado = buscarTitulo(titulos, nombre);
                añadirTitulo(titulos, encontrado, nombre);
                break;
            case 2: //OPCIÓN ELIMINAR TITULO
                nombre = titulo();
                eliminarTitulo(titulos, nombre);
                break;
            case 3: //OPCIÓN BUSCAR TITULO
                nombre = titulo();
                encontrado = buscarTitulo(titulos, nombre);
                if (encontrado) {
                    System.out.println("Se encuentra en la lista");
                    System.out.println("Quieres volver a empezar?(Si / No): ");
                    decision();
                } else {
                    System.out.println("No se encuentra este título guardado en la lista");
                    System.out.print("Quieres hacer algo con este título?(Si / No): ");
                    decision();
                }
                break;
            case 4: //VER TODOS LOS TÍTULOS
                verTitulos(titulos);
                System.out.print("\nQuieres añadir o eliminar algún título?: ");
                boolean modificar = (input.nextLine().equalsIgnoreCase("Si")) ? true : false;
                if (modificar) {
                    System.out.print("Cual: 1.- Añadir , 2.- Eliminar ó 3.- Verlos Ordenados alfabéticamente?: ");
                    numero = input.nextInt();
                    input.nextLine();
                    if (numero == 1) {
                        menu(1);
                    } else if (numero == 2) {
                        menu(2);
                    } else if(numero == 3) {    
                        ordenarTitulos(titulos);
                        verTitulos(titulos);
                    } 
                }
                break;
            default: //SALIR DEL PROGRAMA
                System.out.println(FIN_PROGRAMA);
                break;
        }
    }
     
    public String titulo() { //METODO SENCILLO PARA INTRODUCCIR EL TÍTULO EN ALGUNA VARIABLE Y QUE SEA MÁS CORTO EL CÓDIGO CENTRAL
        String nombre;
        System.out.print("Cuál es el título?(sin tildes): ");
        return nombre = input.nextLine();
    }

    public void añadirTitulo(String[] array, boolean encontrado, String titulo) { //MÉTODO PARA INTRODUCIR EL TITULO SI NO SE ENCUENTRA EN EL ARRAY PRINCIPAL
        if (encontrado) {
            System.out.println("Ya está este título dentro.");
        } else {
            System.out.println("\n¡AÑADIDO!");
            int i;
            for (i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    array[i] = titulo;
                    break;
                }
            }
        }
    }

    public void eliminarTitulo(String[] array, String titulo) { //MÉTODO PARA ELIMINAR EL TÍTULO SI SE ENCUENTRA EN ARRAY PRINCIPAL
        boolean encontrado = false; 
        for (int i = 0; i < array.length; i++) {
            if(array[i] != null && array[i].equalsIgnoreCase(titulo)){
                encontrado = true;
                for (int j = i; j < array.length - 1; j++) {
                    array[j] = array[j +1]; 
                }
                array[array.length - 1] = null; 
            }
        }
        if(encontrado){
            System.out.println("\nSe ha eliminado el título \"" + titulo +"\".");
        }else{
            System.out.println("\nEl título \""+ titulo +"\" no está en la lista.");
        }
    }

    public void ordenarTitulos(String[] array) { //MÉTODO PARA PODER ORDENAR TITULOS SI ES UNA PETICIÓN EXCLUSIVA, NO ES NECESARIO , SOLO POR GUSTO DEL USUARIO
        String aux;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j + 1] == null) return; 
                if (array[j].charAt(0) > array[j + 1].charAt(0)) {
                    aux = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = aux;
                }
                
            }
        }
    }

    public boolean buscarTitulo(String[] array, String titulo) { //MÉTODO BOOLEANO PARA TRUE SI ESTÁ EL TITULO Y FALSE SI NO LO ESTÁ
        for (int i = 0; i < array.length; i++) {
            if (titulo.trim().equalsIgnoreCase(array[i])) {
                return true;
            }
            if (array[i] == null) {
                return false;
            }
        }
        return false;
    }

    public int validarNumero() { //MÉTODO BÁSICO PARA ENTRADA AL MENÚ PERO NO POR ELLO MENOS IMPORTANTE
        int numero;
        System.out.print("Selecciona una opción: ");
        while (!input.hasNextInt() || (numero = input.nextInt()) < 1 || numero > 5) {
            input.nextLine();
            System.out.print("ERROR!! Esto no es una opción, intentalo de nuevo: ");
        }
        input.nextLine();
        return numero;
    }

    public void decision() { //MÉTODO PARA VOLVER OTRA VEZ AL INICIO, NO ES ABSOLUTAMENTE NECESARIO YA QUE SE VA REPITIENDO INICIO HASTA QUE EL USUARIO QUIERA, PERO UNA VEZ HECHO ME HA PAERCIDO INTERESANTE DEJARLO.
        boolean decision = (input.nextLine().equalsIgnoreCase("Si")) ? true : false;
        if (decision) {
            inicio();
        } else {
            System.out.println(FIN_PROGRAMA);
        }
    }

    public void verTitulos(String[] array) { //MÉTODO PARA IMPRIMIR TODOS LOS TÍTULOS POR PANTALLA
        for (String titulos : array) {
            if (titulos == null) {
                return;
            }
            System.out.println(titulos);
        }
    }
}
