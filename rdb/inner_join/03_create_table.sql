-- 資格テーブルを作成
CREATE TABLE certificates(id TEXT PRIMARY KEY, name TEXT NOT NULL UNIQUE);

-- 従業員ID・資格ID・資格取得日を記載したテーブルを作成
CREATE TABLE employee_certificates(
	certificate_id TEXT NOT NULL, 
	employee_id INTEGER NOT NULL, 
	certified_date DATE NOT NULL,
	FOREIGN KEY(certificate_id) REFERENCES certificates(id),
	FOREIGN KEY(employee_id) REFERENCES employees(id)
);
