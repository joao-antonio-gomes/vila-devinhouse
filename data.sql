create schema if not exists `village_manager`;

create table if not exists villagers
(
    vlgr_id         serial
        primary key,
    vlgr_first_name varchar(255) not null,
    vlgr_surname    varchar(255) not null,
    vlgr_cpf        varchar(14)  not null,
    vlgr_birth_date date         not null,
    vlgr_rent       numeric      not null,
    vlgr_password   varchar(255) not null,
    vlgr_role       varchar(255) not null,
    vlgr_email      varchar(255) not null
);

create table if not exists financial_report
(
    fr_id                         serial
        primary key,
    fr_date                       date default CURRENT_DATE not null,
    fr_remaining_budget           numeric                   not null,
    fr_total_expenses             numeric                   not null,
    fr_total_budget               numeric                   not null,
    fr_most_expensive_villager_id integer                   not null
        references villagers
);

INSERT INTO village_manager.villagers (vlgr_first_name, vlgr_surname, vlgr_cpf, vlgr_birth_date, vlgr_rent,
                                       vlgr_password, vlgr_role, vlgr_email)
VALUES ('admin', 'admin', '000.000.000-00', '2021-12-18', 5000,
        '$2a$12$gDbjHbSDg6lHHaTztxa/IuVL1SVzRFQz2bm1JUyFG0ma/ZsZWiGmS', '[ROLE_ADMIN]', 'admin@email.com');

