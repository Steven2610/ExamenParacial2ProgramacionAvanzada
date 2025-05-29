import java.util.*;
import java.util.concurrent.*;

public class TorneoTenis {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }

        // Reordenar para que jueguen 1 vs 16, 2 vs 15, ..., 8 vs 9
        List<Jugador> reordenados = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            reordenados.add(jugadores.get(i));           // Jugador 1, 2, 3, ..., 8
            reordenados.add(jugadores.get(15 - i));       // Jugador 16, 15, 14, ..., 9
        }
        jugadores = reordenados;

        ExecutorService executor = Executors.newFixedThreadPool(8);
        String[] rondas = {"OCTAVOS DE FINAL", "CUARTOS DE FINAL", "SEMIFINAL", "FINAL"};

        for (String ronda : rondas) {
            System.out.println("===== " + ronda + " =====");
            List<Future<PartidoResultado>> resultados = new ArrayList<>();

            for (int i = 0; i < jugadores.size(); i += 2) {
                Partido partido = new Partido(jugadores.get(i), jugadores.get(i + 1));
                resultados.add(executor.submit(partido));
            }

            List<Jugador> ganadores = new ArrayList<>();
            List<String> resultadosTexto = new ArrayList<>();
            for (Future<PartidoResultado> future : resultados) {
                PartidoResultado partidoResultado = future.get();
                resultadosTexto.add(partidoResultado.getResultadoTexto());
                ganadores.add(partidoResultado.getGanador());
            }

            jugadores = ganadores;
            resultadosTexto.forEach(System.out::print);
        }

        executor.shutdown();
        System.out.println("\ud83c\udfc6 ¡Campeón del torneo: " + jugadores.get(0).getNombre() + "!");
    }
}
