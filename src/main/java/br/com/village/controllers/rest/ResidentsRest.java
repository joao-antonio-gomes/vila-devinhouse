package br.com.village.controllers.rest;

import br.com.village.controllers.service.ResidentsService;
import br.com.village.exceptions.ResidentsException;
import br.com.village.model.transport.ResidentsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/residents")
public class ResidentsRest {

    private ResidentsService residentsService;

    public ResidentsRest(ResidentsService residentsService) {
        this.residentsService = residentsService;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ResidentsDTO residentsDTO) {
        Map newResident = null;
        try {
            newResident = residentsService.create(residentsDTO);
        } catch (ResidentsException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: Ocorreu um erro ao tentar criar o registro, tente novamente!");
        }
        return ResponseEntity.ok(newResident);
    }

    @GetMapping("/list")
    public ResponseEntity list(@RequestParam(value = "month", required = false) Integer month,
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
        ArrayList<Map> residents = null;
        try {
            residents = residentsService.listByFilter(filters);
            if (residents == null) {
                return ResponseEntity.badRequest().body("Erro: Não foram encontrados habitantes para listar!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: Ocorreu um erro ao tentar listar os habitantes, tente novamente!");
        }
        return ResponseEntity.ok(residents);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        Map resident = null;
        try {
            resident = residentsService.getById(id);
            if (resident == null) {
                return ResponseEntity.badRequest().body("Erro: Não foi encontrado nenhum habitante com esse id!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: Ocorreu um erro ao tentar listar os habitantes, tente novamente!");
        }
        return ResponseEntity.ok(resident);
    }

    @DeleteMapping()
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            boolean deletado = residentsService.delete(id);
            if (!deletado) {
                return ResponseEntity.badRequest().body("Erro: Não foi encontrado nenhum habitante com esse id!");
            }
            return ResponseEntity.ok("Habitante deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: Ocorreu um erro ao tentar deletar o registro, tente novamente!");
        }
    }

}
