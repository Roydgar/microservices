CREATE TABLE customer_order
(
    id uuid,
    customer_id uuid NOT NULL,
    status varchar(255) NOT NULL,
    cancel_reason varchar(255) NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    version integer NOT NULL
)