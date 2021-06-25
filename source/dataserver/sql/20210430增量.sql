#房屋表 1、将字段设置为数据变动后自动给时间；2、更新时间为空自动补为创建时间
ALTER TABLE base_community_houses 
MODIFY COLUMN `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间' AFTER `update_by`;
update base_community_houses set update_time = create_time where update_time is null;

#车辆表 1、将字段设置为数据变动后自动给时间；2、更新时间为空自动补为创建时间
ALTER TABLE base_vehicle_information 
MODIFY COLUMN `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间' AFTER `update_by`;
update base_vehicle_information set update_time = create_time where update_time is null;