package juego;

import java.awt.Color;

public class Manzana {
    private double x;
    private double y;
    private int ancho;
    private int alto;
    private Color color;

    public Manzana(double x, double y, int ancho, int alto, Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }

    public Color getColor() {
        return color;
    }
}
