--在SEND(发货手段)表中删除数据，条件为发货名称是圆通快递的数据
DELETE FROM SEND WHERE "Send_name" = '圆通快递'