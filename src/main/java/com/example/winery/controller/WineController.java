package com.example.winery.controller;

import com.example.winery.dto.WineDTO;
import com.example.winery.entity.Wine;
import com.example.winery.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class WineController {

    @Autowired
    private WineService wineService;

    @Secured("ROLE_MANAGERS")
    @GetMapping("/wine")
    public Collection<WineDTO> getAll() {
        return wineService.getAll();
    }

    @GetMapping("/wine/{id}")
    public WineDTO getWine(@PathVariable int id) {
        return wineService.getWine(id);
    }

    @Secured("ROLE_ADMINS")
    @DeleteMapping("/wine/{id}")
    public String deleteWine(@PathVariable int id) {
        return wineService.deleteWine(id);

    }

    @PostMapping("/wine")
    public WineDTO createWine(@RequestBody WineDTO wineDTO) {
        WineDTO wineDTO1 = wineService.createWine(wineDTO);
        return wineDTO1;
    }

    @PutMapping("/wine/{id}")
    public WineDTO updateWine(@RequestBody WineDTO wineDTO, @PathVariable int id) {
        WineDTO wineDTO1 = wineService.updateWine(wineDTO, id);
        return wineDTO1;
    }

    @GetMapping("/recommend/best")
    public Collection<WineDTO> getBest(@RequestParam("top") int top) {
        return wineService.findByRating().stream().limit(top).collect(Collectors.toList());
    }

    @GetMapping("/recommend/expensive")
    public Collection<WineDTO> findExpensive(@RequestParam("top") int top) {
        return wineService.findByPrice().stream().limit(top).collect(Collectors.toList());
    }

    @GetMapping("/recommend/bang")
    public Collection<WineDTO> findBang(@RequestParam("top") int top) {
        return wineService.findByBang().stream().limit(top).collect(Collectors.toList());
    }

    @GetMapping("/recommend/vintage")
    public Collection<WineDTO> findVintage(@RequestParam("top") int top) {
        return wineService.findByRating2(top);
    }

}
