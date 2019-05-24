-- 初始化默认设备分组
INSERT INTO `tzzd`.`device_group` (`id`, `name`, `remark`) VALUES ('1', '默认', '默认分组');

-- 初始化数据字典
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'isDel', '删除状态', '0', '未删除');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'isDel', '删除状态', '1', '已删除');

INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'deviceStatus', '设备状态', '1', '在线');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'deviceStatus', '设备状态', '2', '中断');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'deviceStatus', '设备状态', '3', '下线');

INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'devLogType', '设备日志类型', '1', '注册');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'devLogType', '设备日志类型', '2', '删除');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'devLogType', '设备日志类型', '3', '修改');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'devLogType', '设备日志类型', '4', '登入');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'devLogType', '设备日志类型', '5', '登出');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'devLogType', '设备日志类型', '6', '报错');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'devLogType', '设备日志类型', '4', '登入');


INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'sysLogType', '系统日志类型', '1', '注册');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'sysLogType', '系统日志类型', '2', '删除');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'sysLogType', '系统日志类型', '3', '修改');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'sysLogType', '系统日志类型', '4', '登入');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'sysLogType', '系统日志类型', '5', '登出');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'sysLogType', '系统日志类型', '6', '报错');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'sysLogType', '系统日志类型', '4', '登入');

INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'softType', '软件类型', '1', '大厅');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'softType', '软件类型', '2', '双色球');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'softType', '软件类型', '3', '3D');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'softType', '软件类型', '4', '七乐彩');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'softType', '软件类型', '5', '快乐十分');
INSERT INTO `tzzd`.`dictionary` (`id`, `type`, `type_name`, `code`, `value` ) VALUES (UUID_SHORT(), 'softType', '软件类型', '6', '打印机');





