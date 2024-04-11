package com.unibague.CitasMedicas.model;

import com.unibague.CitasMedicas.services.IMedicoG;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder

public class CitaGeneral extends CitaMedica implements IMedicoG {

    private String nombreGeneralista;
    private String observacion;
    private static Set<Integer> consultoriosAsignados;
    private Random random;

    public CitaGeneral( ){
        this.consultoriosAsignados = new HashSet<>();
        this.random = new Random();
    }

    //Pull para asignarConsultorio y lo demás de abajo se debe corregir.
    @Override
    public  int asignarConsultorio() {
        int consultorioAsignado;

        if (consultoriosAsignados.size() == 10) {
            consultoriosAsignados.clear();
        }

        do {
            consultorioAsignado = random.nextInt(10) + 1;
        } while (consultoriosAsignados.contains(consultorioAsignado));

        consultoriosAsignados.add(consultorioAsignado);

        return consultorioAsignado;
    }
    public double calcularCosto() {
        return getCosto();
    }

    public boolean idExistente(String numeroIdentificacion) {
        return false;
    }

}