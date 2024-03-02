package juego;

public class Punto
{
	private double x;
	private double y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
//-----------------------------------------------------------------------------------------------------------------------------
//Getters y setters
	public double getX()
	{
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
//-----------------------------------------------------------------------------------------------------------------------------	
	
	public void moverX(double x) {
		this.x += x;
	}
	
	public void moverY(double y) {
		this.y += y;
	}
	
	public void desplazar(double despx,double despy)
	{
		this.x+=despx;
		this.y+=despy;
	}
	
	public double distancia(Punto pos2) //necesaria para la implementacion de la clase Circulo
	{
		return Math.sqrt((Math.pow(Math.abs(this.x-pos2.getX()),2)+Math.pow(Math.abs(this.y-pos2.getY()),2)));
	
	}
	
	public boolean posIguales(Exterminador pos2) //implimentacion directa en la clase juego
	{											
		if(pos2.getPosicion().getX()== this.x&& pos2.getPosicion().getY()== this.y)
		{
			return true;
		}
		return false;
		
	}
			
	public static boolean contenidoEnRectangulo(Edificios bloques,Exterminador jugador)
	{
		for (int numEdi=0;numEdi<bloques.getConstruccion().length;numEdi++)
		{
			if( jugador.getPosicion().getX() >= bloques.getConstruccion()[numEdi].getPosicion().getX()- ((bloques.getConstruccion()[numEdi].getAncho()/2)+ (jugador.getAncho()/2)) &&
					jugador.getPosicion().getX() <= bloques.getConstruccion()[numEdi].getPosicion().getX() +((bloques.getConstruccion()[numEdi].getAncho()/2)+ (jugador.getAncho()/2)) &&
					jugador.getPosicion().getY() >= bloques.getConstruccion()[numEdi].getPosicion().getY() - ((bloques.getConstruccion()[numEdi].getAlto()/2)+ ((jugador.getAlto()/2)))&&
					jugador.getPosicion().getY() <= bloques.getConstruccion()[numEdi].getPosicion().getY() +((bloques.getConstruccion()[numEdi].getAlto()/2)+ (jugador.getAlto()/2)))	
						return false;
		}
		return true;
	}

	
	public static boolean contenidoEnRectangulo(Edificios bloques,Punto posicion,int alto,int ancho)
	{
		for (int i=0;i<bloques.getConstruccion().length;i++)
		{
	if( posicion.getX() > bloques.getConstruccion()[i].getPosicion().getX()- ((bloques.getConstruccion()[i].getAncho()/2) + (ancho/2)) &&
				posicion.getX() <bloques.getConstruccion()[i].getPosicion().getX() +((bloques.getConstruccion()[i].getAncho()/2) + (ancho/2)) &&
				posicion.getY() > bloques.getConstruccion()[i].getPosicion().getY() - ((bloques.getConstruccion()[i].getAlto()/2) + (alto/2))&&
				posicion.getY() < bloques.getConstruccion()[i].getPosicion().getY() +((bloques.getConstruccion()[i].getAlto()/2) + (alto/2)))	
					return false;
		}
		return true;
	}

			
}