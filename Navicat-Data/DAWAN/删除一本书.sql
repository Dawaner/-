BEGIN
	--在BOOK_TYPE(所属信息)表中删除数据，条件为书本id=5
	DELETE FROM BOOK_TYPE WHERE "Book_type_id" = '00000005';
	--在BOOK(书籍)表中删除数据，条件为书本id=5
	DELETE FROM BOOK WHERE "Book_id" = '00000005';
END;