public class Sudoku
{
    private  static  final  int  TAMAÑO  =  9 ; 
    private static int [][] matriz = new int[9][9];
    static Ejecucion traza = new Ejecucion();
    
    public Sudoku (int [][] sudoku){
        matriz = sudoku;
        if  ( solveSudoku ()) {
            printSudoku (); 
        }else{ System.out.println ( "Sin solución" );} 
    }

    private  static  void  printSudoku () 
    { 
        for ( int  i = 0 ; i < TAMAÑO; i ++) 
        { 
            for ( int  j = 0 ; j < TAMAÑO ; j ++) 
            { 
                System.out.print( matriz [ i ] [ j ] + "  " );
                if(j == 2 || j == 5) System.out.print("  ");
            } 
            System.out.println ("");
            if(i == 2 || i == 5) System.out.println("");
        } 
        System.out.println ("");
        System.out.println ("");
    }

    // función para verificar si todas las celdas están asignadas o no 
    // si hay alguna celda no asignada 
    // entonces esta función cambiará los valores de 
    // fila y col en consecuencia 
    private  static  int []  numberUnassigned ( int  row ,  int  col ) 
    { 
        int  numunassign  =  0 ; 
        for ( int  i = 0 ; i < TAMAÑO ; i ++) 
        { 
            for ( int  j = 0 ; j <TAMAÑO ; j ++) 
            { 
                // la celda no está asignada 
                if ( matriz [ i ] [ j ]  ==  0 ) 
                { 
                    // cambiando los valores de row y col 
                    row  =  i ; 
                    col  =  j ; 
                    // hay una o más celdas sin asignar 
                    numunassign  =  1 ; 
                    int []  a  =  { numunassign ,  row ,  col }; 
                    return  a ; 
                } 
            } 
        } 
        int [] a  =  { numunassign ,  - 1 ,  - 1 }; 
        return  a ; 
    }

    // función para comprobar si podemos poner un 
    // valor en una celda particular o no 
    private static boolean isSafe ( int n , int r , int c ) { 
        // comprobando en fila 
        for ( int i = 0 ; i < TAMAÑO ; i ++) 
        { 
        // hay una celda con el mismo valor 
        if ( matriz [ r ] [ i ] == n ) return false ; 
        }       
        // comprobando la columna 
        for ( int  i = 0 ; i < TAMAÑO ; i ++) 
        { 
            // hay una celda con el valor igual a i 
            if ( matriz [ i ] [ c ] == n ) return  false ; 
        } 
        // comprobación de submatriz 
        int  row_start  =  ( r / 3 ) * 3 ; 
        int  col_start  =  ( c / 3 ) * 3 ;
        for ( int  i = row_start ; i < row_start + 3 ; i ++) 
        { 
            for ( int  j = col_start ; j < col_start + 3 ; j ++) 
            { 
                if ( matriz [ i ] [ j ] == n ) 
                    return  false ; 
            } 
        } 
        return  true ; 
    }

    // función para resolver sudoku 
    // usando backtracking 
    private static boolean solveSudoku () 
    { 
        if(traza.traza) printSudoku ();
        int  row = 0 ; 
        int  col = 0 ; 
        int []  a  =  numberUnassigned ( row ,  col ); 
        // si todas las celdas están asignadas, el sudoku ya está resuelto 
        // pasar por referencia porque number_unassigned cambiará los valores de row y col 
        if ( a [ 0 ]  ==  0 ) 
            return  true ;
        // número entre 1 y 9 
        row  =  a [ 1 ]; 
        col  =  a [ 2 ]; 
        for ( int  i = 1 ; i <= TAMAÑO ; i ++) 
        { 
            // si podemos asignar i a la celda o no 
            // la celda es matriz [fila] [col] 
            if ( isSafe ( i ,  row ,  col )) 
            { 
                matriz [ row ] [ col ]  =  i ; 
                // retroceder 
                if( solveSudoku ()) 
                    return  true ; 
                // si no podemos continuar con esta solución 
                // reasignar la matriz de celda 
                matriz [ row ] [ col ] = 0 ; 
            } 
        } 
        return  false; 
    }
}
