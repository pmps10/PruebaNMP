package mx.prueba.loansmanager.infrastructure.data.mongo;

import mx.prueba.loansmanager.infrastructure.data.mongo.model.CatalogPriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogPriceMongoRepository extends MongoRepository<CatalogPriceEntity, String> {
}
