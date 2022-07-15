SELECT d.name, c.name, COUNT(e.id)
	FROM divisions d
		INNER JOIN employees e 
			ON d.id = e.division_id
		INNER JOIN employee_certificates ec 
			ON e.id = ec.employee_id
		INNER JOIN certifications c
			ON ec.certificate_id = c.id
		GROUP BY d.id, c.id;
