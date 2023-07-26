package ventanaFX;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GestorArchivos extends Application {

    private TreeView<String> treeView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestor de archivos");

        // Crear el árbol binario
        Nodo raiz = new Nodo("C:");
        ArbolBinario arbol = new ArbolBinario(raiz);

        // Crear los nodos del árbol
        Nodo nodo1 = new Nodo("Windows");
        Nodo nodo2 = new Nodo("Program Files");
        Nodo nodo3 = new Nodo("Users");
        Nodo nodo4 = new Nodo("Documents");
        Nodo nodo5 = new Nodo("Downloads");

        // Insertar los nodos en el árbol
        raiz.ramaIzdo(nodo1);
        raiz.ramaDcho(nodo2);
        nodo1.ramaIzdo(nodo3);
        nodo1.ramaDcho(nodo4);
        nodo2.ramaIzdo(nodo5);

        // Crear los elementos del árbol
        TreeItem<String> rootItem = new TreeItem<>("C:");
        rootItem.setExpanded(true);
        TreeItem<String> node1 = new TreeItem<>("Windows");
        TreeItem<String> node2 = new TreeItem<>("Program Files");
        TreeItem<String> node3 = new TreeItem<>("Users");
        TreeItem<String> node4 = new TreeItem<>("Documents");
        TreeItem<String> node5 = new TreeItem<>("Downloads");

        // Agregar los elementos al árbol
        rootItem.getChildren().addAll(node1, node2);
        node1.getChildren().addAll(node3, node4);
        node2.getChildren().add(node5);

        // Crear el TreeView
        treeView = new TreeView<>(rootItem);

        // Crear los botones
        Button insertarButton = new Button("Insertar");
        insertarButton.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String newValue = "Nuevo elemento";
                Nodo nuevoNodo = new Nodo(newValue);
                selectedItem.getChildren().add(new TreeItem<>(newValue));
                Nodo nodoPadre = buscarNodo(arbol.raizArbol(), selectedItem.getValue());
                nodoPadre.ramaIzdo(nuevoNodo);
            }
        });
        Button eliminarButton = new Button("Eliminar");
        eliminarButton.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                selectedItem.getParent().getChildren().remove(selectedItem);
                Nodo nodoPadre = buscarNodo(arbol.raizArbol(), selectedItem.getValue());
                nodoPadre.ramaIzdo(null);
            }
        });
        Button modificarButton = new Button("Modificar");
        modificarButton.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String newValue = "Elemento modificado";
                selectedItem.setValue(newValue);
                Nodo nodoPadre = buscarNodo(arbol.raizArbol(), selectedItem.getValue());
                nodoPadre.nuevoValor(newValue);
            }
        });

        // Agregar los botones a un HBox
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(insertarButton, eliminarButton, modificarButton);

        // Crear el encabezado
        Text headerText = new Text("Gestor de archivos");
        headerText.setFont(Font.font("Arial", 24));
        headerText.setFill(Color.RED);

        // Crear la animación del encabezado
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(headerText.fillProperty(), Color.RED)),
                new KeyFrame(Duration.seconds(1), new KeyValue(headerText.fillProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(2), new KeyValue(headerText.fillProperty(), Color.BLUE)),
                new KeyFrame(Duration.seconds(3), new KeyValue(headerText.fillProperty(), Color.RED))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Crear el BorderPane y agregar los elementos
        BorderPane root = new BorderPane();
        root.setTop(headerText);
        root.setCenter(treeView);
        root.setBottom(buttonBox);

        // Crear la escena y agregar el BorderPane
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    

    

    private Nodo buscarNodo(Nodo nodo, String valor) {
        if (nodo == null) {
            return null;
        }
        if (nodo.valorNodo().equals(valor)) {
            return nodo;
        }
        Nodo nodoIzquierdo = buscarNodo(nodo.subarbolIzdo(), valor);
        if (nodoIzquierdo != null) {
            return nodoIzquierdo;
        }
        Nodo nodoDerecho = buscarNodo(nodo.subarbolDcho(), valor);
        if (nodoDerecho != null) {
            return nodoDerecho;
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}