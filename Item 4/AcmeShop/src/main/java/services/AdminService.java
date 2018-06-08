
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import domain.Admin;

@Service
@Transactional
public class AdminService {

	//Managed Repository ----
	@Autowired
	private AdminRepository	adminRepository;


	//Constructors
	public AdminService() {
		super();
	}

	public Admin create() {
		Admin result;

		result = new Admin();

		return result;
	}

	public Collection<Admin> findAll() {
		Collection<Admin> result;

		result = this.adminRepository.findAll();

		return result;
	}

	public void delete(final Admin admin) {

		this.adminRepository.delete(admin);

	}

	public Admin save(final Admin admin) {
		Admin result;

		result = this.adminRepository.save(admin);
		return result;
	}

	public Admin findOne(final int adminId) {
		Admin result;

		result = this.adminRepository.findOne(adminId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.adminRepository.flush();
	}

}
