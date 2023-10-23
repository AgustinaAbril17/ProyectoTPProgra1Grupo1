package juego;

import java.awt.Color;
import entorno.Entorno;
import juego.Eva01.Colisionable;

public class Angel implements Colisionable {	//Colisionable se vincula con el metodo Colisonable en Eva01.java
    private double x;
    private double y;
    private int ancho;
    private int alto;
    private double velocidad;
    private Color color;
    private String nombre;
    private boolean seMueveHorizontal;

    public Angel(double x, double y, int ancho, int alto, double velocidad, String nombre, boolean seMueveHorizontal, Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.nombre = nombre;
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
    
    public void angelDisparo(Juego juego, Tanque[] tanques) {
        if (!juego.Muerto()) {
            double angelX = getX();
            double angelY = getY();
            double velocidadX = (juego.getEva01().getX() - angelX) * 2; // El doble de la velocidad del Ángel
            double velocidadY = (juego.getEva01().getY() - angelY) * 2;

            Proyectil proyectil = new Proyectil(angelX, angelY, 10, Color.RED, velocidadX, velocidadY);

            // Verificar colisión con Eva01
            if (juego.getEva01().colisionaConProyectil(proyectil)) {
                juego.setMuerto(true);
            }

            // Verificar colisión con tanques
            for (int i = 0; i < tanques.length; i++) {
                if (tanques[i].colisionaConProyectil(proyectil)) {
                    // Eliminar el tanque de la lista
                    tanques[i] = null;
                }
            }
        }
    }

    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, color);
    }
}
