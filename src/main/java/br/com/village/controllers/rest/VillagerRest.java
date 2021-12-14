package br.com.village.controllers.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/village")
public class VillagerRest {

    private final Vill villagerService;

    public VillagerRest(Vill villagerService) {
        this.villagerService = villagerService;
    }


}
