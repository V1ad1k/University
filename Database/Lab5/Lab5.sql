USE world1;
SELECT DISTINCT doc->"$.government.GovernmentForm" AS Form FROM countryinfo;
SELECT doc->"$.geography.Continent" AS continent, COUNT(*) AS number
FROM countryinfo
GROUP BY continent
ORDER BY number DESC LIMIT 1;
SELECT DISTINCT doc->"$.Name" AS name, doc->"$.IndepYear" AS indyear
FROM countryinfo
ORDER BY indyear DESC;
SELECT Language, COUNT(IsOfficial = 'T') AS official
FROM countrylanguage
GROUP BY Language
ORDER BY official DESC;
SELECT Language, SUM(Take_Speakers) AS Total
FROM  (SELECT countrylanguage.Language, (CAST(doc->>"$.demographics.Population" AS UNSIGNED) * countrylanguage.Percentage * (0.01)) AS Take_Speakers
FROM countryinfo
LEFT JOIN countrylanguage ON CountryCode = doc->'$._id') AS JoinedTable
GROUP BY Language;
SELECT DISTINCT GNP.Country FROM (
  SELECT DISTINCT doc->>"$.Name" AS Country
  FROM countryinfo
  ORDER BY doc->'$.GNP' DESC LIMIT 20) AS GNP
    RIGHT JOIN (
    SELECT DISTINCT doc->>'$.Name' AS Country
    FROM countryinfo
    WHERE doc->'$.demographics.LifeExpectancy' IS NOT NULL
    ORDER BY doc->'$.demographics.LifeExpectancy' DESC LIMIT 20) AS LE
ON GNP.Country = LE.Country;
