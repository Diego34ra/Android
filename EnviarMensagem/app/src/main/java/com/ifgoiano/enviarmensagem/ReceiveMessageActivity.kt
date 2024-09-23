package com.ifgoiano.enviarmensagem

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReceiveMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_message)

        val textViewMessage: TextView = findViewById(R.id.textView)

        val message = intent.getStringExtra("mensagem")
        textViewMessage.text = message
    }
}