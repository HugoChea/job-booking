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

DROP TABLE IF EXISTS "applicant";
CREATE TABLE "public"."applicant" (
    "photo_url" character varying(255),
    "id" bigint NOT NULL,
    CONSTRAINT "applicant_id" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "application_job";
DROP SEQUENCE IF EXISTS application_job_id_seq;
CREATE SEQUENCE application_job_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."application_job" (
    "id" integer DEFAULT nextval('application_job_id_seq') NOT NULL,
    "company" character varying(255) NOT NULL,
    "position" character varying(255) NOT NULL,
    "stack" character varying(255) NOT NULL,
    "description" text NOT NULL,
    "link" character varying(255) NOT NULL,
    "contact" character varying(255) NOT NULL,
    "comment" character varying(255) NOT NULL,
    "date" date NOT NULL,
    "status" character varying(255) NOT NULL,
    "id_user" integer NOT NULL,
    CONSTRAINT "application_job_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


INSERT INTO "roles" ("id", "name") VALUES
(1,	'ROLE_USER'),
(2,	'ROLE_MODERATOR'),
(3,	'ROLE_ADMIN'),
(4,	'ROLE_APPLICANT'),
(5,	'ROLE_RECRUITER');

ALTER TABLE ONLY "public"."applicant" ADD CONSTRAINT "applicant_id_fkey" FOREIGN KEY (id) REFERENCES users(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."application_job" ADD CONSTRAINT "application_job_id_user_fkey" FOREIGN KEY (id_user) REFERENCES users(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."application_job" ADD CONSTRAINT "fkrm3vi3ar7vyv0qmptcpceikbw" FOREIGN KEY (id_user) REFERENCES applicant(id) NOT DEFERRABLE;

