package br.com.village.controllers.rest;

import br.com.village.controllers.service.VillagerService;
import br.com.village.exceptions.VillagerException;
import br.com.village.model.transport.VillagerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/villager")
public class VillagerRest {

    private VillagerService villagerService;

    public VillagerRest(VillagerService villagerService) throws ParseException {
        this.villagerService = villagerService;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody VillagerDTO villagerDTO) {
        Map newVillager = null;
        try {
            newVillager = villagerService.create(villagerDTO);
        } catch (VillagerException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
        return ResponseEntity.ok(newVillager);
    }

    @GetMapping("/list")
    public ResponseEntity<ArrayList<Map>> list(@RequestParam(value = "month", required = false) Integer month,
                                               @RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "age", required = false) Integer age) {
        Map<String, String> filters = new HashMap<>();
        if (month != null) {
            filters.put("month", month.toString());
        }
        if (name != null) {
            filters.put("name", name);
        }
        if (age != null) {
            filters.put("age", age.toString());
        }
        ArrayList<Map> villagers = villagerService.listByFilter(filters);
        if (villagers == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(villagers);
    }

}
