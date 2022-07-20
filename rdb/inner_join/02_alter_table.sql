-- 従業員テーブルに部署IDをカラムを追加
ALTER TABLE employees ADD COLUMN division_id TEXT;

-- division_idにNOT NULL制約を追加
ALTER TABLE employees
	ALTER COLUMN division_id SET NOT NULL;

-- 外部キー制約を追加
ALTER TABLE employees 
	ADD CONSTRAINT division_id
	FOREIGN KEY(division_id) REFERENCES divisions(id);
