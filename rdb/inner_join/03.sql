-- 1つの部署を指定して、部署と従業員と従業員が持つ資格を一覧表示する
SELECT d.name, e.name, c.name 
	FROM divisions d
		INNER JOIN employees e 
			ON d.id = e.division_id 
		INNER JOIN employee_certificates ec 
			ON e.id = ec.employee_id 
		INNER JOIN certifications c
			ON ec.certificate_id = c.id 
	WHERE d.id = '01';

-- 1つの資格を指定して、その資格を持つ従業員とその部署を一覧表示する
SELECT e.name, d.name 
	FROM divisions d
		INNER JOIN employees e 
			ON d.id = e.division_id 
		INNER JOIN employee_certificates ec 
			ON e.id = ec.employee_id 
		INNER JOIN certifications c
			ON ec.certificate_id = c.id 
	WHERE c.id = '001';
