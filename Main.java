/*
 * Tarea Obligatoria :  Juego Conecta 4
 * Alumno : Fernando Ismael Romero
 * Materia : Testeo de Software
 * Version : 1.8
 */

//  Version sin implementar clases


/*
 * ----------------------------------------------------------
 * |Code Review | Clase 3 - Tarea Adicional 		    |
 * ----------------------------------------------------------
 * |Autor:  	Espina Gabriel				    |
 * |Matricula:	56003160 // Testing y Prueba de Software    |
 * |Alias: 	CREG (Code Review Espina Gabriel)   	    |
 * ----------------------------------------------------------
 */


/*
 * ------------------------------------------------------
* |CREG:	main(String[] args)			|
* -------------------------------------------------------
* |> El juego esta programado a raiz de una sola clase	|
* |							|
* |> Provoca la creacion de una varidad extensa		|
* |  de metodos, que realizan diferentes acciones	|									
* -------------------------------------------------------
*/

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class Main {
	private static Scanner input;
	
	public static void main(String[] args) throws IOException {
		
		/*
		 * ------------------------------------------------------
		 * |CREG:	main(String[])				|
		 * ------------------------------------------------------
		 * |> El main contiene demasiado codigo.		|
		 * |							|
		 * |> Los sysout() podrian ser parte  de un metodo, que	|
		 * |  interactuen directamente por consola, con el	|
		 * |  usuario.						|
		 * |							|
		 * |> La forma de nombrar metodos y atributos es	|
		 * |  incorrecto					|
		 * ------------------------------------------------------
		 */
		
		// TODO Auto-generated method stub

		////////////////////////MENU PRINCIPAL//////////////////////////////////////////
		boolean SalidaPrincipal =false;
		do {
			System.out.println();
			int DificultadElegida = elecciondificultad(); // llamo a eleccion de dificultad donde valida el ingreso
			String [][]  Matriz = new String[0][0];	 // Se crea la matriz en con dimensiones 0 y 0.
			Matriz = generartablerovacio(Matriz,DificultadElegida); // Genero el tablero segun las dimensiones de la dificultad
			
			// Partida actual
			boolean SalidaPartida = false;
			int SaberQueJugadorToca =0;
			do {
				SaberQueJugadorToca++;  // Para ir cambiando de jugador por turno
				System.out.println();
				System.out.println();
				imprimirmatriz(Matriz);
				System.out.println();
				Matriz = ingresarficha(Matriz,SaberQueJugadorToca);
				
				if (tablerolleno(Matriz)) {
					imprimirmatriz(Matriz);
					System.out.println("-----------------------------------------------");
					System.out.println("-               No gano nadie                 -");
					System.out.println("-----------------------------------------------");
					SalidaPartida = true;
				}else {
				if (hayganador(Matriz,SaberQueJugadorToca,DificultadElegida)) {
					if (SaberQueJugadorToca %2 ==0) {
						imprimirmatriz(Matriz);
						System.out.println("-----------------------------------------------");
						System.out.println("-      El ganador es el numero 2 (X)          -");
						System.out.println("-----------------------------------------------");
					}else {
						imprimirmatriz(Matriz);
						System.out.println("-----------------------------------------------");
						System.out.println("-      El ganador es el numero 1 (O)          -");
						System.out.println("-----------------------------------------------");
					}	
					SalidaPartida = true;
				}	
				}
			}while(!SalidaPartida);
			
			/*
			 * ------------------------------------------------------
			* |CREG:	main(String[])::while(!SalidaPrincipal)	|
			* -------------------------------------------------------
			* |> La condicion if siguiente es innecesaria		|
			* |							|
			* |> "VolverAJugar()" deberia estar dentro del 		|
			* |  condicional while()				|	
			* |							|
			* |> Por lo tanto el atributo booleano SalidaPrincipal 	|
			* |  es inncesesario					|
			* -------------------------------------------------------
			*/
			
			if(VolverAJugar()) {
				SalidaPrincipal = true;
			}
		}while(!SalidaPrincipal);
	}

	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	// Verifico si hay ganador en el tablero	
	public static boolean hayganador (String[][] Matriz, int QueJugadorToca, int Dificultad) {
		
		/*
		 * ------------------------------------------------------
		* |CREG:	hayganador (String, int, int)		|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados 			|
		* |							|
		* |> Falta comentar el metodo				|
		* -------------------------------------------------------
		*/
		
		final int FACIL = 1;
		final int MEDIO = 2;
		final int DIFICIL = 3;
		
		String SimboloQueCorresponde = "0";
		if (QueJugadorToca%2==0) {
			SimboloQueCorresponde = "(X)";
		}else {
			SimboloQueCorresponde = "(O)";
		}
		
		boolean SeguirBuscandoGanador = false;	
		switch(Dificultad) {  // Busco un ganador acorde a la dificultad de la partida actual
			case FACIL:
				if(GanadorFacil(Matriz, SimboloQueCorresponde)) {
					SeguirBuscandoGanador = true;
				}
				break;
			case MEDIO:
				if(GanadorMedio(Matriz, SimboloQueCorresponde)) {
					SeguirBuscandoGanador = true;
				}
				break;
				
			case DIFICIL:
				if(GanadorDificil(Matriz, SimboloQueCorresponde)) {
					SeguirBuscandoGanador = true;
				}
				break;
		}
		return (SeguirBuscandoGanador);
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Buscar ganador en partida Dificil
	public static boolean GanadorDificil(String Matriz[][], String SimboloQueCorresponde) {
		
		/*
		 * ------------------------------------------------------
		* |CREG:	GanadorDificil (String[][], String)	|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal nombrado.			|
		* |							|
		* |> Las tres estructuras FOR podrian reducirse a un for|
		* |  introducido en un metodo en la misma clase.	|
		* |							|
		* |> La estructura FOR utilizada es erronea, deberia ser|
		* |  un WHILE().					|
		* |							|
		* |> La validacion del if dentro del FOR podria ser 	|
		* |  parte de un metodo, para acortar el codigo y que 	|
		* |  sea mas legible.					|
		* -------------------------------------------------------
		*/
		
		int Largoy = Matriz.length;
		int Largox = (Matriz[1].length);
		boolean SeguirBuscandoGanador = false;
		int i=0;
		int x=0;
		//HORIZONTAL
		for(x=0;(x<Largoy && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<3 && !SeguirBuscandoGanador);i++) {
				if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x][i+1] == SimboloQueCorresponde) && (Matriz[x][i+2] == SimboloQueCorresponde) && (Matriz[x][i+3] == SimboloQueCorresponde)&& (Matriz[x][i+4] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		// VERTICAL
		for(x=0;(x<4 && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<Largox && !SeguirBuscandoGanador);i++) {
				if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i] == SimboloQueCorresponde) && (Matriz[x+2][i] == SimboloQueCorresponde) && (Matriz[x+3][i] == SimboloQueCorresponde)&& (Matriz[x+4][i] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		//DIAGONAL \
		for(x=0;(x<4 && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<3 && !SeguirBuscandoGanador);i++) {
					if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i+1] == SimboloQueCorresponde) && (Matriz[x+2][i+2] == SimboloQueCorresponde)&& (Matriz[x+3][i+3] == SimboloQueCorresponde) && (Matriz[x+4][i+4] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		//DIAGONAL / 
		for(x=0;(x<4 && !SeguirBuscandoGanador);x++) {
			for(i=4;(i<Largox && !SeguirBuscandoGanador);i++) {
					if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i-1] == SimboloQueCorresponde) && (Matriz[x+2][i-2] == SimboloQueCorresponde) && (Matriz[x+3][i-3] == SimboloQueCorresponde) && (Matriz[x+4][i-4] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		return SeguirBuscandoGanador;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	// Buscar Ganador en dificultad media
	public static boolean GanadorMedio(String Matriz[][], String SimboloQueCorresponde) {
		
		/*
		 * ------------------------------------------------------
		* |CREG:	GanadorMedio (String[][], String)	|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal nombrado.			|
		* |							|
		* |> Las tres estructuras FOR podrian reducirse a un for|
		* |  introducido en un metodo en la misma clase.	|
		* |							|
		* |> La estructura FOR utilizada es erronea, deberia ser|
		* |  un WHILE().					|
		* |							|
		* |> La validacion del if dentro del FOR podria ser 	|
		* |  parte de un metodo, para acortar el codigo y que 	|
		* |  sea mas legible.					|
		* -------------------------------------------------------
		*/
		
		int Largoy = Matriz.length;
		int Largox = (Matriz[1].length);

		boolean SeguirBuscandoGanador = false;
		int i=0;
		int x=0;
		//HORIZONTAL
		for(x=0;(x<Largoy && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<3 && !SeguirBuscandoGanador);i++) {
				if((Matriz[x][i] ==SimboloQueCorresponde) && (Matriz[x][i+1] == SimboloQueCorresponde) && (Matriz[x][i+2] == SimboloQueCorresponde) && (Matriz[x][i+3] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		// VERTICAL
		for(x=0;(x<4 && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<Largox && !SeguirBuscandoGanador);i++) {
				if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i] == SimboloQueCorresponde) && (Matriz[x+2][i] == SimboloQueCorresponde) && (Matriz[x+3][i] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		//DIAGONAL \
		for(x=0;(x<4 && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<3 && !SeguirBuscandoGanador);i++) {
					if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i+1] == SimboloQueCorresponde) && (Matriz[x+2][i+2] == SimboloQueCorresponde)&& (Matriz[x+3][i+3] == SimboloQueCorresponde) ) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		//DIAGONAL / 
		for(x=0;(x<4 && !SeguirBuscandoGanador);x++) {
			for(i=3;(i<Largox && !SeguirBuscandoGanador);i++) {
					if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i-1] == SimboloQueCorresponde) && (Matriz[x+2][i-2] == SimboloQueCorresponde) && (Matriz[x+3][i-3] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}			
		return SeguirBuscandoGanador;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// Buscar Ganador en dificultad facil
	public static boolean GanadorFacil(String Matriz[][], String SimboloQueCorresponde) {
		
		/*
		 * ------------------------------------------------------
		* |CREG:	GanadorFacil (String[][], String)	|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal nombrado.			|
		* |							|
		* |> Las tres estructuras FOR podrian reducirse a un for|
		* |  introducido en un metodo en la misma clase.	|
		* |							|
		* |> La estructura FOR utilizada es erronea, deberia ser|
		* |  un WHILE().					|
		* |							|
		* |> La validacion del if dentro del FOR podria ser 	|
		* |  parte de un metodo, para acortar el codigo y que 	|
		* |  sea mas legible.					|
		* -------------------------------------------------------
		*/
		
		int Largoy = Matriz.length;
		int Largox = (Matriz[1].length);

		boolean SeguirBuscandoGanador = false;
		int i=0;
		int x=0;
		//HORIZONTAL
		for(x=0;(x<Largoy && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<4 && !SeguirBuscandoGanador);i++) {
				if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x][i+1] == SimboloQueCorresponde) && (Matriz[x][i+2] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		// VERTICAL
		for(x=0;(x<3 && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<Largox && !SeguirBuscandoGanador);i++) {
				if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i] == SimboloQueCorresponde) && (Matriz[x+2][i] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		//DIAGONAL \
		for(x=0;(x<3 && !SeguirBuscandoGanador);x++) {
			for(i=0;(i<4 && !SeguirBuscandoGanador);i++) {
					if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i+1] == SimboloQueCorresponde) && (Matriz[x+2][i+2] == SimboloQueCorresponde) ) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}
		//DIAGONAL / 
		for(x=0;(x<3 && !SeguirBuscandoGanador);x++) {
			for(i=2;(i<Largox && !SeguirBuscandoGanador);i++) {
					if((Matriz[x][i] == SimboloQueCorresponde) && (Matriz[x+1][i-1] == SimboloQueCorresponde) && (Matriz[x+2][i-2] == SimboloQueCorresponde)) {
						SeguirBuscandoGanador = true;
						break;
					}
			}
		}	
		return SeguirBuscandoGanador;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	// Ingreso la Ficha en la columna seleccionada y se acomoda segun la ocupacion de esta.
	public static String[][] ingresarficha(String [][] Matriz, int QueJugadorToca) {
		
		/*
		 * ------------------------------------------------------
		* |CREG:	ingresarficha (String[][], int)		|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal nombrado.			|
		* -------------------------------------------------------
		*/
		
		int DondeDepositaFicha = validaringresoseleccionficha(Matriz, QueJugadorToca);
		int Ejey=0;
		String SimboloQueCorresponde = "0";
		if (QueJugadorToca%2==0) {
			SimboloQueCorresponde = "(X)";
		}else {
			SimboloQueCorresponde = "(O)";
		}
		boolean prueba = false;
		while(!prueba) {
			for(Ejey=0;Ejey<Matriz.length;Ejey++) {
				int auxiliar = (Matriz.length-1)-Ejey;
				if (Matriz[auxiliar][DondeDepositaFicha-1].equals("()")){
					Matriz[auxiliar][DondeDepositaFicha-1] = SimboloQueCorresponde;
					prueba = true;		
					break;
				}
			}
		}
		return Matriz;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	// Valido el ingreso y si la columna esta llena
	public static int validaringresoseleccionficha(String[][] Matriz, int JugadorNro) {
		
		/*
		 * ------------------------------------------------------
		* |CREG:validaringresoseleleccionficha (String[][], int)|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal nombrado.			|
		* |							|
		* |> Las validaciones dentro de los IF que son		|
		* |  extensos deberian ser parte de un metodo booleano	|
		* |							|
		* |> Falta comentar el codigo				|
		* -------------------------------------------------------
		*/
		
		// Validacion de Ingreso 
		boolean Salida = false;
		int EleccionPosicionFicha=0;
		if (JugadorNro %2 ==0) {
			System.out.println("---------------Turno del jugador nro 2 : (X)--------------------");
		}else {
			System.out.println("---------------Turno del jugador nro 1 : (O)--------------------");
		}
		do {
			System.out.println("En que fila quiere ingresar la ficha? "); 
			try {
				EleccionPosicionFicha = input.nextInt();
				}catch (InputMismatchException IME) {
					System.out.println("Formato no admitido! Ingrese una opcion valida ");
					input.next();
					EleccionPosicionFicha = 100; // Provisorio
		    	}
		
			if ((EleccionPosicionFicha < 1) || (EleccionPosicionFicha >Matriz[1].length)) {
				System.out.println("ERROR: Ingrese un valor desde 1 a "+(Matriz[1].length));
			}else {
				if((Matriz[0][EleccionPosicionFicha-1].equals("(X)")) || (Matriz[0][EleccionPosicionFicha-1].equals("(O)")) ) {
					System.out.println("Fila llena");
				}else {
					Salida = true;
				}
			}
			}while( !Salida);
		
		return EleccionPosicionFicha;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// Pregunto si el tablero esta lleno
	public static boolean tablerolleno(String [][] Matriz) {
		/*
		 * ------------------------------------------------------
		* |CREG:	tablerolleno (String[][])		|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> Falta comentar el codigo				|
		* -------------------------------------------------------
		*/
		
		int total = 0;
		for(int x = 0; x<Matriz[0].length; x++) {
			if(Matriz[0][x] == "(X)" || Matriz[0][x] == "(O)") {
				total++;
			}
		}
		return (total == Matriz[0].length) ;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// Imprimo la matriz con lo que tenga cargado
	public static void imprimirmatriz(String [][] Matriz) {
		
		/*
		 * ------------------------------------------------------
		* |CREG:	imprimirmatriz (String[][])		|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal escrito				|
		* |							|
		* |> Falta comentar el codigo				|
		* -------------------------------------------------------
		*/
		
		int Ejex=0;
		int Ejey=0;
		
		System.out.print("|");
		for(Ejex=0;Ejex < Matriz[1].length;Ejex++) {
			System.out.print(Ejex+1);
			if(Ejex != Matriz[Ejey].length-1) {
				System.out.print("\t");
			}
		}
		System.out.println("|");
		
		for(Ejey=0;Ejey<Matriz.length;Ejey++) {
			System.out.print("|");
			for(Ejex=0;Ejex<Matriz[Ejey].length;Ejex++) {
				System.out.print(Matriz[Ejey][Ejex]);
				if(Ejex != Matriz[Ejey].length-1) {
					System.out.print("\t");
				}
			}
			System.out.println("|");
		}
	}
///////////////////////////////////////////////////////////////////////////////
	//Pregunto si deseo volver a jugar y valido el ingreso
	public static boolean VolverAJugar() {
		/*
		 * ------------------------------------------------------
		* |CREG:	VolverAJugar ()				|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal escrito				|
		* |							|
		* |> Falta comentar el codigo				|
		* -------------------------------------------------------
		*/
		input = new Scanner(System.in);
		boolean SalidaValidacion = false;
		int Eleccion;
		do {
			System.out.println("Desea volver a jugar?  1-SI    2-NO");
			try {
				Eleccion = input.nextInt();
			}catch(InputMismatchException IME) {  // Agarro el error y pido reingresar 
				System.out.println("Formato no admitido! Ingrese una opcion valida ! ");
				input.next();
				Eleccion = 100; // Provisorio
			}
			
			if ((Eleccion != 1) && (Eleccion !=2)) {
				System.out.println("ERROR: Ingrese una opcion valida!");
			}else {
				SalidaValidacion = true;
			}
		}while( !SalidaValidacion);
		
		return (Eleccion == 2);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	// Validar el ingreso de la eleccion de dificultad
	public static int elecciondificultad() {
		
		/*
		 * ------------------------------------------------------
		* |CREG:	elecciondificultad ()			|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal escrito				|
		* |							|
		* |> Falta comentar el codigo				|
		* -------------------------------------------------------
		*/
		
		input = new Scanner(System.in);
		boolean SalidaValidacion = false;
		int Eleccion;
		final int FACIL = 1;
		final int MEDIO = 2;
		final int DIFICIL = 3;
		do {
			System.out.println("Elija la dificultad : \n 1) Facil \n 2) Medio \n 3) Dificil"  );
			try {
				Eleccion = input.nextInt();
				}catch (InputMismatchException IME) {  // Agarro el error y pido reingresar 
					System.out.println("Formato no admitido! Ingrese una opcion valida ! ");
					input.next();
					Eleccion = 100; // Provisorio
				}
			if ((Eleccion != 1) && (Eleccion !=2) && (Eleccion !=3)) {
				System.out.println("ERROR: Ingrese una opcion valida!");
			}else {
				SalidaValidacion = true;
			}
			}while( !SalidaValidacion);

		switch(Eleccion) {
		case FACIL: System.out.println("La dificultad elegida es : Facil");
					break;
		case MEDIO: System.out.println("La dificultad elegida es : Medio");
					break;
		case DIFICIL: System.out.println("La dificultad elegida es : Dificil");
					break;
		}
			return Eleccion;
	}	
	
	/////////////////////////////////////////////////////////////////////////////
	// Genero el tablero vacio acorde a la dificultad
	public static String[][] generartablerovacio(String [][] Matriz,int Eleccion) {
		/*
		 * ------------------------------------------------------
		* |CREG:	generartablerovacio (String,int)	|
		* -------------------------------------------------------
		* |> Los atributos estan mal nombrados. 		|
		* |							|
		* |> El metodo esta mal escrito				|
		* |							|
		* |> Falta comentar el codigo				|
		* -------------------------------------------------------
		*/
			final int JUEGO_FACIL = 1;
			final int JUEGO_MEDIO = 2;
			final int JUEGO_DIFICIL = 3;
			int Ejey=0;
			int Ejex=0;
			
			switch(Eleccion) {
				case JUEGO_FACIL : Matriz = new String[5][6];
											break;
				case JUEGO_MEDIO : Matriz = new String[7][6];
											break;
				case JUEGO_DIFICIL : Matriz = new String[8][7];
											break;
			}
			
			for(Ejey=0;Ejey<Matriz.length;Ejey++) {
				for(Ejex=0;Ejex<Matriz[Ejey].length;Ejex++) {
					Matriz[Ejey][Ejex] =  "()";
				}
			}
			
			return Matriz;
			
	}

}
