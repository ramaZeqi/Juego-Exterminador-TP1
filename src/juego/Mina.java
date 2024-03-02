package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Mina
{
	private Circulo mina;
	
	private boolean duracionExplosion;//dibuja la explosion mientra este activado - mantiene en imagen la mina
	
	private Image imgMina,imgExplosion; 
	
	public Mina( double x,double y,double radio)
	{
		this.mina=new Circulo(x,y,radio);
		this.duracionExplosion=false;
		this.imgExplosion=Herramientas.cargarImagen("explosion.png");
		this.imgMina=Herramientas.cargarImagen("Mina.png");
		
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------
//Getters y Setters
	public Circulo getMina()
	{
		return mina;
	}
	
	public void setMina(Circulo mina)
	{
		this.mina = mina;
	}
	
	public boolean isDuracionExplosion() {
		return duracionExplosion;
	}
	
	public void setDuracionExplosion(boolean duracionExplosion) {
		this.duracionExplosion = duracionExplosion;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void dibujarMina(Entorno entorno) 
	{
		this.duracionExplosion=false;
		entorno.dibujarImagen(this.imgMina,this.mina.getCentro().getX(),this.mina.getCentro().getY(),Math.PI);
	}	
	

	public void posicionMina(Exterminador jugador,int flagPosicion)
	{
		this.mina.setCentro(new Punto(jugador.getPosicion().getX(),jugador.getPosicion().getY()));

	}
	
	public void explosionTelaraña(Entorno entorno,Circulo mina,Arañas insectos)
	{
		entorno.dibujarImagen(this.imgExplosion, mina.getCentro().getX(),mina.getCentro().getY(), Math.PI*2);
		this.duracionExplosion=true;
		this.mina.setRadio(60); //aumenta el tamaño     //entorno.dibujarImagen(imagen, x, y, 0, 0.05);
		for(int numAraña=0;numAraña<insectos.getInsectos().length;numAraña++)
			for (int numTela=0;numTela<insectos.getInsectos()[numAraña].getTela().length;numTela++)
				if(this.mina.contiene(insectos.getInsectos()[numAraña].getTela()[numTela].getTelaraña()))
					insectos.getInsectos()[numAraña].getTela()[numTela].desaparecerTela();
	}
		
	public void explosionMina(Entorno entorno,Exterminador jugador,Mina [] bomba,Arañas insectos,int nivelDeJuego)
	{
		entorno.dibujarImagen(this.imgExplosion, mina.getCentro().getX(),mina.getCentro().getY(), Math.PI*2);
		Herramientas.play("sound_explosion.wav");
		
		for(int numMina=0;numMina<bomba.length;numMina++)
		{
		
		if(this.mina.contiene(bomba[numMina].mina)&& !(this.mina==bomba[numMina].getMina()))
			{
			explosionTelaraña(entorno,bomba[numMina].mina,insectos);
			if(bomba[numMina].mina.contieneAraña(insectos))
				explosionAraña(entorno,bomba,insectos,jugador,nivelDeJuego);
			if(bomba[numMina].mina.contiene(jugador.getPosicion()))
				jugador.setExplosion(true);
				
			bomba[numMina].mina.setCentro(new Punto(-50.0,-50.0));
			bomba[numMina].mina.setRadio(15);//vuelve al tamaño norma	
			}
		 
		}
		
		
	}
	
	public void explosionAraña(Entorno entorno,Mina[] bomba,Arañas insectos,Exterminador jugador,int nivelDeJuego)
	{
		entorno.dibujarImagen(this.imgExplosion, mina.getCentro().getX(),mina.getCentro().getY(), Math.PI*2);
		Herramientas.play("sound_explosion.wav");
		explosionMina(entorno,jugador,bomba,insectos,nivelDeJuego);
		for(int cantidadArañas=0;cantidadArañas<insectos.getInsectos().length;cantidadArañas++)
		{
			if(this.mina.contiene(insectos.getInsectos()[cantidadArañas].getPosAra()))	
			{
				insectos.getInsectos()[cantidadArañas].setEliminada(true); //habilita la funcion siguiente
				insectos.getInsectos()[cantidadArañas].desaparicionDelTablero();//lleva a la araña fuera del mapa
				jugador.sumaDePuntos(nivelDeJuego);
				if(this.mina.contiene(jugador.getPosicion()))
					jugador.setExplosion(true); //si la explosion toca al jugador	//insectos.getInsectos()[cantidadArañas].getArma().setTelaraña(new Circulo(-100.0,900.0,25.0));//lleva fuera de la pantalla a la telaraña
			}
		}
			
		this.mina.setCentro(new Punto(-50.0,-50.0));//lleva fuera de la pantalla a la mina
		this.mina.setRadio(15);//vuelve al tamaño normal
	}
		
	public void explosionPorDisparo(Entorno entorno,Mina []bomba, Bala[] disparo,Arañas insectos ,Exterminador jugador,int nivelDeJuego)
	{
		
		
		
		explosionTelaraña(entorno,this.mina,insectos);	
		explosionMina(entorno,jugador,bomba,insectos,nivelDeJuego);
		
		for(int numDisparo=0;numDisparo<disparo.length;numDisparo++)
		{	
			
			if(this.mina.contiene(disparo[numDisparo].getPosicion()))
			{	
				
				if(this.mina.contiene(jugador.getPosicion()))
					jugador.setExplosion(true);	
				if(this.mina.contieneAraña(insectos))
					explosionAraña(entorno,bomba,insectos,jugador,nivelDeJuego);
				disparo[numDisparo].desaparicionDisparo();
				
			}
		}
		this.mina.setCentro(new Punto(-50.0,-50.0));
		this.mina.setRadio(15);//vuelve al tamaño normal
	}

}