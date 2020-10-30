-- Database: shoppingcart

-- DROP DATABASE shoppingcart;

CREATE DATABASE shoppingcart
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
-- SCHEMA: sc

-- DROP SCHEMA sc ;

CREATE SCHEMA sc
    AUTHORIZATION postgres;



-- Table: sc."user"

-- DROP TABLE sc."user";

CREATE TABLE sc."user"
(
    user_id bigint NOT NULL DEFAULT nextval('sc.user_user_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE sc."user"
    OWNER to postgres;
    
    
-- Table: sc.cart

-- DROP TABLE sc.cart;

CREATE TABLE sc.cart
(
    cart_id bigint NOT NULL DEFAULT nextval('sc.cart_cart_id_seq'::regclass),
    creation_date timestamp without time zone,
    status smallint DEFAULT 0,
    user_id bigint,
    CONSTRAINT cart_pkey PRIMARY KEY (cart_id),
    CONSTRAINT fkl70asp4l4w0jmbm1tqyofho4o FOREIGN KEY (user_id)
        REFERENCES sc."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE sc.cart
    OWNER to postgres;
    
    
-- Table: sc.product

-- DROP TABLE sc.product;

CREATE TABLE sc.product
(
    product_id bigint NOT NULL DEFAULT nextval('sc.product_product_id_seq'::regclass),
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    stock integer NOT NULL,
    unit_price numeric(19,2) NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id)
)

TABLESPACE pg_default;

ALTER TABLE sc.product
    OWNER to postgres;
    
    
-- Table: sc.item

-- DROP TABLE sc.item;

CREATE TABLE sc.item
(
    quantity integer NOT NULL,
    subtotal numeric(19,2) NOT NULL,
    unit_price numeric(19,2) NOT NULL,
    cart_id bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT item_pkey PRIMARY KEY (cart_id, product_id),
    CONSTRAINT fk4g2q77pbbf0faqae5uywbsodk FOREIGN KEY (cart_id)
        REFERENCES sc.cart (cart_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkd1g72rrhgq1sf7m4uwfvuhlhe FOREIGN KEY (product_id)
        REFERENCES sc.product (product_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE sc.item
    OWNER to postgres;
    
    
-- SEQUENCE: sc.cart_cart_id_seq

-- DROP SEQUENCE sc.cart_cart_id_seq;

CREATE SEQUENCE sc.cart_cart_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE sc.cart_cart_id_seq
    OWNER TO postgres;
    
    
-- SEQUENCE: sc.product_product_id_seq

-- DROP SEQUENCE sc.product_product_id_seq;

CREATE SEQUENCE sc.product_product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE sc.product_product_id_seq
    OWNER TO postgres;
    
    
-- SEQUENCE: sc.user_user_id_seq

-- DROP SEQUENCE sc.user_user_id_seq;

CREATE SEQUENCE sc.user_user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE sc.user_user_id_seq
    OWNER TO postgres;
    
    
    
INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (1, 'Alcohol Bialcohol 250cc', 25, 80);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (2, 'Aceite de girasol Cocinero 900cc', 30, 95);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (3, 'Arroz largo fino 00000 Primor 1kg', 15, 54);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (4, 'Fideos corto mostachol Cica 500 gramos', 5, 45);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (5, 'Galletitas de agua Mediatarde 110 gramos', 55, 20);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (6, 'Harina de maíz Morixe 500 gramos', 25, 32);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (7, 'Harina de trigo 000 Morixe 1 kg', 35, 36);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (8, 'Yerba mate Taragüí 500 gramos', 65, 130);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (9, 'Pañales descartables supersec Pampers 26 unidades', 95, 330);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (10, 'Agua mineralizada sin gas Cellier/Favaloro1 litro', 50, 35);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (11, 'Lavandina común Odex 1 litro', 40, 43);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (12, 'Papel higiénico hoja simple 30 metros Elite por 4 unidades', 65, 70);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (13, 'Toalla femenina confort normales con alas LadySoft 8 unidades', 55, 49);

INSERT INTO sc.product(product_id, description, stock, unit_price) VALUES (14, 'Repelente Family Active Off 165cc', 75, 145);

    