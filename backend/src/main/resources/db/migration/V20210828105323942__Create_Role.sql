create table tb_role (
                            id UUID NOT NULL,
                            authority VARCHAR(100) NOT NULL /* authority */
);

/* Primary Key Constraints */
alter table tb_role add constraint pk_tb_role_id primary key(id);
