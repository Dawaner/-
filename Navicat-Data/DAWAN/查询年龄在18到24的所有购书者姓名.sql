--查询在BUYER(购书者)表总年龄在18到24之间的所有购书者姓名
SELECT BUYER."Buyer_name"
FROM BUYER
WHERE "Buyer_age" BETWEEN '18' and '24'