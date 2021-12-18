package br.com.village.controllers.service;

import br.com.village.model.transport.VillagerDTO;
import br.com.village.model.dao.UserSpringSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.village.model.dao.VillagerDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class VillagerService implements UserDetailsService {

	private VillagerDAO villagerDao;

	public VillagerService() throws SQLException {
		this.villagerDao = new VillagerDAO();
	}

	public void updateUser(VillagerDTO user) {
		villagerDao.update(user);
	}

	public VillagerDTO getVillagerByEmail(String email) {
		try {
			return villagerDao.getVillagerByEmail(email);
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o usuário pelo email");
		}
		return null;
	}

	public static UserSpringSecurity authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return new UserSpringSecurity((String) authentication.getPrincipal(), null,
					authentication.getAuthorities());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		VillagerDTO user = getVillagerByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(user.getEmail(), user.getPassword(), user.getRole());
	}

	public void createVillager() {

	}

	public VillagerDTO create(VillagerDTO villagerDTO) {
		try {
			VillagerDTO newVillager = new VillagerDTO(
					villagerDTO.getFirstName(),
					villagerDTO.getSurname(),
					villagerDTO.getCpf(),
					villagerDTO.getPassword(),
					villagerDTO.getRent(),
					villagerDTO.getBirthDate(),
					villagerDTO.getPassword(),
					villagerDTO.getEmail(),
					villagerDTO.getRole()
			);
			return villagerDao.create(newVillager);
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return villagerDTO;
	}

	public ArrayList<Map> listAll() {
		try {
			ArrayList<VillagerDTO> villagerList = villagerDao.listAll();
			ArrayList<Map> villagerMapList = new ArrayList<>();
			for (VillagerDTO villager : villagerList) {
				Map<String, String> villagerMap = new HashMap<>();
				villagerMap.put("id", String.valueOf(villager.getId()));
				villagerMap.put("firstName", villager.getFirstName());
				villagerMap.put("surname", villager.getSurname());
				villagerMapList.add(villagerMap);
			}
			return villagerMapList;
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Map> filterByName(String name) {
		try {
			ArrayList<VillagerDTO> villagerList = villagerDao.listAll();
			ArrayList<Map> villagerMapList = new ArrayList<>();
			for (VillagerDTO villager : villagerList) {
				if (!villager.getFirstName().toLowerCase().contains(name.toLowerCase())) {
					continue;
				}
				Map<String, String> villagerMap = new HashMap<>();
				villagerMap.put("id", String.valueOf(villager.getId()));
				villagerMap.put("firstName", villager.getFirstName());
				villagerMap.put("surname", villager.getSurname());
				villagerMapList.add(villagerMap);
			}
			return villagerMapList;
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Map> filterByMonth(Integer month) {
		try {
			ArrayList<VillagerDTO> villagerList = villagerDao.listAll();
			ArrayList<Map> villagerMapList = new ArrayList<>();
			for (VillagerDTO villager : villagerList) {
				int monthValue = villager.getBirthDate().getMonthValue();
				if (monthValue != month) {
					continue;
				}
				Map<String, String> villagerMap = new HashMap<>();
				villagerMap.put("id", String.valueOf(villager.getId()));
				villagerMap.put("firstName", villager.getFirstName());
				villagerMap.put("surname", villager.getSurname());
				villagerMapList.add(villagerMap);
			}
			return villagerMapList;
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
