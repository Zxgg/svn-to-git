ALTER TABLE isstech.base_personnel_information ADD identification_positive varchar(1024) NULL COMMENT '身份证正面照片';
ALTER TABLE isstech.base_personnel_information ADD identification_reverse varchar(1024) NULL COMMENT '身份证反面照片';


ALTER TABLE isstech.base_data_request_notification MODIFY COLUMN get_by_certificate varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '领取时需要的携带的材料（比如本人身份证）';
ALTER TABLE isstech.base_data_request_notification MODIFY COLUMN get_replace_by_certificate varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '带领人需要携带的证件（如果允许带领）';
ALTER TABLE isstech.base_certify_application_form MODIFY COLUMN required_material varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '所需申请人提供的材料信息（由配置项中获取）';
