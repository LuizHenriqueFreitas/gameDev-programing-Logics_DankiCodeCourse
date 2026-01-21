//arquivo com implementação de interface gráfica

package grafic_implementation;//pacotes

//importação de recursos do java
import java.awt.Canvas; //importando classe de Canvas
import java.awt.Color; //importação de cores pré configuradas
import java.awt.Dimension; //importando Classe de Dimension
import java.awt.Font; //importação de editor de fontes
import java.awt.Graphics; //importação da biblioteca "Graphics"
import java.awt.Graphics2D; //importação da biblioteca "Graphics2D"
import java.awt.image.BufferStrategy; //importação da biblioteca "BufferStrategy"
import java.awt.image.BufferedImage; //importação da biblioteca "BufferImage"

import javax.swing.JFrame; //importando recurso de JFrame

//classe principal onde a interface é desenhada
public class Game extends Canvas implements Runnable{ //herda a classe Canvas e implementa a intervace Runnable

	//variaveis para configuração da janela
	public static JFrame frame; //instanciando um objeto do tipo JFrame que ajuda no contrela do gráfico
	private Thread thread; //instanciando uma Thread
	private boolean isRunning = true; //variavel que verifica se o jogo está rodando ou não
	private final int WIDTH = 160; //constante para o tamanho horizontal da tela
	private final int HEIGHT = 120; //constante para o tamanho vertical da tela
	private final int SCALE = 4; //escala de dimensionamento da tela
	
	//variaveis relacionadas a imagens esternas
	private BufferedImage image; //instancia um objeto BufferedImge, tem finalidades gráficas
	
	private Spritesheet sheet; //variavel do tipo spritesheet - vai receber uma imagem de spritesheet
	private BufferedImage noAnimationPlayer; //variavel referente ao player sem animação
	
	private BufferedImage[] player; //array que recebe todos os sprites recordados do spritesheet relacionados a animação do player
	//controladores de animação
	private int frames = 0; //contador de frames
	private int maxFrames = 10; //tempo que um sprite permanece antes de passar para o próximo
	private int curAnimation = 0, maxAnimation = 3; //contadores de sprite atual e maximo de sprites 3 de 0 até 2 (0-1-2)
	
