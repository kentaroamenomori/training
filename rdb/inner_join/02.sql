-- 部署IDが01の部署の従業員を取得（INNER JOINなし）
SELECT divisions.id, employees.name 
	FROM divisions, employees 
	WHERE divisions.id = employees.division_id AND divisions.id = '01';

-- 部署IDが01の部署の従業員を取得（INNER JOINあり）
SELECT divisions.name, employees.name 
	FROM divisions 
		INNER JOIN employees ON divisions.id = employees.division_id
	WHERE divisions.id = '01';
