--查询各支付方式的使用情况
SELECT PAY."Pay_name"
FROM BUY
INNER JOIN PAY
ON BUY."Buy_pay_id" = PAY."Pay_id"
--关联两表中的支付id来获得支付名字