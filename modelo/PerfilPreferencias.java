package modelo;

import java.util.ArrayList;

public class PerfilPreferencias {

    private ArrayList<String> estilosViaje;   // multi-estilo
    private ArrayList<String> categorias;
    private double gastoDiarioMax;
    private ArrayList<Double> puntajesAfinidad;

    public PerfilPreferencias(String estiloViaje, double gastoDiarioMax) {
        this.estilosViaje = new ArrayList<String>();
        this.estilosViaje.add(estiloViaje);
        this.gastoDiarioMax = gastoDiarioMax;
        this.categorias = new ArrayList<String>();
        this.puntajesAfinidad = new ArrayList<Double>();
    }

    public void agregarCategoria(String categoria, double puntaje) {
        categorias.add(categoria);
        puntajesAfinidad.add(puntaje);
    }

    public void agregarEstilo(String estilo) {
        if (!estilosViaje.contains(estilo)) {
            estilosViaje.add(estilo);
        }
    }

    public double calcularAfinidad(String categoria) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).equals(categoria)) {
                return puntajesAfinidad.get(i);
            }
        }
        return 0.0;
    }

    public void actualizarPerfil(ArrayList<String> nuevosEstilos, double nuevoGasto) {
        this.estilosViaje = nuevosEstilos;
        this.gastoDiarioMax = nuevoGasto;
        this.categorias = new ArrayList<String>();
        this.puntajesAfinidad = new ArrayList<Double>();
    }

    // Compatibilidad con código existente que usa un solo estilo
    public void actualizarPerfil(String nuevoEstilo, double nuevoGasto) {
        this.estilosViaje = new ArrayList<String>();
        this.estilosViaje.add(nuevoEstilo);
        this.gastoDiarioMax = nuevoGasto;
        this.categorias = new ArrayList<String>();
        this.puntajesAfinidad = new ArrayList<Double>();
    }

    public ArrayList<String> obtenerCategorias() {
        return categorias;
    }

    // Devuelve el primer estilo (compatibilidad)
    public String getEstiloViaje() {
        if (estilosViaje.size() > 0) {
            return estilosViaje.get(0);
        }
        return "";
    }

    public ArrayList<String> getEstilosViaje() {
        return estilosViaje;
    }

    public void setEstilosViaje(ArrayList<String> estilos) {
        this.estilosViaje = estilos;
    }

    public double getGastoDiarioMax() {
        return gastoDiarioMax;
    }

    public void setGastoDiarioMax(double gastoDiarioMax) {
        this.gastoDiarioMax = gastoDiarioMax;
    }

    public ArrayList<Double> getPuntajesAfinidad() {
        return puntajesAfinidad;
    }

    public String toString() {
        return "PerfilPreferencias [estilos=" + estilosViaje + ", gastoDiarioMax=" + gastoDiarioMax + "]";
    }
}
