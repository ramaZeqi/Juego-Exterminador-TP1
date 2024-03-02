package juego;
import java.util.*;

import entorno.Entorno;

public class Arañas
{
	private Araña [] insectos;
	private int numArañaActivada,tiempoAparicion,tiempoArañas; //el tiempo de aparicion es una constante que indica cada cuanto deben aparecer las arañas
																								//el tiempoArañas va incrementadose despes de la aparicion de la primera araña
	public Arañas ()
	{
		this.tiempoAparicion=300;  //lo usamos para obtener el resto entre tiempoarañas%tiempoaparicion con el fin de habilitar el avance de araña por araña
		this.insectos=new Araña[39];
		this.numArañaActivada=0;
		this.tiempoArañas=0;
		inicializarArañas();
		
	}
//----------------------------------------------------------------------------------------------------------------------------
//Getters y setters

	public int getTiempoAparicion() {
		return tiempoAparicion;
	}

	public void setTiempoAparicion(int tiempoAparicion) {
		this.tiempoAparicion = tiempoAparicion;
	}

	public Araña[] getInsectos() {
		return insectos;
	}

	public void setInsectos(Araña[] insectos) {
		this.insectos = insectos;
	}	

	public int getNumArañaActivada() {
		return numArañaActivada;
	}

	public void setNumArañaActivada(int numArañaActivada) {
		this.numArañaActivada =numArañaActivada;
	}
	
//	-----------------------------------------------------------------------------------------------------------------
	
	public boolean distanciaEntreArañas(int Araña) //utilizada en la sig funcion
	{
		for(int numAraña=0;numAraña<Araña;numAraña++)
		{
			if(this.insectos[Araña]==this.insectos[numAraña])
				numAraña++;
			if(this.insectos[Araña].getPosAra().contiene(this.insectos[numAraña].getPosAra().getCentro()))
				return false;																																												
		}
		return true;
	}

 	public void inicializarArañas()  //iniciaiza posicion de la araña fuera del tablero de forma random
	{
		Random rand=new Random();
		int posPartida;
		for(int i=0;i<this.insectos.length;i++ )
		{
			posPartida=rand.nextInt(4);
			if (posPartida==0)
			{
			this.insectos[i]=new Araña(850,rand.nextInt(600));
				while(!distanciaEntreArañas(i))
						this.insectos[i]=new Araña(850,rand.nextInt(600));
			
			}
			else if (posPartida==1)
			{
				this.insectos[i]=new Araña(-50,rand.nextInt(600));
				while(!distanciaEntreArañas(i))
					this.insectos[i]=new Araña(-50,rand.nextInt(600));
			}
			else if (posPartida==2)
			{
				this.insectos[i]=new Araña(rand.nextInt(800),650);
				while(!distanciaEntreArañas(i))
					this.insectos[i]=new Araña(rand.nextInt(800),650);
			}
			else
			{
					this.insectos[i]=new Araña(rand.nextInt(850),-50);
					while(!distanciaEntreArañas(i))
						this.insectos[i]=new Araña(rand.nextInt(850),-50);
			}
		}
	}	

	public void activarArañas(Entorno entorno,Exterminador jugador,Edificios contruccion)	// controla el avanzar de la arañas 
	{
		for(int i=0;i<this.insectos.length;i++)
		{
			this.insectos[i].activarAraña(entorno,jugador,contruccion);
		}
		this.tiempoArañas++;
		
	}

	public void avanzarArañas(Exterminador jugador) //activa el avanzar de una arañas
	{	// sirve para activar las arñas eliminadas y reponerlas en el juego
		if(this.numArañaActivada==this.insectos.length) //cantidad de arañas
			this.numArañaActivada=0;
		this.insectos[this.numArañaActivada].setActivarAraña(true); // activa el avanzar
			// sirve para activar las arñas eliminadas y reponerlas en el juego
		 if(this.tiempoArañas%this.tiempoAparicion==0)  //respawn por tiempo
			 this.numArañaActivada++;  //aumenta el contador de arañas activadas
	}
	
	public void avanzarDouble(Exterminador jugador)	//activa el avanzar de 2 arañas
	{
		if(this.numArañaActivada>this.insectos.length) 
			this.numArañaActivada=0;
		if(this.numArañaActivada==this.insectos.length-1)
			this.insectos[this.numArañaActivada].setActivarAraña(true);
		else
		{	this.insectos[this.numArañaActivada].setActivarAraña(true); 
			this.insectos[this.numArañaActivada+1].setActivarAraña(true); 
		}
		if(this.tiempoArañas%this.tiempoAparicion==0)  
			 this.numArañaActivada+=2;
	}
	
	public void avanzarTriple(Exterminador jugador)	//activa el avanzar de 3 arañas
	{
		if(this.numArañaActivada==this.insectos.length) 
			this.numArañaActivada=0;
		this.insectos[this.numArañaActivada].setActivarAraña(true); 
		this.insectos[this.numArañaActivada+1].setActivarAraña(true);
		this.insectos[this.numArañaActivada+2].setActivarAraña(true);
		if(this.tiempoArañas%this.tiempoAparicion==0)  
			 this.numArañaActivada+=3;
	}
	 
	public boolean tocaJugador(Exterminador jugador) // detiene la ejecucion del juego si una araña toca al jugador
	{
		for(int numAraña=0;numAraña<this.insectos.length;numAraña++)
		{
			if(this.insectos[numAraña].getPosAra().contiene(jugador.getPosicion()))
			{
				jugador.setExplosion(true); //desactiva el avazar de las arañas en la  funcion activarAraña
				return false;
		
			}
		}
		return true;
	}

	public void reiniciarArañas()  //setea los flag de las arañas eliminadas a su etapa base
	{
		for(int numAraña=0;numAraña<this.insectos.length;numAraña++)
				{
				if(this.insectos[numAraña].isEliminada())
					{
					this.insectos[numAraña].estadoInicial();
					}
				}
	}	

	public void dibujarTelaraña(Entorno entorno)
	{
		for(int numAraña=0;numAraña<this.insectos.length;numAraña++)
			for(int numTelaraña=0;numTelaraña<this.insectos[numAraña].getTela().length;numTelaraña++)
				this.insectos[numAraña].getTela()[numTelaraña].dibujarse(entorno);
	}
	
	public void nivelDeJuego(int nivelDeJuego) //aumenta la velocidad de generacion de arañas y telarañas segundo el nivel ingresado
	{
		if(nivelDeJuego==1)
		{
			this.tiempoAparicion=300;
			for(int numAraña=0;numAraña<this.insectos.length;numAraña++)
			{
				this.numArañaActivada=0;
				this.insectos[numAraña].setVelocidad(1.0);
				this.insectos[numAraña].setTiempoActivacionTela(300);
			}
		}
		if(nivelDeJuego==2)
		{
			this.tiempoAparicion=200;
			inicializarArañas();
			this.numArañaActivada=0;
			for(int numAraña=0;numAraña<this.insectos.length;numAraña++)
			{
				this.insectos[numAraña].setVelocidad(1.3);
				this.insectos[numAraña].setTiempoActivacionTela(200);
			}
		}
		if(nivelDeJuego==3)
		{
			this.tiempoAparicion=100;
			this.numArañaActivada=0;
			inicializarArañas();
			for(int numAraña=0;numAraña<this.insectos.length;numAraña++)
				{					
					this.insectos[numAraña].setVelocidad(1.7);
					this.insectos[numAraña].setTiempoActivacionTela(100);
				}
		}	
	}	
}

