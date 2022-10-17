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

  public static void play(){
    System.out.print("\033[H\033[2J");
    Scanner sc = new Scanner(System.in);
    random palabra = new random();
    String respuesta = palabra.getPalabra().toUpperCase();
    String p = "";
    String[][] textoF = new String[6][5];
    

   
    
    System.out.println("Bienvenido a Wordle, ¿Desea iniciar el juego?  y/n");
    char eleccion = sc.next().toLowerCase().charAt(0);
    if(eleccion != 'y' && eleccion != 'n'){
      System.out.println("El valor ingresado debe corresponder a: y(yes) o n(no)");
    } else{
      if (eleccion == 'n'){
        System.out.println("Perfecto, hasta pronto!");
        System.out.println("\033[H\033[2J");
      }else {
        System.out.println("Que comience el juego!");
    

    for (int i = 0; i < 6; i++) {
      
      int ganador = 0;
      System.out.println("");
      p = sc.next().toUpperCase();
       System.out.println("\r");
      char [] intento = p.toCharArray();
      
      if (intento.length != 5) {
        System.out.println("Debes introducir una palabra de 5 letras exactamente, intenta de nuevo!");
        i--;
        continue;
      }
      System.out.print("|");
      
      for (int j = 0; j < intento.length; j++) {
        if (respuesta.charAt(j) == intento[j]) {
          textoF[i][j] = "|"+ANSI_GREEN + "\u001B[32m"+intento[j]+ANSI_RESET;
          continue;
        }
        for (int k = 0; k < intento.length; k++) {
          if (j != k && respuesta.charAt(k) == intento[j]) {
            textoF[i][j] = "|"+ANSI_YELLOW + intento[j] + ANSI_RESET;
            break;
          } else if (k==intento.length-1){
            textoF[i][j] = "|"+ANSI_WHITE + intento[j] + ANSI_RESET;
            
          }
        }
      
      }
      
      if (ganador == 1) {
        break;
      }

      System.out.print("\033[H\033[2J");

      
      for(int l=0;l<=i;l++){
      for(int m = 0;m<textoF[0].length;m++){
        System.out.print(textoF[l][m]+"|");
        
      }
        System.out.println("");
      }
      
      System.out.println("");
      System.out.println("");

       if (p.equals(respuesta)) {
          System.out.println("Felicidades has ganado!");
          break;
       }
      if (i == 5) {
        System.out.println("Ups! Se te Acabaron los Intentos,la Palabra Era "+ANSI_RED+respuesta+ANSI_RESET);

    }
  } 
      }
    }  
}
}
