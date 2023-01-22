DROP INDEX IF EXISTS article_idx;
DROP INDEX IF EXISTS comment_id_account_idx;
DROP INDEX IF EXISTS comment_id_article_idx;

DROP SEQUENCE IF EXISTS account_seq;
DROP SEQUENCE IF EXISTS comment_seq;

DROP TABLE IF EXISTS category cascade;
DROP TABLE IF EXISTS article cascade;
DROP TABLE IF EXISTS account cascade;
DROP TABLE IF EXISTS comment cascade;


CREATE TABLE category
(
    id integer primary key not null,
    name varchar(20) not null,
    url varchar(255) unique not null,
    count_of_articles integer not null
);

CREATE TABLE article
(
    id bigint primary key not null,
    title varchar(255) not null,
    description varchar(255) not null,
    content text not null,
    date_of_created timestamp not null default now(),
    count_of_views integer not null default 0,
    count_of_comments integer not null default 0,
    url_article varchar(255) not null,
    url_logo varchar(255) not null,
    id_category integer not null,
    foreign key (id_category) references category(id)
        on delete restrict on update cascade
);
CREATE INDEX article_idx ON article USING btree (id_category);

CREATE TABLE account(
    id bigint primary key not null,
    name varchar(30) not null,
    email varchar(255) unique not null,
    avatar varchar(255),
    date_of_created timestamp not null default now()
);
CREATE SEQUENCE account_seq
    start 1
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
ALTER SEQUENCE account_seq owner to postgres;


CREATE TABLE comment(
    id bigint primary key not null,
    content text not null,
    date_of_created timestamp not null default now(),
    id_account bigint not null,
    id_article bigint not null,
    foreign key(id_account) references account(id)
        on delete restrict on update cascade,
    foreign key(id_article) references article(id)
        on delete restrict on update cascade
);
CREATE INDEX comment_id_account_idx ON comment USING btree (id_account);
CREATE INDEX comment_id_article_idx ON comment USING btree (id_article);
CREATE SEQUENCE comment_seq
    start 1
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
ALTER SEQUENCE comment_seq owner to postgres;
