package juego;



import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Exterminador jugador;
	private Arañas aracnidos;
	private Pantalla inicio;
	private Edificios construccion;
	private Image imgCesped;
	int nivel,posicion;
	boolean nivel_1,nivel_2,nivel_3,seleccionado_2,seleccionado_3,gameOver;
	// Variables y métodos propios de cada grupo
	// ...
	
	public Juego()
	{
		// Inicializa el objeto entorno
		
		this.posicion=0; //seleccion de espacio
		this.seleccionado_2=false;
		this.gameOver=true;
		this.seleccionado_3=false;
		this.nivel_1=true;
		this.nivel_2=true;
		this.nivel_3=true;
		this.inicio=new Pantalla();
		this.entorno = new Entorno(this, "Prueba del entorno", 800, 600);
		this.jugador=new Exterminador();
		this.aracnidos=new Arañas();
		this.nivel=1; //niveles de juego
		this.imgCesped=Herramientas.cargarImagen("cesped.PNG");
		
		// Inicia el juego!
		this.entorno.iniciar();
	
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick()
	{	
		//pantalla inicial
		this.inicio.principal(entorno);
		if(this.inicio.isMenu() && this.inicio.isControles())
			this.inicio.accionPrincipal(entorno);
		
		
			//seleccionar nivel de juego
		if(!this.inicio.isMenu())
			{
				this.inicio.inicio_Dificultad(entorno);
				this.inicio.accionMenuDificultad(entorno,this);
			
			}	
		
		
		//ventana controles
			if(!this.inicio.isControles())
			{
				this.inicio.controles(entorno);
				this.inicio.accionControles(entorno);
			}
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//	inicio de juego
			if(this.inicio.isJuego())
			{
				dibujarInstancias(); //dibuja jugador, arañas , edificios y todos los elementos restantes del juego
		
				//mientras que la posicion de la araña no sea igual a la del exterminador se ejecuta los movimientos
				if (this.aracnidos.tocaJugador(this.jugador) && !this.jugador.isExplosion())
				{			
					nivelDeJuego();//	controla el nivel de dificultad del juego (cambia la dificultad segun los puntos o el nivel seleccionado)
						
					this.jugador.movimientoJugador(this.entorno);	//mueve al jugador segunda la flecha seleccionada
					this.jugador.movimientoLimitadoJugador(this.entorno, this.construccion); // solucion a las coliciones con los edificios y con los limites de pantalla	
										
					accionArma();    //dibuja arma y balas, direcciona estas ultimas y controla sus colisiones
					accionMina();	 //verifica si una araña interseco alguna mina, de ser asi, gestiona la explosion de esta
					accionTelaraña();//controla interseccion y disminucion de velocidad cuando jugador la intersecta
				}
		
				//gameOver
				else
				{
				if(this.gameOver)
				{
					Herramientas.play("sound_gameover.wav");
					this.gameOver=false;
				}
				this.inicio.gameOver(entorno);
				if(entorno.sePresiono('r'))
				{
					valoresIniciales();
				}
			}
		}
	}
	
	public void valoresIniciales()
	{
		this.seleccionado_2=false;
		this.seleccionado_3=false;
		this.nivel_1=true;
		this.nivel_2=true;
		this.nivel_3=true;
		this.inicio.reset(this.jugador,this.aracnidos);
	}

	public void nivelDeJuego()
	{
		if(this.jugador.getPuntos()>=1000 && !seleccionado_2)
		{						
			this.nivel=2;
		}

		if(this.jugador.getPuntos()>=10000 && !seleccionado_3)
		{
			this.nivel=3;			
		}
		
		if(this.nivel==1)
		{
			if(this.nivel_1)
			{
				this.construccion=new Edificios(4);
				this.jugador.setPosicion(new Punto(400.0,300.0));
				this.aracnidos.nivelDeJuego(this.nivel);
				this.nivel_1=false;
			}
			entorno.cambiarFont("TimesRoman", 15, Color.DARK_GRAY);
			entorno.escribirTexto("Nivel de dificultad: Facil",630.0,20.0);
			this.construccion.dibujarEdificios(entorno);
			this.aracnidos.avanzarArañas(jugador);//activa el avanzar de las arañas
			this.aracnidos.reiniciarArañas();  //reinicia flag de araña
			
		}	

		if(this.nivel==2)
		{
			if(this.nivel_2)
			{	

				this.construccion=new Edificios(10);
				this.jugador.setPosicion(new Punto(400.0,300.0));
				this.aracnidos.nivelDeJuego(this.nivel);
				this.nivel_2=false;
			}
			entorno.cambiarFont("TimesRoman", 15, Color.DARK_GRAY);
			entorno.escribirTexto("Nivel de dificultad: Medio",630.0,20.0);
			this.construccion.dibujarEdificios(entorno);
			this.aracnidos.reiniciarArañas();  //reinicia flag de araña
			this.aracnidos.avanzarDouble(jugador);//activa el avanzar de las arañas
		}
		
		if(this.nivel==3)
		{
			if(this.nivel_3)
			{
				this.construccion=new Edificios(15);
				this.jugador.setPosicion(new Punto(400.0,300.0));
				this.aracnidos.inicializarArañas();
				this.aracnidos.nivelDeJuego(this.nivel);
				this.nivel_3=false;
			}
			entorno.cambiarFont("TimesRoman", 15, Color.DARK_GRAY);
			entorno.escribirTexto("Nivel de dificultad: Dificil",630.0,20.0);
			this.construccion.dibujarEdificios(entorno);
			this.aracnidos.reiniciarArañas();  //reinicia flag de araña
			this.aracnidos.avanzarTriple(jugador);//activa el avanzar de las arañas
		}
	}

	public void dibujarInstancias()
	{
		this.entorno.dibujarImagen(this.imgCesped,400,300,0);
		this.jugador.puntos(entorno);		
		this.aracnidos.activarArañas(entorno,jugador,this.construccion);
		this.jugador.dibujarMinas(entorno);
		this.jugador.dibujarse(entorno); //dibuja plataforma
		this.aracnidos.dibujarTelaraña(entorno);//dibuja telarañas
		this.jugador.getGun().dibujarBalas(entorno,this.jugador);
	}
	
	public void accionArma()
	{
		//dibuja arma
		if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO))
		{				
			jugador.setTiempoEnJuego(0);//temporizador ->aplicar en telaraña
			this.jugador.getGun().setActivada(true);
			this.jugador.getGun().setDibujarPistola(true);
			this.jugador.getGun().activarDisparo();
			Herramientas.play("sound_disparo.wav");
		}
		else
		{
			this.jugador.getGun().setActivada(false);
		}
		
		if(this.jugador.getTiempoEnJuego()<=10 && this.jugador.getGun().isDibujarPistola())
		{
			this.jugador.getGun().posicionArma(jugador);
			this.jugador.getGun().dibujar(entorno, this.jugador);
			if(this.jugador.getTiempoEnJuego()==10) // para q no se dibuje el arma al inicio del juego.
			{
				this.jugador.getGun().setDibujarPistola(false);
			}
		}

		if(this.jugador.getGun().isActivada())// si remplazo activada por disparar la bala queda fija
		{
			this.jugador.getGun().posBala();
		}

		this.jugador.getGun().posicionarBala(this.jugador.getFlagPosicion());
		this.jugador.getGun().impactar(entorno,this.jugador, this.aracnidos, construccion,this.nivel);

	}
	
	public void accionMina()
	{
		//accion de mina
		if(this.entorno.sePresiono('m'))
		{	
			this.jugador.activaMina(this.jugador.getFlagPosicion());
			this.jugador.aumentarNumMina();
		}
		
		this.jugador.explosionArañas(entorno, aracnidos,this.nivel);
		this.jugador.explosionDisparo(entorno, aracnidos,this.nivel);
		
		
	}
	
	public void accionTelaraña()
	{
			//	accion telaraña araña
			this.jugador.minaSobreEdificio(this.construccion);
			this.jugador.jugadorTocaTelaraña(this.aracnidos);
			this.jugador.activarCambioAVelocidadNormal();
			this.jugador.velocidadNormal();
	}

	
	

	
	
	public static void main(String[] args)
	{
		
		Juego ASD = new Juego();
		
	}
}	
					
				
				

		
	

	
