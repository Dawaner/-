--查询在BOOK(书籍)表种类编号为6的所有图书名称
--DISTINCT为去掉重复的书名
--通过BOOK_TYPE(所属信息)表获得种类id为6的书的id，在与BOOK(书籍)表互联获得书本id相同的表
SELECT DISTINCT BOOK."Book_name"
FROM BOOK_TYPE
INNER JOIN BOOK
ON BOOK."Book_id" = BOOK_TYPE."Book_type_id"
WHERE "Type_book_id" = '0006'