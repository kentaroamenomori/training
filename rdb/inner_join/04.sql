SELECT divisions.name, certificates.name, COUNT(employees.name)
	FROM divisions
		INNER JOIN employees ON divisions.id = employees.division_id
		INNER JOIN employee_certificates ON employees.id = employee_certificates.employee_id
		INNER JOIN certificates ON employee_certificates.certificate_id = certificates.id
		GROUP BY divisions.name, certificates.name;
