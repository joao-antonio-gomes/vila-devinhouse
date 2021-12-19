package br.com.village.model.dao;

import br.com.village.exceptions.VillagerException;
import br.com.village.model.transport.VillagerDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class VillagerDAO {
    private Connection connection;

    public VillagerDAO() throws SQLException {
        this.connection = new ConnectionFactoryJDBC().getConnection();
    }

    public VillagerDTO getByCpf(String cpf) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM village_manager.villagers WHERE vlgr_cpf = ?")) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    VillagerDTO villagerDTO = new VillagerDTO(
                            rs.getInt("vlgr_id"),
                            rs.getString("vlgr_first_name"),
                            rs.getString("vlgr_surname"),
                            rs.getString("vlgr_cpf"),
                            rs.getString("vlgr_password"),
                            rs.getDouble("vlgr_rent"),
                            rs.getString("vlgr_birth_date"),
                            rs.getString("vlgr_email"),
                            rs.getString("vlgr_role")
                    );
                    return villagerDTO;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Map create(VillagerDTO villager) throws SQLException, ParseException, VillagerException {
        if (getByCpf(villager.getCpf()) != null) {
            throw new VillagerException("Habitante com CPF já cadastrado!");
        }
        Map<String, String> villagerMap = new HashMap<>();
        String sql = "INSERT INTO village_manager.villagers (vlgr_first_name, vlgr_surname, vlgr_cpf, vlgr_password, vlgr_rent, vlgr_birth_date, vlgr_email, vlgr_role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, villager.getFirstName());
            stmt.setString(2, villager.getSurname());
            stmt.setString(3, villager.getCpf());
            stmt.setString(4, villager.getPassword());
            stmt.setDouble(5, villager.getRent());
            stmt.setDate(6, Date.valueOf(villager.getBirthDate()));
            stmt.setString(7, villager.getEmail());
            stmt.setString(8, String.valueOf(villager.getRole()));
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            villagerMap.put("firstName", villager.getFirstName());
            villagerMap.put("surname", villager.getSurname());
            villagerMap.put("cpf", villager.getCpf());
            villagerMap.put("rent", String.valueOf(villager.getRent()));
            villagerMap.put("birth_date", String.valueOf(villager.getBirthDate()));
            villagerMap.put("email", villager.getEmail());
            while (rs.next()) {
                villagerMap.put("id", String.valueOf(rs.getInt("vlgr_id")));
            }
        }

        return villagerMap;
    }

    public void update(VillagerDTO user) {
    }

    public VillagerDTO getVillagerByEmail(String email) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM village_manager.villagers WHERE vlgr_email = ?")) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    VillagerDTO villagerDTO = new VillagerDTO(
                            rs.getInt("vlgr_id"),
                            rs.getString("vlgr_first_name"),
                            rs.getString("vlgr_surname"),
                            rs.getString("vlgr_cpf"),
                            rs.getString("vlgr_password"),
                            rs.getDouble("vlgr_rent"),
                            rs.getString("vlgr_birth_date"),
                            rs.getString("vlgr_email"),
                            rs.getString("vlgr_role")
                    );
                    return villagerDTO;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<VillagerDTO> listAll() throws SQLException, ParseException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM village_manager.villagers")) {
            try (ResultSet rs = stmt.executeQuery()) {
                ArrayList<VillagerDTO> villagers = new ArrayList<>();
                while (rs.next()) {
                    VillagerDTO villagerDTO = new VillagerDTO(
                            rs.getInt("vlgr_id"),
                            rs.getString("vlgr_first_name"),
                            rs.getString("vlgr_surname"),
                            rs.getString("vlgr_cpf"),
                            rs.getString("vlgr_password"),
                            rs.getDouble("vlgr_rent"),
                            rs.getString("vlgr_birth_date"),
                            rs.getString("vlgr_email"),
                            rs.getString("vlgr_role")
                    );
                    villagers.add(villagerDTO);
                }
                return villagers;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
