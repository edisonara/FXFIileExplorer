package ventanaFX;

public class ArbolBinario {
	protected static Nodo raiz;
	public static int contar= 0;
	protected static int sum, sum2;
	public static String preordenD;
	public static String inordenD;
	public static String posordenD;
	public static String raices;
	public ArbolBinario() {
		raiz = null;
	}

	public ArbolBinario(Nodo raiz) {
		this.raiz = raiz;
	}

	public Nodo raizArbol() {
		return raiz;
	}

	@Override
	public String toString() {
		return "ArbolBinario [raiz=" + raiz.toString() + "]";
	}

	// Comprueba el estatus del árbol
	boolean esVacio() {
		return raiz == null;
	}

	public static Nodo nuevoArbol(Nodo ramaIzqda, Object dato, Nodo ramaDrcha) {
		return new Nodo(ramaIzqda, dato, ramaDrcha);
	}

	// Recorrido de un árbol binario en preorden
	public static void preorden(Nodo r) {
		if (r != null) {
			
			preordenD+= r.visitar(); 
			preorden(r.subarbolIzdo());
			preorden(r.subarbolDcho());
		}
	}
	public static String NodoRaiz(Nodo r) {
		if (r != null) {
		return r.visitar(); }
		return null;
	}
	
	public static void raicesD(Nodo r) {
		if (r != null) {
			if (r==null) {
			raices+= r.visitar(); 
			raicesD(r.subarbolIzdo());
			raicesD(r.subarbolDcho());
			}
		}
		
	}
	
	

	// Recorrido de un árbol binario en inorden
	public static void inorden(Nodo r) {
		if (r != null) {
			inorden(r.subarbolIzdo());
			inordenD+=r.visitar();
			inorden(r.subarbolDcho());
		}
	}
	

	// Recorrido de un árbol binario en postorden
	public static void postorden(Nodo r) {
		if (r != null) {
			postorden(r.subarbolIzdo());
			postorden(r.subarbolDcho());
			posordenD+=r.visitar();
		}
	} 
	public static void contarHojas(Nodo r) {
		//13.7. Escribir un método recursivo que cuente las hojas de un árbol binario.

		if (r != null) {
			contar+=1;
			contarHojas(r.subarbolDcho());
			contarHojas(r.subarbolIzdo());
		}
		
	}
}
