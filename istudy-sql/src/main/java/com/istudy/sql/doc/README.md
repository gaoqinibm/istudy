## 效能语句汇总
    SELECT CONCAT('`', COLUMN_NAME,'`') AS '字段名称',
    (CASE
    WHEN DATA_TYPE='bigint' THEN ' BIGINT,'
    WHEN DATA_TYPE='varchar' THEN ' STRING,'
    WHEN DATA_TYPE='tinyint' THEN ' TINYINT,'
    WHEN DATA_TYPE='smallint' THEN ' SMALLINT,'
    WHEN DATA_TYPE='int' THEN ' INT,'
    WHEN DATA_TYPE='datetime' THEN ' TIMESTAMP,'
    WHEN DATA_TYPE='decimal' THEN ' DECIMAL,'
    WHEN DATA_TYPE='longtext' THEN ' STRING,'
    WHEN DATA_TYPE='text' THEN ' STRING,'
    WHEN DATA_TYPE='date' THEN ' DATE,'
    WHEN DATA_TYPE='json' THEN ' STRING,'
    WHEN DATA_TYPE='double' THEN ' DOUBLE,'
    ELSE '字段对不齐，自查确认'
    END
    ) AS '字段类型'
    FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uat2_proj_0017' AND TABLE_NAME='deliver_fl_std_month_purchase_sink' order by ORDINAL_POSITION ASC;

    SELECT CONCAT('`', COLUMN_NAME,'`') AS '字段名称', CONCAT('', COLUMN_TYPE,',') AS '字段标准类型'
    FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uat2_proj_0017' AND TABLE_NAME='fl_std_month_purchase_view' order by ORDINAL_POSITION ASC;
    
    SELECT CONCAT('`', COLUMN_NAME,'`') AS '字段名称',
    (CASE
    WHEN DATA_TYPE='bigint' THEN ' BIGINT,'
    WHEN DATA_TYPE='varchar' THEN ' STRING,'
    WHEN DATA_TYPE='tinyint' THEN ' TINYINT,'
    WHEN DATA_TYPE='smallint' THEN ' SMALLINT,'
    WHEN DATA_TYPE='int' THEN ' INT,'
    WHEN DATA_TYPE='datetime' THEN ' TIMESTAMP,'
    WHEN DATA_TYPE='decimal' THEN ' DECIMAL,'
    WHEN DATA_TYPE='longtext' THEN ' STRING,'
    WHEN DATA_TYPE='text' THEN ' STRING,'
    WHEN DATA_TYPE='date' THEN ' DATE,'
    WHEN DATA_TYPE='json' THEN ' STRING,'
    WHEN DATA_TYPE='double' THEN ' DOUBLE,'
    ELSE '字段对不齐，自查确认'
    END
    ) AS '字段类型'
    FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uat2_proj_0181' AND TABLE_NAME='fl_raw_sddi_inventory' AND EXTRA!='VIRTUAL GENERATED' order by ORDINAL_POSITION ASC;

## 其他

