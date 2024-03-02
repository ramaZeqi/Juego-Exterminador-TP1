package juego;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;



public class Araña 
{   
	private Circulo posAra;
	private int tiempoAraña,numTela,tiempoActivacionTela,tiempoDesaparicionTela,numArañaTelaDesaparecida;
	private double velocidad;
	private Telaraña [] tela;
	private Image imgAraña;
	
	private boolean avanceEdi,eliminada,activarAraña;//translada la araña fuera del tablero y detiene su avanzar
	
	public Araña(double x,double y)
	{
		this.velocidad=1.0;
		this.imgAraña=Herramientas.cargarImagen("spider.png");
		this.avanceEdi=false;
		this.numArañaTelaDesaparecida=0;
		this.tiempoDesaparicionTela=0;
		this.numTela=0;
		this.posAra = new Circulo(x,y,20.0);
		this.tela=new Telaraña[20];
		this.tiempoAraña=0;
		this.eliminada=false;
		this.activarAraña=false; //utilizado para volver a activar las arañas eliminadas
		this.tiempoActivacionTela=400;
		inicializarTelaraña();
		
		
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

	public int getTiempoAraña() {
		return tiempoAraña;
	}
	
	public void setTiempoAraña(int tiempoAraña) {
		this.tiempoAraña = tiempoAraña;
	}
	
	public boolean isActivarAraña() {
		return activarAraña;
	}
	
	public void setActivarAraña(boolean activarAraña) {
		this.activarAraña = activarAraña;
	}
	
	public Telaraña[] getTela() {
		return tela;
	}
	
	public void setTela(Telaraña[] tela) {
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
			entorno.dibujarImagen(this.imgAraña, posAra.getCentro().getX(),posAra.getCentro().getY(), Math.PI*2);
	}
	
	public void desaparicionDelTablero() ///le da un posicion random a la araña fuera del tablero (util para que aparezcan de forma aleatoria)
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
	
	public void avanzar(Entorno entorno,Exterminador jugador) //mueve la araña hacia el exterminador
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
					this.tiempoAraña++;
					posicionTelaraña(entorno);				
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
	
	public void activarAraña(Entorno entorno,Exterminador jugador,Edificios construccion) //clase que contiene dibujar y avanzar( mejorar)
	{
		if(!this.eliminada)
			dibujar(entorno);
		if(this.activarAraña && !jugador.isExplosion())
		{			
			if(!this.posAra.contenidoEnRectangulo(construccion))  
				avanzar(entorno,jugador); //dirige activadas arañas hacia la pos del exterminador
			else 
				avanceEdificio(jugador.getPosicion());	
		}
		
	}
	
	public void estadoInicial()	//settea los flag al estado inicial ( aplicacion en arañas eliminadas)
	{
		this.tiempoAraña=0;
		this.eliminada=false;
		this.activarAraña=false;
	}
	
	//--------------------------------------------------------------------------------------------------------------
	//accion de la telaraña
	
	public void inicializarTelaraña()	//inicializa la posicion de la araña fuera del tablero
	{
		for(int cantidad=0;cantidad<this.tela.length;cantidad++)
			this.tela[cantidad]=new Telaraña();
	}
	
	public void posicionTelaraña(Entorno entorno)	//fija la telarañaen la misma posicion de la araña
	{
		if(this.numTela==this.tela.length)
			this.numTela=0;
		if(this.tiempoAraña%this.tiempoActivacionTela==0)
			{
			this.tela[numTela].setFijaTelaraña(false);
			}
		if(this.tiempoAraña%(this.tiempoActivacionTela*4)==0)
			desaparicionTelaraña();
		if(!this.tela[numTela].isFijaTelaraña())
			{
				this.tela[numTela].posicionTelaraña(this);
				this.numTela++;
			}		  
	}
	
	public void desaparicionTelaraña()  //desaparece la telaraña despues de cierto tiempo de estar activada
	{
		if(this.numArañaTelaDesaparecida==this.tela.length)
			this.numArañaTelaDesaparecida=0;
		
		this.tela[this.numArañaTelaDesaparecida].desaparecerTela();
		this.numArañaTelaDesaparecida++;
	}

}

