package proyectoahorcado;

import java.util.HashSet;

public class logica {
    
    //Variable donde se guardará la palabra a adivinar
    private String palabra;
    //Utilizamos Stringbuilder porque nos permitirá modificar la palabra más fácilmente
    private StringBuilder progresoPalabra;
    //Utilizaremos HashSet, que es como un array pero que sólo permite valores únicos para controlar las letras utilizadas
    private HashSet<Character> letrasUsadas;
    private int intentos; //Variable para los intentos restantes.

    //Creamos un constructor que iniciará el juego con una palabra
    public logica(String palabra) {
        this.palabra = palabra.toLowerCase(); //Pasamos la palabra ingresada a minúscula para evitar conflictos
        //La siguiente línea sustituirá la palabra por guiones bajos
        this.progresoPalabra = new StringBuilder("_".repeat(palabra.length()));
        this.letrasUsadas = new HashSet<>(); //Iniciamos el "depósito" de letras usadas.
        this.intentos = 6; //número de intentos
    }

    //Función para probar una letra
    public boolean probarLetra(char letra) {
        if(letrasUsadas.contains(letra)){ //contains es de la clase HashSet que comprobará si la letra ya está utilizada
            return false; //Si ya se había intentado una letra no se procesará
        }
        //Añadimos la letra a las letras usadas
        letrasUsadas.add(letra);
        boolean acierto = false;

        //Se recorre la palabra para ver si está en la palabra oculta
        for (int i = 0; i < palabra.length(); i++) {
            if(palabra.charAt(i) == letra) {
                progresoPalabra.setCharAt(i, letra); //Si la letra está en nuestra palabra sustituimos el guión bajo por la letra
                acierto = true;
            }
        }

        //Si no se ha acertado se reduce el número de intentos
        if(!acierto) {
            intentos--;
        }

        return acierto; //Devolvemos si la letra es o no correcta.
    }

    //Función para devolver el progreso de la palabra oculta
    public String getProgresoPalabra() {
        return progresoPalabra.toString();
    }

    //Función para devolver el número de intentos restantes
    public int getIntentos() {
        return intentos;
    }

    //Funciókn para commprobar si ha terminado la partida:
    public boolean partidaTerminada() {
        return intentos == 0 || progresoPalabra.toString().equals(palabra);
        //La partida se termina si nos quedamos sin intentos o si se adivina la palabra
    }

    //Verificamos si hay victoria
    public boolean victoria() {
        return progresoPalabra.toString().equals(palabra);
        //Si el progreso de la palabra equivale a la palabra oculta hay victoria
    }

    //Función que devuelve las letras que ya se han ingresado:
    public HashSet<Character> getLetrasUsadas() {
        return letrasUsadas;
    }

    //Hacemos una función para reiniciar la partida, declaramos una nueva variable para palabra nueva:
    public void reiniciar(String palabraNueva) {
        this.palabra = palabraNueva.toLowerCase();
        this.progresoPalabra = new StringBuilder("_".repeat(palabraNueva.length()));
        this.letrasUsadas.clear(); //Limpiamos el "array" creado con HashSet.
        this.intentos = 6; //Reiniciamos los intentos
    }
}