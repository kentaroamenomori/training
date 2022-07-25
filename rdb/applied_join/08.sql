-- 姓がエマヌエーレである上司を持つ従業員の一覧を取得する
SELECT * FROM employees
	WHERE boss_id IN (
		SELECT id FROM employees
			WHERE second_name = 'エマヌエーレ'
		);
