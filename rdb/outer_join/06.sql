-- 一つの部署を指定して部署と従業員と従業員が持つ資格を一覧表示する（資格を保有していない従業員も表示する）
SELECT divisions.name, employees.name, certificates.name 
	FROM divisions 
		INNER JOIN employees ON divisions.id = employees.division_id 
		LEFT OUTER JOIN employee_certificates ON employees.id = employee_certificates.employee_id 
		LEFT OUTER JOIN certificates ON employee_certificates.certificate_id = certificates.id 
	WHERE divisions.id = '03';
