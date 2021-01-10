package pract7;

//package pract7;
import pract5.Point;
/**
 * Clase PolygonGroup. Grupo de poligonos en el plano.
 * Los poligonos estan en orden segun la secuencia en que se añaden
 * al grupo, de manera que se considera que cada poligono esta mas
 * arriba en el grupo que los poligonos anteriores, o dicho de otro
 * modo, se superpone a los anteriores.
 * Se supone que el orden del grupo da la secuencia en que se dibujan
 * los poligonos, de manera que cada uno se dibuja por encima de los
 * anteriores, superponiendose a aquellos con los que solape.
 *
 * Ademas de añadir poligonos al grupo, se puede seleccionar un poligono
 * para eliminarlo, para trasladar sus coordenadas en el plano, o para
 * cambiar su posicion relativa en el grupo: llevarlo al frente (arriba
 * del todo), llevarlo al fondo (debajo del todo), ...
 *
 * La manera de seleccionar el poligono a mover en el grupo, es dando un
 * punto visible del poligono, es decir, dando un punto que no pertenezca
 * a los poligonos que aparecen superpuestos en el dibujo.
 *
 * @author IIP - Practica 7
 * @version Curso 2020/21
 */
public class PolygonGroup {
    /* COMPLETAR la definicion de la constante, atributo de clase publico */
    public static final int MAX = 10;
    private Polygon[] group = new Polygon[MAX];
    private int size;

    /* COMPLETAR la definicion de atributos de instancia privados */

    /**
     * Crea un grupo de 0 poligonos.
     */
    public PolygonGroup() {
        group = new Polygon[MAX];
        this.size = 0;
    }

    /** Devuelve el numero de poligonos del grupo,
     *  esto es, la talla del grupo.
     *  return int, la talla.
     */
    public int getSize() { return this.size;}

    /** Añade al grupo, arriba del todo, un poligono dado.
     *  Si se excede la capacidad del grupo, el poligono
     *  no se añade.
     *  @param pol Polygon, el poligono.
     *  @return boolean, true si se ha añadido o false en caso contrario.
     */
    public boolean add(Polygon pol) {
        
        if(size == 10){return false;} //Si el tamaño ya es 10, no se puede añadir nada.
        
        this.size++; //Añadimos 1 al tamaño
        this.group[size-1] = pol; //Añadimos grupo de polígonos "pol", ponemos la variable referencia en la primera entrada null del array.
        
        return true; //Devolvemos true porque hemos podido añadir el polígono
    }

    /** Devuelve un array con la secuencia de poligonos del grupo,
     *  por orden desde el de mas abajo al de mas arriba.
     *  @return Polygon[], el array.
     */
    public Polygon[] toArray() {
        
        Polygon[] poligonos = new Polygon[size];
        for(int i = 0 ; i < this.size ; i ++){    
            poligonos[i] = this.group[i];
        }
        
        return poligonos;
    }

    /** Busca en el grupo descendentemente, de mas arriba
     *  a mas abajo, el primer poligono que contiene a un
     *  punto dado, devolviendo su posicion en el grupo.
     *  Si no existe, devuelve -1.
     *  @param Point p, el punto.
     *  @return int, posicion en el grupo del poligono que
     *  contiene al punto o -1 si no existe.
     */
    private int search(Point p) {
        
        for(int i = MAX-1 ;  i >= 0 ; i--){ //Recorremos el array de Polígonos
            
            if(group[i] instanceof Polygon && group[i].inside(p) ){ return i; }
            
        }
        
        return -1;
    }

    /** Traslada en el plano el poligono seleccionado
     *  mediante el punto p. Las abscisas y las ordenadas
     *  de sus vertices se incrementan o decrementan en dos
     *  valores dados. El metodo no hace nada si no
     *  hay ningun poligono que contenga a p.
     *  @param p Point, el punto.
     *  @param incX double, el incremento o decremento de las abscisas.
     *  @param incY double, el incremento o decremento de las ordenadas.
     */
    public void translate(Point p, double incX, double incY) {
        
        int indice = this.search(p); //El índice del polígono que contiene p lo buscamos con search.
        
        if(indice != -1){ // Si el punto está efectivamente en algún polígono:
            
            group[indice].translate(incX, incY); //Usamos el método translate de la clase Polygon para mover el polígono de incX en x y incY en y.
        
        }
        
    }
    
    /** Elimina del grupo el poligono seleccionado
     *  mediante el punto p. El metodo no hace nada si no
     *  hay ningun poligono que contenga a p.
     *  @param p Point, el punto.
     *  @return boolean, true si se ha eliminado o false en caso contrario.
     */
    public boolean remove(Point p) {
        int indice = this.search(p); //El índice del polígono que contiene p lo buscamos con search.
        
        if(indice == -1){ //Si el índice es -1, el punto no pertece a ningún polinomio, nada se hace y se devuelve false.
            return false;
        }
        
        //this.group[indice] = null; //Se sobreescribe con null la variable objeto del objeto que tiene el punto p (que está en el indice "indice".
        
        for(int i = indice; i < size-1; i++){
                
            group[i] = group[i+1];
                
        }
        
        group[this.size-1] = null; //Ponemos el último elemento a null
        
        size--; //Hemos eliminado un polígono así que la talla del grupo es menor.
        return true; //Devolvemos true en vista de una eliminación exitosa.
    }

    /** Situa al frente del grupo el poligono seleccionado
     *  mediante el punto p. El metodo no hace nada si no
     *  hay ningun poligono que contenga a p.
     *  @param p Point, el punto.
     */
    public void toFront(Point p) {

        int indice = this.search(p); //El índice del polígono que contiene p lo buscamos con search.
        
        if(indice == -1){return;} //Si el índice es -1, el punto no pertece a ningún polinomioi, nada se hace. return acaba el método.
        
        Polygon Auxiliar = group[indice]; //Guardamos la variable referencia del polygono que queremos mover.
        this.remove(p); //Eliminamos el poligono del grupo y hacemos shift hacia la izquierda.
        
        this.size = this.size + 1; //Como hemos añadido un objeto, hay que sumarle 1 a size.  
        
        group[this.size-1] = Auxiliar; // Añadimos el polígono que queríamos mover al array, estará en 
                                       // la posición size-1 que era la posición del último poligono antes de hacer remove..
        
    }

    /** Situa al fondo del grupo el poligono seleccionado
     *  mediante el punto p. El metodo no hace nada si no
     *  hay ningun poligono que contenga a p.
     *  @param p Point, el punto.
     */
    public void toBack(Point p) { 
        
        /* Vamos a hacer shift hacia la izquierda y poner el polígono deseado en la posición 0 */
            
        int indice = this.search(p); //El índice del polígono que contiene p lo buscamos con search.
        
        if(indice == -1){return;} //Si el índice es -1, el punto no pertece a ningún polinomioi, nada se hace. return acaba el método.
        
        Polygon Auxiliar = group[indice];
        
        for(int i = indice ; i > 0 ; i--){
            
            group[i] = group[i - 1];
        
        }
        
        group[0] = Auxiliar; 
        
    }
}
