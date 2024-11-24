package com.alura.literatura.challenge.services;



import com.fasterxml.jackson.databind.ObjectMapper;


public class ConvertirDatos implements IConvertirDatos {


    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (Exception e) {
            throw new RuntimeException("Error tipo: " +e);
        }
    }

    
}
