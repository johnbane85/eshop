package vttp2022.paf.assessment.eshop.respositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import vttp2022.paf.assessment.eshop.models.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public List<Customer> findCustomerByName(String name) {
		// TODO: Task 3

		final List<Customer> custs = new LinkedList<>();

		// Perform the query
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_CUSTOMER_BY_NAME, name);

		while (rs.next()) {
			custs.add(Customer.create(rs));
		}

		return custs;

	}
}
