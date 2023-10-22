package juego;


import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Planta {
  private double x;
    private double y;
    private int ancho;
    private int alto;
    private double velocidad;
    private Image imagen;
    private boolean esVertical;

    public Planta(double x, double y, int ancho, int alto, double v, boolean esVertical, Image imagen){
        this.x = x;
        this.y = y; 
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = v;
        this.esVertical = esVertical;
        this.imagen = imagen;
    } 
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    
    public double getAncho() {
      return ancho;
    }
    
    public double getAlto() {
      return alto;
    }  
    
    public void desplazarseIzq(){
        x -= velocidad;
    }
    public void desplazarseDer(){
        x += velocidad;
    }
    
    public void desplazarseArb(){
        y -= velocidad;
    }
    public void desplazarseAbj(){
        y += velocidad;
    }

    public void dibujar(Entorno e){
      e.dibujarImagen(imagen, x, y, 0);
    }
    
    public boolean chocasteConH(Entorno e, int i) {
    return x <= 0 + this.ancho ||x >= e.ancho() - i;    
  }
    
    public boolean chocasteConV(Entorno e, int i) {
    return y <= 0 + this.alto ||y >= e.alto() - i;    
  }
    
    public void cambiarTrayectoria() {
      velocidad *= -1;
      if (esVertical) {
            if (velocidad > 0) {
                imagen = Herramientas.cargarImagen("TanqueAbj.png");
            } else {
                imagen = Herramientas.cargarImagen("TanqueArr.png");
            }
        } else {
            if (velocidad > 0) {
                imagen = Herramientas.cargarImagen("TanqueDer.png");
            } else {
                imagen = Herramientas.cargarImagen("TanqueIzq.png");
            }
        }
  }
    
    public boolean chocasteConPersonaje(Personaje personaje) {
        // Verifica si el enemigo colisiona con el personaje
        return (x + ancho >= personaje.getX() && x <= personaje.getX() + personaje.getAncho() &&
                y + alto >= personaje.getY() && y <= personaje.getY() + personaje.getAlto());
    }
    
    
}