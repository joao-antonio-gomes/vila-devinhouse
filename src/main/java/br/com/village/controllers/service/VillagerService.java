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

@Service
public class VillagerService implements UserDetailsService {

	private VillagerDAO villagerDao;

	public VillagerService(VillagerDAO villagerDao) {
		this.villagerDao = villagerDao;
	}

	public void updateUser(VillagerDTO email) {
		villagerDao.updateUser(email);
	}

	public VillagerDTO getVillagerByEmail(String email) {
		return villagerDao.getVillagerByEmail(email);
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		VillagerDTO user = getVillagerByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSpringSecurity(user.getEmail(), user.getPassword(), user.getRole());
	}
}
