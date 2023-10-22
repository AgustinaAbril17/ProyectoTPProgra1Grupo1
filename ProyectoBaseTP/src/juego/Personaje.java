package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Personaje {
    private double x;
    private double y;
    private int ancho;
    private int alto;

    private double factorDesplazamiento;

    public Personaje(double x, double y, int ancho, int alto, double f) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.factorDesplazamiento = f;
    }
    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
    }

    public void moverIzquierda(Entorno e) {
    	if(e.estaPresionada('a') && x-ancho/2>0) {
    		x -= factorDesplazamiento;
    	}	
    }

    public void moverDerecha(Entorno e) {
    	if(e.estaPresionada('d') && x+ancho/2<e.ancho()) {
    		x += factorDesplazamiento;
    	}	
    }

    public void moverArriba(Entorno e) {
    	if(e.estaPresionada('w') && y-alto/2>0) {
    		y -= factorDesplazamiento;
    	} 
    }

    public void moverAbajo(Entorno e) {
        if(e.estaPresionada('s') && y+alto/2<e.alto()) {
        	y += factorDesplazamiento;
        }
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

    public double getFactorDesplazamiento() {
        return factorDesplazamiento;
    }
}
