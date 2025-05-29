import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

class Partido implements Callable<PartidoResultado> {
    private final Jugador jugador1;
    private final Jugador jugador2;

    public Partido(Jugador j1, Jugador j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
    }

    @Override
    public PartidoResultado call() throws Exception {
        List<Jugador> setsGanados = new ArrayList<>();
        Random random = new Random();

        StringBuilder resultado = new StringBuilder();
        resultado.append(jugador1.getNombre()).append(" vs ").append(jugador2.getNombre()).append("\n");

        while (Collections.frequency(setsGanados, jugador1) < 2 && Collections.frequency(setsGanados, jugador2) < 2) {
            Thread.sleep(1500 + random.nextInt(501)); // 1.5 a 2 segundos
            Jugador ganadorSet = random.nextBoolean() ? jugador1 : jugador2;
            setsGanados.add(ganadorSet);
            resultado.append("Set ").append(setsGanados.size()).append(": ").append(ganadorSet.getNombre()).append("\n");
        }

        Jugador ganador = Collections.frequency(setsGanados, jugador1) > Collections.frequency(setsGanados, jugador2) ? jugador1 : jugador2;
        Jugador perdedor = (ganador == jugador1) ? jugador2 : jugador1;
        perdedor.eliminar();

        resultado.append("Ganador del partido: ").append(ganador.getNombre()).append("\n\n");
        return new PartidoResultado(resultado.toString(), ganador);
    }
}