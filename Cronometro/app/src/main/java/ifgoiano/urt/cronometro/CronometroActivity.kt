package ifgoiano.urt.cronometro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CronometroActivity : AppCompatActivity() {

    private var segundos: Int = 0
    private var rodando: Boolean = false
    private var estavaEmExecucao: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    private val TAG: String = "si-urutai"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cronometro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btn_open_temporizador).setOnClickListener {
            val intent = Intent(this, TemporizadorActivity::class.java)
            startActivity(intent)
        }

        if (savedInstanceState != null) {
            segundos = savedInstanceState.getInt("segundos")
            rodando = savedInstanceState.getBoolean("rodando")
            estavaEmExecucao = savedInstanceState.getBoolean("estavaEmExecucao")
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
        handler.post(object : Runnable {
            override fun run() {
                updateTimerUI();
                if (rodando) {
                    segundos++
                    updateTimerUI();
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    fun updateTimerUI() {
        val timeView = findViewById<TextView>(R.id.time_view)

        val horas = segundos / 3600
        val minutos = (segundos % 3600) / 60
        val secs = segundos % 60
        val time = String.format("%02d:%02d:%02d", horas, minutos, secs)

        timeView.text = time

    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, getClassName() + ".onStart() chamado.")
        if (estavaEmExecucao)
            rodando = true
    }

    override fun onResume() {
        super. onResume()
        Log.i(TAG, getClassName() + ".onResume() chamado.")
        if (estavaEmExecucao)
            rodando = true
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, getClassName() + ".onStop() chamado.")
        estavaEmExecucao = rodando
        rodando = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("segundos", segundos)
        outState.putBoolean("rodando", rodando)
        outState.putBoolean("estavaEmExecucao", estavaEmExecucao)
        Log.i(TAG, getClassName() + ".onSaveInstanceState() chamado.")
    }

    private fun getClassName(): String {
        val s = javaClass.name
        return s.substring(s.lastIndexOf("."))
    }
}