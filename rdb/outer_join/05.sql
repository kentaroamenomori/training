-- 全ての従業員とその勤務先事務所を取得する（勤務先事務所がない従業員も表示する）
SELECT employees.name, offices.name
	FROM employees
		LEFT OUTER JOIN offices ON employees.office_id = offices.id;
