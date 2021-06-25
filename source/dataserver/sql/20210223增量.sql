ALTER TABLE t_specia_aids ADD community_id varchar(38) COMMENT '社区ID';
ALTER TABLE t_specia_aids ADD community_child_id varchar(38) COMMENT '小区ID';

ALTER TABLE t_specia_correct ADD community_id varchar(38) COMMENT '社区ID';
ALTER TABLE t_specia_correct ADD community_child_id varchar(38) COMMENT '小区ID';

ALTER TABLE t_specia_mental ADD community_id varchar(38) COMMENT '社区ID';
ALTER TABLE t_specia_mental ADD community_child_id varchar(38) COMMENT '小区ID';

ALTER TABLE t_special_drug_addicts ADD community_id varchar(38) COMMENT '社区ID';
ALTER TABLE t_special_drug_addicts ADD community_child_id varchar(38) COMMENT '小区ID';

ALTER TABLE t_special_released_people ADD community_id varchar(38) COMMENT '社区ID';
ALTER TABLE t_special_released_people ADD community_child_id varchar(38) COMMENT '小区ID';