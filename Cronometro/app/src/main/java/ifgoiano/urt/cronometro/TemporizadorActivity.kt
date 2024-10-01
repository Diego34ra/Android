package ifgoiano.urt.cronometro

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TemporizadorActivity : AppCompatActivity() {
    private var tempoRestante: Long = 0
    private var temporizadorRodando: Boolean = false
    private var temporizadorPausado: Boolean = false
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var npHours: NumberPicker
    private lateinit var npMinutes: NumberPicker
    private lateinit var npSeconds: NumberPicker
    private lateinit var timeView: TextView
    private lateinit var linearLayout: LinearLayout // LinearLayout que contém os NumberPickers
    private val TAG: String = "si-urutai"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporizador)

        // Inicializar componentes
        npHours = findViewById(R.id.np_hours)
        npMinutes = findViewById(R.id.np_minutes)
        npSeconds = findViewById(R.id.np_seconds)
        timeView = findViewById(R.id.time_view_temporizador)
        linearLayout = findViewById(R.id.linearLayout)

        // Configurar valores mínimos e máximos para os NumberPickers
        npHours.minValue = 0
        npHours.maxValue = 23

        npMinutes.minValue = 0
        npMinutes.maxValue = 59

        npSeconds.minValue = 0
        npSeconds.maxValue = 59

        // Botão para iniciar o temporizador
        findViewById<Button>(R.id.btn_start_temporizador).setOnClickListener {
            if (!temporizadorRodando) {
                iniciarTemporizador()
            }
        }

        // Botão para voltar ao cronômetro
//        findViewById<Button>(R.id.btn_open_cronometro).setOnClickListener {
//            val intent = Intent(this, CronometroActivity::class.java)
//            startActivity(intent)
//        }
    }

    fun iniciarTemporizador() {
        val horas = npHours.value
        val minutos = npMinutes.value
        val segundos = npSeconds.value

        tempoRestante = (horas * 3600 + minutos * 60 + segundos) * 1000L

        if (tempoRestante > 0) {
            temporizadorRodando = true
            linearLayout.visibility = LinearLayout.GONE
            timeView.visibility = TextView.VISIBLE

            countDownTimer = object : CountDownTimer(tempoRestante, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tempoRestante = millisUntilFinished
                    atualizarTimerUI(millisUntilFinished)
                }

                override fun onFinish() {
                    temporizadorRodando = false
                    timeView.text = "00:00:00"
                    linearLayout.visibility = LinearLayout.VISIBLE
                    timeView.visibility = TextView.GONE
                    Log.i(TAG, "Temporizador finalizado!")
                }
            }.start()
        } else {
            Log.i(TAG, "Tempo inválido definido.")
        }
    }

    fun onClickPausarTemporizador(view: View) {
        if (temporizadorRodando) {
            countDownTimer.cancel()
            temporizadorRodando = false
            temporizadorPausado = true
            Log.i(TAG, "Temporizador pausado.")
        }
    }

    fun onClickRetomarTemporizador(view: View) {
        if (temporizadorPausado) {
            iniciarTemporizadorComTempoRestante()
            temporizadorPausado = false
        }
    }

    fun iniciarTemporizadorComTempoRestante() {
        countDownTimer = object : CountDownTimer(tempoRestante, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tempoRestante = millisUntilFinished
                atualizarTimerUI(millisUntilFinished)
            }

            override fun onFinish() {
                temporizadorRodando = false
                timeView.text = "00:00:00"
                linearLayout.visibility = LinearLayout.VISIBLE // Mostrar os NumberPickers novamente
                timeView.visibility = TextView.GONE // Esconder o tempo restante
                Log.i(TAG, "Temporizador finalizado!")
            }
        }.start()
    }

    private fun atualizarTimerUI(millisUntilFinished: Long) {
        val horas = (millisUntilFinished / 1000) / 3600
        val minutos = ((millisUntilFinished / 1000) % 3600) / 60
        val segundos = (millisUntilFinished / 1000) % 60
        timeView.text = String.format("%02d:%02d:%02d", horas, minutos, segundos)
    }

    override fun onStop() {
        super.onStop()
        if (temporizadorRodando) {
            countDownTimer.cancel()
            temporizadorRodando = false
            Log.i(TAG, "Temporizador pausado.")
        }
    }
}