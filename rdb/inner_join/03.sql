-- 1つの部署を指定して、部署と従業員と従業員が持つ資格を一覧表示する
SELECT divisions.name, employees.name, certificates.name 
	FROM divisions 
		INNER JOIN employees ON divisions.id = employees.division_id 
		INNER JOIN employee_certificates ON employees.id = employee_certificates.employee_id 
		INNER JOIN certificates ON employee_certificates.certificate_id = certificates.id 
	WHERE divisions.id = '01';

-- 1つの資格を指定して、その資格を持つ従業員とその部署を一覧表示する
SELECT certificates.name, employees.name, divisions.name 
	FROM divisions 
		INNER JOIN employees ON divisions.id = employees.division_id 
		INNER JOIN employee_certificates ON employees.id = employee_certificates.employee_id 
		INNER JOIN certificates ON employee_certificates.certificate_id = certificates.id 
	WHERE certificates.id = '001';
