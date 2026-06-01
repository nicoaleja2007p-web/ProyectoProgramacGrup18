package modelo;

import java.util.ArrayList;

public class HistorialViaje {

    private ArrayList<Viaje> viajes;
    private double gastoAcumulado;

    public HistorialViaje() {
        this.viajes = new ArrayList<Viaje>();
        this.gastoAcumulado = 0.0;
    }

    public void agregarViaje(Viaje viaje) {
        viajes.add(viaje);
        gastoAcumulado = gastoAcumulado + viaje.getCostoTotal();
    }

    public ArrayList<Viaje> getViajes() {
        return viajes;
    }

    public double getGastoAcumulado() {
        // Recalcula por si los costos cambiaron
        gastoAcumulado = 0.0;
        for (int i = 0; i < viajes.size(); i++) {
            gastoAcumulado = gastoAcumulado + viajes.get(i).getCostoTotal();
        }
        return gastoAcumulado;
    }

    public int getTotalViajes() {
        return viajes.size();
    }

    public String toString() {
        String resultado = "=== HISTORIAL DE VIAJES ===\n";
        if (viajes.size() == 0) {
            resultado = resultado + "No hay viajes registrados.\n";
        } else {
            for (int i = 0; i < viajes.size(); i++) {
                Viaje v = viajes.get(i);
                resultado = resultado + (i + 1) + ". " + v.getDestino()
                        + " | " + v.getFechaInicio() + " - " + v.getFechaFin()
                        + " | Gasto: $" + v.getCostoTotal() + "\n";
            }
            resultado = resultado + "Gasto acumulado total: $" + getGastoAcumulado() + "\n";
        }
        return resultado;
    }
}
