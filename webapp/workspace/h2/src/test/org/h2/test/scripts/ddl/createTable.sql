-- Copyright 2004-2022 H2 Group. Multiple-Licensed under the MPL 2.0,
-- and the EPL 1.0 (https://h2database.com/html/license.html).
-- Initial Developer: H2 Group
--

CREATE TABLE TEST(A INT CONSTRAINT PK_1 PRIMARY KEY);
> ok

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
> CONSTRAINT_NAME CONSTRAINT_TYPE
> --------------- ---------------
> PK_1            PRIMARY KEY
> rows: 1

DROP TABLE TEST;
> ok

CREATE TABLE TEST(ID IDENTITY, CONSTRAINT PK_1 PRIMARY KEY(ID));
> ok

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
> CONSTRAINT_NAME CONSTRAINT_TYPE
> --------------- ---------------
> PK_1            PRIMARY KEY
> rows: 1

DROP TABLE TEST;
> ok

CREATE TABLE T1(ID INT PRIMARY KEY, COL2 INT);
> ok

INSERT INTO T1 VALUES (1, 2), (11, 22);
> update count: 2

CREATE TABLE T2 AS SELECT * FROM T1;
> ok

SELECT * FROM T2 ORDER BY ID;
> ID COL2
> -- ----
> 1  2
> 11 22
> rows (ordered): 2

DROP TABLE T2;
> ok

CREATE TABLE T2 AS SELECT * FROM T1 WITH DATA;
> ok

SELECT * FROM T2 ORDER BY ID;
> ID COL2
> -- ----
> 1  2
> 11 22
> rows (ordered): 2

DROP TABLE T2;
> ok

CREATE TABLE T2 AS SELECT * FROM T1 WITH NO DATA;
> ok

SELECT * FROM T2 ORDER BY ID;
> ID COL2
> -- ----
> rows (ordered): 0

DROP TABLE T2;
> ok

DROP TABLE T1;
> ok

CREATE TABLE TEST(A INT, B INT INVISIBLE);
> ok

SELECT * FROM TEST;
> A
> -
> rows: 0

SELECT A, B FROM TEST;
> A B
> - -
> rows: 0

SELECT COLUMN_NAME, IS_VISIBLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TEST' ORDER BY ORDINAL_POSITION;
> COLUMN_NAME IS_VISIBLE
> ----------- ----------
> A           TRUE
> B           FALSE
> rows (ordered): 2

DROP TABLE TEST;
> ok

CREATE TABLE TEST1(ID IDENTITY);
> ok

CREATE TABLE TEST2(ID BIGINT GENERATED BY DEFAULT AS IDENTITY);
> ok

SELECT CONSTRAINT_TYPE, TABLE_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_SCHEMA = 'PUBLIC';
> CONSTRAINT_TYPE TABLE_NAME
> --------------- ----------
> PRIMARY KEY     TEST1
> rows: 1

DROP TABLE TEST1, TEST2;
> ok

CREATE TABLE TEST(A);
> exception UNKNOWN_DATA_TYPE_1

CREATE TABLE TEST(A, B, C) AS SELECT 1, 2, CAST ('A' AS VARCHAR);
> ok

SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TEST';
> COLUMN_NAME DATA_TYPE
> ----------- -----------------
> A           INTEGER
> B           INTEGER
> C           CHARACTER VARYING
> rows: 3

DROP TABLE TEST;
> ok

CREATE MEMORY TABLE TEST(A INT, B INT GENERATED ALWAYS AS (1), C INT GENERATED ALWAYS AS (B + 1));
> exception COLUMN_NOT_FOUND_1

CREATE MEMORY TABLE TEST(A INT, B INT GENERATED ALWAYS AS (1), C INT GENERATED ALWAYS AS (A + 1));
> ok

SCRIPT NOPASSWORDS NOSETTINGS NOVERSION TABLE TEST;
> SCRIPT
> -----------------------------------------------------------------------------------------------------------------------------------
> CREATE USER IF NOT EXISTS "SA" PASSWORD '' ADMIN;
> CREATE MEMORY TABLE "PUBLIC"."TEST"( "A" INTEGER, "B" INTEGER GENERATED ALWAYS AS (1), "C" INTEGER GENERATED ALWAYS AS ("A" + 1) );
> -- 0 +/- SELECT COUNT(*) FROM PUBLIC.TEST;
> rows (ordered): 3

DROP TABLE TEST;
> ok

CREATE TABLE TEST(A INT GENERATED BY DEFAULT AS (1));
> exception SYNTAX_ERROR_2

CREATE TABLE TEST(A IDENTITY GENERATED ALWAYS AS (1));
> exception SYNTAX_ERROR_2

CREATE TABLE TEST(A IDENTITY AS (1));
> exception SYNTAX_ERROR_2

CREATE TABLE TEST1(ID BIGINT GENERATED ALWAYS AS IDENTITY);
> ok

CREATE TABLE TEST2(ID BIGINT GENERATED BY DEFAULT AS IDENTITY);
> ok

CREATE TABLE TEST3(ID BIGINT NULL GENERATED ALWAYS AS IDENTITY);
> exception COLUMN_MUST_NOT_BE_NULLABLE_1

CREATE TABLE TEST3(ID BIGINT GENERATED BY DEFAULT AS IDENTITY NULL);
> exception COLUMN_MUST_NOT_BE_NULLABLE_1

SELECT COLUMN_NAME, IDENTITY_GENERATION, IS_NULLABLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'PUBLIC';
> COLUMN_NAME IDENTITY_GENERATION IS_NULLABLE
> ----------- ------------------- -----------
> ID          ALWAYS              NO
> ID          BY DEFAULT          NO
> rows: 2

DROP TABLE TEST1, TEST2;
> ok

CREATE TABLE TEST(ID BIGINT GENERATED ALWAYS AS IDENTITY(MINVALUE 1 MAXVALUE 2), V INT);
> ok

INSERT INTO TEST(V) VALUES 1;
> update count: 1

SELECT IDENTITY_BASE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TEST' AND COLUMN_NAME = 'ID';
>> 2

INSERT INTO TEST(V) VALUES 2;
> update count: 1

SELECT IDENTITY_BASE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TEST' AND COLUMN_NAME = 'ID';
>> null

DROP TABLE TEST;
> ok

CREATE TABLE TEST(ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, V INT);
> ok

INSERT INTO TEST(V) VALUES 1;
> update count: 1

INSERT INTO TEST VALUES (2, 2);
> update count: 1

INSERT INTO TEST(V) VALUES 3;
> exception DUPLICATE_KEY_1

TABLE TEST;
> ID V
> -- -
> 1  1
> 2  2
> rows: 2

DROP TABLE TEST;
> ok

CREATE TABLE TEST1(R BIGINT GENERATED BY DEFAULT AS IDENTITY);
> ok

SET MODE HSQLDB;
> ok

CREATE TABLE TEST2(M BIGINT GENERATED BY DEFAULT AS IDENTITY);
> ok

SET MODE MySQL;
> ok

CREATE TABLE TEST3(H BIGINT GENERATED BY DEFAULT AS IDENTITY);
> ok

SET MODE Regular;
> ok

SELECT COLUMN_NAME, DEFAULT_ON_NULL FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'PUBLIC';
> COLUMN_NAME DEFAULT_ON_NULL
> ----------- ---------------
> H           TRUE
> M           TRUE
> R           FALSE
> rows: 3

DROP TABLE TEST1, TEST2, TEST3;
> ok

EXECUTE IMMEDIATE 'CREATE TABLE TEST(' || (SELECT LISTAGG('C' || X || ' INT') FROM SYSTEM_RANGE(1, 16384)) || ')';
> ok

DROP TABLE TEST;
> ok

EXECUTE IMMEDIATE 'CREATE TABLE TEST(' || (SELECT LISTAGG('C' || X || ' INT') FROM SYSTEM_RANGE(1, 16385)) || ')';
> exception TOO_MANY_COLUMNS_1

CREATE TABLE TEST AS (SELECT REPEAT('A', 300));
> ok

TABLE TEST;
> C1
> ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
> AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
> rows: 1

DROP TABLE TEST;
> ok

CREATE TABLE T1(ID BIGINT PRIMARY KEY);
> ok

CREATE TABLE T2(ID BIGINT PRIMARY KEY, R BIGINT REFERENCES T1 NOT NULL);
> ok

SELECT IS_NULLABLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'T2' AND COLUMN_NAME = 'R';
>> NO

DROP TABLE T2, T1;
> ok
