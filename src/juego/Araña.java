package juego;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;



public class Ara�a 
{   
	private Circulo posAra;
	private int tiempoAra�a,numTela,tiempoActivacionTela,tiempoDesaparicionTela,numAra�aTelaDesaparecida;
	private double velocidad;
	private Telara�a [] tela;
	private Image imgAra�a;
	
	private boolean avanceEdi,eliminada,activarAra�a;//translada la ara�a fuera del tablero y detiene su avanzar
	
	public Ara�a(double x,double y)
	{
		this.velocidad=1.0;
		this.imgAra�a=Herramientas.cargarImagen("spider.png");
		this.avanceEdi=false;
		this.numAra�aTelaDesaparecida=0;
		this.tiempoDesaparicionTela=0;
		this.numTela=0;
		this.posAra = new Circulo(x,y,20.0);
		this.tela=new Telara�a[20];
		this.tiempoAra�a=0;
		this.eliminada=false;
		this.activarAra�a=false; //utilizado para volver a activar las ara�as eliminadas
		this.tiempoActivacionTela=400;
		inicializarTelara�a();
		
		
	}
		
	//--------------------------------------------------------------------------------------------------------------
	//Getter y setter (ignorar)
	
	public int getTiempoDesaparicionTela() {
		return tiempoDesaparicionTela;
	}

	public void setTiempoDesaparicionTela(int tiempoDesaparicionTela) {
		this.tiempoDesaparicionTela = tiempoDesaparicionTela;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public int getNumTela() {
		return numTela;
	}

	public void setNumTela(int numTela) {
		this.numTela = numTela;
	}

	public int getTiempoActivacionTela() {
		return tiempoActivacionTela;
	}

	public void setTiempoActivacionTela(int tiempoActivacionTela) {
		this.tiempoActivacionTela = tiempoActivacionTela;
	}

	public int getTiempoAra�a() {
		return tiempoAra�a;
	}
	
	public void setTiempoAra�a(int tiempoAra�a) {
		this.tiempoAra�a = tiempoAra�a;
	}
	
	public boolean isActivarAra�a() {
		return activarAra�a;
	}
	
	public void setActivarAra�a(boolean activarAra�a) {
		this.activarAra�a = activarAra�a;
	}
	
	public Telara�a[] getTela() {
		return tela;
	}
	
	public void setTela(Telara�a[] tela) {
		this.tela = tela;
	}
	
	public boolean isEliminada() {
		return eliminada;
	}
	
	public void setEliminada(boolean eliminada) {
		this.eliminada = eliminada;
	}
	
	public Circulo getPosAra() {
		return posAra;
	}
	
	public void setPosAra(Circulo posAra) {
		this.posAra = posAra;
	}
	
	//--------------------------------------------------------------------------------------------------------------
	//comienzo de funciones aplicadas al juego
	
	public void dibujar(Entorno entorno)
	{
		if(!this.eliminada)
			entorno.dibujarImagen(this.imgAra�a, posAra.getCentro().getX(),posAra.getCentro().getY(), Math.PI*2);
	}
	
	public void desaparicionDelTablero() ///le da un posicion random a la ara�a fuera del tablero (util para que aparezcan de forma aleatoria)
	{
		Random rand=new Random();
		int posPartida=rand.nextInt(4);
		
		if (posPartida==0)
			this.posAra=new Circulo(850,rand.nextInt(600),5.0);
		else if (posPartida==1)
			this.posAra=new Circulo(-50,rand.nextInt(600),5.0);
		else if (posPartida==2)
			this.posAra=new Circulo(rand.nextInt(800),650,5.0);
		else
			this.posAra=new Circulo(rand.nextInt(850),-50,5.0);		
	}
	
	public void avanzar(Entorno entorno,Exterminador jugador) //mueve la ara�a hacia el exterminador
	{	
		if(!this.posAra.contiene(jugador.getPosicion()) && !this.eliminada && !this.avanceEdi)  
				{
					if(this.posAra.getCentro().getX()<jugador.getPosicion().getX())
						this.posAra.getCentro().moverX(velocidad);
					if(this.posAra.getCentro().getX()>jugador.getPosicion().getX())
						this.posAra.getCentro().moverX(-velocidad);
					if(this.posAra.getCentro().getY()<jugador.getPosicion().getY())
						this.posAra.getCentro().moverY(velocidad);
					if(this.posAra.getCentro().getY()>jugador.getPosicion().getY())
						this.posAra.getCentro().moverY(-velocidad);
					this.tiempoAra�a++;
					posicionTelara�a(entorno);				
				}			
	}	
		
	public void avanceEdificio(Punto pos) //cambia su avance al tocar un edificio
	{
		if(this.posAra.getCentro().getX()<pos.getX() && this.posAra.getCentro().getY()<pos.getY())
			this.posAra.getCentro().moverX(-1.5);
		if(this.posAra.getCentro().getX()>pos.getX() && this.posAra.getCentro().getY()>pos.getY())
			this.posAra.getCentro().moverX(1.5);
		if(this.posAra.getCentro().getX()<pos.getX() && this.posAra.getCentro().getY()>pos.getY())
			this.posAra.getCentro().moverY(1.5);
		if(this.posAra.getCentro().getX()>pos.getX() && this.posAra.getCentro().getY()<pos.getY())
			this.posAra.getCentro().moverY(-1.5);
	}
	
	public void activarAra�a(Entorno entorno,Exterminador jugador,Edificios construccion) //clase que contiene dibujar y avanzar( mejorar)
	{
		if(!this.eliminada)
			dibujar(entorno);
		if(this.activarAra�a && !jugador.isExplosion())
		{			
			if(!this.posAra.contenidoEnRectangulo(construccion))  
				avanzar(entorno,jugador); //dirige activadas ara�as hacia la pos del exterminador
			else 
				avanceEdificio(jugador.getPosicion());	
		}
		
	}
	
	public void estadoInicial()	//settea los flag al estado inicial ( aplicacion en ara�as eliminadas)
	{
		this.tiempoAra�a=0;
		this.eliminada=false;
		this.activarAra�a=false;
	}
	
	//--------------------------------------------------------------------------------------------------------------
	//accion de la telara�a
	
	public void inicializarTelara�a()	//inicializa la posicion de la ara�a fuera del tablero
	{
		for(int cantidad=0;cantidad<this.tela.length;cantidad++)
			this.tela[cantidad]=new Telara�a();
	}
	
	public void posicionTelara�a(Entorno entorno)	//fija la telara�aen la misma posicion de la ara�a
	{
		if(this.numTela==this.tela.length)
			this.numTela=0;
		if(this.tiempoAra�a%this.tiempoActivacionTela==0)
			{
			this.tela[numTela].setFijaTelara�a(false);
			}
		if(this.tiempoAra�a%(this.tiempoActivacionTela*4)==0)
			desaparicionTelara�a();
		if(!this.tela[numTela].isFijaTelara�a())
			{
				this.tela[numTela].posicionTelara�a(this);
				this.numTela++;
			}		  
	}
	
	public void desaparicionTelara�a()  //desaparece la telara�a despues de cierto tiempo de estar activada
	{
		if(this.numAra�aTelaDesaparecida==this.tela.length)
			this.numAra�aTelaDesaparecida=0;
		
		this.tela[this.numAra�aTelaDesaparecida].desaparecerTela();
		this.numAra�aTelaDesaparecida++;
	}

}

