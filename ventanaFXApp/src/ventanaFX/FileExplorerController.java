package ventanaFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
//import ventanaFX.GestorArchivos2.Nodo;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;

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
    private AnchorPane paneArbol;
    @FXML
    private AnchorPane dataVm;
    
    

    @FXML
    private TabPane tabPane;

    @FXML
    private TreeView<String> treeView;
    @FXML
    private TreeView<String> ventana02;


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
				agregarArchivo();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    	VerArchivo.setOnSelectionChanged(event -> {
            if (VerArchivo.isSelected()) {
            	parte2();
            }
        });
    }

    private void agregarArchivo() throws Exception {
    	
    	System.out.println("inicializando...");
    	
        String nombre = nombreArchivo.getText();
        System.out.println("inicializando..." + nombre);

        if (!nombre.isEmpty()) {
            
                // Agregar el archivo al árbol binario de búsqueda
        	
        	ArbolBinarioBusqueda.insertar(nombre);

                // Dibujar el árbol en el Tab VerArbol
                dibujarArbol(arbolBusqueda.raizArbol().subarbolIzdo(), paneArbol);
                dibujarArbol(arbolBusqueda.raizArbol().subarbolDcho(), paneArbol);
                // Manejar la excepción si el nodo ya existe
                System.out.println("El nodo ya existe en el árbol.");
                
            
        }

        
    }

// Método para dibujar el árbol binario en el AnchorPane
    private void dibujarArbol(Nodo nodo, AnchorPane pane) {
        // Limpia el contenido actual del AnchorPane
        pane.getChildren().clear();

        // Dibuja el árbol en el AnchorPane
        if (nodo != null) {
            // Dibujar el nodo
            Circle circulo = new Circle(400, 40, RADIO_NODO);
            circulo.setFill(Color.YELLOW);
            pane.getChildren().add(circulo);
            Text texto = new Text(400 - 4, 40 + 4, nodo.valorNodo().toString());
            pane.getChildren().add(texto);

            // Dibujar las líneas a los hijos
            if (nodo.subarbolIzdo() != null) {
                dibujarLinea(400 - ESPACIO_VERTICAL, 40 + ESPACIO_VERTICAL, 400, 40, pane);
                dibujarArbol(nodo.subarbolIzdo(), pane);
            }
            if (nodo.subarbolDcho() != null) {
                dibujarLinea(400 + ESPACIO_VERTICAL, 40 + ESPACIO_VERTICAL, 400, 40, pane);
                dibujarArbol(nodo.subarbolDcho(), pane);
            }
        }
    }

    private void dibujarLinea(double x1, double y1, double x2, double y2, AnchorPane pane) {
        Line linea = new Line(x1, y1, x2, y2);
        pane.getChildren().add(linea);
    }
  
    private void showAlert() {
    	// Crear el árbol de nombres de archivos
    	dataVm.getChildren().clear();
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        File folder = new File("C:\\Users\\user\\eclipse-workspace\\ventanaFXApp\\src\\Root"); // Cambiar "RUTA_DE_TU_CARPETA" por la ruta de la carpeta de tu proyecto
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            Arrays.sort(fileList, Comparator.comparing(File::getName));
            for (File file : fileList) {
                if (file.isFile()) {
                    try {
                        arbol.insertar(file.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Crear la interfaz gráfica
        TreeItem<String> rootItem = createTreeItem(arbol.raizArbol());
        TreeView<String> treeView = new TreeView<>(rootItem);
        treeView.setShowRoot(false);
        VBox vbox = new VBox(treeView);
        dataVm.getChildren().add(vbox);
        
    	
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
    
    private void parte2() {
    	//dataVm.getChildren();
    	// Crear el árbol binario de búsqueda
    	TreeView<String> ventana02 = new TreeView<>();
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // Ruta de la carpeta con los archivos
        String folderPath = "C:\\Users\\user\\eclipse-workspace\\ventanaFXApp\\src\\Root";
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
        createTreeItems(ventana02.getRoot(), arbol.raizArbol());
        
        
        
        
    }

    // Método recursivo para crear los TreeItems a partir del árbol binario
    private void createTreeItems(TreeItem<String> parent, Nodo nodo) {
        if (nodo == null) {
            return;
        }

        TreeItem<String> treeItem = new TreeItem<>((String) nodo.valorNodo());
        treeItem.setExpanded(true);
        parent.getChildren().add(treeItem);

        createTreeItems(treeItem, nodo.subarbolIzdo());
        createTreeItems(treeItem, nodo.subarbolDcho());
    }
    
//===============================================================================
    

    // Método para dibujar una línea entre dos puntos
    /*private void dibujarLinea(double x1, double y1, double x2, double y2, AnchorPane pane) {
        Line line = new Line(x1, y1, x2, y2);
        pane.getChildren().add(line);
    }*/
    
//===============================================================================
    

    // Método para dibujar una línea entre dos puntos
    /*private void dibujarLinea(double x1, double y1, double x2, double y2, AnchorPane pane) {
        Line line = new Line(x1, y1, x2, y2);
        pane.getChildren().add(line);
    }*/

}
