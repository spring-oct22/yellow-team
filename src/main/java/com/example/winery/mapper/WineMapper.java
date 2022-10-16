package com.example.winery.mapper;

import org.springframework.stereotype.Component;

import com.example.winery.dto.WineDTO;
import com.example.winery.entity.Wine;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WineMapper {


    public WineDTO wineToDTO(Wine w) {
        WineDTO wineDto = new WineDTO(w.getId(), w.getName(), w.getYear(), w.getRating(), w.getNum_reviews(), w.getPrice(), w.getBody(), w.getAcidity());
        return wineDto;
    }

    public Wine dtoToWine(WineDTO w) {
        Wine wine= new Wine(w.getId(), w.getName(), w.getYear(), w.getRating(), w.getNum_reviews(), w.getPrice(), w.getBody(), w.getAcidity(),null,null,null);
        return wine;
    }
    public List<WineDTO> listToListDTO(List<Wine>wineList){
        List<WineDTO> listDTO = wineList.stream().map(w->wineToDTO(w)).collect(Collectors.toList());
                return listDTO;
    }

}
