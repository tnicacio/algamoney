create table category (
                         id UUID NOT NULL,
                         name VARCHAR(50) NOT NULL /* name */
);

/* Primary Key Constraints */
alter table category add constraint pk_category_id primary key(id);
