#select count(1)和select count(*)的区别
##select count(1) from 表a //查询时会对常数列进行统计行数
##select count(*) from 表a //查询时会找表a中最短的列进行统计行数

因为使用count(*)查询会有一个找寻最短列的过程，从效率上讲会慢一些，虽然不明显，但是
一般我们使用count(1)查询就可以了，查询结果是一样的