package com.bank.accounts.serializer;

import com.bank.accounts.dto.ResponseDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ResponseDTOSerializer extends JsonSerializer<ResponseDTO> {
    @Override
    public void serialize(ResponseDTO responseDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String serializedResponse = "{";

        if(responseDTO.getOrigin() != null){
            serializedResponse += "\"origin\": {";
            serializedResponse += "\"id\": \"" + responseDTO.getOrigin().getId()+"\", ";
            serializedResponse += "\"balance\": "+responseDTO.getOrigin().getBalance();
            serializedResponse += "}";
        }

        if(responseDTO.getOrigin() != null && responseDTO.getDestination() != null){
            serializedResponse += ", ";
        }

        if(responseDTO.getDestination() != null){
            serializedResponse += "\"destination\": {";
            serializedResponse += "\"id\": \"" + responseDTO.getDestination().getId()+"\", ";
            serializedResponse += "\"balance\": "+responseDTO.getDestination().getBalance();
            serializedResponse += "}";
        }

        serializedResponse += "}";

        jsonGenerator.writeRaw(serializedResponse);
    }
}
