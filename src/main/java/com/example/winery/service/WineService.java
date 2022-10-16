package com.example.winery.service;

import com.example.winery.dto.WineDTO;
import com.example.winery.entity.Wine;
import com.example.winery.mapper.WineMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.winery.repository.WineRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WineService {



    private final WineRepository wineRepository;

    private final WineMapper wineMapper;

    public Collection<WineDTO> getAll() {

        List<Wine> listWines = wineRepository.findAll();
        Collection<WineDTO> listWinesDTO = listWines.stream()
                .map(wine -> wineMapper.wineToDTO(wine))
                .collect(Collectors.toList());
        return listWinesDTO;
    }

    public WineDTO getWine(int id) {
        //Collection<WineDTO> wineDTOS = getAll();
        //WineDTO wineDTO = wineDTOS.stream().filter(wine->wine.getId()==id).collect(Collectors.toList()).get(0);
        Optional<Wine> wine = wineRepository.findById(id);
        return wineMapper.wineToDTO(wine.get());
    }

    public String deleteWine(int id) {
        if (getWine(id) != null) {
            wineRepository.deleteById(id);
            String deleteMessage = "El vino con id " + id + " ha sido eliminado";
            return deleteMessage;

        }
        return "Vino no encontrado";
    }

    public WineDTO createWine(WineDTO wineDTO) {
        Wine wine = wineMapper.dtoToWine(wineDTO);
        wine = wineRepository.save(wine);
        WineDTO wineDTO1 = wineMapper.wineToDTO(wine);
        return wineDTO1;
    }

    public WineDTO updateWine(WineDTO wineDTO, int id) {
        Wine wine = wineMapper.dtoToWine(wineDTO);
        wine.setId(id);
        wine = wineRepository.save(wine);
        return wineMapper.wineToDTO(wine);
    }
    ///recommend/best?top=10
    public List<WineDTO> findByRating(){
        List<WineDTO> wineListDTO=  wineMapper.listToListDTO(wineRepository.findAll().stream().filter(s->s.getRating()!=null).collect(Collectors.toList()));
        return  wineListDTO.stream()
                .sorted(Comparator.comparing(WineDTO::getRating).reversed()).collect(Collectors.toList());
    }
    public List<WineDTO> findByPrice(){
        List<WineDTO> wineDTOList= wineMapper.listToListDTO(wineRepository.findAll().stream().filter(s->s.getPrice()!=null).collect(Collectors.toList()));
        return  wineDTOList.stream().sorted(Comparator.comparing(WineDTO::getPrice).reversed()).collect(Collectors.toList());
    }
    public List<WineDTO> findByBang(){
        List<WineDTO> wineDTOList= wineMapper.listToListDTO(wineRepository.findAll().stream().filter(s->s.getPrice()!=null).collect(Collectors.toList()));
        return wineDTOList.stream().sorted((s1,s2)->{
            if(s1.getPrice()/s1.getRating()== s2.getPrice()/s2.getRating()){
                return 0;
            }
            if(s1.getPrice()/s1.getRating()>s2.getPrice()/s2.getRating()) {
                return 1;
            }else{
                    return -1;
                }
            })

        .collect(Collectors.toList());

    }
    public List<WineDTO> findByRating2(int top){
        List<WineDTO> wineListDTO=  wineMapper.listToListDTO(wineRepository.findAllByOrderByRatingDesc(PageRequest.of(0,top)));
        return  wineListDTO.stream()
                .sorted(Comparator.comparing(WineDTO::getRating).reversed()).collect(Collectors.toList());
    }

}
