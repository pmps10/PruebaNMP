package mx.prueba.loansmanager.infrastructure.data.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogPriceEntity {
    @Id
    private String id;
    private String material;
    private BigDecimal precioGramo;

}
