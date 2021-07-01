--Image
CREATE TABLE app_image
(
    id   bigserial PRIMARY KEY,
    path varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL
);


--User stuff
CREATE TABLE app_role
(
    id   bigserial PRIMARY KEY,
    "name" varchar(20)
);

CREATE TABLE app_account
(
    id       bigserial PRIMARY KEY,
    email    character varying(255) UNIQUE NOT NULL,
    password character varying(1000)       NOT NULL,
    role_id bigint NOT NULL REFERENCES app_role (id)
);

CREATE TABLE app_user
(
    id         bigserial PRIMARY KEY,
    first_name character varying(100) NOT NULL,
    last_name  character varying(100) NOT NULL,
    account_id bigint NOT NULL REFERENCES app_account (id),
    image_id bigint NOT NULL REFERENCES app_image (id)
);


--User types
CREATE TABLE app_patient
(
    id    bigserial PRIMARY KEY,
    phone varchar(12),
    FOREIGN KEY (id) REFERENCES app_user (id)
);

CREATE TABLE app_admin
(
    id bigserial PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES app_user (id)
);

--Specialty
CREATE TABLE app_specialty
(
    id   bigserial PRIMARY KEY,
    "name" character varying(100) NOT NULL
);

--Doctor
CREATE TABLE app_doctor
(
    id           bigserial PRIMARY KEY,
    experience   int    NOT NULL,
    phone        varchar(12),
    specialty_id bigint NOT NULL REFERENCES app_specialty (id),
    FOREIGN KEY (id) REFERENCES app_user (id)
);

--Service
CREATE TABLE app_service
(
    id        bigserial PRIMARY KEY,
    "name"      character varying(255),
    price     decimal(8, 2),
    image_id bigint NOT NULL REFERENCES app_image (id)
);

CREATE TABLE app_doctor_service (
    doctor_id bigint NOT NULL REFERENCES app_doctor(id),
    service_id bigint NOT NULL REFERENCES app_service(id)
);

CREATE TABLE app_schedule
(
    id          bigserial PRIMARY KEY,
    day_of_week int NOT NULL,
    "start"       time     NOT NULL,
    "end"       time     NOT NULL,
    doctor_id   bigint   NOT NULL REFERENCES app_doctor (id)
);

CREATE TABLE app_appointment
(
    id         bigserial PRIMARY KEY,
    "time"       timestamp NOT NULL,
    doctor_id  bigint    NOT NULL REFERENCES app_doctor (id),
    patient_id bigint    NOT NULL REFERENCES app_patient (id),
    service_id bigint    NOT NULL REFERENCES app_service (id)
);