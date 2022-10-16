package com.example.winery.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wine {


   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer year;
    private Double rating;
    private Integer num_reviews;
    private Double price;
    private Integer body;
    private Integer acidity;
    
    @ManyToOne
    @JoinColumn(name="winery_id")
    private Winery winery;
    
    @ManyToOne
    @JoinColumn(name="type_id")
    private Type type;
    
    @ManyToOne
    @JoinColumn(name ="region_id")
    private Region region;

*/
    //--------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;



    @Column(name="name")
    @NotBlank
    private String name;




    @Column(name="year")
    @Min(value=1900)
    @Max(value=2022)
    @NotNull
    private Integer year;

    @NotNull
    @Min(value=0,message = "value must be in [0,5]")
    @Max(value=5,message = "value must be in [0,5]")
    @PositiveOrZero(message="Must be positive or zero")
    private Double rating;

    @NotNull
    @PositiveOrZero(message="Must be positive or zero")
    private Integer num_reviews;

    @NotNull
    @PositiveOrZero(message="Must be positive or zero")
    private Double price;

    @Min(value=1,message = "value must be in [1,5]")
    @Max(value=5,message = "value must be in [1,5]")
    private Integer body;


    @Min(value=1,message = "value must be in [1,5]")
    @Max(value=5,message = "value must be in [1,5]")
    private Integer acidity;



    @ManyToOne
    @JoinColumn(name="winery_id")
    private Winery winery;

    @ManyToOne
    @JoinColumn(name="type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name ="region_id")
    private Region region;

}