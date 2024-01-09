package mx.prueba.loansmanager.infrastructure.rest.controller;

import lombok.AllArgsConstructor;
import mx.prueba.loansmanager.application.Calculator;
import mx.prueba.loansmanager.domain.exception.LoansManagerException;
import mx.prueba.loansmanager.infrastructure.rest.controller.model.LoansResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class LoanController {

    private Calculator calculator;

    @GetMapping(value = "/loan")
    public ResponseEntity<LoansResponse> loansImport(@RequestParam String idMaterial, @RequestParam Integer peso) {
        try {
            return new ResponseEntity<>(LoansResponse.builder().importe(calculator.apply(idMaterial, peso)).build(), HttpStatus.OK);
        } catch (LoansManagerException e) {
            return new ResponseEntity<>(LoansResponse.builder().importe(BigDecimal.ZERO).build(), HttpStatus.NOT_FOUND);
        }
    }

}
