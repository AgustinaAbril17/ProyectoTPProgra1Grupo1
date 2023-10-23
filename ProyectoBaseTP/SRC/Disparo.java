package juego;

import java.awt.Color;
import entorno.Entorno;

public class Disparo {
    private double x;
    private double y;
    private double velocidadX; // Velocidad en el eje X
    private double velocidadY; // Velocidad en el eje Y
    private boolean disparado;

    public Disparo(double x, double y) {
        this.x = x;
        this.y = y;
        this.disparado = true; // Inmediatamente marcamos el disparo como disparado
    }
    
    public void disparar(double xEva, double yEva, double velocidadX, double velocidadY) {
        if (!disparado) {
            x = xEva;
            y = yEva;
            this.velocidadX = velocidadX; // Sobreescribe la velocidad en el eje X
            this.velocidadY = velocidadY; // Sobreescribe la velocidad en el eje Y
            disparado = true;
        }
    }
    
	public boolean estaDisparado() {
	        return disparado;
	}
	
    public void mover() {
        if (disparado) {
            x += velocidadX;
            y += velocidadY;
        }
    }

    public void dibujar(Entorno entorno) {
        if (disparado) {
            entorno.dibujarCirculo(x, y, 20, Color.RED);
        }
    }
	
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    
    public boolean haLlegadoAlBorde(Entorno entorno) {
        return x >= entorno.ancho();
    }
    // Método para verificar colisión con un objeto Angel
    public boolean chocasteCon(Angel angel) {
        double distanciaX = Math.abs(x - angel.getX());
        double distanciaY = Math.abs(y - angel.getY());
        double sumaRadios = 3 + angel.getAncho() / 2; // Ajusta el valor según el Angel
        return (distanciaX < sumaRadios) && (distanciaY < sumaRadios);
    }
    // Método para verificar colisión con un objeto Tanque
    public boolean chocasteCon(Tanque tanque) {
        double distanciaX = Math.abs(x - tanque.getX());
        double distanciaY = Math.abs(y - tanque.getY());
        double sumaRadios = 3 + tanque.getAncho() / 2; // Ajusta el valor según el Tanque
        return (distanciaX < sumaRadios) && (distanciaY < sumaRadios);
    }
}
