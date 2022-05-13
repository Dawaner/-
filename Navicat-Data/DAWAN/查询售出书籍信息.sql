--查询售出书籍信息(只输出Book_id和Book_name两列信息)
--条件为"Book_sales"=1(1为已售出，0为未售出)
SELECT "Book_id","Book_name"
FROM BOOK
WHERE "Book_sales" = 1