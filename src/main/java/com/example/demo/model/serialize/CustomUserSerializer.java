package com.example.demo.model.serialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomUserSerializer extends StdSerializer<List<User>> {

	private static final long serialVersionUID = 499440791943909835L;

	public CustomUserSerializer() {
	        this(null);
	    }

	public CustomUserSerializer(Class<List<User>> t) {
	        super(t);
	    }

	@Override
	public void serialize(
			List<User> users, 
			JsonGenerator gen, 
			SerializerProvider provider) throws IOException, JsonProcessingException {
		
				List<Integer> ids = new ArrayList<>();
				for (User user : users) {
					ids.add(user.getId());
				}
				
				gen.writeObject(ids);
	}

}
