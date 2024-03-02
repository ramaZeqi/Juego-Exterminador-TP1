package juego;
import java.util.*;

import entorno.Entorno;

public class Ara�as
{
	private Ara�a [] insectos;
	private int numAra�aActivada,tiempoAparicion,tiempoAra�as; //el tiempo de aparicion es una constante que indica cada cuanto deben aparecer las ara�as
																								//el tiempoAra�as va incrementadose despes de la aparicion de la primera ara�a
	public Ara�as ()
	{
		this.tiempoAparicion=300;  //lo usamos para obtener el resto entre tiempoara�as%tiempoaparicion con el fin de habilitar el avance de ara�a por ara�a
		this.insectos=new Ara�a[39];
		this.numAra�aActivada=0;
		this.tiempoAra�as=0;
		inicializarAra�as();
		
	}
//----------------------------------------------------------------------------------------------------------------------------
//Getters y setters

	public int getTiempoAparicion() {
		return tiempoAparicion;
	}

	public void setTiempoAparicion(int tiempoAparicion) {
		this.tiempoAparicion = tiempoAparicion;
	}

	public Ara�a[] getInsectos() {
		return insectos;
	}

	public void setInsectos(Ara�a[] insectos) {
		this.insectos = insectos;
	}	

	public int getNumAra�aActivada() {
		return numAra�aActivada;
	}

	public void setNumAra�aActivada(int numAra�aActivada) {
		this.numAra�aActivada =numAra�aActivada;
	}
	
//	-----------------------------------------------------------------------------------------------------------------
	
	public boolean distanciaEntreAra�as(int Ara�a) //utilizada en la sig funcion
	{
		for(int numAra�a=0;numAra�a<Ara�a;numAra�a++)
		{
			if(this.insectos[Ara�a]==this.insectos[numAra�a])
				numAra�a++;
			if(this.insectos[Ara�a].getPosAra().contiene(this.insectos[numAra�a].getPosAra().getCentro()))
				return false;																																												
		}
		return true;
	}

 	public void inicializarAra�as()  //iniciaiza posicion de la ara�a fuera del tablero de forma random
	{
		Random rand=new Random();
		int posPartida;
		for(int i=0;i<this.insectos.length;i++ )
		{
			posPartida=rand.nextInt(4);
			if (posPartida==0)
			{
			this.insectos[i]=new Ara�a(850,rand.nextInt(600));
				while(!distanciaEntreAra�as(i))
						this.insectos[i]=new Ara�a(850,rand.nextInt(600));
			
			}
			else if (posPartida==1)
			{
				this.insectos[i]=new Ara�a(-50,rand.nextInt(600));
				while(!distanciaEntreAra�as(i))
					this.insectos[i]=new Ara�a(-50,rand.nextInt(600));
			}
			else if (posPartida==2)
			{
				this.insectos[i]=new Ara�a(rand.nextInt(800),650);
				while(!distanciaEntreAra�as(i))
					this.insectos[i]=new Ara�a(rand.nextInt(800),650);
			}
			else
			{
					this.insectos[i]=new Ara�a(rand.nextInt(850),-50);
					while(!distanciaEntreAra�as(i))
						this.insectos[i]=new Ara�a(rand.nextInt(850),-50);
			}
		}
	}	

	public void activarAra�as(Entorno entorno,Exterminador jugador,Edificios contruccion)	// controla el avanzar de la ara�as 
	{
		for(int i=0;i<this.insectos.length;i++)
		{
			this.insectos[i].activarAra�a(entorno,jugador,contruccion);
		}
		this.tiempoAra�as++;
		
	}

	public void avanzarAra�as(Exterminador jugador) //activa el avanzar de una ara�as
	{	// sirve para activar las ar�as eliminadas y reponerlas en el juego
		if(this.numAra�aActivada==this.insectos.length) //cantidad de ara�as
			this.numAra�aActivada=0;
		this.insectos[this.numAra�aActivada].setActivarAra�a(true); // activa el avanzar
			// sirve para activar las ar�as eliminadas y reponerlas en el juego
		 if(this.tiempoAra�as%this.tiempoAparicion==0)  //respawn por tiempo
			 this.numAra�aActivada++;  //aumenta el contador de ara�as activadas
	}
	
