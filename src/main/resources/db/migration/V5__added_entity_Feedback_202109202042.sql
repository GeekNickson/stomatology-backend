CREATE TABLE "app_feedback" (
    id bigserial PRIMARY KEY,
    text text NOT NULL,
    rating integer NOT NULL,
    patient_id bigint NOT NULL REFERENCES app_patient (id)
);