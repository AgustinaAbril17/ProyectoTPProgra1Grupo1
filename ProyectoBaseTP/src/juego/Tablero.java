package juego;

import java.awt.Color;

import entorno.Entorno;

public class Tablero {
	private double x;
	private double y;
	private double angulo;
	private int rectangulos;
	
	public Tablero(double x, double y, double angulo, int rectangulos) {
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		this.rectangulos = rectangulos;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, x, rectangulos, angulo, Color.white);
		e.dibujarRectangulo(442, 148, 200, 168, 0, Color.green );
		e.dibujarRectangulo(442, 377, 200, 168, 0, Color.green );
		e.dibujarRectangulo(442, 608, 200, 168, 0, Color.green );
		e.dibujarRectangulo(172, 148, 200, 168, 0, Color.green );
		e.dibujarRectangulo(172, 377, 200, 168, 0, Color.green );
		e.dibujarRectangulo(172, 608, 200, 168, 0, Color.green );
		e.dibujarRectangulo(712, 148, 200, 168, 0, Color.green );
		e.dibujarRectangulo(712, 377, 200, 168, 0, Color.green );
		e.dibujarRectangulo(712, 608, 200, 168, 0, Color.green );
		e.dibujarRectangulo(308, 380, 5, 683, 0, Color.white );
		e.dibujarRectangulo(578, 380, 5, 683, 0, Color.red );
		e.dibujarRectangulo(442, 264, 804, 5, 0, Color.blue );
		e.dibujarRectangulo(442, 492, 804, 5, 0, Color.yellow );
		e.dibujarRectangulo(442, 724, 810, 5, 0, Color.gray );
		e.dibujarRectangulo(442, 35, 810, 5, 0, Color.gray );
		e.dibujarRectangulo(847, 378, 5, 690, 0, Color.gray );
		e.dibujarRectangulo(37, 378, 5, 690, 0, Color.gray );
	}
	
}