package juego;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Tablero tablero;
	private Personaje personaje;
//	private Enemigo enemigo;
//	private Personaje personaje;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Plantas Invasoras - Grupo 1", 885, 760);
		// Inicializar lo que haga falta para el juego
		// ...
		tablero = new Tablero(0,0,0,4);
		personaje= new Personaje(15,15, 10, 10, 3);

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		tablero.dibujar(entorno);
		personaje.dibujar(entorno);
		personaje.moverDerecha(entorno);
		personaje.moverAbajo(entorno);
		personaje.moverArriba(entorno);
		personaje.moverIzquierda(entorno);
		

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
