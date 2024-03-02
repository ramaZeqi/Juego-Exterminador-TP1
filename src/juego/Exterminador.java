package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class Exterminador
{

	private Punto posicion;
	private int alto,ancho,flagPosicion,tiempoEnJuego,numMina,tiempoVelocidadDisminuida,tiempoVelocidadNormal,puntos;
	private double rotar,angulo;
	double velocidad;
	private boolean explosion,activarMina,activarDisminucion,invertir;
	private Pistola gun;
	private Mina [] bomba;
	private Image imgJugador,imgjugadorTelaraña;
	
	
	
	
	public Exterminador() 
	{
		
		this.invertir=false;
		this.angulo=1.0;
		this.rotar=0.0;
		this.puntos=0;
		this.tiempoVelocidadNormal=100;
		this.tiempoVelocidadDisminuida=0;
		this.numMina=0;
		this.bomba=new Mina[30];
		this.tiempoEnJuego=0;
		this.imgjugadorTelaraña=null;
		this.posicion = new Punto(400.0,300.0); //posicion prefija ->(centro)
		this.alto = 30;
		this.ancho = 30;
		this.velocidad = 3.5;
		this.activarMina=false;
		this.imgJugador=null;
		this.explosion=false;
		this.activarDisminucion=false;
		this.flagPosicion=0;
 		this.gun=new Pistola((-Math.sin(Math.toRadians(this.angulo))),(-Math.cos(Math.toRadians(this.angulo))));
 		
		inicializarMinas();
	}
//--------------------------------------------------------------------------------------------------------------------------------
// Getters y setters
	
	public boolean isInvertir() {
		return invertir;
	}

	public void setInvertir(boolean invertir) {
		this.invertir = invertir;
	}
	
	public double getRotar() {
		return rotar;
	}

	public void setRotar(double rotar) {
		this.rotar = rotar;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Punto getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
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

	public int getFlagPosicion() {
		return flagPosicion;
	}

	public void setFlagPosicion(int flagPosicion) {
		this.flagPosicion = flagPosicion;
	}

	public int getTiempoEnJuego() {
		return tiempoEnJuego;
	}

	public void setTiempoEnJuego(int tiempoEnJuego) {
		this.tiempoEnJuego = tiempoEnJuego;
	}

	public int getNumMina() {
		return numMina;
	}

	public void setNumMina(int numMina) {
		this.numMina = numMina;
	}

	public int getTiempoVelocidadDisminuida() {
		return tiempoVelocidadDisminuida;
	}

	public void setTiempoVelocidadDisminuida(int tiempoVelocidadDisminuida) {
		this.tiempoVelocidadDisminuida = tiempoVelocidadDisminuida;
	}

	public int getTiempoVelocidadNormal() {
		return tiempoVelocidadNormal;
	}

	public void setTiempoVelocidadNormal(int tiempoVelocidadNormal) {
		this.tiempoVelocidadNormal = tiempoVelocidadNormal;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public boolean isExplosion() {
		return explosion;
	}

	public void setExplosion(boolean explosion) {
		this.explosion = explosion;
	}

	public boolean isActivarMina() {
		return activarMina;
	}

	public void setActivarMina(boolean activarMina) {
		this.activarMina = activarMina;
	}

	public boolean isActivarDisminucion() {
		return activarDisminucion;
	}
	
	public void setActivarDisminucion(boolean activarDisminucion) {
		this.activarDisminucion = activarDisminucion;
	}

	public Pistola getGun() {
		return gun;
	}

	public void setGun(Pistola gun) {
		this.gun = gun;
	}

	public Mina[] getBomba() {
		return bomba;
	}

	public void setBomba(Mina[] bomba) {
		this.bomba = bomba;
	}
	
	public void sumaDePuntos(int nivel)
	{
		int suma=50*nivel;
		this.puntos+=suma;
		
	}
	
	public void puntos(Entorno entorno)
	{
		entorno.cambiarFont("TimesRoman", 20, Color.white);
		entorno.escribirTexto("Puntos:",10.0,15.0);
		entorno.escribirTexto(Integer.toString(this.puntos),70.0,15.0);
		
	}

	public void aumentarNumMina() {
		this.numMina++;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//disminucion velocidad jugador
	public void disminucionVel()  //dismnuye velocidad del jugador a un 10% de su total
	{	
			this.activarDisminucion=true;
			this.velocidad*=0.1;
			//dibujar en misma posicion de jugador una un jugador con telaraña
			 
	}


	public void activarCambioAVelocidadNormal()
	{
		if(this.activarDisminucion)
			this.tiempoVelocidadDisminuida++;
	}
	
	
	public void velocidadNormal()
	{
		if(this.tiempoVelocidadDisminuida%this.tiempoVelocidadNormal==0)
		{
			this.activarDisminucion=false;
			this.tiempoVelocidadDisminuida=0;
			this.velocidad=3.5;
			//borrar el dibujo de jugador con telaraña
		}
		
	}
	
	
	public void jugadorTocaTelaraña(Arañas insectos) 
	{
		for (int numAraña=0;numAraña<insectos.getInsectos().length;numAraña++)
			for(int numTela=0;numTela<insectos.getInsectos()[numAraña].getTela().length;numTela++)
				if(insectos.getInsectos()[numAraña].getTela()[numTela].getTelaraña().contiene(this.posicion))
				{
					disminucionVel();
					insectos.getInsectos()[numAraña].getTela()[numTela].desaparecerTela();
					
				}
		
	}
	
//desplazamiento
	
	public void movimientoJugador(Entorno entorno)
	{
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
		{
			subir();
			this.invertir=false; //utilizado en disparo 
		}
		if (entorno.estaPresionada(entorno.TECLA_ABAJO))
		{
			bajar();
			this.invertir=true;//utilizado en disparo
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA))
		{
			derecha();
			
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
		{
			izquierda();
			
		}
	}
	
	public void movimientoLimitadoJugador(Entorno entorno,Edificios construccion)
	{
		//limita el espacio de movimiento del exterminador al tamano horizontal de la plataforma
		if(fueraDeRangoHorizontal())  
		{
			if (entorno.estaPresionada(entorno.TECLA_ABAJO))
				subir();
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
				bajar();
			
		}
		
		//limita el espacio de movimiento del exterminador al tamano vertical de la plataforma
		if(fueraDeRangoVertical()) 
		{	
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
				bajar();
			if (entorno.estaPresionada(entorno.TECLA_ABAJO))
				subir();
		}
		
		//movimiento del jugador al intersecar edificio
		if(!Punto.contenidoEnRectangulo(construccion,this.posicion,this.alto,this.ancho))
		{
			if (entorno.estaPresionada(entorno.TECLA_DERECHA))
				izquierda();
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
				derecha();
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
				bajar();
			if (entorno.estaPresionada(entorno.TECLA_ABAJO))
				subir();
		}
	}
		
//direccion de jugador
	public void dibujarse(Entorno entorno) 
	{
		//entorno.dibujarRectangulo(posicion.getX(), posicion.getY(), this.ancho, this.alto, 0, Color.white);
		if (this.activarDisminucion && !this.invertir)
			{
				this.imgjugadorTelaraña=Herramientas.cargarImagen("frenteTelaraña.png");
				entorno.dibujarImagen(this.imgjugadorTelaraña,  this.posicion.getX(),this.posicion.getY(),this.rotar);
			}
		
		else if (this.activarDisminucion && this.invertir)
			{
				this.imgjugadorTelaraña=Herramientas.cargarImagen("trasTelaraña.png");
				entorno.dibujarImagen(this.imgjugadorTelaraña,  this.posicion.getX(),this.posicion.getY(),this.rotar);
			}
		
		else if(!this.invertir) 
			{
			
				this.imgJugador=Herramientas.cargarImagen("tras.png");
				entorno.dibujarImagen(this.imgJugador, this.posicion.getX(),this.posicion.getY(), this.rotar);
			}
		else
		{
				this.imgJugador=Herramientas.cargarImagen("frente.png");
				entorno.dibujarImagen(this.imgJugador, this.posicion.getX(),this.posicion.getY(), this.rotar);
			
		}
	
		this.tiempoEnJuego++;
	}

	public void subir()
	{
		this.posicion.desplazar((-Math.sin(Math.toRadians(angulo)))*velocidad,(-Math.cos(Math.toRadians(angulo)))*velocidad);
		
	}

	public void bajar()
	{
		this.posicion.desplazar(Math.sin(Math.toRadians(angulo))*velocidad,Math.cos(Math.toRadians(angulo))*velocidad);
		
	}
	
	public void derecha()
	{
		this.rotar+=0.061;
		this.angulo-=3.5;	
		
	}
	
	public void izquierda()
	{
		this.angulo+=3.5;
		this.rotar-=0.061;
	
		
	}
		
	public boolean fueraDeRangoVertical() 
	 
	{
		if (this.posicion.getY()-this.alto/2 < 0 || this.posicion.getY()+this.alto/2 > 600)
			return true;
		else
			return false;
	}
	
	public boolean fueraDeRangoHorizontal() 
	{
		if (this.posicion.getX()-this.ancho/2< 0 || this.posicion.getX()+this.ancho/2> 800)
			return true;
		else
			return false;
	}
 
//funcion minas
	
	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public void inicializarMinas()
	{	
		for(int cantMinas=0;cantMinas<this.bomba.length;cantMinas++)
		{
			
			this.bomba[cantMinas]=new Mina(1000.0,1000.0,15.0);
			
	
		}
	}
	
	public void dibujarMinas(Entorno entorno)
	{
		for(int cantMinas=0;cantMinas<this.bomba.length;cantMinas++)
		{
			this.bomba[cantMinas].dibujarMina(entorno);
		}	
	}
	
	public void activaMina(int flagPosicion)
	{
		if(this.numMina==this.bomba.length)
			this.numMina=0;
		this.bomba[this.numMina].posicionMina(this, flagPosicion);
		this.activarMina=true; // si no esta activado la mina aparece en la pantalla fugazmente			
	
	}

	public void explosionArañas(Entorno entorno,Arañas aracnidos,int nivelDeJuego)
	{
		
		for(int numMina=0;numMina<this.bomba.length;numMina++)
		{
			if(this.bomba[numMina].getMina().contieneAraña(aracnidos))
				{
				this.bomba[numMina].explosionAraña(entorno,this.bomba, aracnidos,this,nivelDeJuego);
				
				}
				
		}
	}
 
	public void explosionDisparo(Entorno entorno,Arañas aracnidos,int nivelDeJuego)
	{
		for(int numMina=0;numMina<this.bomba.length;numMina++)
		{
			if(this.bomba[numMina].getMina().contieneDisparo(this.gun.getShoot()))
				{
				 
				this.bomba[numMina].explosionPorDisparo(entorno,this.bomba,this.gun.getShoot(),aracnidos,this,nivelDeJuego);
				
				}
				
		}
	}
		
	public void minaSobreEdificio(Edificios construccion)
	{
		for(int numMina=0;numMina<this.bomba.length;numMina++)
		{
			if(this.bomba[numMina].getMina().contenidoEnRectangulo(construccion)) //si la mina se coloca arriba de un edificio desaparece
				{
					this.bomba[numMina].getMina().setCentro(new Punto(1000.0,-500.0));
				}
			
		}	
			
		
	}
	
	public void desaparecerMinas()
	{
		for(int numMina=0;numMina<this.bomba.length;numMina++)
		{
			this.bomba[numMina].getMina().setCentro(new Punto(1000.0,-500.0));;;
		}
	}
	
}