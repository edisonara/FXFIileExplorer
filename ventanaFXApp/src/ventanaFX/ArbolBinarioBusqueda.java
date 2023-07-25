package ventanaFX;


public class ArbolBinarioBusqueda extends ArbolBinario{
	public static int nuevoNodo=0;
	public ArbolBinarioBusqueda(){
		super();
	}
	public Nodo buscar(Object buscado){
		Comparador dato;
		dato = (Comparador) buscado;
		if (raiz == null)
			return null;
		else
			return localizar(raizArbol(), dato);
	}
	protected Nodo localizar(Nodo raizSub, Comparador buscado){
		if (raizSub == null)
			return null;
		else if (buscado.igualQue(raizSub.valorNodo()))
			return raiz;
		else if (buscado.menorQue(raizSub.valorNodo()))
			return localizar(raizSub.subarbolIzdo(), buscado);
		else
			return localizar (raizSub.subarbolDcho(), buscado);
	}
 
	public Nodo buscarIterativo (Object buscado){
		Comparador dato;
		boolean encontrado = false;
		Nodo raizSub = raiz;
		dato = (Comparador) buscado;
		while (!encontrado && raizSub != null){
			if (dato.igualQue(raizSub.valorNodo()))
				encontrado = true;
			else if (dato.menorQue(raizSub.valorNodo()))
				raizSub = raizSub.subarbolIzdo();
			else 
				raizSub = raizSub.subarbolDcho();
		}	
		return raizSub;
	}
	public static void insertar(String valor) throws Exception {
		int dato= obtenerCodigoAscii(valor);
        raiz = insertarRecursivo(raiz, dato);
    }
	public static int obtenerCodigoAscii(String str) {
        int sumaAscii = 0;
        for (int i = 0; i < str.length(); i++) {
            char caracter = str.charAt(i);
            sumaAscii += (int) caracter;
        }
        return sumaAscii;
    }

	private static Nodo insertarRecursivo(Nodo raizActual, int valor)throws Exception {
        if (raizActual == null) {
            // Si el nodo actual es nulo, se crea un nuevo nodo con el valor dado
            return new Nodo(valor);
        }
        String nombre1=  (String) raizActual.valorNodo();
        int dato1= obtenerCodigoAscii(nombre1);
        
        // Si el valor es menor que el valor del nodo actual, se inserta en el subárbol izquierdo
        if (valor <dato1 ) {
            raizActual.ramaIzdo(insertarRecursivo(raizActual.izdo, valor))  ;
        } else if (valor > dato1) {
            // Si el valor es mayor que el valor del nodo actual, se inserta en el subárbol derecho
            raizActual.ramaDcho(insertarRecursivo(raizActual.dcho, valor))  ;
        } else {
            // Si el valor es igual, no se permite la inserción de valores duplicados
            // (puedes cambiar esta lógica si quieres permitir duplicados)
            System.out.println("El valor " + valor + " ya existe en el árbol.");
        }

        return raizActual;
    }
	/*public static void insertar (Object valor )throws Exception{
		Comparador dato;
		dato = (Comparador) valor;
		raiz = insertar(raiz, dato);
	}*/
	//método interno para realizar la operación
	protected static Nodo insertar(Nodo raizSub, Comparador dato) throws Exception{
		if (raizSub == null)
			raizSub = new Nodo(dato);
		else if (dato.menorQue(raizSub.valorNodo()))
		{
			Nodo iz;
			iz = insertar(raizSub.subarbolIzdo(), dato);
			raizSub.ramaIzdo(iz);
			raizSub.subarbolDcho();
		}

		else if (dato.mayorQue(raizSub.valorNodo()))
		{
			Nodo dr;
			dr = insertar(raizSub.subarbolDcho(), dato);
			raizSub.ramaDcho(dr);
		}
		else
			throw new Exception("Nodo duplicado");
		return raizSub;
	}

	public void eliminar (Object valor) throws Exception{
		Comparador dato;
		dato = (Comparador) valor;
		raiz = eliminar(raiz, dato);

	}
	//método interno para realizar la operación
	protected Nodo eliminar (Nodo raizSub, Comparador dato) throws Exception{
		if (raizSub == null)
			throw new Exception ("No encontrado el nodo con la clave");
		else if (dato.menorQue(raizSub.valorNodo())){
			Nodo iz;
			iz = eliminar(raizSub.subarbolIzdo(), dato);
			raizSub.ramaIzdo(iz);
		}
		else if (dato.mayorQue(raizSub.valorNodo())){
			Nodo dr;
			dr = eliminar(raizSub.subarbolDcho(), dato);
			raizSub.ramaDcho(dr);
		}
		else // Nodo encontrado 
		{
			Nodo q;
			q = raizSub; // nodo a quitar del árbol
			if (q.subarbolIzdo() == null)
				raizSub = q.subarbolDcho();
			else if (q.subarbolDcho() == null)
				raizSub = q.subarbolIzdo();
			else
			{ // tiene rama izquierda y derecha 
				q = reemplazar(q);
			}
			q = null;
		}
		return raizSub;
	}
	// método interno para susutituir por el mayor de los menores
	private Nodo reemplazar(Nodo act){
		Nodo a, p;
		p = act;
		a = act.subarbolIzdo(); // rama de nodos menores
		while (a.subarbolDcho() != null){
			p = a;
			a = a.subarbolDcho();
		}
		act.nuevoValor(a.valorNodo());
		if (p == act)
			p.ramaIzdo(a.subarbolIzdo());
		else
			p.ramaDcho(a.subarbolIzdo());
		return a;
	}
	public static void Maximo(Nodo raizSub) throws Exception {
		/*13.5. Construir un método en la clase ArbolBinarioBusqueda que encuentre el nodo máximo.*/
		/*El nodo máximo en un árbol es el nodo con el valor más grande en todo el árbol.*/
		if (raizSub != null) {
			Maximo(raizSub.subarbolIzdo());
			Maximo(raizSub.subarbolDcho());
			if((int)raizSub.valorNodo()> nuevoNodo) {
				nuevoNodo = (int)raizSub.valorNodo();
			}
		}	
		
	}
	
	public static void publicar() {
		System.out.println( "El numero mayor es: "+nuevoNodo+ " ");
		
	}
}

