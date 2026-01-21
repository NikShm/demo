package com.example.demo.model;

import java.io.Serializable;

public interface EntityDTO<ID extends Serializable> extends Serializable {
	ID getId();
}
