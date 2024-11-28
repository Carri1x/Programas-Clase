
import java.util.Scanner;

/**
 *
 * @author ÁLVARO CARRIÓN ROMERO
 */
public class ProyectoAdivinaLaPalabra2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String siRepetir = "noooo";
        String repetir = "si"; //Esto me lo he inventado feamente para la condicion de repetir el juego entero
        boolean hasGanado = false;
        String[][] palabras = { //Dos filas las 0 = Faciles y 1 = Dificiles.
            {"Monitor", "Elefante", "Linterna", "Reloj", "Violeta", "Mesa", "Chocolate", "Ventana", "Avion", "Ordenador"},
            {"Efervescente", "Zancadilla", "Eclipse", "Hipopotamo", "Estratosfera", "Anagrama", "Horizonte", "Gabinete", "Esternocleidomastoideo", "Camaleon"},}; //2 x 8
        char[] vocales = {'a', 'e', 'i', 'o', 'u'};
        System.out.println("-------------------------------------------");
        System.out.println("---Bienvenido a: Adivina la palabra---");
        do {
            hasGanado = false;
            if (siRepetir.equals(repetir)) {
                System.out.println("--------------------------------------------");
                System.out.println("---PLAAAAYING AGAIN (ADIVINA LA PALABRA)---");
                System.out.println("--------------------------------------------");
            }
            
            System.out.println("Dime el tipo de dificultad que quieres.....");
            System.out.print("\nEscribe: Dificil, Fácil o Aleatorio:  ");

            String dificultadScanner; //para leer por teclado la dificultad
            char[] chScanner;    //hago este array para comparar letra por letra
            char[][] miDificultad = {{'a', 'l', 'e', 'a', 't', 'o', 'r', 'i', 'o'},
            {'d', 'i', 'f', 'i', 'c', 'i', 'l'},
            {'f', 'a', 'c', 'i', 'l'},};
            boolean palabraCorrecta = false;
            String opcionDificultad = "";
            int x = 0;
            int i = 0;
            while (!palabraCorrecta) {

                dificultadScanner = input.nextLine().toLowerCase().trim();
                chScanner = new char[dificultadScanner.length()];  //Doy el número de index dependiendo de la longitud de la palabra insertada

                if (dificultadScanner.length() > 9 || dificultadScanner.length() < 5) {
                    palabraCorrecta = false;

                } else { //Aquí como entra en el rango de numero de letras de la plabra correcta pues hacemos una comprobacion que sean alguna de las 3 palabras

                    //Vuelco la palabra para compararla luego con el array de las palabras miDificultad
                    for (i = 0; i < dificultadScanner.length(); i++) {
                        chScanner[i] = dificultadScanner.charAt(i);
                    }
                    for (i = 0; i < miDificultad.length && !palabraCorrecta; i++) { //Comparo las palabras
                        for (int j = 0; j < chScanner.length && !palabraCorrecta; j++) {
                            if (miDificultad[i][j] != chScanner[j]) {
                                palabraCorrecta = false;
                            } else {
                                palabraCorrecta = true;
                                opcionDificultad = new String(chScanner);
                            }
                        }
                    }
                }
                if (x >= 2 && !palabraCorrecta) {
                    System.out.println("\nPOR FAVOOOOR ELGIE BIEN ENTRE : ALEATORIO, DIFICIL Y FACIL!!!!!!!");
                    System.out.print("(-.-) Escribe...: ");
                } else if (!palabraCorrecta) {
                    System.out.print("\nERROR, elige bien la dificultad: ");
                }
                x++;
            }

            String wordGuess = ""; //Variable para la palabra que tiene que adivinar el usuario
            char[] arrayWordGuess;
            int numeroRandom;
            String palabraUsuario;
            char[] chPalabraUsuario;
            char[] enseñarPistaPalabra;
            String palabraAdivinada; //para convertir a String el array enseñarPistaPalabra y en caso de que complete el ahorcado gane de todas formas
            boolean letraEncontrada = false; //Este boolean lo utilizo para saber si he encontrado una letra que no haga ciertas cosas...
            int letraCorrecta, multiplicador, rachaAciertos = 0; //para el puntuaje
            int pista = i;
            boolean unaVez = false; //Este boolean lo utilizo para que solo se rellene el array de enseñarPistaPalabra de  '_' una vez
            
            switch (opcionDificultad) {
                case "aleatorio"-> {
                    //Parte aleatoria
                    int filaRandom = (int) (Math.random() * 2); // Como el apartado es aleatorio pues que salga una fila o facil o dificil
                    numeroRandom = (int) (Math.random() * 10);
                    wordGuess = palabras[filaRandom][numeroRandom].toLowerCase();  //...meto la palabra escogida en esta variable...
                    arrayWordGuess = new char[wordGuess.length()]; //...Hago un array para ir comparando letra a letra y poder manejar estas letras...
                    letraCorrecta = 0;
                    for (int j = 0; j < wordGuess.length(); j++) {
                        arrayWordGuess[j] = wordGuess.charAt(j); // Ya está completo el array
                    }
                    System.out.println("Estas listo?? Empezamos!!!!!");
                    for (i = 0; i < 10 && !hasGanado; i++) { //Un for para llegar a un límite de intentos

                        if (!letraEncontrada) {
                            System.out.print("\tNúmero de letras " + wordGuess.length() + " :  ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                System.out.print("_ ");
                            }
                        }
                        System.out.print("\nIntento " + i + " : ");
                        palabraUsuario = input.nextLine().toLowerCase().trim();
                        while (!palabraUsuario.matches("[a-zA-Z]+")) {
                            System.out.print("Introduce solo letras: ");
                            palabraUsuario = input.nextLine().trim();
                        } // compruebo que la palabra tenga solo letras
                        while (palabraUsuario.length() > wordGuess.length() || palabraUsuario.length() < wordGuess.length()) {
                            System.out.println("RECUERDA! La palabra tiene " + wordGuess.length() + " letras no " + palabraUsuario.length() + " -.-");
                            System.out.print("Escribela bien: ");
                            palabraUsuario = input.nextLine().trim();
                            while (!palabraUsuario.matches("[a-zA-Z]+")) {
                                System.out.print("Introduce solo letras: ");
                                palabraUsuario = input.nextLine().trim();
                            } // compruebo que la palabra tenga solo letras
                        } //compruebo que la palabra sea del mismo tamaño
                        chPalabraUsuario = new char[palabraUsuario.length()];
                        enseñarPistaPalabra = new char[wordGuess.length()]; //...para enseñar por pantalla que letras tiene descubiertas....

                        for (int j = 0; j < chPalabraUsuario.length; j++) { //Volcar la palabra introducida en un array de tipo char
                            chPalabraUsuario[j] = palabraUsuario.charAt(j);
                        }

                        for (int j = 0; j < chPalabraUsuario.length; j++) {
                            for (int k = 0; k < chPalabraUsuario.length; k++) {
                                if (chPalabraUsuario[j] == arrayWordGuess[k]) {
                                    enseñarPistaPalabra[k] = arrayWordGuess[k];
                                    letraEncontrada = true;
                                }
                            }
                        }
                        if (letraEncontrada && !hasGanado) {
                            System.out.print("\tNumero de letras " + wordGuess.length() + " :  ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                if (enseñarPistaPalabra[j] == '\u0000') { //esto de '\u0000' me ha tocado buscarlo en el chat gpt porque he estado horas muertas intentando ver que hacia porque no me salia los guiones bajos
                                    System.out.print("_ ");
                                } else {
                                    System.out.print(enseñarPistaPalabra[j] + " ");
                                    letraCorrecta++;
                                    multiplicador = 1 + (letraCorrecta / 2);
                                    rachaAciertos += 10 * multiplicador;
                                }
                            }
                            if(wordGuess.length()< 5){  //Aqui tengo el puntuaje de los aciertos...
                                System.out.println("\tPuntos: " + (rachaAciertos / (i+1))/5);
                            } else if(wordGuess.length() >= 5 && wordGuess.length() < 10){
                                System.out.println("\tPuntos: " + (rachaAciertos / (i+1))/2);
                            } else {
                                System.out.println("\tPuntos: " + rachaAciertos / (i+1));
                            }
                        }
                        if(pista % (i + 1)== 0){
                            boolean consonante = true;
                            System.out.print("\t\t\t PISTA: ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                consonante = true;
                                for (int k = 0; k < vocales.length; k++) {
                                    if(wordGuess.charAt(j) == vocales[k]){
                                        System.out.print("V ");
                                        consonante = false;
                                    }
                                }
                                if(consonante){
                                    System.out.print("C "); 
                                }
                            }
                        }
                        palabraAdivinada = new String(enseñarPistaPalabra);
                        if (palabraAdivinada.equals(wordGuess) || palabraUsuario.equals(wordGuess)) {
                            hasGanado = true;
                        }
                    }
                    if (hasGanado) {
                        System.out.println("\nFELICIDADES LA PALABRA ERA: " + wordGuess);
                        System.out.println("¡¡¡¡MUY BIEN!!!! :3 (/\\./\\) ");

                    }
                }
                case "dificil" -> {
                    //Parte dificil
                    numeroRandom = (int) (Math.random() * 10);
                    wordGuess = palabras[1][numeroRandom].toLowerCase();  //...meto la palabra escogida en esta variable...
                    arrayWordGuess = new char[wordGuess.length()]; //...Hago un array para ir comparando letra a letra y poder manejar estas letras...
                    letraCorrecta = 0;
                    for (int j = 0; j < wordGuess.length(); j++) {
                        arrayWordGuess[j] = wordGuess.charAt(j); // Ya está completo el array
                    }
                    System.out.println("Estas listo?? Empezamos!!!!!");
                    for (i = 0; i < 10 && !hasGanado; i++) { //Un for para llegar a un límite de intentos

                        if (!letraEncontrada) {
                            System.out.print("\tNúmero de letras " + wordGuess.length() + " :  ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                System.out.print("_ ");
                            }
                        }
                        System.out.print("\nIntento " + i + " : ");
                        palabraUsuario = input.nextLine().toLowerCase().trim();
                        while (!palabraUsuario.matches("[a-zA-Z]+")) {
                            System.out.print("Introduce solo letras: ");
                            palabraUsuario = input.nextLine().trim();
                        } // compruebo que la palabra tenga solo letras
                        while (palabraUsuario.length() > wordGuess.length() || palabraUsuario.length() < wordGuess.length()) {
                            System.out.println("RECUERDA! La palabra tiene " + wordGuess.length() + " letras no " + palabraUsuario.length() + " -.-");
                            System.out.print("Escribela bien: ");
                            palabraUsuario = input.nextLine().trim();
                            while (!palabraUsuario.matches("[a-zA-Z]+")) {
                                System.out.print("Introduce solo letras: ");
                                palabraUsuario = input.nextLine().trim();
                            } // compruebo que la palabra tenga solo letras
                        } //compruebo que la palabra sea del mismo tamaño
                        chPalabraUsuario = new char[palabraUsuario.length()];
                        enseñarPistaPalabra = new char[wordGuess.length()]; //...para enseñar por pantalla que letras tiene descubiertas....

                        for (int j = 0; j < chPalabraUsuario.length; j++) { //Volcar la palabra introducida en un array de tipo char
                            chPalabraUsuario[j] = palabraUsuario.charAt(j);
                        }

                        for (int j = 0; j < chPalabraUsuario.length; j++) {
                            for (int k = 0; k < chPalabraUsuario.length; k++) {
                                if (chPalabraUsuario[j] == arrayWordGuess[k]) {
                                    enseñarPistaPalabra[k] = arrayWordGuess[k];
                                    letraEncontrada = true;
                                }
                            }
                        }
                        if (letraEncontrada && !hasGanado) {
                            System.out.print("\tNumero de letras " + wordGuess.length() + " :  ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                if (enseñarPistaPalabra[j] == '\u0000') { //esto de '\u0000' me ha tocado buscarlo en el chat gpt porque he estado horas muertas intentando ver que hacia porque no me salia los guiones bajos
                                    System.out.print("_ ");
                                } else {
                                    System.out.print(enseñarPistaPalabra[j] + " ");
                                    letraCorrecta++;
                                    multiplicador = 1 + (letraCorrecta / 2);
                                    rachaAciertos += 10 * multiplicador;
                                }
                            }
                            if(wordGuess.length()< 5){  //Aqui tengo el puntuaje de los aciertos...
                                System.out.println("\tPuntos: " + (rachaAciertos / (i+1))/5);
                            } else if(wordGuess.length() >= 5 && wordGuess.length() < 10){
                                System.out.println("\tPuntos: " + (rachaAciertos / (i+1))/2);
                            } else {
                                System.out.println("\tPuntos: " + rachaAciertos / (i+1));
                            }  
                        }
                        if(pista % (i + 1)== 0){
                            boolean consonante = true;
                            System.out.print("\t\t\t PISTA: ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                consonante = true;
                                for (int k = 0; k < vocales.length; k++) {
                                    if(wordGuess.charAt(j) == vocales[k]){
                                        System.out.print("V ");
                                        consonante = false;
                                    }
                                }
                                if(consonante){
                                    System.out.print("C "); 
                                }
                            }
                        }
                        palabraAdivinada = new String(enseñarPistaPalabra);
                        if (palabraAdivinada.equals(wordGuess) || palabraUsuario.equals(wordGuess)) {
                            hasGanado = true;
                        }
                    }
                    if (hasGanado) {
                        System.out.println("\nFELICIDADES LA PALABRA ERA: " + wordGuess);
                        System.out.println("¡¡¡¡MUY BIEN!!!! :3 (/\\./\\) ");

                    }
                }
                case "facil" -> {
                    //Parte facil
                    numeroRandom = (int) (Math.random() * 10);
                    wordGuess = palabras[0][numeroRandom].toLowerCase();  //...meto la palabra escogida en esta variable...
                    arrayWordGuess = new char[wordGuess.length()]; //...Hago un array para ir comparando letra a letra y poder manejar estas letras...
                    letraCorrecta = 0;
                    for (int j = 0; j < wordGuess.length(); j++) {
                        arrayWordGuess[j] = wordGuess.charAt(j); // Ya está completo el array
                    }
                    System.out.println("Estas listo?? Empezamos!!!!!");
                    for (i = 0; i < 10 && !hasGanado; i++) { //Un for para llegar a un límite de intentos

                        if (!letraEncontrada) {
                            System.out.print("\tNúmero de letras " + wordGuess.length() + " :  ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                System.out.print("_ ");
                            }
                        }
                        System.out.print("\nIntento " + i + " : ");
                        palabraUsuario = input.nextLine().toLowerCase().trim();
                        while (!palabraUsuario.matches("[a-zA-Z]+")) {
                            System.out.print("Introduce solo letras: ");
                            palabraUsuario = input.nextLine().trim();
                        } // compruebo que la palabra tenga solo letras
                        while (palabraUsuario.length() > wordGuess.length() || palabraUsuario.length() < wordGuess.length()) {
                            System.out.println("RECUERDA! La palabra tiene " + wordGuess.length() + " letras no " + palabraUsuario.length() + " -.-");
                            System.out.print("Escribela bien: ");
                            palabraUsuario = input.nextLine().trim();
                            while (!palabraUsuario.matches("[a-zA-Z]+")) {
                                System.out.print("Introduce solo letras: ");
                                palabraUsuario = input.nextLine().trim();
                            } // compruebo que la palabra tenga solo letras
                        } //compruebo que la palabra sea del mismo tamaño
                        chPalabraUsuario = new char[palabraUsuario.length()];
                        enseñarPistaPalabra = new char[wordGuess.length()]; //...para enseñar por pantalla que letras tiene descubiertas....

                        for (int j = 0; j < chPalabraUsuario.length; j++) { //Volcar la palabra introducida en un array de tipo char
                            chPalabraUsuario[j] = palabraUsuario.charAt(j);
                        }

                        for (int j = 0; j < chPalabraUsuario.length; j++) {
                            for (int k = 0; k < chPalabraUsuario.length; k++) {
                                if (chPalabraUsuario[j] == arrayWordGuess[k]) {
                                    enseñarPistaPalabra[k] = arrayWordGuess[k];
                                    letraEncontrada = true;  
                                }
                            }
                        }
                        if (letraEncontrada && !hasGanado) {
                            System.out.print("\tNumero de letras " + wordGuess.length() + " :  ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                if(enseñarPistaPalabra[j] == '\u0000'){ //esto de '\u0000' me ha tocado buscarlo en el chat gpt porque he estado horas muertas intentando ver que hacia porque no me salia los guiones bajos
                                    System.out.print("_ ");
                                } else{
                                    System.out.print(enseñarPistaPalabra[j] + " ");
                                    letraCorrecta++;
                                    multiplicador = 1 + (letraCorrecta / 2);
                                    rachaAciertos += 10 * multiplicador;
                                }
                            }
                            if(wordGuess.length()< 5){  //Aqui tengo el puntuaje de los aciertos...
                                System.out.println("\tPuntos: " + (rachaAciertos / (i+1))/5);
                            } else if(wordGuess.length() >= 5 && wordGuess.length() < 10){
                                System.out.println("\tPuntos: " + (rachaAciertos / (i+1))/2);
                            } else {
                                System.out.println("\tPuntos: " + rachaAciertos / (i+1));
                            }  
                        }
                        if(pista % (i + 1)== 0){
                            boolean consonante = true;
                            System.out.print("\t\t\t PISTA: ");
                            for (int j = 0; j < wordGuess.length(); j++) {
                                consonante = true;
                                for (int k = 0; k < vocales.length; k++) {
                                    if(wordGuess.charAt(j) == vocales[k]){
                                        System.out.print("V ");
                                        consonante = false;
                                    }
                                }
                                if(consonante){
                                    System.out.print("C "); 
                                }
                            }
                        }
                        palabraAdivinada = new String(enseñarPistaPalabra);
                        if(palabraAdivinada.equals(wordGuess)|| palabraUsuario.equals(wordGuess)){
                            hasGanado = true;
                        }
                    }
                    if(hasGanado){
                        System.out.println("\nFELICIDADES LA PALABRA ERA: "+ wordGuess);
                        System.out.println("¡¡¡¡MUY BIEN!!!! :3 (/\\./\\) ");
                        
                    }
                }
            }
            
            System.out.println("\n QUIERES REPETIR???");
            System.out.print("\nEscribe si para repetir, otra cosa para salir del juego: ");
            siRepetir = input.nextLine();
            
        } while (repetir.equalsIgnoreCase(siRepetir));
        input.close();
    }
}
