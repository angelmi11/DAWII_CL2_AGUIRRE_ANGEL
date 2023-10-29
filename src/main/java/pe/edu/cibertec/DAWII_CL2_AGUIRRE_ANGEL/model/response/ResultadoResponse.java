package pe.edu.cibertec.DAWII_CL2_AGUIRRE_ANGEL.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoResponse {
    private Boolean respuesta;
    private String mensaje;
}
