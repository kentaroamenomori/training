-- 部署IDが01の部署の従業員を取得（INNER JOINなし）
SELECT d.name, e.name 
	FROM divisions d, employees e 
	WHERE d.id = e.division_id AND d.id = '01';

-- 部署IDが01の部署の従業員を取得（INNER JOINあり）
SELECT d.name, e.name 
	FROM divisions d
		INNER JOIN employees e ON d.id = e.division_id
	WHERE d.id = '01';
