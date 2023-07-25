package ventanaFX;

public class ArbolBinario {
	protected static Nodo raiz;
	public static int contar= 0;
	protected static int sum, sum2;

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
			r.visitar();
			preorden(r.subarbolIzdo());
			preorden(r.subarbolDcho());
		}
	}

	// Recorrido de un árbol binario en inorden
	public static void inorden(Nodo r) {
		if (r != null) {
			inorden(r.subarbolIzdo());
			r.visitar();
			inorden(r.subarbolDcho());
		}
	}
	

	// Recorrido de un árbol binario en postorden
	public static void postorden(Nodo r) {
		if (r != null) {
			postorden(r.subarbolIzdo());
			postorden(r.subarbolDcho());
			r.visitar();
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
	public static void sumarNormal(Nodo r) {
		/*13.11. Se dispone de un árbol binario de elementos de tipo entero. Escribir métodos que 
			calculen:
			a) La suma de sus elementos.
			b) A suma de sus elementos que son múltiplos de 3.*/
		if (r != null) {
			sum= sum +(int)r.valorNodo();
			System.out.println(sum);
			sumarNormal(r.subarbolIzdo());
			//sum =sum+(int)r.valorNodo();
			sumarNormal(r.subarbolDcho());
			
			
		}
		
		
	}
	public static void sumarMult3(Nodo r) {
		/*13.11. Se dispone de un árbol binario de elementos de tipo entero. Escribir métodos que 
			calculen:
			a) La suma de sus elementos.
			b) La suma de sus elementos que son múltiplos de 3.*/
		if (r != null) {
			if(((int)r.valorNodo()%3)==0) {
				sum2= sum2 +(int)r.valorNodo();
				System.out.println(sum2);
			}
			sumarMult3(r.subarbolIzdo());
			sumarMult3(r.subarbolDcho());
		}
	}
	
	
	public static int numNodos(Nodo raiz)
	{
		if (raiz == null)
			return 0;
		else
			return 1 + numNodos(raiz.subarbolIzdo()) + numNodos(raiz.subarbolDcho());
	}
}
