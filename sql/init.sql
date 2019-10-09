create database stockdb;
use stockdb;

CREATE TABLE b_dict (
  id varchar(10) NOT NULL COMMENT '主键',
  dict_type varchar(50) NOT NULL COMMENT '字典类型',
  dict_type_desc varchar(50) NOT NULL COMMENT '类型描述',
  dict_code varchar(10) NOT NULL COMMENT '码值',
  dict_name varchar(50) NOT NULL COMMENT '码值名称',
  show_flag varchar(10) DEFAULT NULL COMMENT '显示标识',
  status char(1) DEFAULT '1' COMMENT '码状态 0.无效 1.有效',
  remark varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id),
  UNIQUE KEY dict_type (dict_type,dict_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

grant all on stockdb.* to stockuser IDENTITFIED by '!Q2w3e4r';


CREATE  TABLE stock_index(
  id int PRIMARY key auto_increment COMMENT '主键',
  stock_name_first_letter varchar(10)  COMMENT  '股票名称首字母',
  stock_code varchar(10) NOT NULL COMMENT '股票代码',
  stock_name varchar(10) NOT NULL COMMENT '股票名称',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  UNIQUE KEY stock_name_index (stock_name_first_letter,stock_code)
);