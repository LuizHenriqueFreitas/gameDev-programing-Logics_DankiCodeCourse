//Spawner.cs
/*
 * Esse codigo tem sprits para spawnar balas em direção ao player com intervalo de tempo randomico
 * */

using Unity.VisualScripting;                                    //biblioteca dos recursos de visual scripting da Unity
using UnityEngine;                                              //biblioteca dos recursos da Unity

//Classe Spawner herdando os recursos da classe MonoBehaviour - base de todos os scripts da Unity
public class Spawner : MonoBehaviour 
{
    public GameObject bullet;                                  // variável pública para armazenar o prefab da bala que será instanciada

    float timer = 2f;                                          // tempo em segundos para controlar o intervalo de spawn das balas
    float curtimer = 0;                                        // contador do tempo decorrido desde o último spawn

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        GameObject player = GameObject.Find("Player");         // encontra o objeto do Player na cena usando seu nome
        transform.LookAt(player.transform);                    // faz com que o objeto Spawner olhe na direção do Player, ou seja, a frente do Spawner estará voltada para o Player
    }

    // Update is called once per frame
    void Update()
    {
        curtimer += Time.deltaTime;                            // incrementa o contador de tempo com o tempo decorrido desde o último frame
        //caso o contador seja maior ou igual ao tempo definido para o spawn
        if (curtimer >= timer)
        {
            int n = Random.Range(0, 100);                      // gera um número aleatório entre 0 e 100 para controlar a chance de spawnar
            //caso o número gerado seja menor que 50 (50% de chance)
            if (n < 50) 
            {
                GameObject obj = Instantiate(bullet, transform.position + transform.forward*2f, Quaternion.identity); // instancia uma nova bala usando o prefab armazenado na variável bullet, na posição do Spawner mais um deslocamento para frente (transform.forward*2f) e sem rotação (Quaternion.identity)
                Destroy(obj, 4f);                              //destroi a bala instanciada após 4 segundos para liberar memoria

            }
            curtimer = 0;                                      //zera o contador
        }
    }
}
