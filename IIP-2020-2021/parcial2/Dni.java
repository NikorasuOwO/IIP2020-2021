package parcial2;


import java.util.*; //Hay que importar la clase java.utils para poder usar el scanner!

public class Dni {

    public static boolean filtroLongitud(String dni) {
        boolean nuevedigitos = (dni.length() == 9); //(dni.length() == 9) es una expresión lógica que compara 
                                                     //la logitud del dni y el número 9.
        return nuevedigitos; //devuelve si el dni tiene 9 dígitos o no
    }
    
    public static boolean filtroDigitosLetra(String dniraw){
        if (!filtroLongitud(dniraw)){ //Si el DNI no tiene 9 caracteres, el DNI no es correcto simplemente.
          //  System.out.println("Introducción incorrecta del DNI: Longitud incorrecta");
            return false;
        }
           
        
        boolean numerosOK = false;
        boolean letraOK = false;
        String NumerosDni = dniraw.substring(0, 8);
        
        for (int i = 0; i < NumerosDni.length()-1 ; i++){ //recorremos los números del DNI, i será el index
            System.out.println(dniraw.charAt(i));
          /*  if ((int) NumerosDni.charAt(i) >= 48 && (int) NumerosDni.charAt(i) <= 58){
                //compara si el caracter ASCII en el index i es un numero.
                System.out.println(NumerosDni.charAt(i) + "is between 48 and 58");
                numerosOK = true;
            }else{ 
                //Si algún caracter no es un número, el DNI está mal introducido. e.g: "/", "A", "&"
                System.out.println("Formato incorrecto: los 8 primeros caracteres deben ser números");
                System.out.println(NumerosDni.charAt(i) + "is NOT between 48 and 58");
                numerosOK = false;
            }
            */
            //Con matches se haría más fácil lo sé. 
            
            if(NumerosDni.matches("[0-9]+")){numerosOK = true;}
            
        char Letra = dniraw.charAt(dniraw.length()-1); //La letra es el último caracter del String del DNI.
        
        if ((int) Letra >= 65 && (int) Letra <= 90){
        //Si su ASCII está entre 65 y 90, confirmamos que el último caracter es una letra MAYUSCULA.
            letraOK = true;
        
        }else{ //Si la última letra no es una letra mayúscula, el DNI está mal introducido.
           // System.out.println("Formato incorrecto: el último carácter debe ser una letra");
            return false;
        }
        
        

    };
    
    boolean valor = (numerosOK && letraOK);
    
    return valor; //devolvemos la evaluación del formato del dni
    //En realidad podríamos devolver true directamente pero así se entiende mejor.
}

    public static boolean filtroLetraCorrecta (String dniraw){
    
    if (filtroDigitosLetra(dniraw) == false){ //Si el DNI tiene un mal formato, el DNI no es correcto simplemente.
            return false;
        }
        
    //A partir de aquí asumimos que el dni tiene formato correcto i.e. 9 caracteres
    //tal que los 8 primeros son cifras y el último una letra mayúscula.
        
    char Letra = dniraw.charAt(dniraw.length()-1); //Letra será la última Letra del DNI
    int numeros = Integer.parseInt(dniraw.substring(0, 8)); //Números será el número del DNI i.e. los 8 primeros elementos del dni
    int resto = numeros % 23;
    boolean EsLetraCorrecta = ("TRWAGMYFPDXBNJZSQVHLCKE".indexOf(Letra) == resto); 
    //La letra será correcta si la letra es una de las del string y si además su índice coincide con
    //el que daría el resto i.e. si además de ser una de las letras posibles, es la correcta.
    
    if (!EsLetraCorrecta){
        
        char LetraCorrecta = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(resto); //Esta es la letra correcta
        //System.out.println("La letra introducida no es la corecta, debería ser: "+LetraCorrecta);
    }
    
    return EsLetraCorrecta;
}
    public static int parseInt(String a){
    
    int cifra = 0;
    int numero = 0;
    int index = 0;
    
    for (int i = a.length()-1 ; i >= 0 ; i--){
        
        cifra = (int) a.charAt(i); 
        cifra = cifra - 48; //Pasamos el char en el indice i del string
                            //utilizando la codificación ASCII
        
        numero = numero + cifra * (int) Math.pow(10, index); //Añadimos las cifras al número con su respectiva potencia.
        
        //System.out.println(numero);
        index++;
    }
    
    
    return numero;
    }
    
    public static boolean Isint(String a ){
    
        boolean value = false;
        
        value = a.matches("[0-9]+");
        
    return value;
    }
    
    public static void main (String[] args){
    /* APARTADO 1
        Scanner DniScan = new Scanner(System.in);
        System.out.println("Dime tu DNI por favor: ");
        String DNI = DniScan.next(); //Leemos el dni
        if (FiltroLongitud(DNI)) { //Si el DNI pasa el filtro i.e.
            System.out.println("Tu DNI es: "+DNI);
        }else { //Si no ha pasado el filtro
            System.out.println("Longitud incorrecta"); 
        }*/
        
        
        /* APARTADO 2
        
         * Scanner DniScan = new Scanner(System.in);
        
        
        System.out.println("Dime tu DNI por favor: ");
        String DNI = DniScan.next(); //Leemos el dni
        if (filtroDigitosLetra(DNI)) { //Si el DNI pasa el filtro i.e.
            System.out.println("Tu DNI es: "+DNI);
        }else { //Si no ha pasado el filtro
            System.out.println("Error 404"); 
    }
}*/

//APARTADO 3
        Scanner DniScan = new Scanner(System.in);
        
        
        System.out.println("Dime tu DNI por favor: ");
        
        
        String DNI = new String();
        
         //Leemos el dni
        
        do{
         DNI = DniScan.next();
         if (filtroLetraCorrecta(DNI)) { //Si el DNI pasa el filtro de la letra
            System.out.println("Tu DNI es correcto y es: "+DNI);
         }else { //Si no ha pasado el filtro
            System.out.println("Introduce tu DNI de nuevo, el que has introducido es incorrecto."); 
    }
    System.out.println(DNI);
}while(!filtroLetraCorrecta(DNI));
}
}
