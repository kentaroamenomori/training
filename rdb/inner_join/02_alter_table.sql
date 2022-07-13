-- 従業員テーブルに部署IDをカラムを追加
ALTER TABLE employees ADD COLUMN division_id TEXT;

-- 外部キー制約を追加
ALTER TABLE employees 
	ADD CONSTRAINT division_id
	FOREIGN KEY(division_id) REFERENCES divisions(id);
