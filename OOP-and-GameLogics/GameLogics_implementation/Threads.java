//arquivo com a implementação de uma Thread

//**ATTENTION!** You need to replace this file name to "Game.java", and this will work right

package Threads_implementation_exemple;//pacotes

//implementação da classe Game
public class Game implements Runnable {//é necessário implementar a interface runnable para usar threads
	
	//variavel privada de controle, verifica se a thread deve estar rodando ou não
	private boolean isRunning;
	//objeto thread do tipo Thread
	private Thread thread;

	//função estática principal de inicio de programa
	public static void main(String[] args) {
		Game game = new Game();//implementa um objeto game da classe Game
		game.start();//chama o metodo start() do objeto game
	}
	
	//metodo start - é sincronizado, recurso normalmente usado sómente para game dev
	public synchronized void start() {
		isRunning = true;// informa que a thread deve ser iniciada
		thread = new Thread(this);//instancia uma nova thread passando como parametro o próprio arquivo como a si mesmo por meio do "this"
		thread.start();//inicia a thead com seu metodo, original da classe Thread, "start()"
	}
	
	//metodo que atualiza o jogo
	public void tick() {
		System.out.println("Tick");//debug message
	}
	
	//metodo que renderiza/ desenha o jogo
	public void render() {
		System.out.println("Render");//debug message
		
	}
	
	//sobreescreve o metodo "run()" da interface Runnable
	@Override
	public void run() {
		//looping 'infinito' enquanto a thread existir
		while(isRunning) {
			tick();//chama a função "tick()" do objeto game
			render();//chama a função "render()" do objeto game
		}
	}
	
}
