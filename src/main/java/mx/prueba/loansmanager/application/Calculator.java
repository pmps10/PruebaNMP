package mx.prueba.loansmanager.application;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import mx.prueba.loansmanager.domain.CatalogPriceRepository;
import mx.prueba.loansmanager.domain.exception.LoansManagerException;
import mx.prueba.loansmanager.domain.model.CatalogPrice;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@AllArgsConstructor
public class Calculator implements BiFunction<String, Integer, BigDecimal> {

    private CatalogPriceRepository catalogPriceRepository;
    private final BigDecimal PORCENTAJE = BigDecimal.valueOf(0.8);

    @Override
    public BigDecimal apply(String id, Integer gramos) {
        Optional<CatalogPrice> findById = catalogPriceRepository.findById(id);
        if (findById.isEmpty()) {
            throw new LoansManagerException("Id de material no encontrado");
        }
        CatalogPrice catalogPrice = findById.get();
        return BigDecimal.valueOf(gramos).multiply(catalogPrice.getPrecioGramo()).multiply(PORCENTAJE);
    }

}
