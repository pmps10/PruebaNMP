package mx.prueba.loansmanager.application;

import mx.prueba.loansmanager.domain.CatalogPriceRepository;
import mx.prueba.loansmanager.domain.model.CatalogPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    private CatalogPriceRepository catalogPriceRepository;

    @InjectMocks
    private Calculator calculator;

    @Test
    void should_return_error_given_an_id_when_find_price_repository_throws_an_exception() {
        //GIVEN
        assertThat(calculator).isNotNull();
        final RuntimeException expectedException = new RuntimeException("Unit Test");

        given(catalogPriceRepository.findById("ID")).willThrow(expectedException);

        //WHEN
        final Throwable throwable = catchThrowable(() -> calculator.apply("ID", 10));

        //THEN
        assertThat(throwable)
                .isInstanceOf(RuntimeException.class);

        then(catalogPriceRepository).should().findById(eq("ID"));
    }

    @Test
    void should_return_price_given_an_id_when_find_price_repository_returns_a_price() {
        //GIVEN
        assertThat(calculator).isNotNull();

        given(catalogPriceRepository.findById("ID")).willReturn(Optional.ofNullable(mockCatalogPrice()));

        //WHEN
        final BigDecimal response = calculator.apply("ID", 5);

        //THEN
        assertThat(response).isNotNull()
                .isEqualTo(new BigDecimal("6000.0"));

        then(catalogPriceRepository).should().findById(eq("ID"));
    }

    private CatalogPrice mockCatalogPrice() {
        return CatalogPrice.builder()
                .id("ID")
                .material("001")
                .precioGramo(new BigDecimal("1500"))
                .build();
    }

}
