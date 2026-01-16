package com.ivoautomativo.usuario.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collation  = "usuario_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CompoundIndex(name = "idx_nome_idade", def = "{'nome': 1, 'idade': -1}")
public class UsuarioEntity {

    @Id
    private String id;

    private String nome;

    @Indexed(unique = true)
    private String email;

    private String documento;

    private Integer idade;

    @Indexed
    private List<String> habilidades;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point localizacao;

    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}