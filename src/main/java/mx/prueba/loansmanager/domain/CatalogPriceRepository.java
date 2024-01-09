package mx.prueba.loansmanager.domain;

import mx.prueba.loansmanager.domain.model.CatalogPrice;

import java.util.Optional;

public interface CatalogPriceRepository {

    Optional<CatalogPrice> findById(String id);
}
