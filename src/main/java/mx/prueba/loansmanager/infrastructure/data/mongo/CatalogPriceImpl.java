package mx.prueba.loansmanager.infrastructure.data.mongo;

import lombok.AllArgsConstructor;
import mx.prueba.loansmanager.domain.CatalogPriceRepository;
import mx.prueba.loansmanager.domain.model.CatalogPrice;
import mx.prueba.loansmanager.infrastructure.data.mongo.mapper.CatalogMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CatalogPriceImpl implements CatalogPriceRepository {

    private CatalogMapper catalogMapper;
    private CatalogPriceMongoRepository catalogPriceMongoRepository;

    @Override
    public Optional<CatalogPrice> findById(String id) {
        return catalogPriceMongoRepository.findById(id)
                .map(catalogMapper::mapToCatalogPrice);
    }
}
