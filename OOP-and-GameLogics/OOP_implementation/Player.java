//Arquivo de implementação da classe Player

package class_orientation_implementation;//pacotes

//declaração da classe
public class Player {
	
	//atributos declarados de maneira privada
	private int life = 100;//variavel de vida
	private int attackDamage = 1;//variavel de dano que o jogador causa
	
	//função de ataque do jogador
	public void playerAttack(Enemy enemy) {//trás um objeto inimigo como parâmetro
		
		//executa o metodo de "tomar dano" do objeto que foi trazido, 
		//usa como valor do dano causado a variavel "attackDamage"
		enemy.takeDamage(attackDamage);;
	}

}
