package companyx.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import companyx.inventory.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
