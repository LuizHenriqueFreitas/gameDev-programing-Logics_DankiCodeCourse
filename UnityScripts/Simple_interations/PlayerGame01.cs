//PlayerGame01.cs
/*
 * Esse codigo tem sprits para mocimentação do player, movimentação da camera, detecção de colisão com inimigos e chão, controle de game over e pontuação
 * */
 
using Mono.Cecil.Cil;                                          // biblioteca para manipulação de código IL (Intermediate Language)
using System.Runtime.InteropServices;                          // biblioteca para interoperabilidade com código nativo
using UnityEngine;                                             // biblioteca dos recursos da Unity
using UnityEngine.InputSystem;                                 // biblioteca para o sistema de entrada da Unity, usada para detectar a entrada do teclado

//Classe Player herdando os recursos da classe MonoBehaviour - base de todos os scripts da Unity
public class Player : MonoBehaviour
{

    private float spd = 0.004f;                                 // velocidade de movimento do player
    private GameObject mainCamera;                              // aponta para o objeto da câmera principal na cena
    private GameObject gameOverUI;                              // aponta para o canvas que exibe a mensagem de game over
    public bool gameOver = false;                               // controlador do estado do game

    public GameObject pontosUI;                                 // aponta para do canvas que exibe a pontuação do jogador
    public int pontos = 0;                                      // armazena a pontuação do player

    private bool canJump = false;                               // controla se o player pode pular ou não
    public Rigidbody rb;                                        // aponta para o componente Rigidbody do player para aplicar forças físicas
    private int jumpForce = 300;                                // Controla a força do pulo do player

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        mainCamera = GameObject.Find("Main Camera");            // atribui a "mainCamera" o objeto camera usado na cena
        gameOver = false;                                       // diz que game over é falso, portanto o jogo esta rodando
        gameOverUI = GameObject.Find("Canvas");                 // atribui a "gameOverUI" o objeto canvas usado para exibir a mensagem de game over
        pontosUI = GameObject.Find("Teste").transform.Find("Pontos").gameObject; // atribui "pontosUI" a pontuação do jogador, que é um filho do objeto "Teste"
        // gameOverUI.gameObject.SetActive(false); 
    }

    // Update is called once per frame
    void Update()
    {
        //caso o jogo não tenha acabado
        if (gameOver == false)
        {
            //transform.position += new Vector3(0, 0, spd);
            mainCamera.transform.position = transform.position + new Vector3(0, 2f, -5f); // faz a camera seguir o player , mantendo um offset de 2 unidades para cima e 5 unidades para trás
            //caso a tecla D seja pressionada
            if (Keyboard.current.dKey.isPressed)
            {
                transform.position += new Vector3(spd, 0, 0);   // move o player para a direita, adicionando um vetor de deslocamento positivo no eixo x à posição atual do player
            }
            //caso a tecla A seja pressionada
            else if (Keyboard.current.aKey.isPressed)
            {
                transform.position += new Vector3(-spd, 0, 0);  // move o player para a esquerda, adicionando um vetor de deslocamento negativo no eixo x à posição atual do player
            }
            //caso seta para direita seja pressionada
            if (Keyboard.current.rightArrowKey.isPressed)
            {
                transform.Rotate(0, 0.2f, 0);                   // gira o player para a direita, adicionando um vetor de rotação positivo no eixo y à rotação atual do player
            }
            //caso seta para esquerda seja pressionada
            else if (Keyboard.current.leftArrowKey.isPressed)
            {
                transform.Rotate(0, -0.2f, 0);                  // gira o player para a esquerda, adicionando um vetor de rotação negativo no eixo y à rotação atual do player
            }
            //caso a barra de espaço seja pressionada
            if (Keyboard.current.spaceKey.isPressed)
            {
                // caso o player possa pular
                if (canJump)
                {
                    canJump = false;                            // diz que o player não pode pular
                    rb.AddForce(0, jumpForce, 0);               // aplica uma força para cima no Rigidbody do player, fazendo com que ele pule
                }
            }
            transform.position += transform.forward * 0.01f;    // move o player para frente, adicionando um vetor de deslocamento positivo no eixo z à posição atual do player
        }
        // caso o jogo tenha acabado e a barra de espaço seja pressionada
        else if (Keyboard.current.spaceKey.isPressed)
        {
            Application.LoadLevel(Application.loadedLevel);     // recarrega a cena atual, reiniciando o jogo
        }
    }

    // Função chamada quando o player colide com outro objeto
    void OnCollisionEnter(Collision obj)
    {
        //caso o objeto colidido seja um inimigo
        if (obj.gameObject.tag == "Enemy")
        {
            gameOverUI.GetComponent<Canvas>().enabled = true;   // ativa o canvas para exibir a mensagem de game over
            gameOver = true;                                    // diz que o jogo acabou
            //Debug.Log("Perdeu!"); //log de desenvolvimento
        }
        //caso o objeto colidido seja o chão
        else if (obj.gameObject.tag == "floor")
        {
            canJump = true;                                     // diz que o player pode pular
        }
    }
}