	//metodo construtor
	public Game() {
		//importação e divisão de spritesheet
		sheet = new Spritesheet("/spritesheet.png"); //atribui a variavel sheet a imagem presente no caminho, no caso o arquivo "/spriteSheet.png" que esta na pasta "res"
		noAnimationPlayer = sheet.getSprite(0, 0, 32, 32); //informa qual é o recorte do player sem animação
		player = new BufferedImage[3]; //informa que o array do player animado vai ter 3 slots (0-1-2)
		player[0] = sheet.getSprite(0, 0, 32, 32); //informa qual é o recorte do slot 1 do player
		player[1] = sheet.getSprite(32, 0, 32, 32); //informa qual é o recorte do slot 2 do player
		player[2] = sheet.getSprite(64, 0, 32, 32); //informa qual é o recorte do slot 3 do player
		//cria a janela usando o recurso "Dimension()" e as constantes como parâmetro
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		//metodo de configuração da janela e JFrame
		initFrame();
		//atribui um objeto real na variavel image
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	//metodo de configuração da janela e JFrame
	public void initFrame() {
		frame = new JFrame("Game #01"); //atribui valor um objeto a frame
		frame.add(this); //adiciona esta classe como parametro para o objeto frame
		frame.setResizable(false); //informa que redimensionar a janela é falso, portanto não é possível redimensiona-la
		frame.pack(); //recurso de performance
		frame.setLocationRelativeTo(null); //informa que não há localização relativa a nada
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //faz com que o Jframe encerre todo o programa ao ser fechado
		frame.setVisible(true); //torna visível
	}
	
	//metodo de start sincronizado
	public synchronized void start() {
		thread = new Thread(this); //atribui um objeto Thread com esta classe como parametro
		isRunning = true; //coloca o jogo para rodar
		thread.start(); //inicia a thread
		
	}
	
	//metodo de finalização sincronizado
	public synchronized void stop() {
		//faz o jogo parar de rodar
		isRunning = false;
		try {
			thread.join();//encerra a thread corretamente
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//metodo estático principal de inicio
	public static void main(String args[]) {
		Game game = new Game(); //instancia um novo objeto game da classe Game
		game.start();//inicia o jogo pelo metodo "start();" do objeto game
	}
	
	//metodo de update de logica
	public void tick() {
		frames++;//adiciona +1 no contador de frames
		//se frames for maior que o maximo permitido
		if(frames > maxFrames) {
			frames = 0; //zera o contador de frames
			curAnimation++; //passa pra próxima animação
			//se animação atual é maior ou igual ao maximo de animações
			if(curAnimation >= maxAnimation) {
				curAnimation = 0;//retorna para a primeira animação
			}
		}
	}
	
	//metodo de update de renderização
	public void render() {
		// variavel "bs" controla a estratégia de buffer do render
		BufferStrategy bs = this.getBufferStrategy();
		//caso bs seja nulo
		if(bs == null) {
			this.createBufferStrategy(3);//cria 3 estágios do bufferStrategy, ele deixa de estar vazio
			return;//sai da condição
		}
		//perceba que g vai funcionar como um ponteiro, por enquanto vai apontar os recursos de "image" com o metodo "getGraphics()" de image
		Graphics g =image.getGraphics();//variavel g pega os graficos do objeto image
		
		/*
		 Aqui no java o que acontede é que, 
		 quanto antes uma coisa é renderizada, 
		 mais atrá ela fica no render,
		 se quiser que algo fique acima ou a frente,
		 renderize ele depois ou mais abaixo 
		 falando em códigos o que fica em cima
		 rendezida mais no fundo. 
		*/
		
		//configurando retalgulo referente ao background
		g.setColor(new Color(145, 234, 76)); //configura a cor do objeto image (com RGB manual)
		g.fillRect(0, 0, WIDTH, HEIGHT); //desenha um retangulo apartir do objeto image
		
		//configurando um segundo retangulo
		g.setColor(new Color(25, 116, 243)); //configura a cor do objeto image (com RGB manual)
		g.fillRect(25, 60, 49, 23); //desenha um retangulo apartir do objeto image
		
		//configurando uma elipse
		g.setColor(Color.MAGENTA); //configura a cor do objeto image (com as cores pré configuradas da classe Color)
		g.fillOval(100, 30, 30, 20); //desenha a elipse artir do objeto image
		
		//configura um texto
		g.setFont(new Font("Arial", Font.BOLD, 15)); //configura a Font do objeto image
		g.setColor(Color.black); //configura a cor do objeto image (com as cores pré configuradas da classe Color)
		g.drawString("Este é um texto", 3, 19); // desenha a String apartir do objeto image
		
		//desenha um recorte da spritesheet sem animação
		g.drawImage(noAnimationPlayer, 20, 20, null);
		
		//instanciando um Graphic2para a animação
		Graphics2D g2 = (Graphics2D) g; //converte g de graphic comum para graphic2D
		g2.drawImage(player[curAnimation], 90, 90, null); //desenha o frame atual da animação apartir do objeto imagem
		
		//recurso de performance
		g.dispose();
		
		//troca o significado de g, agora ele aponta para "bs", com o metodo "getDrawGraphics()" de bs
		g = bs.getDrawGraphics(); //atribui g aos metodos de desenhar graficos de bs
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null); //desenha a imagem geral do canvas a partir de bs
		bs.show(); //mostra a tela com o metodo "show()" diretamente de bs
	}
	
	//metodo de loop
	@Override
	public void run() {
		//declaração das variaveis locais de controle
		long lastTime = System.nanoTime(); //variavel referente ao ultimo update
		double amountOfTicks = 60.0; //variavel referente ao numero de updates desejados por segundo
		double ns = 1000000000 / amountOfTicks; //recurso para os calculos
		double delta = 0; //recurso para os calculos
		int frames = 0; //contador de frames gerados
		double timer = System.currentTimeMillis(); //contador de tempo
		
		//loop de repetição
		while(isRunning) {
			long now = System.nanoTime(); //coleta o tempo do momento presente
			delta += (now - lastTime) / ns; //delta é acrescido da divisão do resto entre agora e o ultimo update pelo valor de ns 
			lastTime = now; //o ultimo update se torna o momento presente
			//caso delta seja maior ou igual a 1
			if(delta >= 1) {
				tick(); //update de logica
				render(); //update de tenderização
				frames++; //soma +1 aos frames
				delta--; //decrementa 1 de delta
			}
			//caso o tempo presente - contador de tempo seja maior ou igual a 1000
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+ frames); // escreva no console: FPS + o valor da variavel frame
				frames = 0; //zera o numero de frames
				timer+=1000; //aumenta o valor de timer em 1000
			}
		}
		//chama o metodo de encerramento adequado do programa
		stop();
	}
}
