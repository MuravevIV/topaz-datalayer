
CREATE TABLE PUBLIC.dual (
    x INT NOT NULL
);

CREATE TABLE PUBLIC.bills (
    id_agree INT NOT NULL,
    id_bill INT NOT NULL,
    CONSTRAINT PK0_USER_ROLE PRIMARY KEY (id_agree, id_bill),
    bill_no VARCHAR(80) NOT NULL,
    bill_sum DECIMAL NOT NULL
);
