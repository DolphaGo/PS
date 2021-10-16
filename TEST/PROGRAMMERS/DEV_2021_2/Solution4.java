        SELECT *
        FROM SUBWAY_STATIONS
        where id not in (
        select station_id
        from LINE_ROUTES
        where LINE_COLOR = 'Red'
        or LINE_COLOR = 'Green'
        )
        order by id;
