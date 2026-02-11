//Bullet.cs - script para controlar o comportamento da bala no jogo
/*
 * Esse codigo tem sprits para controlar o movimento da bala em direção ao player, atribuindo uma velocidade aleatória para cada bala
*/

using UnityEngine;                                      //biblioteca dos recursos da Unity

//Classe Bullet herdando os recursos da classe MonoBehaviour - base de todos os scripts da Unity
public class Bullet : MonoBehaviour
{
    float spd = 0;                                      // velocidade de movimento da bala

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        spd = Random.Range(0.01f, 0.1f);                // atribui um valor aleatório entre 0.01 e 0.1 para a velocidade da bala
        GameObject player = GameObject.Find("Player");  // encontra o objeto do Player na cena usando seu nome
        transform.LookAt(player.transform);             // faz com que a bala olhe na direção do Player
    }

    // Update is called once per frame
    void Update()
    {
        transform.position += transform.forward * spd;  // move a bala para frente
    }
}
