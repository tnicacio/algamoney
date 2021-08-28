create table tb_user_role (
                            user_id UUID NOT NULL /* userId */,
                            role_id UUID NOT NULL /* roleId */
);

/* Primary Key Constraints */
alter table tb_user_role add constraint pk_tb_user_role_id primary key(user_id, role_id);

/* Foreign Key Constraints */
alter table tb_user_role add constraint fk_tb_user_role_tb_user_id foreign key (user_id) references tb_user (id);
alter table tb_user_role add constraint fk_tb_user_role_tb_role_id foreign key (role_id) references tb_role (id);
