package juego;

public class Circulo
{

	private double radio;
	private Punto centro;

	public Circulo( double centrox,double centroy,double radio) {
		
		this.radio = radio;
		this.centro = new Punto(centrox,centroy);
	}
//----------------------------------------------------------------------------------------------------------------------------
//Getters y setters
	
	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}

//-----------------------------------------------------------------------------------------------------------------------------

	public boolean contiene(Punto c1)
	{
		double dis=this.centro.distancia(c1);
		if(dis<=this.radio)
			return true;
		return false;
	}

	public boolean contiene(Circulo c1)
	{
		double dis=this.centro.distancia(c1.getCentro());
		if(dis-c1.getRadio()<=this.radio)
			return true;
		return false;
	}
	
	public boolean contieneAraña(Arañas insectos)
	{
		double dis;
		for(int cantidadArañas=0;cantidadArañas<insectos.getInsectos().length;cantidadArañas++)
		{
			dis=this.centro.distancia(insectos.getInsectos()[cantidadArañas].getPosAra().getCentro());
			if(dis-insectos.getInsectos()[cantidadArañas].getPosAra().getRadio()<=this.radio)
				return true;
		}
		return false;
	}
	
	public boolean contieneDisparo(Bala[]disparo)	//disparo interseca a circulo(araña)
	{
		for(int numDisparo=0;numDisparo<disparo.length;numDisparo++)
		{	
			double dis=this.centro.distancia(disparo[numDisparo].getPosicion());
			if(dis<=this.radio)
			{
				return true;
			}
		}
		return false;
	}
 
	public boolean contenidoEnRectangulo(Edificios bloques)
	{
		for (int numBloque=0;numBloque<bloques.getConstruccion().length;numBloque++)
		{
			if( this.centro.getX() > bloques.getConstruccion()[numBloque].getPosicion().getX()- ((bloques.getConstruccion()[numBloque].getAncho()/2)+ (this.radio/2)) &&
					this.centro.getX() <bloques.getConstruccion()[numBloque].getPosicion().getX() +((bloques.getConstruccion()[numBloque].getAncho()/2)+ (this.radio/2)) &&
					this.centro.getY() > bloques.getConstruccion()[numBloque].getPosicion().getY() - ((bloques.getConstruccion()[numBloque].getAlto()/2)+ (this.radio/2))&&
					this.centro.getY() < bloques.getConstruccion()[numBloque].getPosicion().getY() +((bloques.getConstruccion()[numBloque].getAlto()/2)+ (this.radio/2)))
					return true;
		}
		return false;
	}
	
	public boolean contieneMinas(Mina[] bombas)
	{
		double dis;
		for(int numMinas=0;numMinas<bombas.length;numMinas++)
		{
			
			if(!(this==bombas[numMinas].getMina()))  //mientras que la mina sea diferente a la instancia de mina que convoca a la funcion
			{
				dis=this.centro.distancia(bombas[numMinas].getMina().getCentro());
				if(dis - bombas[numMinas].getMina().getRadio()<=this.radio)
					return true;
			}
		}
		return false;
	}
	
	
}
	
	
	
	
	
