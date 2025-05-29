class Jugador {
    private final String nombre;
    private boolean activo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.activo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void eliminar() {
        this.activo = false;
    }
}