	public void avanzarDouble(Exterminador jugador)	//activa el avanzar de 2 ara�as
	{
		if(this.numAra�aActivada>this.insectos.length) 
			this.numAra�aActivada=0;
		if(this.numAra�aActivada==this.insectos.length-1)
			this.insectos[this.numAra�aActivada].setActivarAra�a(true);
		else
		{	this.insectos[this.numAra�aActivada].setActivarAra�a(true); 
			this.insectos[this.numAra�aActivada+1].setActivarAra�a(true); 
		}
		if(this.tiempoAra�as%this.tiempoAparicion==0)  
			 this.numAra�aActivada+=2;
	}
	
	public void avanzarTriple(Exterminador jugador)	//activa el avanzar de 3 ara�as
	{
		if(this.numAra�aActivada==this.insectos.length) 
			this.numAra�aActivada=0;
		this.insectos[this.numAra�aActivada].setActivarAra�a(true); 
		this.insectos[this.numAra�aActivada+1].setActivarAra�a(true);
		this.insectos[this.numAra�aActivada+2].setActivarAra�a(true);
		if(this.tiempoAra�as%this.tiempoAparicion==0)  
			 this.numAra�aActivada+=3;
	}
	 
	public boolean tocaJugador(Exterminador jugador) // detiene la ejecucion del juego si una ara�a toca al jugador
	{
		for(int numAra�a=0;numAra�a<this.insectos.length;numAra�a++)
		{
			if(this.insectos[numAra�a].getPosAra().contiene(jugador.getPosicion()))
			{
				jugador.setExplosion(true); //desactiva el avazar de las ara�as en la  funcion activarAra�a
				return false;
		
			}
		}
		return true;
	}

	public void reiniciarAra�as()  //setea los flag de las ara�as eliminadas a su etapa base
	{
		for(int numAra�a=0;numAra�a<this.insectos.length;numAra�a++)
				{
				if(this.insectos[numAra�a].isEliminada())
					{
					this.insectos[numAra�a].estadoInicial();
					}
				}
	}	

	public void dibujarTelara�a(Entorno entorno)
	{
		for(int numAra�a=0;numAra�a<this.insectos.length;numAra�a++)
			for(int numTelara�a=0;numTelara�a<this.insectos[numAra�a].getTela().length;numTelara�a++)
				this.insectos[numAra�a].getTela()[numTelara�a].dibujarse(entorno);
	}
	
	public void nivelDeJuego(int nivelDeJuego) //aumenta la velocidad de generacion de ara�as y telara�as segundo el nivel ingresado
	{
		if(nivelDeJuego==1)
		{
			this.tiempoAparicion=300;
			for(int numAra�a=0;numAra�a<this.insectos.length;numAra�a++)
			{
				this.numAra�aActivada=0;
				this.insectos[numAra�a].setVelocidad(1.0);
				this.insectos[numAra�a].setTiempoActivacionTela(300);
			}
		}
		if(nivelDeJuego==2)
		{
			this.tiempoAparicion=200;
			inicializarAra�as();
			this.numAra�aActivada=0;
			for(int numAra�a=0;numAra�a<this.insectos.length;numAra�a++)
			{
				this.insectos[numAra�a].setVelocidad(1.3);
				this.insectos[numAra�a].setTiempoActivacionTela(200);
			}
		}
		if(nivelDeJuego==3)
		{
			this.tiempoAparicion=100;
			this.numAra�aActivada=0;
			inicializarAra�as();
			for(int numAra�a=0;numAra�a<this.insectos.length;numAra�a++)
				{					
					this.insectos[numAra�a].setVelocidad(1.7);
					this.insectos[numAra�a].setTiempoActivacionTela(100);
				}
		}	
	}	
}

