create table tb_user (
                            id UUID NOT NULL,
                            first_name VARCHAR(100) /* firstName */,
                            last_name VARCHAR(100) /* lastName */,
                            email VARCHAR(255) /* email */,
                            password VARCHAR(255) /* password */,
                            created_At timestamp without time zone,
                            created_By VARCHAR(100),
                            updated_At timestamp without time zone,
                            updated_By VARCHAR(100)
);

/* Primary Key Constraints */
alter table tb_user add constraint pk_tb_user_id primary key(id);

/* Unique Key Constraints */
alter table tb_user add constraint uk_tb_user_email unique (email);

