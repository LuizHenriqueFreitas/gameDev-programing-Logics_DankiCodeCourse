//EventClicks.cs
/*
 * Esse codigo tem sprits para carregar as cenas dos jogos 1 e 2 quando os botoes correspondentes sejam clicados
 * */

using UnityEditor.SearchService;    //biblioteca dos recursos de busca da Unity
using UnityEngine;                  //biblioteca dos recursos da Unity
using UnityEngine.SceneManagement;  //biblioteca dos recursos de gerenciamento de cenas da Unity

public class EventClick : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {

    }

    //caso o botao que tenha essa função linkada seja selecionado
    public void clickButtonJogo1()
    {
        SceneManager.LoadScene("Jogo01"); //carrega a cena do jogo 1 usando o nome da cena definido na Unity
    }

    //caso o botao que tenha essa função linkada seja selecionado
    public void clickButtonJogo2()
    {
        SceneManager.LoadScene("Jogo02"); //carrega a cena do jogo 1 usando o nome da cena definido na Unity
    }
}
