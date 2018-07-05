package com.example.demo.model.serialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Register;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomRegisterSerializer extends StdSerializer<List<Register>> {

	private static final long serialVersionUID = 499440791943909835L;

	public CustomRegisterSerializer() {
	        this(null);
	    }

	public CustomRegisterSerializer(Class<List<Register>> t) {
	        super(t);
	    }

	@Override
	public void serialize(
			List<Register> registers, 
			JsonGenerator gen, 
			SerializerProvider provider) throws IOException, JsonProcessingException {
		
				List<Integer> ids = new ArrayList<>();
				for (Register register : registers) {
					ids.add(register.getId());
				}
				
				gen.writeObject(ids);
	}

}
