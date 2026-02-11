//PlayerGame02.cs
/*
 * Esse codigo tem sprits para mocimentação de camera e detecção de colisão do ponteiro do mouse com o mundo 3D
 * */

using UnityEngine;                                          //biblioteca dos recursos da Unity

//Classe Player2 herdando os recursos da classe MonoBehaviour - base de todos os scripts da Unity
public class Player2 : MonoBehaviour 
{

    public float speedH = 0.2f;                             // velocidade de movimento horizontal da camera
    public float speedV = 0.2f;                             // velocidade de movimento vertical da camera

    public float yaw = 0.0f;                                //offset de rotação horizontal da camera
    public float pitch = 0.0f;                              //offset de rotação vertical da camera
            
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        Cursor.visible = false;                             // Esconde o cursor do mouse
        Cursor.lockState = CursorLockMode.Locked;           // Trava o cursor do mouse no centro da tela
    }

    // Update is called once per frame
    void Update()
    {
        pitch -= speedV * Input.GetAxis("Mouse Y");         // Calcula o novo valor de pitch com base no movimento vertical do mouse
        yaw += speedH * Input.GetAxis("Mouse X");           // Calcula o novo valor de yaw com base no movimento horizontal do mouse
        transform.eulerAngles = new Vector3(pitch, yaw, 0); // Aplica a rotação calculada à câmera
        
        // caso o botão esquerdo do mouse seja pressionado
        if (Input.GetMouseButtonDown(0))
        {
            detectCollisionRay();                           // Chama a função para detectar colisões usando um raio
        }
    }

    // Função para detectar colisões usando um raio (raycast) - estamos usando para saber onde o mouse esta clicando no ambiente 3D
    void detectCollisionRay()
    {
        RaycastHit hit;                                     //instancia um objeto do tipo RaycastHit para armazenar as informações da colisão detectada pelo raycast
        //caso o raycast detecte uma colisão, ele retorna true e armazena as informações da colisão no objeto hit
        if (Physics.Raycast(transform.position, transform.forward, out hit, 100f))
        {
            GameObject obj = hit.collider.gameObject;       //obtém o objeto que foi atingido pelo raycast
            // caso seja uma bala
            if (obj.tag == "bullet")
            {
                Destroy(obj);                               //destroi o objeto atingido
            }
        }
    }
}
