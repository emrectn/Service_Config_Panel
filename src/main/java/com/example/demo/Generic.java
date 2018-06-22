package com.example.demo;

public class Generic <T>{
    T ob;
    
    public Generic(T ob){
        this.ob=ob;
    }
    
    public void show(){
        System.out.println(ob.getClass().getName()+":");
        System.out.println(ob.toString());
        System.out.println("----------------");
        
    }
    
    public T get() {
    	return ob;
    }
}

