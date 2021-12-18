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
}
