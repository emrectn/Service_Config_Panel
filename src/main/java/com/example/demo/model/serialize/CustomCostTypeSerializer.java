package com.example.demo.model.serialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.CostType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomCostTypeSerializer extends StdSerializer<List<CostType>> {
	
	private static final long serialVersionUID = 499440791943909835L;

	public CustomCostTypeSerializer() {
	        this(null);
	    }

	public CustomCostTypeSerializer(Class<List<CostType>> t) {
	        super(t);
	    }


	@Override
	public void serialize(
			List<CostType> costTypes, 
			JsonGenerator gen, 
			SerializerProvider provider) throws IOException, JsonProcessingException {
		
				List<Integer> ids = new ArrayList<>();
				for (CostType costType : costTypes) {
					ids.add(costType.getId());
				}
				
				gen.writeObject(ids);
	}

}
