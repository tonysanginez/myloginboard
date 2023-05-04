package com.tasm.enums;

public enum TipoNumero {
	
	MOBILE("MOBILE"),
	FIXED_LINE("FIXED_LINE"),
	FIXED_LINE_OR_MOBILE("FIXED_LINE_OR_MOBILE");

	private String name;

	TipoNumero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}