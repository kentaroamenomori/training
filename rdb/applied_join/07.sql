-- 部署名と、それぞれの部署で最後に入社した従業員の名前・入社日を取得する
SELECT d.name, e.name, e.joined_at
    FROM divisions d
        INNER JOIN employees e
		ON d.id = e.division_id
        INNER JOIN 
            (
                SELECT MAX(joined_at) AS joined_at, division_id 
                FROM employees e
                GROUP BY division_id
            ) AS tenures 
            ON d.id = tenures.division_id
    WHERE tenures.joined_at = e.joined_at;
