create table tb_user (
                            id UUID NOT NULL,
                            first_name VARCHAR(100) /* firstName */,
                            last_name VARCHAR(100) /* lastName */,
                            email VARCHAR(255) /* email */,
                            password VARCHAR(255) /* password */
);

/* Primary Key Constraints */
alter table tb_user add constraint pk_tb_user_id primary key(id);

/* Unique Key Constraints */
alter table tb_user add constraint uk_tb_user_email unique (email);

