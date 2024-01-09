package mx.prueba.loansmanager.infrastructure.data.mongo.mapper;

import mx.prueba.loansmanager.domain.model.CatalogPrice;
import mx.prueba.loansmanager.infrastructure.data.mongo.model.CatalogPriceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatalogMapper {

    CatalogPrice mapToCatalogPrice(CatalogPriceEntity catalogPriceEntity);

    CatalogPriceEntity mapToCatalogPriceEntity(CatalogPrice catalogPrice);

}
