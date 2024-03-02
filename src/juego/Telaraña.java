package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Telara�a
{
	
	private Circulo telara�a;
	private boolean fijaTelara�a,desaparecerTela;
	private Image imgTelara�a;
	
	
	public Telara�a()
	{
		this.imgTelara�a=Herramientas.cargarImagen("spiderweb.jpg");
		this.desaparecerTela=false;
		this.fijaTelara�a=true;
		this.telara�a=new Circulo(-300.0,-300.0,25.0);
	}
//----------------------------------------------------------------------------------------------------------------------
// getters y setter
	public Circulo getTelara�a() {
		return telara�a;
	}
	public void setTelara�a(Circulo telara�a) {
		this.telara�a = telara�a;
	}
	public boolean isFijaTelara�a() {
		return fijaTelara�a;
	}
	public void setFijaTelara�a(boolean fijaTelara�a) {
		this.fijaTelara�a = fijaTelara�a;
	}
	public boolean isDesaparecerTela() {
		return desaparecerTela;
	}
	public void setDesaparecerTela(boolean desaparecerTela) {
		this.desaparecerTela = desaparecerTela;
	}
//----------------------------------------------------------------------------------------------------------------------
	
	public void posicionTelara�a(Ara�a ara) //situa a la telara�a en la misma pos q la ara�a
	{
		if(!this.fijaTelara�a)
			{
			this.telara�a.setCentro(new Punto(ara.getPosAra().getCentro().getX(),ara.getPosAra().getCentro().getY()));
			this.fijaTelara�a=true;
			}
	}

	public void desaparecerTela()
	{
			
		this.telara�a.setCentro(new Punto(-200.0,200.0));
		
	}
	
	public void dibujarse(Entorno entorno) 
	{
		entorno.dibujarImagen(this.imgTelara�a,this.telara�a.getCentro().getX(),this.telara�a.getCentro().getY(),Math.PI*2);
	}
	
	public void disVelocidad(Exterminador jugador) //dismunuye vel del jugador
	{
		jugador.setActivarDisminucion(true);
	}
}
