package com.devsuperior.bds02.controller.exceptions;

import java.time.Instant;

public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

   public StandardError () {

   }
}
