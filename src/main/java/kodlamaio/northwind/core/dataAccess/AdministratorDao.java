package kodlamaio.northwind.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.Administrator;

public interface AdministratorDao extends JpaRepository<Administrator, Integer>{

}
