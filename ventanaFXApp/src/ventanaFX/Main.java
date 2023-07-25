package ventanaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Árbol de Nombres de Archivos");

        // Crear el árbol binario de búsqueda
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

        // Crear el TreeView
        TreeView<String> treeView = new TreeView<>();
        treeView.setRoot(createTreeItems(arbol.raizArbol()));

        // Crear la escena con el TreeView
        Scene scene = new Scene(treeView, 800, 600);

        // Mostrar la ventana
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método recursivo para crear los TreeItems a partir del árbol binario
    private TreeItem<String> createTreeItems(Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        TreeItem<String> treeItem = new TreeItem<>((String) nodo.valorNodo());
        treeItem.setExpanded(true);
        treeItem.getChildren().add(createTreeItems(nodo.subarbolIzdo()));
        treeItem.getChildren().add(createTreeItems(nodo.subarbolDcho()));

        return treeItem;
    }
}
