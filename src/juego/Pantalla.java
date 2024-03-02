package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Pantalla {
	
	private Punto posicionMenu;
	private boolean menu,controles,juego,fijar;
	private int posicion;
	private Image imagenControles;
	public Pantalla()
	{
		
		this.posicion=0;
		this.fijar=true;
		this.menu=true;
		this.controles=true;
		this.posicionMenu=new Punto(180.0,185.0);
		this.juego=false;
		this.imagenControles=Herramientas.cargarImagen("controles.PNG");
	}
//--------------------------------------------------------------------------------------------------------------------------------	
//Getters y setters
	public Punto getPosicionMenu() {
		return posicionMenu;
	}

	public void setPosicionMenu(Punto posicionMenu) {
		this.posicionMenu = posicionMenu;
	}

	public boolean isFijar() {
		return fijar;
	}

	public void setFijar(boolean fijar) {
		this.fijar = fijar;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public boolean isControles() {
		return controles;
	}

	public void setControles(boolean controles) {
		this.controles = controles;
	}

	public boolean isJuego() {
		return juego;
	}

	public void setJuego(boolean juego) {
		this.juego = juego;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------
	public void principal(Entorno entorno)
	{
		if(this.menu && this.controles)
		{	
			entorno.cambiarFont("TimesRoman", 50, Color.RED);
			entorno.escribirTexto("EXTERMINADOR",200.0,100.0);
		
			entorno.cambiarFont("TimesRoman", 50, Color.white);
			entorno.escribirTexto("Inicio",200.0,200.0);
		
			entorno.cambiarFont("TimesRoman", 50, Color.white);
			entorno.escribirTexto("Controles",200.0,300.0);
			
			entorno.cambiarFont("TimesRoman", 25, Color.white);
			entorno.escribirTexto("Presione ENTER para continuar",470.0,580.0);
		
			
			entorno.dibujarTriangulo(this.posicionMenu.getX(),this.posicionMenu.getY(), 25, 50, 0, Color.red);
			
		}
		
	

	}

	public void accionPrincipal(Entorno entorno)
	{
		
		
		
			if(entorno.sePresiono(entorno.TECLA_ENTER))
			{
				if(this.getPosicionMenu().getX()==180 && this.getPosicionMenu().getY()==185)
					this.setMenu(false);
				else
					this.setControles(false);
			}
			
			if (entorno.sePresiono(entorno.TECLA_ARRIBA))
			{
				this.posicion--;
				if(this.posicion<0)
					this.posicion=1;
				if(this.posicion==0)
				{
					this.getPosicionMenu().setX(180.0);
					this.getPosicionMenu().setY(185.0);
				}
				if(this.posicion==1)
				{
					this.getPosicionMenu().setX(180.0);
					this.getPosicionMenu().setY(285.0);
				}
			}
			if (entorno.sePresiono(entorno.TECLA_ABAJO))
			{
				this.posicion++;
				if(this.posicion>1)
					this.posicion=0;
				{
					this.getPosicionMenu().setX(180.0);
					this.getPosicionMenu().setY(185.0);
				}
				if(this.posicion==1)
				{	
					this.getPosicionMenu().setX(180.0);
					this.getPosicionMenu().setY(285.0);
				}
			}
		
	}

	public void accionMenuDificultad(Entorno entorno,Juego game)
	{
		

	if (entorno.sePresiono(entorno.TECLA_ARRIBA))
	{	
		this.posicion--;
		if(this.posicion<0)
			this.posicion=2;
		if(this.posicion==0)
		{
			this.getPosicionMenu().setX(180.0);
			this.getPosicionMenu().setY(185.0);
		}
		if(this.posicion==1)
		{
			this.getPosicionMenu().setX(180.0);
			this.getPosicionMenu().setY(285.0);
		}
		if(this.posicion==2)
		{	
			this.getPosicionMenu().setX(180.0);
			this.getPosicionMenu().setY(385.0);
		}
	}
	if (entorno.sePresiono(entorno.TECLA_ABAJO))
	{
		this.posicion++;
		if(this.posicion>2)
			this.posicion=0;
		{
			this.getPosicionMenu().setX(180.0);
			this.getPosicionMenu().setY(185.0);
		}
		if(this.posicion==1)
		{	
			this.getPosicionMenu().setX(180.0);
			this.getPosicionMenu().setY(285.0);
		}
		
		if(this.posicion==2)
		{	
			this.getPosicionMenu().setX(180.0);
			this.getPosicionMenu().setY(385.0);
		}
		
	}
	if(entorno.sePresiono('c')&& this.isFijar()) //ejecuta el juego
	{
		if(this.getPosicionMenu().getX()==180.0 && this.getPosicionMenu().getY()==185.0 )
		{
			game.nivel=1;
			this.setFijar(false);
			this.setJuego(true);
		}
	
		else if(this.getPosicionMenu().getX()==180.0 && this.getPosicionMenu().getY()==285.0 )
			{
			game.nivel=2;
			this.setFijar(false);
			game.seleccionado_2=true; //habilita el nivel 2
			this.setJuego(true);
			}

		else
		{
			game.nivel=3;
			this.setFijar(false);
			game.seleccionado_2=true;
			game.seleccionado_3=true;
			this.setJuego(true);
		}
	}
	
	if(entorno.sePresiono('x')) //vuelve a la pantalla principal
	{
		this.getPosicionMenu().setX(180.0);
		this.getPosicionMenu().setY(185.0);
		this.setMenu(true);
		this.setControles(true);
		
	}
}

	public void accionControles(Entorno entorno)
	{
		if (entorno.sePresiono('x'))
		{	
			this.getPosicionMenu().setX(180.0);
			this.getPosicionMenu().setY(185.0);
			this.setMenu(true);
			this.setControles(true);
		}
	}

	public void controles(Entorno entorno)
	{
		entorno.dibujarImagen(this.imagenControles, 400,300, 0);
	}
	
	public void inicio_Dificultad(Entorno entorno)
	{
		if(!this.menu)
		{	
			if(this.fijar)
			{
				entorno.cambiarFont("TimesRoman", 50, Color.RED);
				entorno.escribirTexto("Dificultad",100.0,100.0);
		
				entorno.cambiarFont("TimesRoman", 50, Color.white);
				entorno.escribirTexto("Facil",200.0,200.0);
		
				entorno.cambiarFont("TimesRoman", 50, Color.white);
				entorno.escribirTexto("Medio",200.0,300.0);
	
				entorno.cambiarFont("TimesRoman", 50, Color.white);
				entorno.escribirTexto("Dificil",200.0,400.0);

				entorno.cambiarFont("TimesRoman", 25, Color.white);
				entorno.escribirTexto("Presione C para continuar",480.0,540.0);

				entorno.cambiarFont("TimesRoman", 25, Color.white);
				entorno.escribirTexto("Presione X para Volver",480.0,580.0);
		
				entorno.dibujarTriangulo(this.posicionMenu.getX(),this.posicionMenu.getY(), 25, 50, 0, Color.red);
		
			}
		}
	}
	
	public void gameOver(Entorno entorno)
	{
		
			entorno.cambiarFont("TimesRoman", 80, Color.RED);
			entorno.escribirTexto("GAME OVER",150.0,300.0);		
			
			entorno.cambiarFont("TimesRoman", 25, Color.white);
			entorno.escribirTexto("Presione R para volver al menú ",470.0,580.0);
			
	}

	public void reset(Exterminador jugador,Arañas insectos)
	{
		
		jugador.setPuntos(0);
		jugador.desaparecerMinas();
		insectos.inicializarArañas();
		jugador.setExplosion(false);
		jugador.setPosicion(new Punto(400.0,300.0));
		jugador.setTiempoVelocidadDisminuida(100);
		jugador.velocidadNormal();
		jugador.setAngulo(1.0);
		jugador.setRotar(0.0);
		this.juego=false;
		this.fijar=true;
		this.menu=true;
		this.controles=true;
		this.posicionMenu.setX(180.0);
		this.posicionMenu.setY(185.0);
		
	}
	
}