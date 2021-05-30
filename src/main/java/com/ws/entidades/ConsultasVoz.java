package com.ws.entidades;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table(value = "consulta_voz")
public class ConsultasVoz {

    @Id
    @Column
    private Long id;
    private String pregunta1;
    private String pregunta2;
    private String pregunta3;

    @Getter
    @Setter
    public static class consulta{

        private String consulta;
    }
}
