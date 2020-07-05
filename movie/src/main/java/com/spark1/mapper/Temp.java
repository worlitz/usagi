package com.spark1.mapper;

import lombok.Data;
import java.io.Serializable;

@Data
public class Temp implements Serializable{
    private String id;

    private String name;

    private String people;

    private static final long serialVersionUID = 1L;
}
