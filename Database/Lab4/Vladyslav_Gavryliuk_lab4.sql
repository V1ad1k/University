use world;

SELECT doc->>'$.name' AS Name, 
MAX(CAST(doc->>'$.GNP' AS DOUBLE)) AS max_gnp
FROM countryinfo
GROUP BY Name;

SELECT doc->>'$.geography.Continent', MIN(doc->>"$.GNP") AS 'MIN', MAX(doc->>"$.GNP") AS 'MAX', AVG(doc->>"$.GNP") AS 'AVG' FROM countryinfo GROUP BY doc->>'$.geography.Continent' ; 

SELECT doc->>"$.Name" FROM countryinfo WHERE doc->>"$.geography" LIKE "%North America%" ;

SELECT doc->>"$.Name" AS Name FROM countryinfo WHERE doc->>"$.government" LIKE "%Elisabeth%";

SELECT doc->>'$.geography.Continent', COUNT(doc->>'$.Name') FROM countryinfo GROUP BY doc->>'$.geography.Continent' ; 

SELECT DISTINCT doc->>"$.Name" AS 'Name', doc->>"$.demographics.LifeExpectancy" AS 'LifeExpectancy' FROM countryinfo ORDER BY CAST(doc->>"$.demographics.LifeExpectancy" AS UNSIGNED) DESC LIMIT 10;
SELECT DISTINCT doc->>"$.Name" AS 'Name', doc->>"$.demographics.LifeExpectancy" AS 'LifeExpectancy' FROM countryinfo ORDER BY CAST(doc->>"$.demographics.LifeExpectancy" AS UNSIGNED) ASC  LIMIT 10;
