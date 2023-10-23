package juego;

import java.awt.Color;
import entorno.Entorno;
import juego.Eva01.Colisionable;

public class Tanque implements Colisionable {	//Colisionable se vincula con el metodo Colisonable en Eva01.java
    private double x;
    private double y;
    private int ancho;
    private int alto;
    private double velocidad;
    private Color color;
    private boolean seMueveHorizontal;

    public Tanque(double x, double y, int ancho, int alto, double velocidad, boolean seMueveHorizontal, Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.seMueveHorizontal = seMueveHorizontal;
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

    public void mover() {
        if (seMueveHorizontal) {
            x += velocidad;
            if (x <= 0 + ancho || x >= 1000 - ancho) {
                velocidad *= -1;
            }
        } else {
            y += velocidad;
            if (y <= 0 + alto || y >= 755 - alto) {
                velocidad *= -1;
            }
        }
    }

    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, color);
    }
}
