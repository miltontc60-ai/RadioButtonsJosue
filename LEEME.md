# Deber de Aplicaciones Móviles - Semana 2: RadioButtons
**Estudiante:** Josué  
**Universidad:** UNIANDES  
**Materia:** 07_A Aplicaciones Móviles  

---

## 📱 Contenido del Proyecto
Este proyecto contiene los 5 ejercicios solicitados implementados mediante una interfaz moderna basada en **Material Design 3**, con una pantalla principal (`MainActivity`) que actúa como menú de navegación hacia cada ejercicio:

1. **Encuesta de Satisfacción (`EncuestaActivity`):**
   - Utiliza `RadioGroup` y `RadioButton` con 4 niveles: *Excelente (⭐⭐⭐⭐⭐)*, *Bueno (⭐⭐⭐⭐)*, *Regular (⭐⭐⭐)* y *Malo (⭐)*.
   - Permite ingresar comentarios opcionales.
   - Muestra un resumen visual con tarjeta destacada, retroalimentación personalizada y botón de limpieza.

2. **Selector de Tema (`SelectorTemaActivity`):**
   - Permite seleccionar entre *Modo Claro ☀️*, *Modo Oscuro 🌙* y *Predeterminado del Sistema ⚙️*.
   - Aplica el tema dinámicamente mediante `AppCompatDelegate.setDefaultNightMode(...)`.
   - Guarda la preferencia en `SharedPreferences` para persistir la selección al reiniciar o cambiar de pantalla.

3. **Quiz Simple (`QuizActivity`):**
   - Cuestionario de opción múltiple con 4 preguntas sobre desarrollo en Android.
   - Cada pregunta está agrupada en un `RadioGroup` independiente.
   - Valida que todas las preguntas hayan sido respondidas, calcula el puntaje sobre 4 y el porcentaje (100%), detallando aciertos y errores con opción de reinicio.

4. **Calculadora de Propinas (`PropinasActivity`):**
   - Campo para ingresar el monto total consumido con validación numérica.
   - Selección del porcentaje mediante RadioButtons: *10% (Básico)*, *15% (Recomendado)* y *20% (Excelente)*.
   - Calcula el valor exacto de la propina y el importe final a pagar con formato monetario `$0.00`.

5. **Conversor de Unidades (`ConversorActivity`):**
   - Selector principal de magnitudes mediante RadioButtons: *Longitud (Metros)*, *Peso (Kilogramos)* y *Temperatura (°C / °F)*.
   - Al cambiar la opción principal, adapta dinámicamente las etiquetas y las opciones de conversión correspondientes (m a km/mi/ft; kg a lb/g/oz; °C a °F/K).
   - Realiza la conversión matemática y detalla la fórmula empleada.

---

## 🚀 Cómo abrir y ejecutar en Android Studio
1. Abre **Android Studio**.
2. Haz clic en **Open** (o `File -> Open`).
3. Selecciona la carpeta **Josue** (`c:\Users\milto\Downloads\deber josue y galo\Josue`).
4. Espera a que Gradle sincronice automáticamente y presiona el botón verde de **Run** (`Shift + F10`).
