package com.alura.literatura.challenge.services;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
