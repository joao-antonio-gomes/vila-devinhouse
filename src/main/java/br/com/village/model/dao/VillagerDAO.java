package br.com.village.model.dao;

import br.com.village.model.transport.VillagerDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.Set;

@Repository
public class VillagerDAO {
    private Connection connection;

    public VillagerDAO() throws SQLException {
        this.connection = new ConnectionFactoryJDBC().getConnection();
    }

    public VillagerDTO create(VillagerDTO villager) throws SQLException, ParseException {
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
            while (rs.next()) {
                villager.setId(rs.getInt(1));
            }
        }
        return villager;
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
}
