/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/5/24 15:40:27                           */
/*==============================================================*/

set character set utf8;

/*==============================================================*/
/* Table: device_group                                          */
/*==============================================================*/
create table device_group
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(50) not null comment '分组名称',
   remark               varchar(500) comment '备注',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id),
   unique key AK_Key_2 (name)
);

alter table device_group comment '设备分组信息';

/*==============================================================*/
/* Table: device_info                                           */
/*==============================================================*/
create table device_info
(
   id                   varchar(32) not null comment 'id',
   serial_num           varchar(32) not null comment '序列号',
   serial_code          varchar(32) not null comment '串码',
   notice_time          timestamp comment '通告时间',
   dict_device_status   varchar(50) comment '(字典)设备状态',
   fk_group_id          varchar(32) comment '分组ID',
   owner_name           varchar(20) comment '业主姓名',
   owner_phone          varchar(20) comment '业主电话',
   remark               varchar(500) comment '地址/备注',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id),
   unique key unq_device_info_serial_num (serial_num),
   unique key unq_device_info_serial_code (serial_code)
);

alter table device_info comment '设备信息';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on device_info
(
   serial_num
);

/*==============================================================*/
/* Table: device_log                                            */
/*==============================================================*/
create table device_log
(
   id                   varchar(32) not null comment 'id',
   fk_dev_serial_num    varchar(32) comment '设备串码',
   dict_dev_log_type    varchar(50) comment '(字典)日志类型',
   content              varchar(500) comment '内容',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id)
);

alter table device_log comment '设备日志';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on device_log
(
   create_time
);

/*==============================================================*/
/* Table: soft_info                                             */
/*==============================================================*/
create table soft_info
(
   id                   varchar(32) not null comment 'id',
   dict_soft_type       varchar(50) comment '(字典)软件所属类型编码',
   name                 varchar(100) comment '软件名称',
   url                  varchar(200) comment '下载地址',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id)
);

alter table soft_info comment '软件信息';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on soft_info
(
   create_time
);

/*==============================================================*/
/* Table: soft_manager                                          */
/*==============================================================*/
create table soft_manager
(
   id                   varchar(32) not null comment 'id',
   dict_soft_type       varchar(50) comment '(字典)软件所属类型编码',
   version              varchar(20) comment '当前版本号',
   fk_soft_id           varchar(32) comment '软件ID',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id)
);

alter table soft_manager comment '软件管理';

/*==============================================================*/
/* Table: sys_attachment                                        */
/*==============================================================*/
create table sys_attachment
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(100) comment '附件名称',
   size                 int comment '附件大小',
   path                 varchar(200) comment '下载路径',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id)
);

alter table sys_attachment comment '附件';

/*==============================================================*/
/* Table: sys_dictionary                                        */
/*==============================================================*/
create table sys_dictionary
(
   id                   varchar(32) not null comment 'id',
   type                 varchar(50) comment '编码类型',
   type_name            varchar(50) comment '编码类型名称',
   code                 varchar(50) not null comment '编码',
   value                varchar(50) not null comment '值',
   value4               varchar(500) comment '值4',
   value3               varchar(100) comment '值3',
   value2               varchar(50) comment '值2',
   order_num            int comment '排序，越大越前',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id)
);

alter table sys_dictionary comment '数据字典';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on sys_dictionary
(
   type,
   code
);

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   varchar(32) not null comment 'id',
   dict_sys_log_type    varchar(32) comment '日志类别',
   content              varchar(500) comment '内容',
   creator              varchar(32) default '1' comment '创建人ID',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   is_del               char default '0' comment '删除标识:1删除0未删除',
   primary key (id)
);

alter table sys_log comment '系统日志';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on sys_log
(
   create_time
);