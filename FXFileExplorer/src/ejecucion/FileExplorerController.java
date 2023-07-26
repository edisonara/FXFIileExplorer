package ejecucion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import ventanaFX.ArbolBinarioBusqueda;
import ventanaFX.Nodo;

public class FileExplorerController implements Initializable {

    @FXML
    private TextField nombreArchivo;

    @FXML
    private Button Agregar;

    @FXML
    private Tab VerArbol;
    @FXML
    private Tab VerArchivo;
    @FXML
    private Tab ventana3;
    @FXML
    private Tab ventana4;
    @FXML
    private Tab ventana5;
    @FXML
    private AnchorPane paneArbol;
    @FXML
    private ScrollPane ventana1;
    @FXML
    private AnchorPane pagina1Ancla;
    
    @FXML
    private AnchorPane dataVm;
    @FXML
    private Pane ventana2;
    
    @FXML
    private TextField preorden;
    @FXML
    private TextField inorden;
    @FXML
    private TextField posorden;
    
    @FXML
    private TextField NodoRaiz;
    @FXML
    private TextField NumNodos;
    @FXML
    private TextField NodosHoja;
    
    
    @FXML
    private TextField AModificar;
    
    @FXML
    private Button Cambiar;
    
    
    

    private TreeItem<String> rootItem;
    private static final double RADIO_NODO = 10;
    private static final double ESPACIO_VERTICAL = 10;
    private static final double ESPACIO_HORIZONTAL = 10;

    private Nodo raiz;
    private ArbolBinarioBusqueda arbolBusqueda;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar el árbol con un nodo raíz vacío
        //rootItem = new TreeItem<>("Archivos");
        //treeView.setRoot(rootItem);
        //treeView.setShowRoot(true);
    	arbolBusqueda = new ArbolBinarioBusqueda();
    	Agregar.setOnAction(e -> {
			try {
				agregarArchivo1();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    	VerArchivo.setOnSelectionChanged(event -> {
            if (VerArchivo.isSelected()) {
            	parte2(ventana2);
            }
        });
    	VerArbol.setOnSelectionChanged(event -> {
            if (VerArbol.isSelected()) {
            	vetana1(ventana1);
            }
        });
    	
        
            
        ventana3.setOnSelectionChanged(event -> {
                if (ventana3.isSelected()) {
                		try {
							ventana3F();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
                }
            });
        
        
        ventana4.setOnSelectionChanged(event -> {
            if (ventana4.isSelected()) {
            		try {
            			ventana4F();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
            }
        });
        Cambiar.setOnAction(e -> {
			try {
				ventana5F();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        
        

    }
    
    /// Pagina 1 a considerar
    /*
     * 
     * 
     * 
     * */
    
    private void agregarArchivo1() throws Exception {
        System.out.println("inicializando...");

        String nombre = nombreArchivo.getText();
        System.out.println("inicializando..." + nombre);
        // Obtenemos los nombres de los archivos de la carpeta
        String folderPath = "C:\\Users\\user\\eclipse-workspace\\FXFileExplorer\\src\\Root";
        File folder = new File(folderPath);
        //NumNodos.setText("Edison.....");
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        arbol.insertar(file.getName());
                    } catch (Exception e) {
                        System.out.println("Error al insertar en el árbol: " + e.getMessage());
                    }
                }
            }
        }
    }
        
    private void vetana1(ScrollPane contenedor) {
    	ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // Ruta de la carpeta con los archivos
        String folderPath = "C:\\Users\\user\\eclipse-workspace\\FXFileExplorer\\src\\Root";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        arbol.insertar(file.getName());
                    } catch (Exception e) {
                        System.out.println("Error al insertar en el árbol: " + e.getMessage());
                    }
                }
            }
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        Pane pane = new Pane();
        pane.setPrefSize(800, 600);
        scrollPane.setContent(pane);
        dibujarArbol(arbol.raizArbol(), pane, 400, 40, 400, 40);

        // Add the ScrollPane with the drawn tree to the appropriate Tab
        contenedor.setContent(scrollPane);
    }

    

 // Modify createTreeItems1 to correctly create the TreeItems for the tree
    private TreeItem<String> createTreeItems1(Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        TreeItem<String> treeItem = new TreeItem<>((String) nodo.valorNodo());
        treeItem.setExpanded(true);
        treeItem.getChildren().add(createTreeItems1(nodo.subarbolIzdo()));
        treeItem.getChildren().add(createTreeItems1(nodo.subarbolDcho()));

        return treeItem;
    }

 // Modify dibujarArbol to correctly draw the tree in the Pane
    private double dibujarArbol(Nodo nodo, Pane pane, double x, double y, double prevX, double prevY) {
        if (nodo == null) {
            return x; // Return the current x position for next node placement
        }

        // Dibujar el nodo (puedes personalizar el nodo gráfico aquí)
        Circle circulo = new Circle(x, y, 20);
        circulo.setFill(Color.YELLOW);
        pane.getChildren().add(circulo);
        Text texto = new Text(x - 4, y + 4, nodo.valorNodo().toString());
        pane.getChildren().add(texto);

        // Dibujar la línea al nodo padre
        if (prevX != x || prevY != y) {
            dibujarLinea(prevX, prevY, x, y, pane);
        }

        // Calcular el espacio horizontal para los hijos
        double espacioHorizontal = 80;
        // Dibujar los hijos y continuar dibujando el árbol
        x = dibujarArbol(nodo.subarbolIzdo(), pane, x - espacioHorizontal, y + 80, x, y);
        x = dibujarArbol(nodo.subarbolDcho(), pane, x + espacioHorizontal, y + 80, x, y);

        return x;
    }
    
