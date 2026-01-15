package com.rafakliv.sistema.clientes.CustomError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    private String message;
    private String type;

}
