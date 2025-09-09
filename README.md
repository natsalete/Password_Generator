# Password Generator - Android

A modern and intuitive Android app for generating secure and customizable passwords, developed in Kotlin with Material Design.

## 📱 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/155b915d-4d87-43e8-9c22-3e571a047e0d" alt="WhatsApp Image 2025-09-06 at 00 08 02" width="400"/>
</p>

## ✨ Features

### 🎯 Password Generation
- **Customizable length**: 4 to 50 characters
- **Secure algorithm**: Uses `kotlin.random.Random` for maximum randomness
- **Diversity guarantee**: Ensures at least one character of each selected type

### ⚙️ Configuration Options
- ✅ **Uppercase Letters** (A-Z)
- ✅ **Lowercase Letters** (a-z)
- ✅ **Numbers** (0-9)
- ✅ **Symbols** (!@#$%^&*()_+-=[]{}|;:,.<>?)
- 🚫 **Character exclusion Similar** (i, l, 1, L, o, 0, O)

### 📊 Security Analysis
- **Visual password strength indicator**
- **4-level classification**:
- 🔴 Very Weak (<25%)
- 🟡 Weak (25-49%)
- 🟠 Medium (50-74%)
- 🟢 Strong (≥75%)

### 🛠️ Additional Features
- **Copy to clipboard** with one tap
- **Responsive interface** with ScrollView
- **Real-time input validation**
- **Visual feedback** through toasts

## 🏗️ Architecture and Technologies

### Technologies Used
- **Language**: Kotlin
- **Target SDK**: Android API 34
- **SDK Minimum**: Android API 21 (Android 5.0+)
- **Design**: Material Design 3
- **Architecture**: Activity-based with implicit ViewBinding

### Main Components
```
src/main/java/com/example/passwordgenerator/
├── MainActivity.kt # Main application logic
└── res/
└── layout/
└── activity_main.xml # User interface
```

### Libraries and Dependencies
```kotlin
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
```

## 🚀 Installation and Configuration

### Prerequisites
- **Android Studio** Arctic Fox or higher
- **JDK** 8 or higher
- **Android SDK** with API 21+

### Installation Steps

1. **Clone the repository**
```bash
git clone https://github.com/natsalete/Password_Generator.git
cd Password_Generator
```

2. **Open Android Studio**
- Launch Android Studio
- Select "Open an Existing Project"
- Navigate to the project folder

3. **Synchronize Dependencies**
- Wait for Android Studio to load the project
- Click "Sync Now" when Requested

4. **Run the app**
- Connect an Android device or launch an emulator
- Click the "Run" button (▶️) or press Shift + F10

## 📖 How to Use

### Generating a Password

1. **Set the length**
- Use the slider to set between 4 and 50 characters
- The current value is displayed next to the slider

2. **Select the character types**
- Select the desired checkboxes
- At least one option must be selected

3. **Set advanced options**
- Enable "Delete Similar Characters" to avoid confusion
- This option removes: `i, l, 1, L, o, 0, O`

4. **Generate the password**
- Tap the "Generate Password" button
- The password will appear in the highlighted area

5. **Copy the password**
- Tap the "Copy" button
- The password will be copied to the clipboard.

### Interpreting Password Strength

Password strength is calculated based on:
- **Length** (≥ 8 characters)
- **Character diversity** (uppercase, lowercase, numbers, symbols)
- **Maximum score**: 4 points

## 🔧 Customization and Extension

### Adding New Character Sets

To add new character types, modify the `MainActivity.kt` file:

```kotlin
// Add the new character set
private val customChars = "ÀÁÂÃÇÉÊÍÓÔÕÚàáâãçéêíóôõú"

// Add the checkbox to the interface
// Include the logic in buildCharacterPool()
```

### Modifying the Strength Algorithm

To customize the strength is calculated, edit the `calculatePasswordStrength()` function:

```kotlin
private fun calculatePasswordStrength(password: String): Int {
var score = 0

// Add your own rules here
if (password.length >= 12) score++
if (password.contains(Regex("[ÀÁÂÃÇÉÊÍÓÔÕÚàáâãçéêíóôõú]"))) score++

return score.coerceIn(0, 4)
}
```

## 🎨 Design System

### Colors Used
```kotlin
// Main Colors
Primary Blue: #3498DB
Success Green: #27AE60
Warning Orange: #F39C12
Danger Red: #E74C3C

// Text Colors
Dark Text: #2C3E50
Medium Text: #34495E
Light Text: #7F8C8D

// Background Colors
Background: #F5F5F5
Card Background: #FFFFFF
Input Background: #F8F9FA
```

### Typography
- **Title**: 28sp, Bold
- **Subtitles**: 18sp, Bold
- **Normal Text**: 16sp, Regular
- **Small Text**: 14sp, Regular
- **Passwords**: 18sp, Bold, Monospace

## 🧪 Tests

### Recommended Manual Tests

1. **Basic Functionality**
- [ ] Generate password with different settings
- [ ] Copy password to clipboard
- [ ] Change password length

2. **Input Validation**
- [ ] Attempt to generate password without selecting any options
- [ ] Check if at least one character of each type is included

3. **Responsive Interface**
- [ ] Test on different screen sizes
- [ ] Check scrolling on small devices

4. **Strength Calculation**
- [ ] Check indicator with weak and strong passwords
- [ ] Confirm correct colors for each level

## 📝 License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the project
2. Create a branch for your feature (git checkout -b feature/AmazingFeature)
3. Commit your changes (git commit -m 'Add: Amazing Feature')
4. Push to the branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

### Commit Patterns
- Add: for new features
- Fix: for bug fixes
- Update: for updates to existing features
- Docs: for documentation changes

## 📞 Support

If you encounter any problems or have suggestions:

- 🐛 Bugs: Open an issue (https://github.com/natsalete/Password_Generator/issues)
- 💡 Suggestions: Open an issue [discussion](https://github.com/natsalete/Password_Generator/discussions)
- 📧 **Contact**: natsalete20@gmail.com

## 🎯 Roadmap

### Version 2.0 (Planned)
- [ ] **Password History** (secure local storage)
- [ ] **Themes** (light/dark)
- [ ] **Settings Backup/Restore**
- [ ] **Home Screen Widget**
- [ ] **Passphrase Generation** (diceware)

### Version 1.1 (In Development)
- [ ] **Accessibility Improvements**
- [ ] **Language Support** (EN, ES, PT)
- [ ] **Transition Animations**
- [ ] **Automated Unit Tests**

## 🏆 Credits

Developed with ❤️ by [Natalia Salete](https://github.com/natsalete)

### Thanks
- Android Community for the documentation
- Material Design for the visual inspiration
- Stack Overflow for troubleshooting help

---

**⭐ If this project was helpful to you, please consider giving the repository a star!**

<details>
<summary>🇧🇷 Versão em Português</summary>

# Gerador de Senhas - Android

Um aplicativo Android moderno e intuitivo para gerar senhas seguras e personalizáveis, desenvolvido em Kotlin com Material Design.

## 📱 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/155b915d-4d87-43e8-9c22-3e571a047e0d" alt="WhatsApp Image 2025-09-06 at 00 08 02" width="400"/>
</p>

## ✨ Funcionalidades

### 🎯 Geração de Senhas
- **Comprimento personalizável**: 4 a 50 caracteres
- **Algoritmo seguro**: Utiliza `kotlin.random.Random` para máxima aleatoriedade
- **Garantia de diversidade**: Assegura pelo menos um caractere de cada tipo selecionado

### ⚙️ Opções de Configuração
- ✅ **Letras Maiúsculas** (A-Z)
- ✅ **Letras Minúsculas** (a-z)
- ✅ **Números** (0-9)
- ✅ **Símbolos** (!@#$%^&*()_+-=[]{}|;:,.<>?)
- 🚫 **Exclusão de caracteres similares** (i, l, 1, L, o, 0, O)

### 📊 Análise de Segurança
- **Indicador visual de força** da senha
- **Classificação em 4 níveis**:
  - 🔴 Muito Fraca (< 25%)
  - 🟡 Fraca (25-49%)
  - 🟠 Média (50-74%)
  - 🟢 Forte (≥ 75%)

### 🛠️ Funcionalidades Adicionais
- **Copiar para área de transferência** com um toque
- **Interface responsiva** com ScrollView
- **Validação de entrada** em tempo real
- **Feedback visual** através de toasts

## 🏗️ Arquitetura e Tecnologias

### Tecnologias Utilizadas
- **Linguagem**: Kotlin
- **SDK Target**: Android API 34
- **SDK Mínimo**: Android API 21 (Android 5.0+)
- **Design**: Material Design 3
- **Arquitetura**: Activity-based com ViewBinding implícito

### Componentes Principais
```
src/main/java/com/example/passwordgenerator/
├── MainActivity.kt          # Lógica principal da aplicação
└── res/
    └── layout/
        └── activity_main.xml    # Interface do usuário
```

### Bibliotecas e Dependências
```kotlin
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
```

## 🚀 Instalação e Configuração

### Pré-requisitos
- **Android Studio** Arctic Fox ou superior
- **JDK** 8 ou superior
- **Android SDK** com API 21+

### Passos para Instalação

1. **Clone o repositório**
   ```bash
   git clone https://github.com/natsalete/Password_Generator.git
   cd Password_Generator
   ```

2. **Abra no Android Studio**
   - Inicie o Android Studio
   - Selecione "Open an Existing Project"
   - Navegue até a pasta do projeto

3. **Sincronize as dependências**
   - Aguarde o Android Studio carregar o projeto
   - Clique em "Sync Now" quando solicitado

4. **Execute o aplicativo**
   - Conecte um dispositivo Android ou inicie um emulador
   - Clique no botão "Run" (▶️) ou pressione `Shift + F10`

## 📖 Como Usar

### Gerando uma Senha

1. **Configure o comprimento**
   - Use o slider para definir entre 4 e 50 caracteres
   - O valor atual é exibido ao lado do slider

2. **Selecione os tipos de caracteres**
   - Marque as caixas de seleção desejadas
   - Pelo menos uma opção deve estar selecionada

3. **Configure opções avançadas**
   - Ative "Excluir Caracteres Similares" para evitar confusão
   - Esta opção remove: `i, l, 1, L, o, 0, O`

4. **Gere a senha**
   - Toque no botão "Gerar Senha"
   - A senha aparecerá na área destacada

5. **Copie a senha**
   - Toque no botão "Copiar"
   - A senha será copiada para a área de transferência

### Interpretando a Força da Senha

A força da senha é calculada baseada em:
- **Comprimento** (≥ 8 caracteres)
- **Diversidade de caracteres** (maiúsculas, minúsculas, números, símbolos)
- **Pontuação máxima**: 4 pontos

## 🔧 Personalização e Extensão

### Adicionando Novos Conjuntos de Caracteres

Para adicionar novos tipos de caracteres, modifique o arquivo `MainActivity.kt`:

```kotlin
// Adicione o novo conjunto
private val customChars = "ÀÁÂÃÇÉÊÍÓÔÕÚàáâãçéêíóôõú"

// Adicione o checkbox na interface
// Inclua a lógica no buildCharacterPool()
```

### Modificando o Algoritmo de Força

Para personalizar como a força é calculada, edite a função `calculatePasswordStrength()`:

```kotlin
private fun calculatePasswordStrength(password: String): Int {
    var score = 0
    
    // Adicione suas próprias regras aqui
    if (password.length >= 12) score++
    if (password.contains(Regex("[ÀÁÂÃÇÉÊÍÓÔÕÚàáâãçéêíóôõú]"))) score++
    
    return score.coerceIn(0, 4)
}
```

## 🎨 Design System

### Cores Utilizadas
```kotlin
// Cores principais
Primary Blue: #3498DB
Success Green: #27AE60
Warning Orange: #F39C12
Danger Red: #E74C3C

// Cores de texto
Dark Text: #2C3E50
Medium Text: #34495E
Light Text: #7F8C8D

// Cores de fundo
Background: #F5F5F5
Card Background: #FFFFFF
Input Background: #F8F9FA
```

### Tipografia
- **Título**: 28sp, Bold
- **Subtítulos**: 18sp, Bold
- **Texto normal**: 16sp, Regular
- **Texto pequeno**: 14sp, Regular
- **Senhas**: 18sp, Bold, Monospace

## 🧪 Testes

### Testes Manuais Recomendados

1. **Funcionalidade básica**
   - [ ] Gerar senha com diferentes configurações
   - [ ] Copiar senha para área de transferência
   - [ ] Alterar comprimento da senha

2. **Validação de entrada**
   - [ ] Tentar gerar senha sem selecionar nenhuma opção
   - [ ] Verificar se pelo menos um caractere de cada tipo é incluído

3. **Interface responsiva**
   - [ ] Testar em diferentes tamanhos de tela
   - [ ] Verificar scroll em dispositivos pequenos

4. **Cálculo de força**
   - [ ] Verificar indicador com senhas fracas e fortes
   - [ ] Confirmar cores corretas para cada nível

## 📝 Licença

Este projeto está licenciado sob a [MIT License](LICENSE) - veja o arquivo LICENSE para detalhes.

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. **Fork** o projeto
2. **Crie** uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add: Amazing Feature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. **Abra** um Pull Request

### Padrões de Commit
- `Add:` para novas funcionalidades
- `Fix:` para correções de bugs
- `Update:` para atualizações de funcionalidades existentes
- `Docs:` para mudanças na documentação

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões:

- 🐛 **Bugs**: Abra uma [issue](https://github.com/natsalete/Password_Generator/issues)
- 💡 **Sugestões**: Abra uma [discussion](https://github.com/natsalete/Password_Generator/discussions)
- 📧 **Contato**: natsalete20@gmail.com

## 🎯 Roadmap

### Versão 2.0 (Planejado)
- [ ] **Histórico de senhas** (armazenamento local seguro)
- [ ] **Temas** (claro/escuro)
- [ ] **Backup/Restore** de configurações
- [ ] **Widget** para tela inicial
- [ ] **Geração de frases-passe** (diceware)

### Versão 1.1 (Em desenvolvimento)
- [ ] **Melhorias de acessibilidade**
- [ ] **Suporte a idiomas** (EN, ES, PT)
- [ ] **Animações** de transição
- [ ] **Testes unitários** automatizados

## 🏆 Créditos

Desenvolvido com ❤️ por [Natalia Salete](https://github.com/natsalete)

### Agradecimentos
- Comunidade Android pela documentação
- Material Design pela inspiração visual
- Stack Overflow pela ajuda na resolução de problemas

---

**⭐ Se este projeto foi útil para você, consider dar uma estrela no repositório!**

</details>