    // Método para dibujar una línea entre dos puntos en el Pane
    private void dibujarLinea(double startX, double startY, double endX, double endY, Pane pane) {
        Line line = new Line(startX, startY, endX, endY);
        pane.getChildren().add(line);
    }
  


    private void ventana3F() throws Exception {
    	ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // Ruta de la carpeta con los archivos
        String folderPath = "C:\\Users\\user\\eclipse-workspace\\FXFileExplorer\\src\\Root";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        arbol.insertar(file.getName());
                    } catch (Exception e) {
                        System.out.println("Error al insertar en el árbol: " + e.getMessage());
                    }
                }
            }
        }
        
        arbol.preorden(arbol.raizArbol());
        //arbol.Maximo(arbol.raizArbol());
        int numero = arbol.maximo();
        String cadena = Integer.toString(numero);
        preorden.setText(arbol.preordenD);
        arbol.inorden(arbol.raizArbol());
        inorden.setText(arbol.inordenD);
        arbol.postorden(arbol.raizArbol());
        posorden.setText(arbol.posordenD);
 
        
        
    	
    }
 // Método para crear el TreeItem recursivamente
    private TreeItem<String> createTreeItem(Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        TreeItem<String> treeItem = new TreeItem<>((String) nodo.valorNodo());
        treeItem.getChildren().add(createTreeItem(nodo.subarbolIzdo()));
        treeItem.getChildren().add(createTreeItem(nodo.subarbolDcho()));
        return treeItem;
    }
    
    private void parte2(Pane contenedor) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // Ruta de la carpeta con los archivos
        String folderPath = "C:\\Users\\user\\eclipse-workspace\\FXFileExplorer\\src\\Root";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        arbol.insertar(file.getName());
                    } catch (Exception e) {
                        System.out.println("Error al insertar en el árbol: " + e.getMessage());
                    }
                }
            }
        }

        // Crear el objeto TreeView y el nodo raíz del árbol visual
        TreeView<String> treeView = new TreeView<>();
        TreeItem<String> rootItem = new TreeItem<>("Archivos");
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
        contenedor.getChildren().add(treeView);

        // Ajustar las propiedades del objeto TreeView para que llene todo el contenedor
        treeView.prefWidthProperty().bind(contenedor.widthProperty());
        treeView.prefHeightProperty().bind(contenedor.heightProperty());

        // Crear los elementos del árbol y agregarlos al objeto TreeView
        createTreeItems(rootItem, arbol.raizArbol());
    }

    // Método recursivo para crear los TreeItems a partir del árbol binario
    private void createTreeItems(TreeItem<String> parent, Nodo nodo) {
        if (nodo == null) {
            return;
        }

        TreeItem<String> treeItem = new TreeItem<>((String) nodo.valorNodo());
        treeItem.setExpanded(true);

        if (parent != null) {
            parent.getChildren().add(treeItem);
        } else {
            // Manejar el caso en el que parent es nulo
            // Por ejemplo, agregar treeItem a la raíz del árbol
        	TreeView<String> treeView = (TreeView<String>) parent.getChildren();
            treeView.setRoot(treeItem);
        }

        // Llama a los métodos recursivos para los hijos izquierdo y derecho
        createTreeItems(treeItem, nodo.subarbolIzdo());
        createTreeItems(treeItem, nodo.subarbolDcho());
    }

    private void ventana4F() throws Exception {
    	ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // Ruta de la carpeta con los archivos
        String folderPath = "C:\\Users\\user\\eclipse-workspace\\FXFileExplorer\\src\\Root";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        arbol.insertar(file.getName());
                    } catch (Exception e) {
                        System.out.println("Error al insertar en el árbol: " + e.getMessage());
                    }
                }
            }
        }
        
        arbol.contarHojas(arbol.raizArbol());
        //arbol.Maximo(arbol.raizArbol());
        int numero = arbol.maximo();
        String cadena = Integer.toString(numero);
        NumNodos.setText("Tenemos "+arbol.contar + " Nodos.");
        arbol.Maximo(arbol.raizArbol());
        NodosHoja.setText("Tenemos "+arbol.maximo());
        NodoRaiz.setText(arbol.NodoRaiz(arbol.raizArbol()));
 
        
        
    	
    }
    
    private void ventana5F() {
        // Obtener el nombre del archivo a eliminar del campo AModificar
        String nombreArchivoEliminar = AModificar.getText();

        // Verificar que el nombre del archivo termine con ".txt"
        if (nombreArchivoEliminar.endsWith(".txt")) {
            // Ruta de la carpeta con los archivos
            String folderPath = "C:\\Users\\user\\eclipse-workspace\\FXFileExplorer\\src\\Root";
            File folder = new File(folderPath);

            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Verificar que el archivo coincida con el nombre a eliminar y termine con ".txt"
                    if (file.isFile() && file.getName().equals(nombreArchivoEliminar)) {
                        eliminarArchivo(file);
                        return; // Terminar el bucle una vez que se elimina el archivo
                    }
                }
            }

            // Si el archivo no se encontró en la carpeta, mostrar un mensaje
            System.out.println("El archivo " + nombreArchivoEliminar + " no se encontró en la carpeta.");
        } else {
            // Mostrar un mensaje de error si el nombre del archivo no termina con ".txt"
            System.out.println("Ingrese un nombre de archivo válido con la extensión .txt");
        }
    }
    
    private void eliminarArchivo(File archivo) {
        try {
            // Convertir el archivo a un objeto Path
            Path path = Paths.get(archivo.getAbsolutePath());
            // Eliminar el archivo usando la clase Files de Java NIO
            Files.delete(path);
            System.out.println("Archivo eliminado: " + archivo.getName());
        } catch (IOException e) {
            System.out.println("Error al eliminar el archivo " + archivo.getName() + ": " + e.getMessage());
        }
    }



}
