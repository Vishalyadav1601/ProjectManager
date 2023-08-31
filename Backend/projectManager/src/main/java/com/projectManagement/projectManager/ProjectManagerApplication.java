package com.projectManagement.projectManager;

import com.projectManagement.projectManager.Config.AppConstants;
import com.projectManagement.projectManager.DAO.RoleDAO;
import com.projectManagement.projectManager.Models.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ProjectManagerApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleDAO roleDAO;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("Niit@123"));
		try{
			Role role = new Role();
			role.setRoleId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setRoleId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			List<Role> roles = List.of(role,role1);
			List<Role> result = this.roleDAO.saveAll(roles);
			result.forEach(r->
			{
				System.out.println(r.getName());
			});
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
