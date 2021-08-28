create table category (
                         id UUID NOT NULL,
                         name VARCHAR(50) NOT NULL /* name */,
                         created_At timestamp without time zone NOT NULL,
                         created_By VARCHAR(100) NOT NULL,
                         updated_At timestamp without time zone,
                         updated_By VARCHAR(100)
);

/* Primary Key Constraints */
alter table category add constraint pk_category_id primary key(id);
