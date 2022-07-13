-- 上司のIDを振ったカラムを追加し、自分自身を上司にすることを禁止
ALTER TABLE employees
	ADD COLUMN boss_id INTEGER REFERENCES employees(id),
	ADD CONSTRAINT boss_id CHECK(boss_id != id);

-- 姓名をカラムに追加
ALTER TABLE employees
	ADD COLUMN first_name TEXT,
	ADD COLUMN second_name TEXT;

-- 姓名入力後に姓名のNULL値を禁止し、nameカラムを削除
ALTER TABLE employees
	ALTER COLUMN first_name SET NOT NULL,
	ALTER COLUMN second_name SET NOT NULL,
	DROP COLUMN name;
