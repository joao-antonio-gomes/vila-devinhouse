package br.com.village.controllers.rest;

import br.com.village.controllers.service.VillagerService;
import br.com.village.model.transport.VillagerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/village")
public class VillagerRest {

    private VillagerService villagerService;

    public VillagerRest(VillagerService villagerService) throws ParseException {
        this.villagerService = villagerService;
    }

    @PostMapping("/create")
    public ResponseEntity<VillagerDTO> create(@RequestBody VillagerDTO villagerDTO) {
        VillagerDTO newVillager = villagerService.create(villagerDTO);
        if (newVillager == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newVillager);
    }

}
