

class PartidoResultado {
    private final String resultadoTexto;
    private final Jugador ganador;

    public PartidoResultado(String resultadoTexto, Jugador ganador) {
        this.resultadoTexto = resultadoTexto;
        this.ganador = ganador;
    }

    public String getResultadoTexto() {
        return resultadoTexto;
    }

    public Jugador getGanador() {
        return ganador;
    }
}

