//arquivo de implementação da classe Enemy

package class_orientation_implementation;//pacotes

//implementação da classe
public class Enemy {
	
	//atributo de vida declarado como privado
	private int life = 5;

	//metodo para tomar dano
	public void takeDamage(int damageValue) {//recebe o valor do dano como parâmetro
		life -= damageValue;//decrementa da vida o valor do dano
	}
	
	//metodo get do atributo vida
	public int getLife() {
		return life;
	}
}
