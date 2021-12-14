package br.com.village.model.dao;

import br.com.village.model.transport.VillagerDTO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class VillagerDAO {
    private static Map<String, VillagerDTO> villagers = new HashMap<>();

    public VillagerDAO() {
        VillagerDTO villager = new VillagerDTO("Joao", "Gomes", 25, "093.558.729-25",
                "Umasenhaforte!23", 5000.00, new Date(System.currentTimeMillis()), "joao@email.com", Set.of("ROLE_ADMIN"));
        villagers.put(villager.getEmail(), villager);
    }

    public VillagerDTO getVillagerByEmail(String email) {
        return villagers.get(email);
    }

    public void updateUser(VillagerDTO email) {
    }
}
