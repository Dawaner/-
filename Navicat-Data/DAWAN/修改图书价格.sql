--更新BOOK(书籍)表中名字为三国演义且未售出(Book_sale = '0')的书的价格，改为10.8
UPDATE BOOK SET "Book_price" = '10.8' WHERE "Book_name" = '三国演义' AND "Book_sales" = '0'