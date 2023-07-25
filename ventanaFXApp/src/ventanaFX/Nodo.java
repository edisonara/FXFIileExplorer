package ventanaFX;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Nodo implements Comparador{
	protected Object nombre;
	protected File date; 
	protected Nodo izdo;
	protected Nodo dcho;
	protected int result ;
	

	public Nodo(Object valor) {
		nombre = valor;
		izdo = dcho = null;
		CreacionFile();
	}

	public Nodo(Nodo ramaIzdo, Object valor, Nodo ramaDcho) {
		nombre = valor;
		izdo = ramaIzdo;
		dcho = ramaDcho;
		CreacionFile();
	}
	private void CreacionFile() {
		Integer numero = (Integer) nombre;
		String nombreArchivo = numero.toString() + ".txt";
		File archivo = new File(nombreArchivo);	
		nombre= archivo.getName();

        try {
            if (archivo.createNewFile()) {
                System.out.println("Se ha creado el archivo: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe: " + archivo.getName());
                
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo: " + e.getMessage());
        }
		
	}

	// operaciones de acceso
	public Object valorNodo() {
		return nombre;
	}

	public Nodo subarbolIzdo() {
		return izdo;
	}

	public Nodo subarbolDcho() {
		return dcho;
	}
	
	public void nuevoValor(Object d){ 
		nombre = d; }
	public void ramaIzdo(Nodo n){ 
		izdo = n; }
	public void ramaDcho(Nodo n){ 
		dcho = n; }

	void visitar() {
		System.out.print(nombre + " ");
	}
	
	int suma(int dato) {
		result += dato;
		return result;
		
	}

	public static Nodo nuevoArbol(Nodo ramaIzqda, Object dato, Nodo ramaDrcha) {
		return new Nodo(ramaIzqda, dato, ramaDrcha);
	}

	@Override
	public boolean igualQue(Object q) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean menorQue(Object q) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean menorIgualQue(Object q) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mayorQue(Object q) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mayorIgualQue(Object q) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
