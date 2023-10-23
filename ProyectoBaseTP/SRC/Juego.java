package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.InterfaceJuego;
import java.awt.Rectangle;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Tablero tablero;
    private Eva01 eva01;
    private Angel[] angeles;
    private Tanque[] tanques;
    private boolean muerto = false;
    private long tiempoInicial;  // Marca de tiempo inicial
    private int duracionJuego = 300;  // Duración del juego en segundos (5 minutos)
    private Disparo[] disparos; // Declaración de un arreglo de disparos
    private Rectangle[] rectangulosAngeles; // Array para los rectángulos de Ángeles


    Juego() {
        this.entorno = new Entorno(this, "Neon Genesis Evangelion - Grupo 1", 1075, 755);
        tablero = new Tablero(0, 0, 0);
        eva01 = new Eva01(420, 725, 23, 23, 2, tablero);
        angeles = new Angel[] {
    	    new Angel(200, 32, 41, 41, 2, "Sachiel", true, Color.LIGHT_GRAY),
    	    new Angel(787, 200, 41, 41, 2, "Shamshel", false, Color.RED),
    	    new Angel(287, 500, 41, 41, 2, "Ramiel", false, Color.BLUE),
    	    new Angel(600, 494, 41, 41, 2, "Bardiel", true, Color.DARK_GRAY),
    	};

        tanques = new Tanque[] {
            new Tanque(100, 250, 23, 23, 1.5, true, Color.YELLOW),
            new Tanque(800, 740, 23, 23, 1.5, true, Color.ORANGE),
            new Tanque(47, 300, 23, 23, 1.5, false, Color.CYAN),
            new Tanque(550, 600, 23, 23, 1.5, false, Color.PINK),
            new Tanque(1025, 400, 23, 23, 1.5, false, Color.WHITE),
        };
        
        // Define los rectángulos para los Ángeles según tus necesidades
        rectangulosAngeles = new Rectangle[] {
            new Rectangle(0, 32, 1075, 41),     // Rectángulo para Sachiel
            new Rectangle(787, 0, 41, 755),     // Rectángulo para Shamshel
            new Rectangle(0, 500, 287, 41),    // Rectángulo para Ramiel
            new Rectangle(0, 494, 1075, 41)    // Rectángulo para Bardiel
        };
        
        disparos = new Disparo[20]; // Inicialización del arreglo de disparos con un tamaño de 20
        
        tiempoInicial = System.currentTimeMillis();
        this.entorno.iniciar();
    }

    public void tick() {
        if (!muerto) {
            // Calcula el tiempo transcurrido en segundos
            long tiempoActual = System.currentTimeMillis();
            long tiempoTranscurrido = (tiempoActual - tiempoInicial) / 1000;
            // Calcula el tiempo restante en segundos
            long tiempoRestante = duracionJuego - tiempoTranscurrido;
            // Asegúrate de que el tiempo restante no sea negativo
            if (tiempoRestante < 0) {
                tiempoRestante = 0;
                muerto = true; // Terminar el juego cuando se agote el tiempo
            }
            // Calcula los minutos y segundos restantes
            long minutosRestantes = tiempoRestante / 60;
            long segundosRestantes = tiempoRestante % 60;
            // Si ha pasado el tiempo máximo, termina el juego
            if (minutosRestantes <= 0 && segundosRestantes <= 0) {
                muerto = true;
            }
            
            eva01.moverDerecha(entorno);
            eva01.moverIzquierda(entorno);
            eva01.moverArriba(entorno);
            eva01.moverAbajo(entorno);
            eva01.getDisparo().mover();

            // Disparar al presionar la barra espaciadora
            if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
                // Buscar un espacio libre en el arreglo de disparos para crear un nuevo disparo
                for (int i = 0; i < disparos.length; i++) {
                    if (disparos[i] == null) {
                        // Crear un disparo desde la posición de Eva01
                        disparos[i] = new Disparo(eva01.getX(), eva01.getY());
                        break; // Salir del bucle para evitar crear más de un disparo en el mismo momento
                    }
                }
            }

            // Llamar al método mover de los disparos en cada ciclo del juego
            for (int i = 0; i < disparos.length; i++) {
                if (disparos[i] != null) {
                    disparos[i].mover();
                    disparos[i].dibujar(entorno);
                    
                    // Verificar colisión con ángeles
                    for (Angel angel : angeles) {
                        if (disparos[i].chocasteCon(angel)) {
                            // Realizar acciones al colisionar con un ángel
                            // Por ejemplo, eliminar el disparo y el ángel
                            disparos[i] = null;
                            // Eliminar el ángel de la lista de ángeles si es necesario
                        }
                    }
                    // Verificar colisión con tanques
                    for (Tanque tanque : tanques) {
                        if (disparos[i] != null && disparos[i].chocasteCon(tanque)) {
                            // Realizar acciones al colisionar con un tanque
                            // Por ejemplo, eliminar el disparo y el tanque
                            disparos[i] = null;
                            // Eliminar el tanque de la lista de tanques si es necesario
                        }
                    }
                }
            }

            for (Angel angel : angeles) {
                angel.mover();
                angel.dibujar(entorno);

                // Comprueba colisión con los disparos
                for (int i = 0; i < disparos.length; i++) {
                    if (disparos[i] != null && disparos[i].chocasteCon(angel)) {
                        // El disparo choca con un ángel
                        disparos[i] = null; // Elimina el disparo
                        // Realiza acciones adicionales en caso de colisión
                    }
                }
            }

            for (Tanque tanque : tanques) {
                tanque.mover();
                tanque.dibujar(entorno);

                // Comprueba colisión con los disparos
                for (int i = 0; i < disparos.length; i++) {
                    if (disparos[i] != null && disparos[i].chocasteCon(tanque)) {
                        // El disparo choca con un tanque
                        disparos[i] = null; // Elimina el disparo
                        // Realiza acciones adicionales en caso de colisión
                    }
                }
            }

            verificarColisiones();
            
            // Dibuja el tiempo restante en formato regresivo minutos:segundos en la esquina superior derecha
            entorno.cambiarFont("Arial", 15, Color.WHITE);
            String tiempoFormateado = String.format("%02d:%02d", minutosRestantes, segundosRestantes);
            entorno.escribirTexto("Tiempo restante: " + tiempoFormateado, entorno.ancho() - 180, 20);

        } else {
            // Si el Eva01 ha muerto o el tiempo ha transcurrido, muestra el mensaje de muerte
            entorno.dibujarRectangulo(entorno.ancho() / 2, entorno.alto() / 2, entorno.ancho(), entorno.alto(), 0, Color.BLACK);
            entorno.cambiarFont("Arial", 30, Color.RED);
            entorno.escribirTexto("Has muerto", entorno.ancho() / 2 - 78, 272);
        }

        // Dibuja el tablero y el Eva01
        tablero.dibujarManzana(entorno);
        eva01.dibujar(entorno);
    }
    
    // Método para verificar las colisiones con los rectángulos de Ángeles
    private void verificarColisiones() {
        for (int i = 0; i < rectangulosAngeles.length; i++) {
            if (rectangulosAngeles[i].contains(eva01.getX(), eva01.getY())) {
                // Colisión con el rectángulo del Ángel correspondiente
                if (i < angeles.length) {
                    Angel angel = angeles[i];
                    double angelX = angel.getX();
                    double angelY = angel.getY();

                    // Calcular la dirección para disparar en la dirección de Eva01
                    double velocidadX = (eva01.getX() - angelX) * 2; // El doble de la velocidad del Ángel
                    double velocidadY = (eva01.getY() - angelY) * 2;

                    // Crear un disparo en la dirección calculada
                    Angel[i].angelDisparo(this, tanques);
                }
            }
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
