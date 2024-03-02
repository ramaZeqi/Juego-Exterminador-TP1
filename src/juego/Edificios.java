package juego;

import java.util.Random;

import entorno.Entorno;


public class Edificios 
{
	Punto[][] posicionesDisponebles;
	private Edificio[] construccion;
	private int alto,ancho,filaPosicion,columnaPosicion,incrementoEdificios,numEdificiosFijos;
	

	public Edificios(int numEdificiosFijos)
	{		
		
		Random num= new Random();
		 
		this.numEdificiosFijos=numEdificiosFijos;
		this.incrementoEdificios=4;
		this.alto=50;  
		this.ancho=80;
		this.filaPosicion= (600-(this.alto*2))/(this.alto*2);     
		this.columnaPosicion=(800-(this.ancho*2))/(this.ancho*2); 
		//creo una array de dos dimensiones para "simular" un grafico en R2
		this.posicionesDisponebles=new Punto[this.filaPosicion][this.columnaPosicion];
		this.construccion=new Edificio[this.numEdificiosFijos+num.nextInt(this.incrementoEdificios)]; //-> max edificios 20
		iniciarPosPosibles(); //divide la plataforma(restringida) en partes iguales 
		inicializarEdificios();	
	}

//-------------------------------------------------------------------------------------------------------------------------------
//Getters y setters
	public Punto[][] getPosicionesDisponebles() {
		return posicionesDisponebles;
	}

	public void setPosicionesDisponebles(Punto[][] posicionesDisponebles) {
		this.posicionesDisponebles = posicionesDisponebles;
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

	public int getFilaPosicion() {
		return filaPosicion;
	}

	public void setFilaPosicion(int filaPosicion) {
		this.filaPosicion = filaPosicion;
	}

	public int getColumnaPosicion() {
		return columnaPosicion;
	}

	public void setColumnaPosicion(int columnaPosicion) {
		this.columnaPosicion = columnaPosicion;
	}

	public int getIncrementoEdificios() {
		return incrementoEdificios;
	}

	public void setIncrementoEdificios(int incrementoEdificios) {
		this.incrementoEdificios = incrementoEdificios;
	}

	public int getNumEdificiosFijos() {
		return numEdificiosFijos;
	}

	public void setNumEdificiosFijos(int numEdificiosFijos) {
		this.numEdificiosFijos = numEdificiosFijos;
	}
	
	public Edificio[] getConstruccion() {
		return construccion;
	}

	public void setConstruccion(Edificio[] construccion) {
		this.construccion = construccion;
	}	
	
//--------------------------------------------------------------------------------------------------------
	public boolean distanciaEntreEdificio(int posBloque) //util para saber si un edificio se repite en el array de construccion
	{
			for(int numBloque=0;numBloque<posBloque;numBloque++ )
			{
			if(this.construccion[numBloque].getPosicion().getX()==this.construccion[posBloque].getPosicion().getX() && this.construccion[numBloque].getPosicion().getY()==this.construccion[posBloque].getPosicion().getY())
				return true;																																												
			
			}
		return false;
			
	}
	
	public void iniciarPosPosibles() //matriz con las posicion que pueden tomar los edificios (rango de posiciones: X= (80-680) e Y=(80-480) 
	{								
		for(int y=0;y<this.filaPosicion;y++)
		{
			//ciclo relacionado al valor de y  'y'
				for(int x=0;x<this.columnaPosicion;x++)
					{
					//ciclo relacionado al valor de 'x'
					 
					this.posicionesDisponebles[y][x] = new Punto(80*2*(x+1), 50*2*(y+1));
					
									
					}
		}
	}
		
	public void inicializarEdificios()	//inicializa el array de edificios a utilizar
	{	
		Random num= new Random();
		
		for(int numEdificio=0; numEdificio<this.construccion.length;numEdificio++)
		{
			this.construccion[numEdificio]=new Edificio(this.posicionesDisponebles[num.nextInt(this.filaPosicion)][num.nextInt(this.columnaPosicion)],this.alto,this.ancho);
			while(distanciaEntreEdificio(numEdificio))
			{
				this.construccion[numEdificio]=new Edificio(this.posicionesDisponebles[num.nextInt(this.filaPosicion)][num.nextInt(this.columnaPosicion)],this.alto,this.ancho);
			}
	
		}
	}
	
	public void dibujarEdificios(Entorno entorno)
	{
		for(int numEdificio=0;numEdificio<this.construccion.length;numEdificio++) //dibuja edificios
			this.construccion[numEdificio].dibujarse(entorno);
	}
	
 
	
}
	


