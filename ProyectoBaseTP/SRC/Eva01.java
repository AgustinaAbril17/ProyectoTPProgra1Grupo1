package juego;

import java.awt.Color;
import java.awt.Font;

import entorno.Entorno;

public class Eva01 {
    private double x;
    private double y;
    private int ancho;
    private int alto;
    private double factorDesplazamiento;
    private Tablero tablero; // Referencia al Tablero
    private Disparo disparo; // Propiedad para gestionar el disparo
    private String direccionDisparo = "arriba"; // Inicialmente, dispara hacia arriba

    public Eva01(double x, double y, int ancho, int alto, double f, Tablero tablero) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.factorDesplazamiento = f;
        this.tablero = tablero;
        this.disparo = new Disparo(getX(), getY()); // Crear una instancia de Disparo
    }

    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, Color.MAGENTA);
        
        // Dibuja el cartel en la parte superior izquierda de la pantalla
        e.cambiarFont("Arial", 15, Color.WHITE); // Usar Entorno.PLAIN en lugar de Font.PLAIN
        e.escribirTexto("Proximo disparo: " + direccionDisparo, 15, 20); // Eliminar el argumento de Color
    }

    public void moverIzquierda(Entorno e) {
        if (e.estaPresionada('a') && x - ancho / 2 > 0 && !colisionIzquierda()) {
            x -= factorDesplazamiento;
            disparo.setX(x); // Actualizar la posición del disparo
            direccionDisparo = "izquierda";
        }
    }
    public void moverDerecha(Entorno e) {
        if (e.estaPresionada('d') && x + ancho / 2 < e.ancho() && !colisionDerecha()) {
            x += factorDesplazamiento;
            disparo.setX(x); // Actualizar la posición del disparo
            direccionDisparo = "derecha";
        }
    }
    public void moverArriba(Entorno e) {
        if (e.estaPresionada('w') && y - alto / 2 > 0 && !colisionArriba()) {
            y -= factorDesplazamiento;
            disparo.setY(y); // Actualizar la posición del disparo
            direccionDisparo = "arriba";
        }
    }
    public void moverAbajo(Entorno e) {
        if (e.estaPresionada('s') && y + alto / 2 < e.alto() && !colisionAbajo()) {
            y += factorDesplazamiento;
            disparo.setY(y); // Actualizar la posición del disparo
            direccionDisparo = "abajo";
        }
    }

    public interface Colisionable {
        double getX();
        double getY();
        int getAncho();
        int getAlto();
    }

    public boolean chocasteCon(Colisionable objeto) {
        double distanciaX = Math.abs(x - objeto.getX());
        double distanciaY = Math.abs(y - objeto.getY());
        double sumaAnchos = (ancho / 2) + (objeto.getAncho() / 2);
        double sumaAltos = (alto / 2) + (objeto.getAlto() / 2);
        return (distanciaX < sumaAnchos) && (distanciaY < sumaAltos);
    }

    private boolean colisionIzquierda() {
        for (Manzana manzana : tablero.getManzana()) {
            if (x - ancho / 2 < manzana.getX() + manzana.getAncho() / 2 &&
                x - ancho / 2 > manzana.getX() - manzana.getAncho() / 2 &&
                y > manzana.getY() - manzana.getAlto() / 2 &&
                y < manzana.getY() + manzana.getAlto() / 2) {
                return true;
            }
        }
        return false;
    }
    private boolean colisionDerecha() {
        for (Manzana manzana : tablero.getManzana()) {
            if (x + ancho / 2 > manzana.getX() - manzana.getAncho() / 2 &&
                x + ancho / 2 < manzana.getX() + manzana.getAncho() / 2 &&
                y > manzana.getY() - manzana.getAlto() / 2 &&
                y < manzana.getY() + manzana.getAlto() / 2) {
                return true;
            }
        }
        return false;
    }
    private boolean colisionArriba() {
        for (Manzana manzana : tablero.getManzana()) {
            if (y - alto / 2 < manzana.getY() + manzana.getAlto() / 2 &&
                y - alto / 2 > manzana.getY() - manzana.getAlto() / 2 &&
                x > manzana.getX() - manzana.getAncho() / 2 &&
                x < manzana.getX() + manzana.getAncho() / 2) {
                return true;
            }
        }
        return false;
    }
    private boolean colisionAbajo() {
        for (Manzana manzana : tablero.getManzana()) {
            if (y + alto / 2 > manzana.getY() - manzana.getAlto() / 2 &&
                y + alto / 2 < manzana.getY() + manzana.getAlto() / 2 &&
                x > manzana.getX() - manzana.getAncho() / 2 &&
                x < manzana.getX() + manzana.getAncho() / 2) {
                return true;
            }
        }
        return false;
    }
    
    public void disparar(Entorno e) {
        if (!disparo.estaDisparado()) {
            double velocidadX = 0;
            double velocidadY = 0;

            // Configura la velocidad del disparo según la dirección guardada
            if (direccionDisparo.equals("arriba")) {
                velocidadY = -2; // Hacia arriba
            } else if (direccionDisparo.equals("abajo")) {
                velocidadY = 2; // Hacia abajo
            } else if (direccionDisparo.equals("izquierda")) {
                velocidadX = -2; // Hacia la izquierda
            } else if (direccionDisparo.equals("derecha")) {
                velocidadX = 2; // Hacia la derecha
            }

            disparo.disparar(x, y, velocidadX, velocidadY);
        }
    }
    
    public boolean estaEnFrente(double angelX, double angelY) {
        // Ajusta el rango de distancia en el eje X y Y según tu necesidad
        double rangoX = 30; // Rango en el eje X para estar en frente
        double rangoY = 50; // Rango en el eje Y para estar en frente

        if (direccionDisparo.equals("derecha") && x < angelX && angelX - x <= rangoX && Math.abs(y - angelY) <= rangoY) {
            return true;
        } else if (direccionDisparo.equals("izquierda") && x > angelX && x - angelX <= rangoX && Math.abs(y - angelY) <= rangoY) {
            return true;
        } else if (direccionDisparo.equals("abajo") && y < angelY && angelY - y <= rangoY && Math.abs(x - angelX) <= rangoX) {
            return true;
        } else if (direccionDisparo.equals("arriba") && y > angelY && y - angelY <= rangoY && Math.abs(x - angelX) <= rangoX) {
            return true;
        }
        return false;
    }

    public Disparo getDisparo() {
        return disparo;
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
