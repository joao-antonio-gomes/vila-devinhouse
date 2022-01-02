package br.com.village.model.dao;

import br.com.village.exceptions.ResidentsException;
import br.com.village.model.transport.ResidentsDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ResidentsDAO {
    private Connection connection;

    public ResidentsDAO() throws SQLException {
        this.connection = new ConnectionFactoryJDBC().getConnection();
    }

    public ResidentsDTO getByCpf(String cpf) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM village_manager.villagers WHERE vlgr_cpf = ?")) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ResidentsDTO residentsDTO = new ResidentsDTO(
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
                    return residentsDTO;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Map create(ResidentsDTO villager) throws SQLException, ParseException, ResidentsException {
        if (getByCpf(villager.getCpf()) != null) {
            throw new ResidentsException("Habitante com CPF já cadastrado!");
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

    public void update(ResidentsDTO user) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE village_manager.villagers SET vlgr_first_name = ?, vlgr_surname = ?, vlgr_cpf = ?, vlgr_password = ?, vlgr_rent = ?, vlgr_birth_date = ?, vlgr_email = ?, vlgr_role = ? WHERE vlgr_id = ?")) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getCpf());
            stmt.setString(4, user.getPassword());
            stmt.setDouble(5, user.getRent());
            stmt.setDate(6, Date.valueOf(user.getBirthDate()));
            stmt.setString(7, user.getEmail());
            stmt.setString(8, String.valueOf(user.getRole()));
            stmt.setInt(9, user.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResidentsDTO getVillagerByEmail(String email) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM village_manager.villagers WHERE vlgr_email = ?")) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ResidentsDTO residentsDTO = new ResidentsDTO(
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
                    return residentsDTO;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<ResidentsDTO> listAll() throws SQLException, ParseException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM village_manager.villagers")) {
            try (ResultSet rs = stmt.executeQuery()) {
                ArrayList<ResidentsDTO> villagers = new ArrayList<>();
                while (rs.next()) {
                    ResidentsDTO residentsDTO = new ResidentsDTO(
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
                    villagers.add(residentsDTO);
                }
                return villagers;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ResidentsDTO getById(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM village_manager.villagers WHERE vlgr_id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ResidentsDTO residentsDTO = new ResidentsDTO(
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
                    return residentsDTO;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM village_manager.villagers WHERE vlgr_id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getTotalRent() {
        try (PreparedStatement stmt = connection.prepareStatement("select sum(vlgr_rent) from village_manager.villagers;")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("sum");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ResidentsDTO getMoreExpensiveVillager() throws ParseException {
        String sql = "SELECT * FROM village_manager.villagers WHERE vlgr_rent = (SELECT max(vlgr_rent) FROM village_manager.villagers)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ResidentsDTO(
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
