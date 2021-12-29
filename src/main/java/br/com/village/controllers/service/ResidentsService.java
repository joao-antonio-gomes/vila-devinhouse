package br.com.village.controllers.service;

import br.com.village.exceptions.CpfException;
import br.com.village.exceptions.ResidentsException;
import br.com.village.model.transport.ResidentsDTO;
import br.com.village.model.dao.UserSpringSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.village.model.dao.ResidentsDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResidentsService implements UserDetailsService {

	private ResidentsDAO residentsDao;

	public ResidentsService() throws SQLException {
		this.residentsDao = new ResidentsDAO();
	}

	public void updateUser(ResidentsDTO user) {
		residentsDao.update(user);
	}

	public ResidentsDTO getVillagerByEmail(String email) {
		try {
			return residentsDao.getVillagerByEmail(email);
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
		ResidentsDTO user = getVillagerByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(user.getEmail(), user.getPassword(), user.getRole());
	}

	public Map create(ResidentsDTO residentsDTO) throws Exception {
		try {
			ResidentsDTO newResident = new ResidentsDTO(
					residentsDTO.getFirstName(),
					residentsDTO.getSurname(),
					residentsDTO.getCpf(),
					residentsDTO.getPassword(),
					residentsDTO.getRent(),
					residentsDTO.getBirthDate(),
					residentsDTO.getEmail(),
					residentsDTO.getRole()
			);
			return residentsDao.create(newResident);
		} catch (ResidentsException | CpfException e) {
			throw new ResidentsException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public ArrayList<Map> listAll() {
		try {
			ArrayList<ResidentsDTO> villagerList = residentsDao.listAll();
			ArrayList<Map> villagerMapList = new ArrayList<>();
			for (ResidentsDTO villager : villagerList) {
				Map<String, String> residentMap = new HashMap<>();
				residentMap.put("id", String.valueOf(villager.getId()));
				residentMap.put("firstName", villager.getFirstName());
				residentMap.put("surname", villager.getSurname());
				villagerMapList.add(residentMap);
			}
			return villagerMapList;
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Map> listByFilter(Map<String, String> filters) {
		try {
			ArrayList<ResidentsDTO> residentsList = residentsDao.listAll();
			ArrayList<Map> residentsMapList = new ArrayList<>();
			for (ResidentsDTO resident : residentsList) {
				int monthValue = resident.getBirthDate().getMonthValue();
				if (
					(filters.containsKey("month") && monthValue != Integer.parseInt(filters.get("month"))) ||
					(filters.containsKey("name") && !resident.getFirstName().toLowerCase().contains(filters.get("name").toLowerCase())) ||
					(filters.containsKey("age") && resident.getAge() < Integer.parseInt(filters.get("age")))
				) {
					continue;
				}
				Map<String, String> residentsMap = new HashMap<>();
				residentsMap.put("id", String.valueOf(resident.getId()));
				residentsMap.put("firstName", resident.getFirstName());
				residentsMap.put("surname", resident.getSurname());
				residentsMapList.add(residentsMap);
			}
			return residentsMapList;
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map getById(Integer id) {
		ResidentsDTO resident = residentsDao.getById(id);
		Map<String, String> residentMap = new HashMap<>();
		residentMap.put("id", String.valueOf(resident.getId()));
		residentMap.put("firstName", resident.getFirstName());
		residentMap.put("surname", resident.getSurname());
		residentMap.put("cpf", resident.getCpf());
		residentMap.put("rent", String.valueOf(resident.getRent()));
		residentMap.put("birth_date", String.valueOf(resident.getBirthDate()));
		residentMap.put("email", resident.getEmail());
		return residentMap;
	}

	public boolean delete(Integer id) {
		return residentsDao.delete(id);
	}

	public double getTotalRent() {
		return residentsDao.getTotalRent();
	}

	public ResidentsDTO getMoreExpensiveVillager() {
		try {
			return residentsDao.getMoreExpensiveVillager();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
