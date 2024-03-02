package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class Edificio {
	private Punto posicion;
	private int alto, ancho;
	private Image imgEdi;
	public Edificio(Punto posicion, int alto, int ancho) {
		this.posicion = posicion;
		this.alto = alto;
		this.ancho = ancho;
		this.imgEdi=Herramientas.cargarImagen("edificio.PNG");
		
	}
//--------------------------------------------------------------------------------------------------------------------------------	
//Getters y setters

	public Punto getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
//--------------------------------------------------------------------------------------------------------------------------------
	public void dibujarse(Entorno entorno) 
	{
		//entorno.dibujarRectangulo(posicion.getX(), posicion.getY(), this.ancho, this.alto, 0, Color.white);
		entorno.dibujarImagen(this.imgEdi, this.posicion.getX(),this.posicion.getY(), Math.PI);
	}
	
	 

}
