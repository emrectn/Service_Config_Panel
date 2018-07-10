package com.example.demo.model.serialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.PermType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


public class CustomPermTypeSerializer extends StdSerializer<List<PermType>> {

	private static final long serialVersionUID = 499440791943909835L;

	public CustomPermTypeSerializer() {
	        this(null);
	    }

	public CustomPermTypeSerializer(Class<List<PermType>> t) {
	        super(t);
	    }

	@Override
	public void serialize(
			List<PermType> permTypes, 
			JsonGenerator gen, 
			SerializerProvider provider) throws IOException, JsonProcessingException {
		
				List<Integer> ids = new ArrayList<>();
				for (PermType permType : permTypes) {
					ids.add(permType.getId());
				}
				
				gen.writeObject(ids);
	}

}