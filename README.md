# To The Top

---

## Integrantes del grupo
- Julian Guido Bollinger
- Santino Gennuso Musmanno
- Tomás Raiter

---

## Descripción corta del juego
**To The Top** es un videojuego 2D de vista frontal (front-view)
para un-cuatro jugador/es, enfocado en la escalada mediante físicas,
con modalidades tanto individual como multijugador cooperativo. 
Los jugadores deben ascender una montaña volcánica compuesta por 
diferentes secciones y obstáculos hasta alcanzar la cima antes de
que ocurra una erupción, utilizando un sistema de movimiento basado
en el uso de las extremidades y la interacción física (ragdoll) con
el entorno y con otros jugadores. El proyecto toma como principales
referencias e inspiración a **Getting Over It*, *Peak* y *Mount Your Friends**.

---

## Tecnologías principales y plataformas objetivo
- Java 21
- LibGDX
- Herramientas adicionales: Box2D(para físicas) y Tiled(Diseño de mapas)
- Plataforma de desarrollo objetivo:Escritorio(Windows y Linux)
- IDE utilizado: InteliJ IDEA Community Edition
---

## Enlace a la wiki del proyecto(Propuesta Detallada)

---

## Cómo compilar y ejecutar
### Windows y Linux
1. *Clonar el repositorio*
   bash
   git clone https://github.com/Jubiito1/ToTheTop
   cd ToTheTop
      
   
2. *Importar el proyecto en un IDE compatible con gradle (recomendado InteliJ)*
- Seleccionar como Gradle project
- Verificar estar usando java 21

3. *Ejecutar el juego*
- Desde el IDE electo ejecutar la clase Lwjgl3Launcher.java ubicada en el módulo lwjgl3
- Desde la consola (Linux y Windows con gradlew)


   bash
   #Linux 
   ./gradlew lwjgl3:run
  
  # Windows (CMD o PowerShell)
  gradlew.bat lwjgl3:run