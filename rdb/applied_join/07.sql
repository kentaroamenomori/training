-- 部署名と、それぞれの部署で最後に入社した従業員の名前・入社日を取得する
SELECT divisions.name, employees.name, employees.date_joined
    FROM divisions
        INNER JOIN employees ON divisions.id = employees.division_id
        INNER JOIN 
            (
                SELECT MAX(date_joined) AS date_joined, division_id 
                FROM employees 
                GROUP BY division_id
            ) AS employee_tenures 
            ON divisions.id = employee_tenures.division_id
    WHERE employee_tenures.date_joined = employees.date_joined;