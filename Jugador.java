import java.util.Scanner;

public class Jugador {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";


  ////metodo para contar cuantas veces se repite un caracter en un String///////////
  public static int contarCaracteres(String cadena, char caracter) {
    int posicion, contador = 0;
    // se busca la primera vez que aparece
    posicion = cadena.indexOf(caracter);
    while (posicion != -1) { // mientras se encuentre el caracter
      contador++; // se cuenta
      // se sigue buscando a partir de la posición siguiente a la encontrada
      posicion = cadena.indexOf(caracter, posicion + 1);
    }
    return contador;
  }
        ////////////////////Fin del metodo///////////////////////////
  
  public static void play() {
    System.out.print("\033[H\033[2J");
    Scanner sc = new Scanner(System.in);
    random palabra = new random(); // Crea el objeto random
    String r = palabra.getPalabra().toUpperCase(); // Guarda la respuesta en variabe r
    String p = ""; // El intento estara guardado en esta variable
    String[][] textoF = new String[6][5]; // se crea la matriz que sera impresa luego

    ////////////////////////////////////// Bienvenida//////////////////////////////////////
    System.out.println("\033[H\033[2J"); // se limpia la consola
    System.out.println("Bienvenido a Wordle, ¿Desea iniciar el juego?  y/n");
    char eleccion = sc.next().toLowerCase().charAt(0);
    if (eleccion != 'y' && eleccion != 'n') {
      System.out.println("El valor ingresado debe corresponder a: y(yes) o n(no)");
    } else {
      if (eleccion == 'n') {
        System.out.println("\033[H\033[2J"); // limpia la consola
        System.out.println("Perfecto, hasta pronto!");
      } else {
        System.out.println("\033[H\033[2J"); // limpia la consola
        System.out.println("Que comience el juego!");
        //////////////////////////// Fin de la
        //////////////////////////// Bienvenida//////////////////////////////////////

        //////////////////////////////// Logica del
        ////////////////////////////////juego//////////////////////////////////////
        for (int i = 0; i < 6; i++) { // se define el numero de intentos en el limite de i

          String respuesta = r; // se define la palabra clave como respuesta

          System.out.println("");
          p = sc.next().toUpperCase(); // Intento del jugador
          char[] intento = p.toCharArray(); // Se convierte el intento en un Array
          int contador = 0; // Contador que define si se debe analizar la palabra de nuevo
          int amarillo = 0;
          if (intento.length != 5) { // Error si se introduce palabra de +^- de 5 letras
            System.out.println("Debes introducir una palabra de 5 letras exactamente, intenta de nuevo!");
            i--;
            continue;
          }
          System.out.print("|"); // Organizar la cuadricula

          for (int j = 0; j < intento.length; j++) { // ciclo anidado para comparar Arreglos
            for (int k = 0; k < intento.length; k++) {
              if (respuesta.charAt(j) == intento[j] || respuesta.charAt(j) == '/') {
                textoF[i][j] = "|" + ANSI_GREEN + intento[j] + ANSI_RESET;
                if (respuesta.charAt(j) != '/') {
                  char[] arrayR = respuesta.toCharArray();
                  arrayR[j] = '/';
                  respuesta = String.valueOf(arrayR);
                  j = -1;
                  contador++;
                }
                break;
              }

              else if (j != k && respuesta.charAt(k) == intento[j]) {
                //si el numero de veces que se repite la letra en la palabra 
                if (contarCaracteres(r, respuesta.charAt(k)) > amarillo) {
                  textoF[i][j] = "|" + ANSI_YELLOW + intento[j] + ANSI_RESET;
                  amarillo++;
                  break;
                } else {
                  textoF[i][j] = "|" + ANSI_WHITE + intento[j] + ANSI_RESET;
                }
              }

              else if (k == intento.length - 1) {
                textoF[i][j] = "|" + ANSI_WHITE + intento[j] + ANSI_RESET;

              }

            }

            if (contador == 1) {
              contador--;
              continue;
            }

          }
          System.out.print("\033[H\033[2J");

          for (int l = 0; l <= i; l++) {
            for (int m = 0; m < textoF[0].length; m++) {
              System.out.print(textoF[l][m] + "|");

            }
            System.out.println("");
          }

          System.out.println("");
          System.out.println("");

          if (p.equals(r)) {
            System.out.println("Felicidades has ganado!");
            break;
          }
          if (i == 5) {
            System.out
                .println("Ups! Se te Acabaron los Intentos,la Palabra Era " + ANSI_RED + r + ANSI_RESET);
          }
        }
      }
    }
  }
}
