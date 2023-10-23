package juego;

import java.awt.Color;
import entorno.Entorno;

public class Tablero {
    private Manzana[] manzana;
    
    public Tablero(double x, double y, double angulo) {
        // Inicializa el array de manzanas
        this.manzana = new Manzana[] {
        	// Columna 1
            new Manzana(162, 148, 190, 168, Color.GREEN),
            new Manzana(162, 377, 190, 168, Color.GREEN),
            new Manzana(162, 608, 190, 168, Color.GREEN),
            // Columna 2
            new Manzana(412, 148, 190, 168, Color.GREEN),
            new Manzana(412, 377, 190, 168, Color.GREEN),
            new Manzana(412, 608, 190, 168, Color.GREEN),
            // Columna 3
            new Manzana(662, 148, 190, 168, Color.GREEN),
            new Manzana(662, 377, 190, 168, Color.GREEN),
            new Manzana(662, 608, 190, 168, Color.GREEN),
            // Columna 4
            new Manzana(912, 148, 190, 168, Color.GREEN),
            new Manzana(912, 377, 190, 168, Color.GREEN),
            new Manzana(912, 608, 190, 168, Color.GREEN),
        };
    }

    public Manzana[] getManzana() {
        return manzana;
    }

    public void dibujarManzana(Entorno e) {
        for (Manzana manzana : manzana) {
            e.dibujarRectangulo(manzana.getX(), manzana.getY(), manzana.getAncho(), manzana.getAlto(), 0, manzana.getColor());
        }
    }
}
