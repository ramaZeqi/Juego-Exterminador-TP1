package juego;

import java.awt.Color;


import entorno.Entorno;


public class Pistola
{
	private Punto posicion;
	private int alto,ancho,numDisparo;
	private Bala [] shoot;
	private boolean activada,dibujarPistola;
	
	
	public Pistola(double x,double y) {
		this.numDisparo=0;
		
		this.posicion = new Punto(x,y);
		this.alto = 10;
		this.ancho = 20;
		this.shoot=new Bala[20]; 
		this.activada=false;
		this.dibujarPistola=false; //para q no se dibuje el arma al inciio del juego
		inicializarBalas();
	}
//-----------------------------------------------------------------------------------------------------------------------------
//Getters y setters
	
	public int getNumDisparo() {
		return numDisparo;
	}

	public void setNumDisparo(int numDisparo) {
		this.numDisparo = numDisparo;
	}

	public Bala[] getShoot() {
		return shoot;
	}

	public void setShoot(Bala[] shoot) {
		this.shoot = shoot;
	}

	public boolean isDibujarPistola() {
		return dibujarPistola;
	}

	public void setDibujarPistola(boolean dibujarPistola) {
		this.dibujarPistola = dibujarPistola;
	}

	public boolean isActivada() {
		return activada;
	}

	public void setActivada(boolean activada) {
		this.activada = activada;
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
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void dibujar(Entorno entorno,Exterminador jugador)
	{
			
			entorno.dibujarRectangulo(posicion.getX(),posicion.getY(),this.ancho,this.alto, Math.PI*3/2+jugador.getRotar(), Color.BLACK);
	
	}
	
	public void posicionArma(Exterminador jugador)
	{
		this.setPosicion(new Punto(jugador.getPosicion().getX(),jugador.getPosicion().getY()));
	}
	
	public void inicializarBalas()		//inicia la posicion de la balas fuera de la resolucion
	{
		for(int numBalas=0;numBalas<this.shoot.length;numBalas++)
			this.shoot[numBalas]=new Bala();
	}
	
	public void dibujarBalas(Entorno entorno,Exterminador jugador)		//dibuja las balas fijas afuera de la resolucion
	{
		for(int numBalas=0;numBalas<this.shoot.length;numBalas++)
			this.shoot[numBalas].dirDisparo(entorno,jugador);
	}
	
	public void activarDisparo()
	{	
		if(this.numDisparo==this.shoot.length-1)
			this.numDisparo=0;
		this.shoot[this.numDisparo].setDisparar(true); //le da movimiento al disparo fijado e dibujarBalas
		this.numDisparo++;
	}
	
	public void posBala()				//activa movimiento de disparo
	{
		int anterior=this.numDisparo-1;
		if(anterior==-1)
			anterior=0;
		this.shoot[anterior].posBala(this);
		this.shoot[anterior].setTrayectoria(false);//habilita el cambio de traectoria en el proximo respawn
		this.shoot[anterior].setFijar(true);
	}
	
	public void posicionarBala(int direccion)	//le da direccion al disparo
	{
		for (int cantBalas=0;cantBalas<this.shoot.length ;cantBalas++)
			if(this.shoot[cantBalas].isDisparar())
				this.shoot[cantBalas].setDireccion(direccion);
	}
	
	public void impactar(Entorno entorno,Exterminador jugador,Arañas insectos, Edificios construccion,int nivelDeJuego)
	{
		for(int numBala=0;numBala<this.shoot.length;numBala++)
			this.shoot[numBala].impacta(jugador,insectos,construccion,entorno,nivelDeJuego);		
	}
}
	
