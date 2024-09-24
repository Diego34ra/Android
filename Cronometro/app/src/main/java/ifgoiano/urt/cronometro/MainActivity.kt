package ifgoiano.urt.cronometro

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var segundos: Int = 0
    private var rodando: Boolean = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        runTimer()

    }

    fun onClickStart(view: View) {
        rodando = true
    }

    fun onClickStop(view: View) {
        rodando = false
    }

    fun onClickReset(view: View) {
        rodando = false
        segundos = 0
    }

    private fun runTimer() {
        val timeView = findViewById<TextView>(R.id.time_view)

        handler.post(object : Runnable {
            override fun run() {
                val horas = segundos / 3600
                val minutos = (segundos % 3600) / 60
                val secs = segundos % 60
                val time = String.format("%02d:%02d:%02d", horas, minutos, secs)

                timeView.text = time

                if (rodando) {
                    segundos++
                }

                handler.postDelayed(this, 1000)
            }
        })
    }
}