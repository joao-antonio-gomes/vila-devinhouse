package br.com.village.controllers.rest;

import br.com.village.controllers.service.VillagerService;
import br.com.village.model.transport.VillagerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/villager")
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

    @RequestMapping("/listAll")
    public ResponseEntity<ArrayList<Map>> listAll() {
        ArrayList<Map> villagers = villagerService.listAll();
        if (villagers == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(villagers);
    }

    @PostMapping("/listByName/{name}")
    public ResponseEntity<ArrayList<Map>> listByName(@PathVariable String name) {
        ArrayList<Map> villagers = villagerService.filterByName(name);
        if (villagers == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(villagers);
    }

    @PostMapping("/listByMonth/{month}")
    public ResponseEntity<ArrayList<Map>> listByMonth(@PathVariable Integer month) {
        ArrayList<Map> villagers = villagerService.filterByMonth(month);
        if (villagers == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(villagers);
    }



}
