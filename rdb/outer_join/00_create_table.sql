-- 事務所テーブルを作成
CREATE TABLE offices(id integer NOT NULL, name text NOT NULL, address text);

-- 従業員テーブルに勤務先事務所IDカラムを追加
ALTER TABLE employees ADD COLUMN office_id integer;
