create sequence product_id_seq start with 1 increment by 10;

create table products(
    id bigint default nextval('product_id_seq') not null,
    code text not null unique,
    name text not null,
    description text,
    image_url text,
    price numeric(10,2) not null,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp default current_timestamp not null,
    primary key (id)
)