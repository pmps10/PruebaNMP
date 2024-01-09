package mx.prueba.loansmanager.infrastructure.data.mongo;

import mx.prueba.loansmanager.domain.model.CatalogPrice;
import mx.prueba.loansmanager.infrastructure.data.mongo.mapper.CatalogMapper;
import mx.prueba.loansmanager.infrastructure.data.mongo.model.CatalogPriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CatalogPriceImplTest {

    @Spy
    private CatalogMapper catalogMapper;

    @Mock
    private CatalogPriceMongoRepository catalogPriceMongoRepository;

    @InjectMocks
    private CatalogPriceImpl catalogPriceImpl;


    @Test
    void should_throw_an_exception_given_a_id_when_find_price_repository_throws_an_exception() {
        //GIVEN
        assertThat(catalogPriceImpl).isNotNull();
        final RuntimeException expectedException = new RuntimeException("Unit Test");

        given(catalogPriceMongoRepository.findById("ID")).willThrow(expectedException);

        //WHEN
        final Throwable throwable = catchThrowable(() -> catalogPriceImpl.findById("ID"));

        //THEN
        assertThat(throwable).isNotNull()
                .isInstanceOf(RuntimeException.class);

        then(catalogMapper).shouldHaveNoInteractions();
        then(catalogPriceMongoRepository).should().findById(eq("ID"));
    }

    @Test
    void should_returns_empty_given_a_id_when_find_price_repository_returns_empty_data() {
        //GIVEN
        assertThat(catalogPriceImpl).isNotNull();

        given(catalogPriceMongoRepository.findById("ID")).willReturn(Optional.empty());

        //WHEN
        final Optional<CatalogPrice> response = catalogPriceImpl.findById("ID");

        //THEN
        assertThat(response).isNotNull()
                .isEqualTo(Optional.empty());

        then(catalogMapper).shouldHaveNoInteractions();
        then(catalogPriceMongoRepository).should().findById(eq("ID"));
    }

    @Test
    void should_returns_price_given_a_id_when_find_price_repository_returns_a_price_data() {
        //GIVEN
        assertThat(catalogPriceImpl).isNotNull();

        final CatalogPriceEntity catalogPriceEntity = mockCatalogPriceEntity();
        given(catalogPriceMongoRepository.findById("ID")).willReturn(Optional.of(catalogPriceEntity));

        //WHEN
        final Optional<CatalogPrice> response = catalogPriceImpl.findById("ID");

        //THEN
        assertThat(response).isNotNull()
                .isEqualTo(Optional.of(mockCatalogPrice()));

        then(catalogMapper).should().mapToCatalogPrice(eq(catalogPriceEntity));
        then(catalogPriceMongoRepository).should().findById(eq("ID"));
    }

    private CatalogPriceEntity mockCatalogPriceEntity() {
        return CatalogPriceEntity.builder()
                .id("ID")
                .material("001")
                .precioGramo(new BigDecimal("1500"))
                .build();
    }

    private CatalogPrice mockCatalogPrice() {
        return CatalogPrice.builder()
                .id("ID")
                .material("001")
                .precioGramo(new BigDecimal("1500"))
                .build();
    }


}
