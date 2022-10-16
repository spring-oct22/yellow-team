package com.example.winery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WineDTO {
	
	
	private Integer id;
    private String name;
    private Integer year;
    private Double rating;
    private Integer num_reviews;
    private Double price;
    private Integer body;
    private Integer acidity;

}
