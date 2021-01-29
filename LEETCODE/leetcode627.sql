UPDATE SALARY
SET SEX = CASE
          WHEN SEX = 'f'
		  THEN 'm'
		  ELSE 'f'
		  END

