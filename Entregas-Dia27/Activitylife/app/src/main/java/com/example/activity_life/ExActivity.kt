package com.example.activity_life

import android.app.Activity
import android.os.Bundle
import android.util.Log

class ExActivity : Activity(){
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        Log.v("Acticity","onCreate")
        setContentView(R.layout.layout)
    }
    override fun onStart() {
        super.onStart()
        Log.v("Acticity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Acticity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("Acticity","onPause")
    }

    override fun onDestroy() {
        Log.v("Acticity","onDestroy")

        super.onDestroy()
    }

    //Tarefa 1 - Abra a tela principal do Android para tirar a sua aplicação do foco
    //ativou o onPause
    //Tarefa 2 - Abra outra aplicação
    //nada mudou
    //Tarefa 3 - Abra novamente no Android o seu App (o que vc acabou de criar)
    //Primeiro ativou o onStart depois onResume
    //Tarefa 4 - Rotacione o celular na horizontal para deixa-lo em modo (paisagem)
    //ao rotacionar ativou a seguinte sequencia : onPause, onDestroy, onCreate, onStart e onResume
    //Tarefa 4 - Rotacione o celular de volta para a vertical para deixá-lo em modo (retrato)
    //ao voltar ativou a seguinte sequencia : onPause, onDestroy, onCreate, onStart e onResume


}