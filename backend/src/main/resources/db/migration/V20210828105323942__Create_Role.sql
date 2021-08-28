create table tb_role (
                            id UUID NOT NULL,
                            authority VARCHAR(100) NOT NULL /* authority */,
                            created_At timestamp without time zone,
                            created_By VARCHAR(100),
                            updated_At timestamp without time zone,
                            updated_By VARCHAR(100)
);

/* Primary Key Constraints */
alter table tb_role add constraint pk_tb_role_id primary key(id);
