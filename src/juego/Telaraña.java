package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Telaraña
{
	
	private Circulo telaraña;
	private boolean fijaTelaraña,desaparecerTela;
	private Image imgTelaraña;
	
	
	public Telaraña()
	{
		this.imgTelaraña=Herramientas.cargarImagen("spiderweb.jpg");
		this.desaparecerTela=false;
		this.fijaTelaraña=true;
		this.telaraña=new Circulo(-300.0,-300.0,25.0);
	}
//----------------------------------------------------------------------------------------------------------------------
// getters y setter
	public Circulo getTelaraña() {
		return telaraña;
	}
	public void setTelaraña(Circulo telaraña) {
		this.telaraña = telaraña;
	}
	public boolean isFijaTelaraña() {
		return fijaTelaraña;
	}
	public void setFijaTelaraña(boolean fijaTelaraña) {
		this.fijaTelaraña = fijaTelaraña;
	}
	public boolean isDesaparecerTela() {
		return desaparecerTela;
	}
	public void setDesaparecerTela(boolean desaparecerTela) {
		this.desaparecerTela = desaparecerTela;
	}
//----------------------------------------------------------------------------------------------------------------------
	
	public void posicionTelaraña(Araña ara) //situa a la telaraña en la misma pos q la araña
	{
		if(!this.fijaTelaraña)
			{
			this.telaraña.setCentro(new Punto(ara.getPosAra().getCentro().getX(),ara.getPosAra().getCentro().getY()));
			this.fijaTelaraña=true;
			}
	}

	public void desaparecerTela()
	{
			
		this.telaraña.setCentro(new Punto(-200.0,200.0));
		
	}
	
	public void dibujarse(Entorno entorno) 
	{
		entorno.dibujarImagen(this.imgTelaraña,this.telaraña.getCentro().getX(),this.telaraña.getCentro().getY(),Math.PI*2);
	}
	
	public void disVelocidad(Exterminador jugador) //dismunuye vel del jugador
	{
		jugador.setActivarDisminucion(true);
	}
}
