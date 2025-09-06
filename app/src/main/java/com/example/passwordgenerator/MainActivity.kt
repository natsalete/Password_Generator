package com.example.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    // Referências dos componentes da interface
    private lateinit var tvGeneratedPassword: TextView
    private lateinit var tvLength: TextView
    private lateinit var tvStrengthLabel: TextView
    private lateinit var btnGenerate: Button
    private lateinit var btnCopy: Button
    private lateinit var seekBarLength: SeekBar
    private lateinit var progressBarStrength: ProgressBar
    private lateinit var cbUppercase: CheckBox
    private lateinit var cbLowercase: CheckBox
    private lateinit var cbNumbers: CheckBox
    private lateinit var cbSymbols: CheckBox
    private lateinit var cbExcludeSimilar: CheckBox

    // Conjuntos de caracteres
    private val uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"
    private val numbers = "0123456789"
    private val symbols = "!@#$%^&*()_+-=[]{}|;:,.<>?"

    // Caracteres similares para exclusão
    private val similarChars = "il1Lo0O"

    // Senha atual gerada
    private var currentPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        tvGeneratedPassword = findViewById(R.id.tvGeneratedPassword)
        tvLength = findViewById(R.id.tvLength)
        tvStrengthLabel = findViewById(R.id.tvStrengthLabel)
        btnGenerate = findViewById(R.id.btnGenerate)
        btnCopy = findViewById(R.id.btnCopy)
        seekBarLength = findViewById(R.id.seekBarLength)
        progressBarStrength = findViewById(R.id.progressBarStrength)
        cbUppercase = findViewById(R.id.cbUppercase)
        cbLowercase = findViewById(R.id.cbLowercase)
        cbNumbers = findViewById(R.id.cbNumbers)
        cbSymbols = findViewById(R.id.cbSymbols)
        cbExcludeSimilar = findViewById(R.id.cbExcludeSimilar)
    }

    private fun setupListeners() {
        // Listener para o SeekBar do comprimento
        seekBarLength.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvLength.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Listener para o botão gerar
        btnGenerate.setOnClickListener {
            generatePassword()
        }

        // Listener para o botão copiar
        btnCopy.setOnClickListener {
            copyPasswordToClipboard()
        }

        // Listeners para os checkboxes (para validação)
        val checkBoxListener = { _: Any ->
            validateSelection()
        }

        cbUppercase.setOnCheckedChangeListener { _, _ -> validateSelection() }
        cbLowercase.setOnCheckedChangeListener { _, _ -> validateSelection() }
        cbNumbers.setOnCheckedChangeListener { _, _ -> validateSelection() }
        cbSymbols.setOnCheckedChangeListener { _, _ -> validateSelection() }
    }

    private fun validateSelection(): Boolean {
        val hasSelection = cbUppercase.isChecked || cbLowercase.isChecked ||
                cbNumbers.isChecked || cbSymbols.isChecked

        btnGenerate.isEnabled = hasSelection

        if (!hasSelection) {
            showToast("Selecione pelo menos uma opção de caracteres")
        }

        return hasSelection
    }

    private fun generatePassword() {
        if (!validateSelection()) return

        val length = seekBarLength.progress
        val characterPool = buildCharacterPool()

        if (characterPool.isEmpty()) {
            showToast("Erro ao criar conjunto de caracteres")
            return
        }

        // Gerar senha aleatória
        val password = StringBuilder()

        // Garantir que pelo menos um caractere de cada tipo selecionado esteja presente
        val requiredChars = mutableListOf<Char>()

        if (cbUppercase.isChecked) {
            val chars = getFilteredCharacters(uppercaseLetters)
            if (chars.isNotEmpty()) requiredChars.add(chars.random())
        }

        if (cbLowercase.isChecked) {
            val chars = getFilteredCharacters(lowercaseLetters)
            if (chars.isNotEmpty()) requiredChars.add(chars.random())
        }

        if (cbNumbers.isChecked) {
            val chars = getFilteredCharacters(numbers)
            if (chars.isNotEmpty()) requiredChars.add(chars.random())
        }

        if (cbSymbols.isChecked) {
            val chars = getFilteredCharacters(symbols)
            if (chars.isNotEmpty()) requiredChars.add(chars.random())
        }

        // Adicionar caracteres obrigatórios
        requiredChars.forEach { password.append(it) }

        // Preencher o restante da senha
        repeat(length - requiredChars.size) {
            password.append(characterPool.random())
        }

        // Embaralhar a senha para randomizar a posição dos caracteres obrigatórios
        currentPassword = password.toString().toList().shuffled().joinToString("")

        // Exibir senha
        tvGeneratedPassword.text = currentPassword

        // Calcular e exibir força da senha
        updatePasswordStrength(currentPassword)

        showToast("Senha gerada com sucesso!")
    }

    private fun buildCharacterPool(): String {
        val pool = StringBuilder()

        if (cbUppercase.isChecked) {
            pool.append(getFilteredCharacters(uppercaseLetters))
        }

        if (cbLowercase.isChecked) {
            pool.append(getFilteredCharacters(lowercaseLetters))
        }

        if (cbNumbers.isChecked) {
            pool.append(getFilteredCharacters(numbers))
        }

        if (cbSymbols.isChecked) {
            pool.append(getFilteredCharacters(symbols))
        }

        return pool.toString()
    }

    private fun getFilteredCharacters(originalSet: String): String {
        return if (cbExcludeSimilar.isChecked) {
            originalSet.filter { !similarChars.contains(it) }
        } else {
            originalSet
        }
    }

    private fun updatePasswordStrength(password: String) {
        val score = calculatePasswordStrength(password)
        val percentage = (score * 100 / 4).coerceIn(0, 100)

        progressBarStrength.progress = percentage

        val (label, color) = when {
            percentage < 25 -> "Muito Fraca" to Color.parseColor("#E74C3C")
            percentage < 50 -> "Fraca" to Color.parseColor("#F39C12")
            percentage < 75 -> "Média" to Color.parseColor("#F1C40F")
            else -> "Forte" to Color.parseColor("#27AE60")
        }

        tvStrengthLabel.text = label
        tvStrengthLabel.setTextColor(color)
        progressBarStrength.progressTintList = android.content.res.ColorStateList.valueOf(color)
    }

    private fun calculatePasswordStrength(password: String): Int {
        var score = 0

        // Verifica comprimento
        if (password.length >= 8) score++

        // Verifica diferentes tipos de caracteres
        if (password.any { it.isUpperCase() }) score++
        if (password.any { it.isLowerCase() }) score++
        if (password.any { it.isDigit() }) score++
        if (password.any { symbols.contains(it) }) score++

        return score.coerceIn(0, 4)
    }

    private fun copyPasswordToClipboard() {
        if (currentPassword.isEmpty()) {
            showToast("Nenhuma senha para copiar")
            return
        }

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Senha Gerada", currentPassword)
        clipboard.setPrimaryClip(clip)

        showToast("Senha copiada para a área de transferência!")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}