-- Adminer 4.8.1 PostgreSQL 14.1 (Debian 14.1-1.pgdg110+1) dump

DROP TABLE IF EXISTS "roles";
DROP SEQUENCE IF EXISTS roles_id_seq;
CREATE SEQUENCE roles_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."roles" (
    "id" integer DEFAULT nextval('roles_id_seq') NOT NULL,
    "name" character varying(20),
    CONSTRAINT "roles_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "roles" ("id", "name") VALUES
(1,	'ROLE_USER'),
(2,	'ROLE_MODERATOR'),
(3,	'ROLE_ADMIN'),
(4,	'ROLE_APPLICANT'),
(5,	'ROLE_RECRUITER');

DROP TABLE IF EXISTS "user_roles";
CREATE TABLE "public"."user_roles" (
    "user_id" bigint NOT NULL,
    "role_id" integer NOT NULL,
    CONSTRAINT "user_roles_pkey" PRIMARY KEY ("user_id", "role_id")
) WITH (oids = false);


DROP TABLE IF EXISTS "users";
DROP SEQUENCE IF EXISTS users_id_seq;
CREATE SEQUENCE users_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."users" (
    "id" bigint DEFAULT nextval('users_id_seq') NOT NULL,
    "email" character varying(255),
    "password" character varying(255),
    "username" character varying(255),
    CONSTRAINT "uk6dotkott2kjsp8vw4d0m25fb7" UNIQUE ("email"),
    CONSTRAINT "ukr43af9ap4edm43mmtq01oddj6" UNIQUE ("username"),
    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


ALTER TABLE ONLY "public"."user_roles" ADD CONSTRAINT "fkh8ciramu9cc9q3qcqiv4ue8a6" FOREIGN KEY (role_id) REFERENCES roles(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."user_roles" ADD CONSTRAINT "fkhfh9dx7w3ubf1co1vdev94g3f" FOREIGN KEY (user_id) REFERENCES users(id) NOT DEFERRABLE;
