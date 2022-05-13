--查询7月份卖出了哪些书
--使用了嵌套INNER JOIN，把BOOK，BUY和BOOK_BUY连接在一起
--以及LIKE模糊查询
SELECT BOOK."Book_name"
FROM BUY
INNER JOIN (BOOK_BUY INNER JOIN BOOK 
ON BOOK_BUY."Book_buy_id" = BOOK."Book_id")
ON BUY."Buy_id" = BOOK_BUY."Book_buy_id"
WHERE "Buy_time" LIKE '07%'