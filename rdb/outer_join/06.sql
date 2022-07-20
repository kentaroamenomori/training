-- 一つの部署を指定して部署と従業員と従業員が持つ資格を一覧表示する（資格を保有していない従業員も表示する）
SELECT d.name, e.name, c.name 
	FROM divisions d
		INNER JOIN employees e
			ON d.id = e.division_id 
		LEFT OUTER JOIN employee_certificates ec
			ON e.id = ec.employee_id 
		LEFT OUTER JOIN certifications c
			ON ec.certificate_id = c.id 
	WHERE d.id = '03';
