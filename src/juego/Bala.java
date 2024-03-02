package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bala  //eliminar bala con null
{ 
	private Punto posicion;
	private int alto,ancho,direccion;
	private boolean disparar,Trayectoria,fijar;
	private double rotacion,angulo,posx,posy;
	private Image imgBala;
	
	public Bala() {
		this.posx=0;
		this.posy=0;
		this.rotacion=0;
		this.rotacion=0;
		this.fijar=true;
		this.imgBala=Herramientas.cargarImagen("bala.png");
		this.posicion=new Punto(-1000.0,-1000.0);
		this.alto = 10;
		this.ancho = 10;
		this.direccion=0;		
		this.disparar=false;
		this.Trayectoria=false;
		

	}
//--------------------------------------------------------------------------------------------------------------------------------
//Getters y setters
	
	
	public int getDireccion() {
		return direccion;
	}
	
	public boolean isFijar() {
		return fijar;
	}


	public void setFijar(boolean fijar) {
		this.fijar = fijar;
	}


	public boolean isTrayectoria() {
		return Trayectoria;
	}

	public void setTrayectoria(boolean trayectoria) {
		Trayectoria = trayectoria;
	}

	public boolean isDisparar() {
		return disparar;
	}

	public void setDisparar(boolean disparar) {
		this.disparar = disparar;
	}

	public Punto getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto posicion) 
	{
		this.posicion=posicion;
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
//----------------------------------------------------------------------------------------------------	
	public void setDireccion(int direccion) {	//le da direccion al disparo
		if(!this.Trayectoria)
			{
			this.direccion = direccion;
			this.Trayectoria=true;
			}			
	}

	public void posBala(Pistola arma)	//posiciona la bala en el misma lugar que el arma
	{
		this.setPosicion(new Punto(arma.getPosicion().getX(),arma.getPosicion().getY()));
	}

	public void dirDisparo(Entorno entorno,Exterminador jugador)	//desplazamiento del disparo
	{						
		
		if(this.fijar)
		{	
			
			this.angulo=jugador.getAngulo();
			if(!jugador.isInvertir())
			{	
				this.rotacion=Math.PI*3/2+jugador.getRotar();
				this.posx=-(Math.sin(Math.toRadians(this.angulo))*6.5);
				this.posy=-(Math.cos(Math.toRadians(this.angulo))*6.5);
			}
			else
			{
				this.rotacion=Math.PI/2+jugador.getRotar();
				this.posx=(Math.sin(Math.toRadians(this.angulo))*6.5);
				this.posy=(Math.cos(Math.toRadians(this.angulo))*6.5);
			}
			this.fijar=false;
		}
	
		entorno.dibujarImagen(this.imgBala, this.posicion.getX(), this.posicion.getY(), this.rotacion);
		//entorno.dibujarRectangulo(this.posicion.getX(), this.posicion.getY()+5.0, this.ancho, this.alto, 0, Color.WHITE);
		if(this.disparar)
			this.posicion.desplazar(this.posx,this.posy);
		
		
}
	
	public void desaparicionDisparo()	//manda afuera de la pantalla al disparo y detiene su avanzar
	{
		this.posicion.desplazar(0.0,0.0);
		this.setPosicion(new Punto(-100.0,-100.0));
		this.disparar=false;
		
	}
	
	public boolean limitePantalla()
	{
		if( this.posicion.getX() >= 0 && this.posicion.getX() <=800 &&
			this.posicion.getY() >=0 &&	this.posicion.getY() <= 600)	
						return false;
			
			return true;
	}
	
	public void impacta(Exterminador jugador,Arañas ara,Edificios bloques,Entorno entorno,int nivelDeJuego) 	//impacto de bala
	{
		for (int AranaEliminada=0;AranaEliminada<ara.getInsectos().length;AranaEliminada++)
		{
			if(ara.getInsectos()[AranaEliminada].getPosAra().contiene(this.posicion))
			{
			ara.getInsectos()[AranaEliminada].setEliminada(true);
			Herramientas.play("sound_eliminada.wav");
			ara.getInsectos()[AranaEliminada].desaparicionDelTablero();
			jugador.sumaDePuntos(nivelDeJuego);										//si elimino la araña[3] la guardo el la pos arañaActivada[0]
			desaparicionDisparo();
			}
		}
		for(int i=0;i<bloques.getConstruccion().length;i++)
			{
			if(!Punto.contenidoEnRectangulo(bloques, this.posicion,this.alto,this.ancho))
				{
				desaparicionDisparo();
				}
			}
		if(limitePantalla())
			{
			desaparicionDisparo();
			}
	}
}
