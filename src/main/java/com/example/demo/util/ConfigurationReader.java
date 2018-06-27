package com.example.demo.util;

public interface ConfigurationReader {

	public <T> T getValue(String name);
}
