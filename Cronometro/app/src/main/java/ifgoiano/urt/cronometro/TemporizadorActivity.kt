package ifgoiano.urt.cronometro

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
    private lateinit var btnPausarRetomar: Button // Botão que alterna entre pausar e retomar
    private lateinit var btnIniciarRestaurar: Button // Botão para iniciar ou restaurar o temporizador
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
        btnPausarRetomar = findViewById(R.id.btn_resume_temporizador)
        btnIniciarRestaurar = findViewById(R.id.btn_start_temporizador)

        // Configurar valores mínimos e máximos para os NumberPickers
        npHours.minValue = 0
        npHours.maxValue = 23

        npMinutes.minValue = 0
        npMinutes.maxValue = 59

        npSeconds.minValue = 0
        npSeconds.maxValue = 59

        // Inicialmente, esconder o botão de pausar/retomar
        btnPausarRetomar.visibility = Button.GONE

        // Botão para iniciar ou restaurar o temporizador
        btnIniciarRestaurar.setOnClickListener {
            if (!temporizadorRodando && !temporizadorPausado) {
                iniciarTemporizador()
            } else {
                restaurarTemporizador()
            }
        }

        // Botão para pausar ou retomar o temporizador
        btnPausarRetomar.setOnClickListener {
            if (temporizadorRodando) {
                pausarTemporizador()
            } else if (temporizadorPausado) {
                retomarTemporizador()
            }
        }
    }

    fun iniciarTemporizador() {
        val horas = npHours.value
        val minutos = npMinutes.value
        val segundos = npSeconds.value

        tempoRestante = (horas * 3600 + minutos * 60 + segundos) * 1000L

        if (tempoRestante > 0) {
            temporizadorRodando = true
            temporizadorPausado = false // Certifique-se de que o temporizador não está pausado
            linearLayout.visibility = LinearLayout.GONE
            timeView.visibility = TextView.VISIBLE
            btnPausarRetomar.visibility = Button.VISIBLE // Exibir o botão de pausar/retomar
            btnIniciarRestaurar.text = "Restaurar"
            btnPausarRetomar.text = "Pausar" // Definir o texto corretamente para "Pausar"

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
                    btnPausarRetomar.visibility = Button.GONE // Esconder o botão de pausar/retomar
                    btnIniciarRestaurar.text = "Iniciar" // Alterar texto do botão para "Iniciar"

                    exibirMensagemTemporizadorFinalizado() // Exibir mensagem ao finalizar o temporizador
                    Log.i(TAG, "Temporizador finalizado!")
                }
            }.start()
        } else {
            Log.i(TAG, "Tempo inválido definido.")
        }
    }

    fun restaurarTemporizador() {
        countDownTimer.cancel()
        temporizadorRodando = false
        temporizadorPausado = false
        tempoRestante = 0
        atualizarTimerUI(0)
        linearLayout.visibility = LinearLayout.VISIBLE
        timeView.visibility = TextView.GONE
        btnPausarRetomar.visibility = Button.GONE // Esconder o botão de pausar/retomar ao restaurar
        btnIniciarRestaurar.text = "Iniciar"
        Log.i(TAG, "Temporizador restaurado para 0.")
    }

    fun pausarTemporizador() {
        if (temporizadorRodando) {
            countDownTimer.cancel()
            temporizadorRodando = false
            temporizadorPausado = true
            btnPausarRetomar.text = "Retomar"
            Log.i(TAG, "Temporizador pausado.")
        }
    }

    fun retomarTemporizador() {
        if (temporizadorPausado) {
            temporizadorRodando = true
            temporizadorPausado = false
            btnPausarRetomar.text = "Pausar" // Alterar para "Pausar" após retomar

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
                    btnPausarRetomar.visibility = Button.GONE
                    btnIniciarRestaurar.text = "Iniciar"

                    exibirMensagemTemporizadorFinalizado() // Exibir mensagem ao finalizar o temporizador
                    Log.i(TAG, "Temporizador finalizado!")
                }
            }.start()
        }
    }

    private fun atualizarTimerUI(millisUntilFinished: Long) {
        val horas = (millisUntilFinished / 1000) / 3600
        val minutos = ((millisUntilFinished / 1000) % 3600) / 60
        val segundos = (millisUntilFinished / 1000) % 60
        timeView.text = String.format("%02d:%02d:%02d", horas, minutos, segundos)
    }

    private fun exibirMensagemTemporizadorFinalizado() {
        Toast.makeText(this, "Temporizador finalizado!", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        if (temporizadorRodando) {
            countDownTimer.cancel()
            temporizadorRodando = false
            Log.i(TAG, "Temporizador pausado ao sair da activity.")
        }
    }
}
