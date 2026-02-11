//Enemy.cs
/*
 Esse codigo tem sprits para detectar quando o player passa por um inimigo, aumentar a pontuação do player e destruir o inimigo
 */

using UnityEngine;                                                                                              //biblioteca dos recursos da Unity
using UnityEngine.UI;                                                                                           // biblioteca dos recursos de interface gráfica da Unity, usada para atualizar a pontuação exibida na tela

//Classe Enemy herdando os recursos da classe MonoBehaviour - base de todos os scripts da Unity
public class Enemy : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float z = transform.position.z;                                                                         // obtém a posição z do inimigo
        float playerZ = GameObject.Find("player").transform.position.z;                                         // obtém a posição z do jogador usando o nome do objeto 
        // caso o jogador tenha passado o inimigo
        if (playerZ > z)
        {
            Player playerScript = GameObject.Find("player").GetComponent<Player>();                             // extrai o script do jogador para acessar a pontuação
            playerScript.pontos++;                                                                              // incrementa a pontuação do jogador - acesso pelo scripft
            playerScript.pontosUI.GetComponent<UnityEngine.UI.Text>().text = playerScript.pontos.ToString();    // atualiza a pontuação exibida na tela convertendo o valor inteiro para string 
            Destroy(gameObject);                                                                                // destrói o objeto do inimigo para removê-lo da cena
            Debug.Log("Enemy destroyed");                                                                       //lod de desenvolvimento
        }
    }
}
