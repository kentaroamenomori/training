-- 全ての従業員とその勤務先事務所を取得する（勤務先事務所がない従業員も表示する）
SELECT e.name, o.name
	FROM employees e
		LEFT OUTER JOIN offices o ON e.office_id = o.id;
