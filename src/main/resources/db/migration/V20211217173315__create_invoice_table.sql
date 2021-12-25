DROP TYPE IF EXISTS invoice_status_enum;
CREATE TYPE invoice_status_enum AS ENUM ('PENDING', 'PAID', 'CANCELED', 'FAILED');
DROP TYPE IF EXISTS invoice_payment_method_enum;
CREATE TYPE invoice_payment_method_enum AS ENUM ('OPTIONAL', 'GATEWAY', 'WALLET');
CREATE TABLE "invoice"
(
    "id"                   serial PRIMARY KEY,
    "amount"               integer                NOT NULL,
    "status"               invoice_status_enum    NOT NULL DEFAULT 'PENDING',
    "tracking_code"        character(12)          NOT NULL,
    "description"          character varying(100) NOT NULL,
    "payment_method"       invoice_payment_method_enum NULL,
    "payment_reference_id" character varying(36) NULL,
    "creator_id"           character varying(36)  NOT NULL,
    "payer_id"             character varying(36) NULL,
    "uuid"                 uuid                   NOT NULL,
    "params"               jsonb NULL,
    "created_at"           timestamp              NOT NULL,
    "updated_at"           timestamp              NOT NULL,
    UNIQUE ("uuid"),
    UNIQUE ("tracking_code")
);

CREATE INDEX "invoice_tracking_code_index" ON "invoice" ("tracking_code");
CREATE INDEX "invoice_uuid_index" ON "invoice" ("uuid");
CREATE INDEX "invoice_status_index" ON "invoice" ("status");