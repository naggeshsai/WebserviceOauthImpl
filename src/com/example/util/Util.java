package com.example.util;

import java.util.UUID;

public class Util {

	public String getToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
