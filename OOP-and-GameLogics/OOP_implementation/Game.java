//arquivo de impolementação da classe Game

package class_orientation_implementation;//pacotes

//implementação da classe
public class Game {

	//criando dentro da classe player um objeto Player e um Objeto Enemy - privados
	private Player player;
	private Enemy enemy;
	
	//metodo construtor
	public Game() {
		player = new Player();//instancia um novo player
		enemy = new Enemy();//instancia um novo Enemy
	}
	
	//metodo da classe Game para pegar seu objeto interno player que é do tipo Player
	public Player getPlayer() {
		return player;
	}
	
	//metodo da classe Game para pegar seu objeto interno enemy que é do tipo Enemy
	public Enemy getEnemy() {
		return enemy;
	}
	
	//metodo *estático* principal de um programa em Java
	public static void main(String[] args) {
		Game game = new Game();//instancia um objeto do tipo Game
		//instancia um novo objeto player do tipo Player, porém com escopo local agora
		//embora this.player seja = game.getPlayer, o que o torna quase como um ponteiro,
		//uma vez que virtualmente aponta para o player original.
		Player player = game.getPlayer();
		//this.player usa o metodo de atacar no inimigo que será puxado pela função "game.getEnemy()"
		player.playerAttack(game.getEnemy());
		//mostra na tela o metodo "getLife()" do inimigo que sera puxado pelo metodo "game.getEnemy()"
		System.out.println(game.getEnemy().getLife());
	}
	
	
}
