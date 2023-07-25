package ventanaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GestorArchivos2 extends Application {

    private static final double RADIO_NODO = 20;
    private static final double ESPACIO_VERTICAL = 80;
    private static final double ESPACIO_HORIZONTAL = 40;

    private Nodo raiz;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Crear el árbol
        raiz = new Nodo(50);
        raiz.insertar(30);
        raiz.insertar(70);
        raiz.insertar(20);
        raiz.insertar(40);
        raiz.insertar(60);
        raiz.insertar(100);

        // Crear el pane para dibujar el árbol
        Pane pane = new Pane();

        // Dibujar el árbol
        dibujarArbol(raiz, pane, 400, 40, 200);

        // Crear la escena y mostrarla
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dibujarArbol(Nodo nodo, Pane pane, double x, double y, double espacio) {
        if (nodo != null) {
            // Dibujar el nodo
            Circle circulo = new Circle(x, y, RADIO_NODO);
            circulo.setFill(Color.YELLOW);
            pane.getChildren().add(circulo);
            Text texto = new Text(x - 4, y + 4, Integer.toString(nodo.valor));
            pane.getChildren().add(texto); 
            // Dibujar las líneas a los hijos
            if (nodo.izquierdo != null) {
                dibujarLinea(x - espacio, y + ESPACIO_VERTICAL, x, y, pane);
                dibujarArbol(nodo.izquierdo, pane, x - espacio, y + ESPACIO_VERTICAL, espacio / 2);
            }
            if (nodo.derecho != null) {
                dibujarLinea(x + espacio, y + ESPACIO_VERTICAL, x, y, pane);
                dibujarArbol(nodo.derecho, pane, x + espacio, y + ESPACIO_VERTICAL, espacio / 2);
            }
        }
    }

    private void dibujarLinea(double x1, double y1, double x2, double y2, Pane pane) {
        Line linea = new Line(x1, y1, x2, y2);
        pane.getChildren().add(linea);
    }

    public static class Nodo {
        int valor;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(int valor) {
            this.valor = valor;
            izquierdo = null;
            derecho = null;
        }

        public void insertar(int valor) {
            if (valor < this.valor) {
                if (izquierdo == null) {
                    izquierdo = new Nodo(valor);
                } else {
                    izquierdo.insertar(valor);
                }
            } else {
                if (derecho == null) {
                    derecho = new Nodo(valor);
                } else {
                    derecho.insertar(valor);
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
