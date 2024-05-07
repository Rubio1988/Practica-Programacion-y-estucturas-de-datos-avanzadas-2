import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ejecucion {

    public static void main(String[] args)throws IOException {
        // TODO Auto-generated method stub
        traza = false;
        ayuda = false;
        lista = false;
        if(args.length>0){
            switch(args[0]){
                case "-h":
                ayuda = true;
                System.out.println("SINTAXIS:torneo[-t][-h] [fichero entrada]");
                System.out.println("  -t                 Traza cada llamada recursiva y sus parámetros");
                System.out.println("  -h                 Muestra esta ayuda");
                System.out.println("[fichero entrada]    Tabla inicial del Sudoku");
                break;
                case "-t":
                traza = true;
                break;
            }
        }
        //fichero de jugadores 
        String sud = "";
        if(traza) {
            if(args.length>1) {
                if(!args[1].isEmpty()) {
                    sud = args[1];
                    lista = true;
                }
            }
        }else {
            if(args.length>0) {
                if(!args[0].isEmpty()) {
                    sud = args[0];
                    lista = true;
                }
            }
        }

        if(lista & !ayuda){
            try {		
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(sud), "UTF-8"));					
                String linea =  in.readLine();
                //contador
                int contador = 0;
                while(linea != null){
                    String[] values = linea.split(" ");
                    //recorremos el arrar de string
                    for (int i = 0; i<values.length; i++) {
                        if(isNumeric(values[i])){
                            sudoku[contador][i] = Integer.parseInt(values[i]);
                        }else{
                        sudoku[contador][i] = 0;
                        }
                    }
                    contador++;
                    linea = in.readLine();
                }			
                in.close();			
            } catch (IOException ex) {			
                System.err.println("No se puede abrir el fichero del Sudoku para su lectura.");			
            }		
        }
        if(!ayuda) {
            if(!lista){
                Scanner consola = new Scanner(System.in);
                for (int x=0; x < sudoku.length; x++) {
                    for (int y=0; y < sudoku[x].length; y++) {
                        System.out.println("Introduzca el elemento " + y + " de la fila" + x + "]");
                        System.out.println("Si el elemento a introducir es una casilla vacia introduzca un 0");
                    }
                }
                Sudoku resolver = new Sudoku(sudoku);
            }else{
                Sudoku resolver = new Sudoku(sudoku);
            }
        }   
    }
    
    private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }

    public static int [][] sudoku = new int [9][9];	
    public static boolean traza;
    public static boolean ayuda;
    public static boolean lista;
}