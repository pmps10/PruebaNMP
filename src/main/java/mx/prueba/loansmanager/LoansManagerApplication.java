package mx.prueba.loansmanager;

import mx.prueba.loansmanager.infrastructure.data.mongo.CatalogPriceMongoRepository;
import mx.prueba.loansmanager.infrastructure.data.mongo.model.CatalogPriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class LoansManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansManagerApplication.class, args);
	}

	@Autowired
	CatalogPriceMongoRepository catalogPriceMongoRepository;

	@PostConstruct
	public void init() throws IOException {
		List<CatalogPriceEntity> catalogo = List.of(
		new CatalogPriceEntity("001", "Oro puro 24k", BigDecimal.valueOf(1500)),
		new CatalogPriceEntity("002", "Oro alto 18k", BigDecimal.valueOf(1000)),
		new CatalogPriceEntity("003", "Oro medio 14k", BigDecimal.valueOf(800)),
		new CatalogPriceEntity("004", "Oro bajo 10k", BigDecimal.valueOf(500)),
		new CatalogPriceEntity("005", "Plata ley .925", BigDecimal.valueOf(300)),
		new CatalogPriceEntity("006", "Titanio", BigDecimal.valueOf(200)),
		new CatalogPriceEntity("007", "Rodio", BigDecimal.valueOf(100)));
		catalogPriceMongoRepository.saveAll(catalogo);
	}

}
